package com.hotel.manage;

import com.hotel.room.api.RoomTypeApi;
import com.hotel.room.entity.RoomType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RoomTypeManage {

    @Autowired
    RoomTypeApi roomTypeApi;

    public List<RoomType> findAll() {
        return roomTypeApi.findAll();
    }

    public RoomType findOne(Integer roomTypeId) {
        return roomTypeApi.findOne(roomTypeId);
    }
}
