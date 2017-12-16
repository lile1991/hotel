package com.hotel.manage;

import com.hotel.api.CheckRecordApi;
import com.hotel.dto.CheckRecordQueryDto;
import com.hotel.entity.CheckRecord;
import com.hotel.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.util.Date;

@Component
public class CheckRecordManage {

    @Autowired
    CheckRecordApi checkRecordApi;

    @Autowired
    CommonManage commonManage;

    public Page<CheckRecord> findManage(CheckRecordQueryDto queryDto) {
        return checkRecordApi.findManage(queryDto);
    }

    /**
     * 登记入住
     * @param checkRecord 登记信息
     * @return 1-成功
     */
    public CheckRecord checkIn(CheckRecord checkRecord) {
        Date now = new Date();
        User user = commonManage.currentUser();

        Assert.isTrue(checkRecord.getCheckInTime().getTime() < checkRecord.getCheckOutTime().getTime(), "离店时间不能大于入住时间");

        checkRecord.setCreateTime(now);
        checkRecord.setUpdateTime(now);
        checkRecord.setUpdateUser(user);
        checkRecord.setCreateUser(user);

        return checkRecordApi.checkIn(checkRecord);
    }

    /**
     * 预约入住
     * @param id 预约ID
     * @return 1-成功
     */
    public int reserveCheckIn(Long id) {
        Date now = new Date();
        CheckRecord checkRecord = new CheckRecord();
        checkRecord.setId(id);
        checkRecord.setUpdateTime(now);
        checkRecord.setUpdateUser(commonManage.currentUser());
        return checkRecordApi.reserveCheckIn(checkRecord);
    }

    /**
     * 退房/取消预约
     * @param id 预约ID
     * @return 1-成功
     */
    public int leave(Long id) {
        Date now = new Date();
        CheckRecord checkRecord = new CheckRecord();
        checkRecord.setId(id);
        checkRecord.setUpdateTime(now);
        checkRecord.setUpdateUser(commonManage.currentUser());
        return checkRecordApi.leave(checkRecord);
    }

    private CheckRecord findOne(Long id) {
        return checkRecordApi.findOne(id);
    }

}
