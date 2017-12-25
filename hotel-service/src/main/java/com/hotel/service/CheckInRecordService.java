package com.hotel.service;

import com.hotel.dao.CheckInRecordRepository;
import com.hotel.dto.CheckInRecordQueryDto;
import com.hotel.entity.CheckInRecord;
import com.hotel.entity.CheckInRecord_;
import com.hotel.entity.Room;
import com.hotel.enums.CheckStateEnum;
import com.hotel.enums.RoomStateEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Slf4j
@Service
public class CheckInRecordService extends BaseService<CheckInRecord, Long, CheckInRecordRepository> {

    @Autowired
    RoomService roomService;

    @Autowired
    OptLogService optLogService;

    @Autowired
    CheckInCustomerService checkInCustomerService;

    @Transactional(readOnly = true)
    public Page<CheckInRecord> findManage(CheckInRecordQueryDto queryDto) {
        // 按更新时间倒序
        queryDto.addOrder(new Sort.Order(Sort.Direction.DESC, CheckInRecord_.updateTime.getName()));

        return findAll((root, query, cb) -> {
            root.fetch(CheckInRecord_.room);
            root.fetch(CheckInRecord_.createUser);
            root.fetch(CheckInRecord_.updateUser);
            return null;
        }, queryDto.toPageable());
    }

    @Transactional
    public CheckInRecord checkIn(CheckInRecord checkInRecord) {
        Room room = roomService.findOne(checkInRecord.getRoomId());

        RoomStateEnum roomStateEnum = RoomStateEnum.valueOf(room.getState());
        if(RoomStateEnum.RESERVE == roomStateEnum) {
            validCheckInTime(checkInRecord);
        } else {
            Assert.isTrue(roomStateEnum == RoomStateEnum.EMPTY, "房间已" + roomStateEnum.desc);
        }

        // 入住时间小于等于当前时间
        if(checkInRecord.getCheckInTime().getTime() <= System.currentTimeMillis()) {
            // 离店时间大于当前时间
            if(checkInRecord.getOverTime().getTime() > System.currentTimeMillis()) {
                // 入住
                checkInRecord.setState(CheckStateEnum.CHECK_IN.name());
            } else {
                // 离店
                checkInRecord.setState(CheckStateEnum.LEFT.name());
            }
        } else {
            // 预定
            checkInRecord.setState(CheckStateEnum.RESERVE.name());
        }

        // 先更新入住登记的状态
        final CheckInRecord dbCheckInRecord = save(checkInRecord);

        // 保存入住客户
        checkInRecord.getCheckInCustomers().forEach(checkInCustomer -> {
            if(checkInCustomer.getMobile() == null) {
                checkInCustomer.setMobile("");
            }

            checkInCustomer.setState("0");
            checkInCustomer.setCreateTime(dbCheckInRecord.getCreateTime());
            checkInCustomer.setCreateUserId(dbCheckInRecord.getCreateUserId());

            checkInCustomer.setCheckInRecordId(dbCheckInRecord.getId());
        });
        checkInCustomerService.save(checkInRecord.getCheckInCustomers());

        // 再更新房间状态, 顺序不可颠倒
        changeState(dbCheckInRecord);

        return dbCheckInRecord;
    }

    @Transactional
    public int reserveCheckIn(CheckInRecord checkRecord) {
        CheckInRecord reserveCheckInRecord = findOne(checkRecord.getId());
        Assert.notNull(reserveCheckInRecord, "预约不存在");

        Assert.isTrue(isCurrentTime(reserveCheckInRecord.getCheckInTime(), reserveCheckInRecord.getOverTime(), - 30 * 60 * 1000), "未到入住时间, 提前30分钟可入住");

        reserveCheckInRecord.setState(CheckStateEnum.CHECK_IN.name());
        reserveCheckInRecord.setUpdateTime(checkRecord.getUpdateTime());
        reserveCheckInRecord.setUpdateUser(checkRecord.getUpdateUser());
        save(reserveCheckInRecord);

        changeState(checkRecord);
        return 1;
    }

    @Transactional(readOnly = true)
    public void validCheckInTime(CheckInRecord checkRecord) {
        final Date checkInTime = checkRecord.getCheckInTime();
        final Date overTime = checkRecord.getOverTime();
        Assert.notNull(checkInTime, "请选择入住时间");
        Assert.notNull(overTime, "请选择到期时间");

        List<CheckInRecord> checkRecordList = findByCheckInTimeGreaterThanEqual(checkRecord.getRoomId(), checkInTime);
        if (! CollectionUtils.isEmpty(checkRecordList)) {
            for (CheckInRecord dbRecord: checkRecordList) {
                if(checkRecord.getOverTime().getTime() < dbRecord.getCheckInTime().getTime()
                        || checkRecord.getCheckInTime().getTime() >= dbRecord.getOverTime().getTime()) {
                    if(log.isDebugEnabled()) {
                        // logger
                    }
                } else {
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH");

                    throw new IllegalArgumentException(
                            String.format(
                                "入住时间冲突, 该房间%s-%s段已有人预定",
                                simpleDateFormat.format(dbRecord.getCheckInTime()),
                                simpleDateFormat.format(dbRecord.getOverTime())
                            )
                    );
                }
            }
        }
        System.out.println(checkRecordList);
    }

    @Transactional(readOnly = true)
    public List<CheckInRecord> findByCheckInTimeGreaterThanEqual(Long roomId, Date checkInTime) {
        return repository.findByRoomIdAndCheckInTimeGreaterThanEqual(roomId, checkInTime);
    }

    /**
     * 是否存在预定(从系统时候往后推)
     * @param roomId 房间号
     * @return 预订数
     */
    @Transactional(readOnly = true)
    public int existsReserve(Long roomId) {
        return repository.existsReserve(roomId, CheckStateEnum.RESERVE.name(), new Date());
    }

    @Transactional
    public int changeState(CheckInRecord checkRecord) {
        // 查找房间
        Room room = roomService.findOne(checkRecord.getRoomId());
        // 当前房间状态
        RoomStateEnum curRoomState = RoomStateEnum.valueOf(room.getState());

        // 停用的直接return
        if(curRoomState == RoomStateEnum.STOP) {
            return 0;
        }

        RoomStateEnum newRoomState = null;

        CheckStateEnum checkState = CheckStateEnum.valueOf(checkRecord.getState());
        // if 入住 发生 在此刻
        switch (checkState) {
            // 入住
            case CHECK_IN:
                newRoomState = RoomStateEnum.CHECK_IN;
                break;
            // 退房或取消预定
            case LEFT:
            case CANCELED_RESERVE:
                newRoomState = existsReserve(room.getId()) == 0 ? RoomStateEnum.EMPTY : RoomStateEnum.RESERVE;
                break;
            // 预定
            case RESERVE:
                if(curRoomState == RoomStateEnum.EMPTY) {
                    newRoomState = RoomStateEnum.RESERVE;
                }
                break;
            default:
                throw new IllegalStateException("错误的登记状态: \"" + checkState.desc + "\"");
        }

        if(newRoomState != null) {
            room.setState(newRoomState.name());
            return 1;
        }
        return 0;
    }


    /**
     * 是否发生再此刻
     * @param begin 时间段-起始
     * @param end 时间段-截止
     * @param offsetTime 可以提前或延后的时间(毫秒)
     *          如 - 30 * 60 1000 表示可提前半小时
     *          如 30 * 60 1000 表示可延后半小时
     * @return 当前时间是否在这个范围
     */
    public boolean isCurrentTime(Date begin, Date end, long offsetTime) {
        long currentTimeMillis = System.currentTimeMillis();
        return ((offsetTime < 0 ? offsetTime : 0) + begin.getTime()) <= currentTimeMillis
                && currentTimeMillis < ((offsetTime > 0 ? offsetTime : 0) + end.getTime());
    }
}
