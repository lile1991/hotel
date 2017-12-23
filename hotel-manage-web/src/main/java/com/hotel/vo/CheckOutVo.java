package com.hotel.vo;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class CheckOutVo {
    @NotNull
    private Long deductDeposit;

    @NotNull
    private Long checkInId;

    private String remark;
}
