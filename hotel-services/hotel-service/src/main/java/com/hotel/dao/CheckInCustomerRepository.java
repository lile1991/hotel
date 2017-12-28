package com.hotel.dao;

import com.hotel.entity.CheckInCustomer;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CheckInCustomerRepository extends BaseRepository<CheckInCustomer, Long> {
    List<CheckInCustomer> findByCheckInRecordId(Long checkInRecordId);
}
