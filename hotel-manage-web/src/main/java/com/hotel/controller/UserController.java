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
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("lock/{id}")
    @ResponseBody
    public ResultVo<?> lock(@PathVariable("id") Long id) {
        return ResultVo.success(userManage.lock(id) == 1 ? "禁用用户成功" : "禁用用户失败");
    }

    @GetMapping("unlock/{id}")
    @ResponseBody
    public ResultVo<?> unlock(@PathVariable("id") Long id) {
        return ResultVo.success(userManage.unlock(id) == 1 ? "启用用户成功" : "启用用户失败");
    }
}
