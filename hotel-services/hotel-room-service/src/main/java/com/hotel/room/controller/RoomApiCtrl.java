package com.hotel.room.controller;

import com.hotel.room.api.RoomApi;
import com.hotel.room.dto.RoomQueryDto;
import com.hotel.room.entity.Room;
import com.hotel.room.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RoomApiCtrl implements RoomApi {

    @Autowired
    RoomService roomService;

    @Override
    public List<Room> findManage(RoomQueryDto queryDto) {
        return roomService.findManage(queryDto);
    }

    @Override
    public List<Room> findAll(Room room) {
        return roomService.findAll(room);
    }

    @Override
    public int enable(Long id) {
        return roomService.enable(id);
    }

    @Override
    public int disable(Long id) {
        return roomService.disable(id);
    }

    @Override
    public Room findOne(Long id) {
        return roomService.findOne(id);
    }

}
