package com.hotel.room.controller;

import com.hotel.room.api.RoomTypeApi;
import com.hotel.room.entity.RoomType;
import com.hotel.room.entity.RoomType_;
import com.hotel.room.service.RoomTypeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
public class RoomTypeApiCtrl implements RoomTypeApi {

    @Autowired
    RoomTypeService roomTypeService;

    @Override
    public List<RoomType> findAll() {
        List<RoomType> list = roomTypeService.findAll(new Sort(Sort.Direction.ASC, RoomType_.id.getName()));
        return list;
    }

    @Override
    public RoomType findOne(Integer id) {
        return roomTypeService.findOne(id);
    }
}
