package com.hotel.room.dto;

import com.hotel.dto.PageableDto;
import lombok.Data;

import java.util.Date;

@Data
public class CheckInRecordQueryDto extends PageableDto {
    private int page;
    private int size;

    private String state;
    private String checkInCustomerName;

    private Date checkInTimeBegin;
    private Date checkInTimeEnd;

    private Date createTimeBegin;
    private Date createTimeEnd;
}
