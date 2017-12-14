package com.hotel.vo;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Data
public class CheckInVo {
    @NotNull
    private Date checkInTime;
    @NotNull
    private Date checkOutTime;
    @NotNull
    private Long payedCharge;
    @NotNull
    private Long payedDeposit;

    private Long customerId;
    @NotNull
    private Long roomId;

    private Long roomTypeId;

    @NotNull
    private List<CheckInCustomerVo> checkInCustomerList;
}
