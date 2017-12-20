package com.hotel.dao;

import com.hotel.entity.CheckInRecord;
import com.hotel.enums.CheckStateEnum;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface CheckInRecordRepository extends BaseRepository<CheckInRecord, Long> {
    List<CheckInRecord> findByRoomIdAndCheckInTimeGreaterThanEqual(@Param("roomId") Long roomId, @Param("checkInTime") Date checkInTime);

    @Query("select count(rd.id) from CheckInRecord rd where rd.room.id = :roomId and rd.state = :state and rd.checkInTime > :now")
    int existsReserve(@Param("roomId") Long roomId, @Param("state") CheckStateEnum state, @Param("now") Date now);
}
