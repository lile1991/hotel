package com.hotel.manage;

import com.hotel.room.api.CheckOutRecordApi;
import com.hotel.room.entity.CheckOutRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class CheckOutRecordManage {

    @Autowired
    CheckOutRecordApi checkOutRecordApi;

    @Autowired
    CommonManage commonManage;

    public Long checkOut(CheckOutRecord checkOutRecord) {
        Date now = new Date();
        checkOutRecord.setCreateTime(now);
        checkOutRecord.setUpdateTime(now);
        checkOutRecord.setCreateUserId(commonManage.currentUserId());
        checkOutRecord.setUpdateUserId(commonManage.currentUserId());
        return checkOutRecordApi.checkOut(checkOutRecord);
    }
}
