package com.hotel.room.dao;

import com.hotel.dao.BaseRepository;
import com.hotel.room.entity.CheckInCustomer;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CheckInCustomerRepository extends BaseRepository<CheckInCustomer, Long> {
    List<CheckInCustomer> findByCheckInRecordId(Long checkInRecordId);
}
