package com.hotel.service;

import com.hotel.dao.CheckInCustomerRepository;
import com.hotel.entity.CheckInCustomer;
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
