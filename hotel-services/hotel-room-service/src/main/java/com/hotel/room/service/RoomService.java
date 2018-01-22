package com.hotel.room.service;

import com.hotel.room.dao.RoomRepository;
import com.hotel.room.dto.RoomQueryDto;
import com.hotel.room.entity.Room;
import com.hotel.room.entity.RoomType_;
import com.hotel.room.entity.Room_;
import com.hotel.room.enums.RoomStateEnum;
import com.hotel.service.BaseService;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.Predicate;
import java.util.List;

@Service
public class RoomService extends BaseService<Room, Long, RoomRepository> {

    @Transactional(readOnly = true)
    public List<Room> findManage(RoomQueryDto queryDto) {
        return super.findAll((root, query, cb) -> {
            Predicate predicate = cb.equal(root.get(Room_.roomType).get(RoomType_.id), queryDto.getRoomTypeId());
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

}
