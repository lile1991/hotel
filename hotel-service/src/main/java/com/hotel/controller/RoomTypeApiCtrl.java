package com.hotel.controller;

import com.hotel.api.RoomTypeApi;
import com.hotel.entity.RoomType;
import com.hotel.entity.RoomType_;
import com.hotel.service.RoomTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RoomTypeApiCtrl implements RoomTypeApi {

    @Autowired
    RoomTypeService roomTypeService;

    @Override
    public List<RoomType> findAll() {
        return roomTypeService.findAll(new Sort(Sort.Direction.ASC, RoomType_.id.getName()));
    }
}
