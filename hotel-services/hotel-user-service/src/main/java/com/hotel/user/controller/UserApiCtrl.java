package com.hotel.user.controller;

import com.hotel.user.api.UserApi;
import com.hotel.user.dto.UserQueryDto;
import com.hotel.user.entity.User;
import com.hotel.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@Component
public class UserApiCtrl implements UserApi {

    @Autowired
    UserService userService;

    @Override
    public User findByUsername(String username) {
        return userService.findByUsername(username);
    }

    @Override
    public User findOne(Long id) {
        return userService.findOne(id);
    }

    @Override
    public Page<User> findManage(UserQueryDto userQueryDto) {
        return userService.findAll(userQueryDto);
    }
}
