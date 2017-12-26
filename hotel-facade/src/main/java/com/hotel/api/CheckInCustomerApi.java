package com.hotel.api;

import com.hotel.entity.CheckInCustomer;

import java.util.List;

public interface CheckInCustomerApi {
    List<CheckInCustomer> findByCheckInRecordId(Long checkInRecordId);
}
