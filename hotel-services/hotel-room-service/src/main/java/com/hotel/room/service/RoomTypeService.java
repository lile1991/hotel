package com.hotel.room.service;

import com.hotel.room.dao.RoomTypeRepository;
import com.hotel.room.entity.RoomType;
import com.hotel.service.BaseService;
import org.springframework.stereotype.Service;

@Service
public class RoomTypeService extends BaseService<RoomType, Integer, RoomTypeRepository> {
}
