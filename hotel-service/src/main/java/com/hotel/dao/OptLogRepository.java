package com.hotel.dao;

import com.hotel.entity.OptLog;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OptLogRepository extends PagingAndSortingRepository<OptLog, Long> {
}
