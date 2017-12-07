package com.hotel.service;

import com.hotel.dao.UserRepository;
import com.hotel.entity.User;
import org.springframework.stereotype.Service;

@Service
public class UserService extends BaseService<User, Long, UserRepository> {

}
