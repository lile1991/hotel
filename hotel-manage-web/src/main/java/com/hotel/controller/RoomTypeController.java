package com.hotel.controller;

import com.hotel.manage.RoomTypeManage;
import com.hotel.vo.ResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("roomType")
public class RoomTypeController extends BaseController {

    @Autowired
    RoomTypeManage roomTypeManage;

    @RequestMapping("findAll")
    @ResponseBody
    public ResultVo<?> findAll() {
        return ResultVo.success(roomTypeManage.findAll());
    }
}
