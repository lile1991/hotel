package com.hotel.manage;

import com.hotel.entity.User;
import org.springframework.stereotype.Component;

@Component
public class CommonManage {

    public Long currentUserId() {
        return 1L;
    }

    public User currentUser() {
        User user = new User();
        user.setId(currentUserId());
        return user;
    }
}
