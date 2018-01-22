package com.hotel.controller;

import com.hotel.manage.user.UserManage;
import com.hotel.user.dto.UserQueryDto;
import com.hotel.vo.ResultVo;
import com.hotel.vo.in.LoginVo;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@RequestMapping("user")
@Controller
public class UserController {

    @Autowired
    UserManage userManage;

    @PostMapping("login")
    @ResponseBody
    public ResultVo<?> login(@RequestBody LoginVo loginVo) {
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken userToken = new UsernamePasswordToken();
        userToken.setUsername(loginVo.getUsername());
        userToken.setPassword(loginVo.getPassword().toCharArray());
//        userToken.setRememberMe(false);
        subject.login(userToken);
        return ResultVo.success(subject.getSession().getId());
    }

    @PostMapping("findManage")
    @ResponseBody
    public ResultVo<?> findManage(UserQueryDto userQueryDto) {
        return ResultVo.success(userManage.findManage(userQueryDto));
    }
}
