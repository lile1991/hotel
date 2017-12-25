package com.hotel.vo;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
public class CheckInVo {
    @NotNull
    private Date checkInTime;
    @NotNull
    private Date overTime;
    @NotNull
    private BigDecimal payedCharge;
    @NotNull
    private BigDecimal payedDeposit;

    private Long memberId;
    @NotNull
    private Long roomId;

    private Long roomTypeId;

    @NotNull
    @Size(min = 1, message = "至少登记一个住户")
    private List<CheckInCustomerVo> checkInCustomers;
}
