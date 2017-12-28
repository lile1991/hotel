package com.hotel.controller;

import com.hotel.vo.ResultVo;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@RequestMapping("user")
@Controller
public class UserController {
    @RequestMapping("login")
    @ResponseBody
    public ResultVo<?> login(HttpServletRequest request) {
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken userToken = new UsernamePasswordToken();
        userToken.setUsername("admin");
        userToken.setPassword("123456".toCharArray());
        subject.login(userToken);
        return ResultVo.success(subject.getSession().getId());
    }
}
