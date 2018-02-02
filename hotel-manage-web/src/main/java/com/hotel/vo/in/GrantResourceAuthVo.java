package com.hotel.vo.in;

import lombok.Data;

import java.util.List;

@Data
public class GrantResourceAuthVo {

    private Long userId;
    private List<Long> resourceIdList;
}
