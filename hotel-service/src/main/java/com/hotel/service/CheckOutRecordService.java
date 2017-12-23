package com.hotel.service;

import com.hotel.dao.CheckOutRecordRepository;
import com.hotel.entity.CheckInRecord;
import com.hotel.entity.CheckOutRecord;
import com.hotel.enums.CheckStateEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.beans.Transient;

@Slf4j
@Service
public class CheckOutRecordService extends BaseService<CheckOutRecord, Long, CheckOutRecordRepository> {

    @Autowired
    CheckInRecordService checkInRecordService;

    @Transient
    public int leave(CheckInRecord leaveCheckInRecord) {
        CheckInRecord curCheckInRecord = checkInRecordService.findOne(leaveCheckInRecord.getId());
        Assert.notNull(curCheckInRecord, "入住记录不存在");

        CheckStateEnum checkStateEnum = CheckStateEnum.valueOf(curCheckInRecord.getState());
        CheckStateEnum newCheckStateEnum;
        switch (checkStateEnum) {
            // 入住中
            case CHECK_IN:
                // 退房
                newCheckStateEnum = CheckStateEnum.LEFT;
                break;
            // 预定中
            case RESERVE:
                // 取消预定
                newCheckStateEnum = CheckStateEnum.CANCELED_RESERVE;
                break;
            default:
                throw new IllegalStateException("重复的操作， 已取消预定或已退房");
        }

        curCheckInRecord.setState(newCheckStateEnum.name());
        curCheckInRecord.setUpdateUser(leaveCheckInRecord.getUpdateUser());
        curCheckInRecord.setUpdateTime(leaveCheckInRecord.getUpdateTime());

        // 更新入住状态
        checkInRecordService.save(curCheckInRecord);
        // 更新房间状态
        checkInRecordService.changeState(curCheckInRecord);
        // TODO 插入退房记录
        return 1;
    }

    public Long checkOut(CheckOutRecord checkOutRecord) {
        return null;
    }
}
