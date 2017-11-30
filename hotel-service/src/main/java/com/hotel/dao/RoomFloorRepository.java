package com.hotel.dao;

import com.hotel.entity.RoomFloor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomFloorRepository extends PagingAndSortingRepository<RoomFloor, Long> {
}
