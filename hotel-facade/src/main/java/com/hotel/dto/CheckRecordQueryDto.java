package com.hotel.dto;

import lombok.Data;

@Data
public class CheckRecordQueryDto extends PageableDto {
    private int page;
    private int size;
}
