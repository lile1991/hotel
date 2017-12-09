package com.hotel.dao;

import com.hotel.entity.Room;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomRepository extends BaseRepository<Room, Long> {
}
