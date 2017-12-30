package com.hotel.user.dao;

import com.hotel.dao.BaseRepository;
import com.hotel.user.entity.Resource;
import org.springframework.stereotype.Repository;

@Repository
public interface ResourceRepository extends BaseRepository<Resource, Long> {
}
