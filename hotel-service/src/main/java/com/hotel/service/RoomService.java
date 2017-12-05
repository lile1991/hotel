package com.hotel.service;

import com.hotel.dao.RoomRepository;
import com.hotel.entity.Room;
import org.springframework.stereotype.Service;

@Service
public class RoomService extends BaseService<Room, Long, RoomRepository> implements IRoomService {
}
