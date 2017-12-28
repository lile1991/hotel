package com.hotel.controller;

import com.hotel.api.CheckOutRecordApi;
import com.hotel.entity.CheckOutRecord;
import com.hotel.service.CheckOutRecordService;
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
