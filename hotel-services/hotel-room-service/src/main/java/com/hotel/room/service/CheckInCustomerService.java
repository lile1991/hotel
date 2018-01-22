package com.hotel.room.service;

import com.hotel.room.dao.CheckInCustomerRepository;
import com.hotel.room.entity.CheckInCustomer;
import com.hotel.service.BaseService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CheckInCustomerService extends BaseService<CheckInCustomer, Long, CheckInCustomerRepository> {

    @Transactional(readOnly = true)
    public List<CheckInCustomer> findByCheckInRecordId(Long checkInRecordId) {
        return repository.findByCheckInRecordId(checkInRecordId);
    }
}
