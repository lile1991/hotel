package com.hotel.controller;

import com.hotel.dto.RoomQueryDto;
import com.hotel.entity.Room;
import com.hotel.manage.CheckInRecordManage;
import com.hotel.manage.RoomManage;
import com.hotel.vo.ResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("room")
public class RoomController extends BaseController {

    @Autowired
    RoomManage roomManage;

    @Autowired
    CheckInRecordManage checkInRecordManage;

    @RequestMapping("findManage")
    @ResponseBody
    public ResultVo<?> findManage(@RequestBody RoomQueryDto roomQueryDto) {
        return ResultVo.success(roomManage.findManage(roomQueryDto));
    }

    @RequestMapping("findAll")
    @ResponseBody
    public ResultVo<?> findAll(@RequestBody Room room) {
        return ResultVo.success(roomManage.findAll(room));
    }

    @RequestMapping("enable/{id}")
    @ResponseBody
    public ResultVo<?> enable(@PathVariable("id") Long id) {
        int result = roomManage.enable(id);
        if(result == 1) {
            return ResultVo.success(result, "启用房间成功");
        }
        return ResultVo.fail(result, "启用房间失败");
    }

    @RequestMapping("disable/{id}")
    @ResponseBody
    public ResultVo<?> disable(@PathVariable("id") Long id) {
        int result = roomManage.disable(id);
        if(result == 1) {
            return ResultVo.success(result, "停用房间成功");
        }
        return ResultVo.fail(result, "停用房间失败");
    }
}
