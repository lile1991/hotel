package com.hotel.dao;

import com.hotel.entity.CheckRecord;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CheckRecordRepository extends PagingAndSortingRepository<CheckRecord, Long> {
}
