package com.hotel.room.api;

import com.hotel.room.entity.RoomType;

import java.util.List;

public interface RoomTypeApi {
    List<RoomType> findAll();

    RoomType findOne(Integer id);
}
