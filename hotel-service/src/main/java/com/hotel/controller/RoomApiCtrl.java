package com.hotel.controller;

import com.hotel.api.RoomApi;
import com.hotel.dto.RoomQueryDto;
import com.hotel.entity.Room;
import com.hotel.service.RoomService;
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
}
