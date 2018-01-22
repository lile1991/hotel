package com.hotel.room.dao;

import com.hotel.dao.BaseRepository;
import com.hotel.room.entity.RoomFloor;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomFloorRepository extends BaseRepository<RoomFloor, Integer> {
}
