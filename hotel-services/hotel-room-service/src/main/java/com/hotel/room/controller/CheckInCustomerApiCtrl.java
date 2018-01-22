package com.hotel.room.controller;

import com.hotel.room.api.CheckInCustomerApi;
import com.hotel.room.entity.CheckInCustomer;
import com.hotel.room.service.CheckInCustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CheckInCustomerApiCtrl implements CheckInCustomerApi {

    @Autowired
    CheckInCustomerService checkInCustomerService;

    @Override
    public List<CheckInCustomer> findByCheckInRecordId(Long checkInRecordId) {
        return checkInCustomerService.findByCheckInRecordId(checkInRecordId);
    }
}
