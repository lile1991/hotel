package com.hotel.user.service;

import com.hotel.service.BaseService;
import com.hotel.user.dao.UserRepository;
import com.hotel.user.entity.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService extends BaseService<User, Long, UserRepository> {

    @Transactional(readOnly = true)
    public User findByUsername(String username) {
        return repository.findByUsernameOrMobile(username, username);
    }
}
