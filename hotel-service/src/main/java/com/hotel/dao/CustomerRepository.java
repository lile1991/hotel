package com.hotel.dao;

import com.hotel.entity.Customer;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends BaseRepository<Customer, Long> {
}
