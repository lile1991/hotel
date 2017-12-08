package com.hotel.controller;

import com.hotel.vo.ResultVo;
import com.hotel.vo.UserSessionVo;
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
        UserSessionVo userSessionVo = new UserSessionVo();
        userSessionVo.setToken("jsessionid:123456789");
        request.setAttribute(UserSessionVo.SESSION_KEY, userSessionVo);
        return ResultVo.success(userSessionVo);
    }
}
