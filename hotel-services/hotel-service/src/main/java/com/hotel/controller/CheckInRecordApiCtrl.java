package com.hotel.controller;

import com.hotel.api.CheckInRecordApi;
import com.hotel.dto.CheckInRecordQueryDto;
import com.hotel.entity.CheckInRecord;
import com.hotel.service.CheckInRecordService;
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
