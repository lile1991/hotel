package com.hotel.service;

import com.hotel.dao.CustomerRepository;
import com.hotel.entity.Customer;
import org.springframework.stereotype.Service;

@Service
public class CustomerService extends BaseService<Customer, Long, CustomerRepository> implements ICustomerService {
}
