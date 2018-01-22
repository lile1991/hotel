package com.hotel.room.dao;

import com.hotel.dao.BaseRepository;
import com.hotel.room.entity.CheckOutRecord;
import org.springframework.stereotype.Repository;

@Repository
public interface CheckOutRecordRepository extends BaseRepository<CheckOutRecord, Long> {
}
