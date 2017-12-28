package com.hotel.vo.in;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
public class CheckOutVo {
    @NotNull
    private BigDecimal deductDeposit;

    @NotNull
    private BigDecimal backDeposit;

    @NotNull
    private Long checkInId;

    private String remark;
}
