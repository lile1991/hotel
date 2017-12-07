package com.hotel.manage;

import com.hotel.api.RoomApi;
import com.hotel.dto.RoomQueryDto;
import com.hotel.entity.Room;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RoomManage {
    @Autowired
    RoomApi roomApi;

    public List<Room> findManage(RoomQueryDto roomQueryDto) {
        return roomApi.findManage(roomQueryDto);
    }
}
