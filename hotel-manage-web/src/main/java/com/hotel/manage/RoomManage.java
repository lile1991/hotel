package com.hotel.manage;

import com.hotel.api.RoomApi;
import com.hotel.api.RoomTypeApi;
import com.hotel.dto.RoomQueryDto;
import com.hotel.entity.Room;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RoomManage {
    @Autowired
    RoomApi roomApi;

    @Autowired
    RoomTypeApi roomTypeApi;

    public List<Room> findManage(RoomQueryDto roomQueryDto) {
        return roomApi.findManage(roomQueryDto);
    }

    public List<Room> findAll(Room room) {
        return roomApi.findAll(room);
    }

    public int enable(Long id) {
        return roomApi.enable(id);
    }

    public int disable(Long id) {
        return roomApi.disable(id);
    }

    public Room findOne(Long id) {
        return roomApi.findOne(id);
    }
}
