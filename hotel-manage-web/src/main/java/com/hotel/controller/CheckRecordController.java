package com.hotel.controller;

import com.hotel.dto.CheckRecordQueryDto;
import com.hotel.entity.CheckRecord;
import com.hotel.entity.Customer;
import com.hotel.entity.Room;
import com.hotel.manage.CheckRecordManage;
import com.hotel.vo.CheckInVo;
import com.hotel.vo.ResultVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;

@Controller
@RequestMapping("checkRecord")
public class CheckRecordController extends BaseController {

    @Autowired
    CheckRecordManage checkRecordManage;


    @RequestMapping("findManage")
    @ResponseBody
    public ResultVo<?> findManage(@RequestBody CheckRecordQueryDto queryDto) {
        return ResultVo.success(checkRecordManage.findManage(queryDto));
    }

    @PostMapping("checkIn")
    @ResponseBody
    public ResultVo<?> checkIn(@Valid @RequestBody CheckInVo checkInVo) {
        CheckRecord checkRecord = new CheckRecord();
        BeanUtils.copyProperties(checkInVo, checkRecord);

        Room room = new Room();
        room.setId(checkInVo.getRoomId());
        checkRecord.setRoom(room);

        Long customerId = checkInVo.getCustomerId();

        if(customerId != null) {
            Customer customer = new Customer();
            customer.setId(customerId);
            checkRecord.setCustomer(customer);
        }

        return ResultVo.success(checkRecordManage.checkIn(checkRecord), "登记入住成功");
    }
}
