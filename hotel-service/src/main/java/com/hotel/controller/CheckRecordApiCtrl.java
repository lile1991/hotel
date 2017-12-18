package com.hotel.controller;

import com.hotel.api.CheckRecordApi;
import com.hotel.dto.CheckRecordQueryDto;
import com.hotel.entity.CheckRecord;
import com.hotel.service.CheckRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@Component
public class CheckRecordApiCtrl implements CheckRecordApi {

    @Autowired
    CheckRecordService checkRecordService;

    @Override
    public Page<CheckRecord> findManage(CheckRecordQueryDto queryDto) {
        return checkRecordService.findManage(queryDto);
    }

    @Override
    public CheckRecord checkIn(CheckRecord checkRecord) {
        return checkRecordService.checkIn(checkRecord);
    }

    @Override
    public int reserveCheckIn(CheckRecord checkRecord) {
        return checkRecordService.reserveCheckIn(checkRecord);
    }

    @Override
    public int leave(CheckRecord leaveCheckRecord) {
        return checkRecordService.leave(leaveCheckRecord);
    }

    @Override
    public CheckRecord findOne(Long id) {
        return checkRecordService.findOne(id);
    }

    @Override
    public CheckRecord findCheckOut(Long id) {
        return checkRecordService.findCheckOut(id);
    }
}
