package com.hotel.api;

import com.hotel.dto.RoomQueryDto;
import com.hotel.entity.Room;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface RoomApi {
    @Transactional(readOnly = true)
    List<Room> findManage(RoomQueryDto queryDto);
}
