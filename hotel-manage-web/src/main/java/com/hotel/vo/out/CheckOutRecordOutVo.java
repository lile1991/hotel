package com.hotel.vo.out;

import com.hotel.entity.CheckOutRecord;
import com.hotel.user.entity.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CheckOutRecordOutVo extends CheckOutRecord {
    private User createUser;
}
