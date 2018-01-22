package com.hotel.room.controller;

import com.hotel.room.api.CheckInRecordApi;
import com.hotel.room.dto.CheckInRecordQueryDto;
import com.hotel.room.entity.CheckInRecord;
import com.hotel.room.service.CheckInRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@Component
public class CheckInRecordApiCtrl implements CheckInRecordApi {

    @Autowired
    CheckInRecordService checkInRecordService;

    @Override
    public Page<CheckInRecord> findManage(CheckInRecordQueryDto queryDto) {
        return checkInRecordService.findManage(queryDto);
    }

    @Override
    public CheckInRecord checkIn(CheckInRecord checkRecord) {
        return checkInRecordService.checkIn(checkRecord);
    }

    @Override
    public int reserveCheckIn(CheckInRecord checkRecord) {
        return checkInRecordService.reserveCheckIn(checkRecord);
    }

    @Override
    public CheckInRecord findOne(Long id) {
        return checkInRecordService.findOne(id);
    }

}
