package com.hotel.user.api;

import com.hotel.user.entity.Resource;

import java.util.List;

public interface UserResourceApi {
    List<Resource> findByUserId(Long userId);
}
