package com.hotel.user.api;

import com.hotel.user.entity.User;

public interface UserApi {
    User findByUsername(String username);

    User findOne(Long id);
}
