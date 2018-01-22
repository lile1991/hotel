package com.hotel.room.service;

import com.hotel.room.dao.RoomFloorRepository;
import com.hotel.room.entity.RoomFloor;
import com.hotel.service.BaseService;
import org.springframework.stereotype.Service;

@Service
public class RoomFloorService extends BaseService<RoomFloor, Integer, RoomFloorRepository> {
}
