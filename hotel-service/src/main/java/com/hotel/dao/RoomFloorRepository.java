package com.hotel.dao;

import com.hotel.entity.RoomFloor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomFloorRepository extends CrudRepository<RoomFloor, Long> {
}
