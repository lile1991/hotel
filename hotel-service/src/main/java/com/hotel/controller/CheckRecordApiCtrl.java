package com.hotel.controller;

import com.hotel.api.CheckRecordApi;
import com.hotel.entity.CheckRecord;
import com.hotel.service.CheckRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CheckRecordApiCtrl implements CheckRecordApi {

    @Autowired
    CheckRecordService checkRecordService;

    @Override
    public Long checkIn(CheckRecord checkRecord) {
        return checkRecordService.checkIn(checkRecord);
    }
}
