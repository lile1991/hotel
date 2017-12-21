package com.hotel.service;

import com.hotel.dao.RoomRepository;
import com.hotel.dto.RoomQueryDto;
import com.hotel.entity.Room;
import com.hotel.entity.RoomType;
import com.hotel.entity.RoomType_;
import com.hotel.entity.Room_;
import com.hotel.enums.RoomStateEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.Predicate;
import java.util.List;

@Service
public class RoomService extends BaseService<Room, Long, RoomRepository> {

    @Autowired
    RoomTypeService roomTypeService;

    @Transactional(readOnly = true)
    public List<Room> findManage(RoomQueryDto queryDto) {
        return super.findAll((root, query, cb) -> {
            Predicate predicate = cb.equal(root.get(Room_.roomType).get(RoomType_.id), queryDto.getRoomType().getId());
            root.fetch(Room_.roomFloor);
            root.fetch(Room_.roomType);
            return predicate;
        }, new Sort(new Sort.Order(Sort.Direction.ASC, Room_.number.getName())));
    }

    @Transactional
    public int enable(Long id) {
        return repository.changeState(id, RoomStateEnum.EMPTY.name());
    }

    @Transactional
    public int disable(Long id) {
        return repository.changeState(id, RoomStateEnum.STOP.name());
    }

    @Transactional(readOnly = true)
    public Room findCheckIn(Long id) {
        Room room = findOne(id);
        RoomType roomType = roomTypeService.findOne(room.getRoomType().getId());
        room.setRoomType(roomType);
        return room;
    }
}
