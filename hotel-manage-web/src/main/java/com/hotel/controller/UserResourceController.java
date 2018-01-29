package com.hotel.controller;

import com.hotel.manage.user.UserResourceManage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("userResource")
public class UserResourceController {

    @Autowired
    UserResourceManage userResourceManage;
}
