package com.hotel.user.dao;

import com.hotel.dao.BaseRepository;
import com.hotel.user.entity.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends BaseRepository<User, Long> {
    User findByUsernameOrMobile(String username, String mobile);
}
