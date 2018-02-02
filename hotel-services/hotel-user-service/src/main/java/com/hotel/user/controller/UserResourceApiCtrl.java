package com.hotel.user.controller;

import com.hotel.user.api.UserResourceApi;
import com.hotel.user.entity.Resource;
import com.hotel.user.service.UserResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class UserResourceApiCtrl implements UserResourceApi {

    @Autowired
    UserResourceService userResourceService;

    @Override
    public List<Resource> findByUserId(Long userId) {
        return userResourceService.findByUserId(userId);
    }

    @Override
    public void grantAuth(Long userId, List<Long> resourceIdList) {
        userResourceService.grantAuth(userId, resourceIdList);
    }
}
