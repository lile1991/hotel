package com.hotel.service;

import com.hotel.dao.CheckRecordRepository;
import com.hotel.dto.CheckRecordQueryDto;
import com.hotel.entity.CheckRecord;
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

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Slf4j
@Service
public class CheckRecordService extends BaseService<CheckRecord, Long, CheckRecordRepository> {

    @Autowired
    RoomService roomService;

    @Transactional(readOnly = true)
    public Page<CheckRecord> findManage(CheckRecordQueryDto queryDto) {
        return findAll(queryDto.toPageable());
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

        // 先更新入住登记的状态
        checkRecord = save(checkRecord);

        // 再更新房间状态, 顺序不可颠倒
        changeState(checkRecord);

        return checkRecord;
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
        if(isCurrentTime(checkRecord.getCheckInTime(), checkRecord.getCheckOutTime())) {
            switch (checkState) {
                case CHECK_IN:
                    newRoomState = RoomStateEnum.CHECK_IN;
                    break;
                case LEAVE:
                case CANCEL:
                    newRoomState = existsReserve(room.getId()) == 0 ? RoomStateEnum.EMPTY : RoomStateEnum.RESERVE;
                    break;
                case RESERVE:
                    newRoomState = RoomStateEnum.RESERVE;
                    break;
                default:
            }
        } else {
            switch (checkState) {
                case LEAVE:
                case CANCEL:
                    // 判断是否有预定
                    if(curRoomState != RoomStateEnum.RESERVE) {
                        newRoomState = RoomStateEnum.EMPTY;
                    }
                    break;
            }
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
     * @return 当前时间是否在这个范围
     */
    public boolean isCurrentTime(Date begin, Date end) {
        long currentTimeMillis = System.currentTimeMillis();
        return begin.getTime() <= currentTimeMillis && currentTimeMillis < end.getTime();
    }
}
