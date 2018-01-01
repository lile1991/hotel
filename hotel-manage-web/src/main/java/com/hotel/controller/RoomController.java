package com.hotel.controller;

import com.hotel.dto.RoomQueryDto;
import com.hotel.entity.Room;
import com.hotel.manage.CheckInRecordManage;
import com.hotel.manage.RoomManage;
import com.hotel.vo.ResultVo;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("room")
public class RoomController extends BaseController {

    @Autowired
    RoomManage roomManage;

    @Autowired
    CheckInRecordManage checkInRecordManage;

    @PostMapping(value = "findManage")
    @ResponseBody
    public ResultVo<?> findManage(@RequestBody RoomQueryDto roomQueryDto) {
        return ResultVo.success(roomManage.findManage(roomQueryDto));
    }

    @PostMapping("findAll")
    @ResponseBody
    public ResultVo<?> findAll(@RequestBody Room room) {
        return ResultVo.success(roomManage.findAll(room));
    }

    @GetMapping("findOne/{id}")
    @ResponseBody
    public ResultVo<?> findAll(@PathVariable("id") Long id) {
        return ResultVo.success(roomManage.findOne(id));
    }

    @GetMapping("enable/{id}")
    @ResponseBody
    @RequiresPermissions("room:enable")
    public ResultVo<?> enable(@PathVariable("id") Long id) {
        int result = roomManage.enable(id);
        if(result == 1) {
            return ResultVo.success(result, "启用房间成功");
        }
        return ResultVo.fail(result, "启用房间失败");
    }

    @GetMapping("disable/{id}")
    @ResponseBody
    @RequiresPermissions("room:disable")
    public ResultVo<?> disable(@PathVariable("id") Long id) {
        int result = roomManage.disable(id);
        if(result == 1) {
            return ResultVo.success(result, "停用房间成功");
        }
        return ResultVo.fail(result, "停用房间失败");
    }
}
