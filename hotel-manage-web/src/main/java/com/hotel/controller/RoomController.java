package com.hotel.controller;

import com.hotel.dto.RoomQueryDto;
import com.hotel.entity.CheckRecord;
import com.hotel.manage.CheckRecordManage;
import com.hotel.manage.RoomManage;
import com.hotel.vo.ResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("room")
public class RoomController extends BaseController {

    @Autowired
    RoomManage roomManage;

    @Autowired
    CheckRecordManage checkRecordManage;

    @RequestMapping("findManage")
    @ResponseBody
    public ResultVo<?> findManage(@RequestBody RoomQueryDto roomQueryDto) {
        return ResultVo.success(roomManage.findManage(roomQueryDto));
    }

    @RequestMapping("checkIn")
    @ResponseBody
    public ResultVo<?> checkIn(@RequestBody CheckRecord checkRecord) {
        return ResultVo.success(checkRecordManage.checkIn(checkRecord));
    }
}
