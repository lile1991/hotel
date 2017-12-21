package com.hotel.controller;

import com.hotel.constant.SystemConstant;
import com.hotel.dto.CheckInRecordQueryDto;
import com.hotel.entity.CheckInCustomer;
import com.hotel.entity.CheckInRecord;
import com.hotel.entity.Member;
import com.hotel.entity.Room;
import com.hotel.enums.CheckStateEnum;
import com.hotel.enums.EnumListConstant;
import com.hotel.manage.CheckInRecordManage;
import com.hotel.vo.CheckInCustomerVo;
import com.hotel.vo.CheckInVo;
import com.hotel.vo.ResultVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("checkInRecord")
public class CheckInRecordController extends BaseController {

    @Autowired
    CheckInRecordManage checkInRecordManage;

    @RequestMapping("findManage")
    @ResponseBody
    public ResultVo<?> findManage(@RequestBody CheckInRecordQueryDto queryDto) {
        return ResultVo.success(checkInRecordManage.findManage(queryDto));
    }

    @RequestMapping("getCheckStateEnums")
    @ResponseBody
    public ResultVo<?> getCheckStateEnums() {
        return ResultVo.success(EnumListConstant.getEnumEntryList(CheckStateEnum.class));
    }

    @PostMapping("checkIn")
    @ResponseBody
    public ResultVo<?> checkIn(@Valid @RequestBody CheckInVo checkInVo) {
        CheckInRecord checkInRecord = new CheckInRecord();
        BeanUtils.copyProperties(checkInVo, checkInRecord);

        Room room = new Room();
        room.setId(checkInVo.getRoomId());
        checkInRecord.setRoom(room);

        long memberId = SystemConstant.getId(checkInVo.getMemberId());
        if(SystemConstant.isNullId(memberId)) {
            // TODO 校验会员是否存在
        }
        Member member = new Member();
        member.setId(memberId);
        checkInRecord.setMember(member);

        List<CheckInCustomerVo> checkInCustomers = checkInVo.getCheckInCustomers();
        checkInRecord.setCheckInCustomers(checkInCustomers.stream().map(vo -> {
            CheckInCustomer checkInCustomer = new CheckInCustomer();
            checkInCustomer.setIdCard(vo.getIdCard());
            checkInCustomer.setMobile(vo.getMobile());
            checkInCustomer.setName(vo.getName());
            return checkInCustomer;
        }).collect(Collectors.toList()));

        return ResultVo.success(checkInRecordManage.checkIn(checkInRecord).getId(), "登记入住成功");
    }

    @RequestMapping("reserveCheckIn/{id}")
    @ResponseBody
    public ResultVo<?> reserveCheckIn(@PathVariable("id") Long id) {
        return ResultVo.success(checkInRecordManage.reserveCheckIn(id), "预约入住成功");
    }

}
