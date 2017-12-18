package com.hotel.service;

import com.hotel.dao.CheckRecordRepository;
import com.hotel.dto.CheckRecordQueryDto;
import com.hotel.entity.CheckRecord;
import com.hotel.entity.CheckRecord_;
import com.hotel.entity.Room;
import com.hotel.enums.CheckStateEnum;
import com.hotel.enums.RoomStateEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

import java.beans.Transient;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Slf4j
@Service
public class CheckRecordService extends BaseService<CheckRecord, Long, CheckRecordRepository> {

    @Autowired
    RoomService roomService;

    @Autowired
    OptLogService optLogService;

    @Transactional(readOnly = true)
    public Page<CheckRecord> findManage(CheckRecordQueryDto queryDto) {
        return findAll((root, query, cb) -> {
            root.fetch(CheckRecord_.room);
            root.fetch(CheckRecord_.createUser);
            return null;
        }, queryDto.toPageable());
    }

    @Transactional
    public CheckRecord checkIn(CheckRecord checkRecord) {
        Room room = roomService.findOne(checkRecord.getRoom().getId());

        RoomStateEnum roomStateEnum = RoomStateEnum.valueOf(room.getState());
        if(RoomStateEnum.RESERVE == roomStateEnum) {
            validCheckInTime(checkRecord);
        } else {
            Assert.isTrue(roomStateEnum == RoomStateEnum.EMPTY, "房间已" + roomStateEnum.desc);
        }

        // 入住时间小于等于当前时间
        if(checkRecord.getCheckInTime().getTime() <= System.currentTimeMillis()) {
            // 离店时间大于当前时间
            if(checkRecord.getCheckOutTime().getTime() > System.currentTimeMillis()) {
                // 入住
                checkRecord.setState(CheckStateEnum.CHECK_IN.name());
            } else {
                // 离店
                checkRecord.setState(CheckStateEnum.LEFT.name());
            }
        } else {
            // 预定
            checkRecord.setState(CheckStateEnum.RESERVE.name());
        }

        // 先更新入住登记的状态
        checkRecord = save(checkRecord);

        // 再更新房间状态, 顺序不可颠倒
        changeState(checkRecord);

        return checkRecord;
    }

    @Transactional
    public int reserveCheckIn(CheckRecord checkRecord) {
        CheckRecord reserveCheckRecord = findOne(checkRecord.getId());
        Assert.notNull(reserveCheckRecord, "预约不存在");

        Assert.isTrue(isCurrentTime(reserveCheckRecord.getCheckInTime(), reserveCheckRecord.getCheckOutTime(), - 30 * 60 * 1000), "未到入住时间");

        reserveCheckRecord.setState(CheckStateEnum.CHECK_IN.name());
        reserveCheckRecord.setUpdateTime(checkRecord.getUpdateTime());
        reserveCheckRecord.setUpdateUser(checkRecord.getUpdateUser());
        save(reserveCheckRecord);

        changeState(checkRecord);
        return 1;
    }

    @Transactional(readOnly = true)
    public void validCheckInTime(CheckRecord checkRecord) {
        final Date checkInTime = checkRecord.getCheckInTime();
        final Date checkOutTime = checkRecord.getCheckOutTime();
        Assert.notNull(checkInTime, "请选择入住时间");
        Assert.notNull(checkOutTime, "请选择离店时间");

        List<CheckRecord> checkRecordList = findByCheckInTimeGreaterThanEqual(checkRecord.getRoom().getId(), checkInTime);
        if (! CollectionUtils.isEmpty(checkRecordList)) {
            for (CheckRecord dbRecord: checkRecordList) {
                if(checkRecord.getCheckOutTime().getTime() < dbRecord.getCheckInTime().getTime()
                        || checkRecord.getCheckInTime().getTime() >= dbRecord.getCheckOutTime().getTime()) {
                    if(log.isDebugEnabled()) {
                        // logger
                    }
                } else {
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH");

                    throw new IllegalArgumentException(
                            String.format(
                                "入住时间冲突, 该房间%s-%s段已有人预定",
                                simpleDateFormat.format(dbRecord.getCheckInTime()),
                                simpleDateFormat.format(dbRecord.getCheckOutTime())
                            )
                    );
                }
            }
        }
        System.out.println(checkRecordList);
    }

    @Transactional(readOnly = true)
    public List<CheckRecord> findByCheckInTimeGreaterThanEqual(Long roomId, Date checkInTime) {
        return repository.findByRoomIdAndCheckInTimeGreaterThanEqual(roomId, checkInTime);
    }

    /**
     * 是否存在预定(从系统时候往后推)
     * @param roomId 房间号
     * @return 预订数
     */
    @Transactional(readOnly = true)
    public int existsReserve(Long roomId) {
        return repository.existsReserve(roomId, CheckStateEnum.RESERVE, new Date());
    }

    @Transactional
    public int changeState(CheckRecord checkRecord) {
        // 查找房间
        Room room = roomService.findOne(checkRecord.getRoom().getId());
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

    @Transient
    public int leave(CheckRecord leaveCheckRecord) {
        CheckRecord curCheckRecord = findOne(leaveCheckRecord.getId());
        Assert.notNull(curCheckRecord, "入住记录不存在");

        CheckStateEnum checkStateEnum = CheckStateEnum.valueOf(curCheckRecord.getState());
        CheckStateEnum newCheckStateEnum;
        switch (checkStateEnum) {
            // 入住中
            case CHECK_IN:
                // 退房
                newCheckStateEnum = CheckStateEnum.LEFT;
                break;
            // 预定中
            case RESERVE:
                // 取消预定
                newCheckStateEnum = CheckStateEnum.CANCELED_RESERVE;
                break;
            default:
                throw new IllegalStateException("重复的操作， 已取消预定或已退房");
        }

        curCheckRecord.setState(newCheckStateEnum.name());
        curCheckRecord.setUpdateUser(leaveCheckRecord.getUpdateUser());
        curCheckRecord.setUpdateTime(leaveCheckRecord.getUpdateTime());

        // 更新入住状态
        save(curCheckRecord);
        // 更新房间状态
        changeState(curCheckRecord);
        return 1;
    }

    @Transactional(readOnly = true)
    public CheckRecord findCheckOut(Long id) {
        return findOne(((root, query, cb) -> {
            root.fetch(CheckRecord_.room);
            return cb.equal(root.get(CheckRecord_.id), id);
        }));
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
