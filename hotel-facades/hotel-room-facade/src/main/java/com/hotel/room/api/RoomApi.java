package com.hotel.room.api;

import com.hotel.room.dto.RoomQueryDto;
import com.hotel.room.entity.Room;

import java.util.List;

public interface RoomApi {
    List<Room> findManage(RoomQueryDto queryDto);

    List<Room> findAll(Room room);

    int enable(Long id);

    int disable(Long id);

    Room findOne(Long id);

}
