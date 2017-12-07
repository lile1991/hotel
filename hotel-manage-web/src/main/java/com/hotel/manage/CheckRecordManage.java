package com.hotel.manage;

import com.hotel.api.CheckRecordApi;
import com.hotel.entity.CheckRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CheckRecordManage {

    @Autowired
    CheckRecordApi checkRecordApi;

    public Long checkIn(CheckRecord checkRecord) {
        return checkRecordApi.checkIn(checkRecord);
    }
}
