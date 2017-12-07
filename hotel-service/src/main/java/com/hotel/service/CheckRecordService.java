package com.hotel.service;

import com.hotel.dao.CheckRecordRepository;
import com.hotel.entity.CheckRecord;
import com.hotel.entity.Room;
import com.hotel.enums.RoomStateEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.Date;
import java.util.List;

@Service
public class CheckRecordService extends BaseService<CheckRecord, Long, CheckRecordRepository> {

    @Autowired
    RoomService roomService;

    @Transactional
    public Long checkIn(CheckRecord checkRecord) {
        Room room = roomService.findOne(checkRecord.getRoom().getId());

        validCheckInTime(checkRecord);

        Assert.isTrue(RoomStateEnum.valueOf(room.getState()) == RoomStateEnum.EMPTY, "房间已被占用");
        checkRecord = save(checkRecord);
        return null;
    }

    @Transactional(readOnly = true)
    public void validCheckInTime(CheckRecord checkRecord) {
        final Date checkInTime = checkRecord.getCheckInTime();
        final Date checkOutTime = checkRecord.getCheckOutTime();
        Assert.notNull(checkInTime, "请选择入住时间");
        Assert.notNull(checkOutTime, "请选择离店时间");

        List<CheckRecord> checkRecordList = findByCheckOutTimeGreaterThanEqual(checkOutTime);
        System.out.println(checkRecordList);
    }

    @Transactional(readOnly = true)
    public List<CheckRecord> findByCheckOutTimeGreaterThanEqual(Date checkOutTime) {
        return repository.findByCheckOutTimeGreaterThanEqual(checkOutTime);
    }
}
