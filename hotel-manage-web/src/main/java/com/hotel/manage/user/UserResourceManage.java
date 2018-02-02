package com.hotel.manage.user;

import com.hotel.user.api.UserResourceApi;
import com.hotel.user.entity.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserResourceManage {

    @Autowired
    UserResourceApi userResourceApi;

    public List<Resource> findByUserId(Long userId) {
        return userResourceApi.findByUserId(userId);
    }

    public void grantAuth(Long userId, List<Long> resourceIdList) {
        userResourceApi.grantAuth(userId, resourceIdList);
    }
}
