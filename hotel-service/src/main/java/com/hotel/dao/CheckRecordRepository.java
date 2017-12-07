package com.hotel.dao;

import com.hotel.entity.CheckRecord;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface CheckRecordRepository extends PagingAndSortingRepository<CheckRecord, Long> {
    List<CheckRecord> findByCheckOutTimeGreaterThanEqual(@Param("checkOutTime") Date checkOutTime);
}
