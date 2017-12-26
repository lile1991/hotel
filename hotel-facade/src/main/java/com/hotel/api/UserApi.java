package com.hotel.api;

import com.hotel.entity.User;

public interface UserApi {

    User findOne(Long id);
}
