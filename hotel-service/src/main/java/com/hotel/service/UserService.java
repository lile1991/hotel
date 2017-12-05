package com.hotel.service;

import com.hotel.dao.UserRepository;
import com.hotel.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService extends BaseService<User, Long, UserRepository> implements IUserService {

}
