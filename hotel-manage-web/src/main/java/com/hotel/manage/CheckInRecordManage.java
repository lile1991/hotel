package com.hotel.manage;

import com.hotel.api.CheckInRecordApi;
import com.hotel.dto.CheckInRecordQueryDto;
import com.hotel.entity.CheckInRecord;
import com.hotel.entity.CheckOutRecord;
import com.hotel.entity.Room;
import com.hotel.entity.RoomType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

import java.util.Date;
import java.util.List;

@Component
public class CheckInRecordManage {

    @Autowired
    CheckInRecordApi checkInRecordApi;

    @Autowired
    RoomManage roomManage;

    @Autowired
    UserManage userManage;

    @Autowired
    RoomTypeManage roomTypeManage;

    @Autowired
    CommonManage commonManage;

    @Autowired
    CheckOutRecordManage checkOutRecordManage;

    @Autowired
    CheckInCustomerManage checkInCustomerManage;

    public Page<CheckInRecord> findManage(CheckInRecordQueryDto queryDto) {
        Page<CheckInRecord> page = checkInRecordApi.findManage(queryDto);
        List<CheckInRecord> content = page.getContent();
        if(! CollectionUtils.isEmpty(content)) {
            content.forEach(checkInRecord -> {
                CheckOutRecord checkOutRecord = checkInRecord.getCheckOutRecord();
                if(checkOutRecord != null) {
                    checkOutRecord.setCheckInRecord(null);
                }
            });
        }
        return page;
    }

    /**
     * 登记入住
     * @param checkRecord 登记信息
     * @return 1-成功
     */
    public CheckInRecord checkIn(CheckInRecord checkRecord) {
        Date now = new Date();
        Long userId = commonManage.currentUserId();

        Assert.isTrue(checkRecord.getCheckInTime().getTime() < checkRecord.getOverTime().getTime(), "到期时间不能大于入住时间");

        checkRecord.setCreateTime(now);
        checkRecord.setUpdateTime(now);
        checkRecord.setUpdateUserId(userId);
        checkRecord.setCreateUserId(userId);

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
        checkRecord.setUpdateUserId(commonManage.currentUserId());
        return checkInRecordApi.reserveCheckIn(checkRecord);
    }

    public CheckInRecord findOne(Long id) {
        return checkInRecordApi.findOne(id);
    }

    public CheckInRecord findDetail(Long id) {
        // 查找入住记录
        CheckInRecord checkInRecord = checkInRecordApi.findOne(id);

        // 查找登记人
        checkInRecord.setCreateUser(userManage.findOne(checkInRecord.getCreateUserId()));

        // 查找入住客户
        checkInRecord.setCheckInCustomers(checkInCustomerManage.findByCheckInRecordId(id));

        // 查找退房记录
        CheckOutRecord checkOutRecord = checkInRecord.getCheckOutRecord();
        if(checkOutRecord != null) {
            checkOutRecord.setCheckInRecord(null);
            checkOutRecord.setCreateUser(userManage.findOne(checkOutRecord.getCreateUserId()));
        }

        // 查找房间信息
        Room room = roomManage.findOne(checkInRecord.getRoomId());
        checkInRecord.setRoom(room);

        // 查找房间类型
        RoomType roomType = roomTypeManage.findOne(room.getRoomTypeId());
        room.setRoomType(roomType);
        return checkInRecord;
    }
}
