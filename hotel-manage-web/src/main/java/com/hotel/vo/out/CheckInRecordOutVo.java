package com.hotel.vo.out;

import com.hotel.entity.CheckInRecord;
import com.hotel.user.entity.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CheckInRecordOutVo extends CheckInRecord {

    private CheckOutRecordOutVo checkOutRecord;
    private User createUser;

}
