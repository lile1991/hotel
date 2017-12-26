package com.hotel.controller;

import com.hotel.api.UserApi;
import com.hotel.entity.User;
import com.hotel.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class UserApiCtrl implements UserApi {

    @Autowired
    UserService userService;

    @Override
    public User findOne(Long id) {
        return userService.findOne(id);
    }
}
