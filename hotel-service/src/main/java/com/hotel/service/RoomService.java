package com.hotel.service;

import com.hotel.dao.RoomRepository;
import com.hotel.dto.RoomQueryDto;
import com.hotel.entity.Room;
import com.hotel.entity.RoomType_;
import com.hotel.entity.Room_;
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
            Predicate predicate = cb.equal(root.get(Room_.roomType).get(RoomType_.id), queryDto.getRoomType().getId());
            root.fetch(Room_.roomFloor);
            root.fetch(Room_.roomType);
            return predicate;
        }, new Sort(new Sort.Order(Sort.Direction.ASC, Room_.number.getName())));
    }
}
