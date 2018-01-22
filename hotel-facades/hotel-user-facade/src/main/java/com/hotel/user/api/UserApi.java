package com.hotel.user.api;

import com.hotel.user.dto.UserQueryDto;
import com.hotel.user.entity.User;
import org.springframework.data.domain.Page;

public interface UserApi {
    User findByUsername(String username);

    User findOne(Long id);

    Page<User> findManage(UserQueryDto userQueryDto);
}
