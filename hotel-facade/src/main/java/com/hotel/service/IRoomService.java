package com.hotel.service;

import com.hotel.dto.RoomQueryDto;
import com.hotel.entity.Room;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface IRoomService {
    @Transactional(readOnly = true)
    List<Room> findManage(RoomQueryDto queryDto);
}
