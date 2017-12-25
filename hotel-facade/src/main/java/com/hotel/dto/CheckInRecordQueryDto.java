package com.hotel.dto;

import lombok.Data;

import java.util.Date;

@Data
public class CheckInRecordQueryDto extends PageableDto {
    private int page;
    private int size;

    private String state;

    private Date checkInTimeBegin;
    private Date checkInTimeEnd;

    private Date createTimeBegin;
    private Date createTimeEnd;
}
