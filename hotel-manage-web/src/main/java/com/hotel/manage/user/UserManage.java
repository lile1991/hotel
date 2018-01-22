package com.hotel.manage.user;

import com.hotel.user.api.UserApi;
import com.hotel.user.dto.UserQueryDto;
import com.hotel.user.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@Component
public class UserManage {

    @Autowired
    UserApi userApi;

    public User findOne(Long id) {
        return userApi.findOne(id);
    }

    public User findByUsername(String username) {
        return userApi.findByUsername(username);
    }

    public Page<User> findManage(UserQueryDto userQueryDto) {
        return userApi.findManage(userQueryDto);
    }

    public int lock(Long id) {
        return userApi.lock(id);
    }

    public int unlock(Long id) {
        return userApi.unlock(id);
    }
}
