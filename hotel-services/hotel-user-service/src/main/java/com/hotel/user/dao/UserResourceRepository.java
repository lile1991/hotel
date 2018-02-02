package com.hotel.user.dao;

import com.hotel.dao.BaseRepository;
import com.hotel.user.entity.UserResource;
import org.springframework.stereotype.Repository;

@Repository
public interface UserResourceRepository extends BaseRepository<UserResource, Long> {
    int deleteByUserId(Long userId);
}
