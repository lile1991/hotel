package com.hotel.api;

import com.hotel.entity.CheckRecord;
import org.springframework.transaction.annotation.Transactional;

public interface CheckRecordApi {
    @Transactional
    CheckRecord checkIn(CheckRecord checkRecord);
}
