package com.hotel.service;

import com.hotel.dao.RoomTypeRepository;
import com.hotel.entity.RoomType;
import org.springframework.stereotype.Service;

@Service
public class RoomTypeService extends BaseService<RoomType, Integer, RoomTypeRepository> {
}
