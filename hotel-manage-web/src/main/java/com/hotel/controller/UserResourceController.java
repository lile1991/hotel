package com.hotel.controller;

import com.hotel.manage.user.UserResourceManage;
import com.hotel.vo.ResultVo;
import com.hotel.vo.in.GrantResourceAuthVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("userResource")
public class UserResourceController {

    @Autowired
    UserResourceManage userResourceManage;

    @PostMapping("grantAuth")
    @ResponseBody
    public ResultVo<?> grantAuth(@RequestBody GrantResourceAuthVo grantResourceAuthVo) {
        userResourceManage.grantAuth(grantResourceAuthVo.getUserId(), grantResourceAuthVo.getResourceIdList());
        return ResultVo.success("授权成功");
    }
}
