package com.hotel.room.dao;

import com.hotel.dao.BaseRepository;
import com.hotel.room.entity.RoomType;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomTypeRepository extends BaseRepository<RoomType, Integer> {
}
