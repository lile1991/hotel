package com.hotel.api;

import com.hotel.dto.CheckRecordQueryDto;
import com.hotel.entity.CheckRecord;
import org.springframework.data.domain.Page;

public interface CheckRecordApi {

    Page<CheckRecord> findManage(CheckRecordQueryDto queryDto);

    CheckRecord checkIn(CheckRecord checkRecord);
    int reserveCheckIn(CheckRecord checkRecord);

    int leave(CheckRecord leaveCheckRecord);

    CheckRecord findOne(Long id);

    CheckRecord findCheckOut(Long id);
}
