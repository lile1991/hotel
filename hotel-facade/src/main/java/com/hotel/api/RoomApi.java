package com.hotel.api;

import com.hotel.dto.RoomQueryDto;
import com.hotel.entity.Room;

import java.util.List;

public interface RoomApi {
    List<Room> findManage(RoomQueryDto queryDto);

    List<Room> findAll(Room room);

    int enable(Long id);

    int disable(Long id);

    Room findOne(Long id);

    Room findCheckIn(Long id);
}
