package com.hotel.manage;

import com.hotel.manage.user.UserManage;
import com.hotel.room.api.CheckInRecordApi;
import com.hotel.room.dto.CheckInRecordQueryDto;
import com.hotel.room.entity.CheckInRecord;
import com.hotel.room.entity.CheckOutRecord;
import com.hotel.room.entity.Room;
import com.hotel.room.entity.RoomType;
import com.hotel.utils.HotelBeanUtils;
import com.hotel.vo.out.CheckInRecordOutVo;
import com.hotel.vo.out.CheckOutRecordOutVo;
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

    public Page<CheckInRecordOutVo> findManage(CheckInRecordQueryDto queryDto) {
        Page<CheckInRecord> page = checkInRecordApi.findManage(queryDto);
        Page<CheckInRecordOutVo> voPage = HotelBeanUtils.convert(page, CheckInRecordOutVo.class);
        List<CheckInRecordOutVo> voContent = voPage.getContent();
        if(! CollectionUtils.isEmpty(voContent)) {
            voContent.forEach(checkInRecord -> {
                CheckOutRecordOutVo checkOutRecord = checkInRecord.getCheckOutRecord();
                if(checkOutRecord != null) {
                    checkInRecord.setCreateUser(userManage.findOne(checkOutRecord.getCreateUserId()));
                }
            });
        }
        return voPage;
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
        CheckInRecordOutVo outVo = HotelBeanUtils.convert(checkInRecord, CheckInRecordOutVo.class);

        // 查找登记人
        outVo.setCreateUser(userManage.findOne(checkInRecord.getCreateUserId()));

        // 查找入住客户
        checkInRecord.setCheckInCustomers(checkInCustomerManage.findByCheckInRecordId(id));

        // 查找退房记录
        CheckOutRecord checkOutRecord = checkInRecord.getCheckOutRecord();
        if(checkOutRecord != null) {
            CheckOutRecordOutVo checkOutRecordOutVo = HotelBeanUtils.convert(checkOutRecord, CheckOutRecordOutVo.class);
            checkOutRecordOutVo.setCheckInRecord(null);
            checkOutRecordOutVo.setCreateUser(userManage.findOne(checkOutRecord.getCreateUserId()));
            outVo.setCheckOutRecord(checkOutRecordOutVo);
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
