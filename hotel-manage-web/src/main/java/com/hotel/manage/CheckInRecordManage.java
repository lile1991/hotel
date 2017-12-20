package com.hotel.manage;

import com.hotel.api.CheckInRecordApi;
import com.hotel.dto.CheckInRecordQueryDto;
import com.hotel.entity.CheckInRecord;
import com.hotel.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.util.Date;

@Component
public class CheckInRecordManage {

    @Autowired
    CheckInRecordApi checkInRecordApi;

    @Autowired
    CommonManage commonManage;

    public Page<CheckInRecord> findManage(CheckInRecordQueryDto queryDto) {
        return checkInRecordApi.findManage(queryDto);
    }

    /**
     * 登记入住
     * @param checkRecord 登记信息
     * @return 1-成功
     */
    public CheckInRecord checkIn(CheckInRecord checkRecord) {
        Date now = new Date();
        User user = commonManage.currentUser();

        Assert.isTrue(checkRecord.getCheckInTime().getTime() < checkRecord.getOverTime().getTime(), "到期时间不能大于入住时间");

        checkRecord.setCreateTime(now);
        checkRecord.setUpdateTime(now);
        checkRecord.setUpdateUser(user);
        checkRecord.setCreateUser(user);

        return checkInRecordApi.checkIn(checkRecord);
    }

    /**
     * 预约入住
     * @param id 预约ID
     * @return 1-成功
     */
    public int reserveCheckIn(Long id) {
        Date now = new Date();
        CheckInRecord checkRecord = new CheckInRecord();
        checkRecord.setId(id);
        checkRecord.setUpdateTime(now);
        checkRecord.setUpdateUser(commonManage.currentUser());
        return checkInRecordApi.reserveCheckIn(checkRecord);
    }

    public CheckInRecord findOne(Long id) {
        return checkInRecordApi.findOne(id);
    }
}
