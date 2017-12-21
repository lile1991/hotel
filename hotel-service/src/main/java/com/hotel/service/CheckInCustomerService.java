package com.hotel.service;

import com.hotel.dao.CheckInCustomerRepository;
import com.hotel.entity.CheckInCustomer;
import org.springframework.stereotype.Service;

@Service
public class CheckInCustomerService extends BaseService<CheckInCustomer, Long, CheckInCustomerRepository> {
}
