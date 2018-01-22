package com.hotel.user.dto;

import com.hotel.dto.PageableDto;
import lombok.Data;

@Data
public class UserQueryDto extends PageableDto {
    private String name;
    private String mobile;
}
