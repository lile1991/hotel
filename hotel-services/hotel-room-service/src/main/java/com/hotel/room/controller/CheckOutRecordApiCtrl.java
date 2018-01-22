package com.hotel.room.controller;

import com.hotel.room.api.CheckOutRecordApi;
import com.hotel.room.entity.CheckOutRecord;
import com.hotel.room.service.CheckOutRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CheckOutRecordApiCtrl implements CheckOutRecordApi {

    @Autowired
    CheckOutRecordService checkOutRecordService;

    @Override
    public Long checkOut(CheckOutRecord checkOutRecord) {
        return checkOutRecordService.checkOut(checkOutRecord);
    }
}
