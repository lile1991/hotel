package com.hotel.controller;

import com.hotel.dto.RoomQueryDto;
import com.hotel.service.IRoomService;
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
    IRoomService roomService;

    @RequestMapping("findAll")
    @ResponseBody
    public ResultVo<?> findAll(@RequestBody RoomQueryDto roomQueryDto) {
        return ResultVo.success(roomService.findManage(roomQueryDto));
    }
}
