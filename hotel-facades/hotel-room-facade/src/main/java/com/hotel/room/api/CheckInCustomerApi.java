package com.hotel.room.api;


import com.hotel.room.entity.CheckInCustomer;

import java.util.List;

public interface CheckInCustomerApi {
    List<CheckInCustomer> findByCheckInRecordId(Long checkInRecordId);
}
