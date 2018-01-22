package com.hotel.room.api;

import com.hotel.room.dto.CheckInRecordQueryDto;
import com.hotel.room.entity.CheckInRecord;
import org.springframework.data.domain.Page;

public interface CheckInRecordApi {

    Page<CheckInRecord> findManage(CheckInRecordQueryDto queryDto);

    CheckInRecord checkIn(CheckInRecord checkRecord);
    int reserveCheckIn(CheckInRecord checkRecord);

    CheckInRecord findOne(Long id);
}
