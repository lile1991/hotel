package com.hotel.api;

import com.hotel.dto.CheckInRecordQueryDto;
import com.hotel.entity.CheckInRecord;
import org.springframework.data.domain.Page;

public interface CheckInRecordApi {

    Page<CheckInRecord> findManage(CheckInRecordQueryDto queryDto);

    CheckInRecord checkIn(CheckInRecord checkRecord);
    int reserveCheckIn(CheckInRecord checkRecord);

    CheckInRecord findOne(Long id);
}
