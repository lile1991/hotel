package com.hotel.dao;

import com.hotel.entity.Room;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomLogRepository extends PagingAndSortingRepository<Room, Long> {
}
