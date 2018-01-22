package com.hotel.manage;

import com.hotel.room.api.CheckInCustomerApi;
import com.hotel.room.entity.CheckInCustomer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CheckInCustomerManage {

    @Autowired
    CheckInCustomerApi checkInCustomerApi;

    public List<CheckInCustomer> findByCheckInRecordId(Long checkInRecordId) {
        return checkInCustomerApi.findByCheckInRecordId(checkInRecordId);
    }
}
