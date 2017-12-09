package com.hotel.dao;

import com.hotel.entity.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends BaseRepository<User, Long> {
}
