package com.hotel.manage;

import com.hotel.api.CheckRecordApi;
import com.hotel.entity.CheckRecord;
import com.hotel.entity.User;
import com.hotel.enums.CheckStateEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.util.Date;

@Component
public class CheckRecordManage {

    @Autowired
    CheckRecordApi checkRecordApi;

    @Autowired
    CommonManage commonManage;

    public CheckRecord checkIn(CheckRecord checkRecord) {
        Date now = new Date();
        User user = commonManage.currentUser();

        Assert.isTrue(checkRecord.getCheckInTime().getTime() < checkRecord.getCheckOutTime().getTime(), "离店时间不能大于入住时间");

        // 入住时间小于等于当前时间
        if(checkRecord.getCheckInTime().getTime() <= System.currentTimeMillis()) {
            // 离店时间大于当前时间
            if(checkRecord.getCheckOutTime().getTime() > System.currentTimeMillis()) {
                // 入住
                checkRecord.setState(CheckStateEnum.CHECK_IN.name());
            } else {
                // 离店
                checkRecord.setState(CheckStateEnum.LEAVE.name());
            }
        } else {
            // 预定
            checkRecord.setState(CheckStateEnum.RESERVE.name());
        }

        checkRecord.setCreateTime(now);
        checkRecord.setUpdateTime(now);
        checkRecord.setUpdateUser(user);
        checkRecord.setCreateUser(user);

        return checkRecordApi.checkIn(checkRecord);
    }
}
