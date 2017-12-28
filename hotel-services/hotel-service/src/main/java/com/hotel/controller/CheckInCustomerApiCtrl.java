package com.hotel.controller;

import com.hotel.api.CheckInCustomerApi;
import com.hotel.entity.CheckInCustomer;
import com.hotel.service.CheckInCustomerService;
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
