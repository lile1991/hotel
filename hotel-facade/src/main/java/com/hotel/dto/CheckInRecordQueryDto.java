package com.hotel.dto;

import lombok.Data;

@Data
public class CheckInRecordQueryDto extends PageableDto {
    private int page;
    private int size;
}
