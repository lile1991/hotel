package com.hotel.room.dao;

import com.hotel.dao.BaseRepository;
import com.hotel.room.entity.OptLog;
import org.springframework.stereotype.Repository;

@Repository
public interface OptLogRepository extends BaseRepository<OptLog, Long> {
}
