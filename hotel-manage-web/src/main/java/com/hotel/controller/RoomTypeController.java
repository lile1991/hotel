package com.hotel.controller;

import com.hotel.manage.RoomTypeManage;
import com.hotel.vo.ResultVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Slf4j
@Controller
@RequestMapping("roomType")
public class RoomTypeController extends BaseController {

    @Autowired
    RoomTypeManage roomTypeManage;

    @GetMapping("findAll")
    @ResponseBody
    public ResultVo<?> findAll() {
        return ResultVo.success(roomTypeManage.findAll());
    }
}
