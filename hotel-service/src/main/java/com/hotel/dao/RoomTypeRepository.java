package com.hotel.dao;

import com.hotel.entity.RoomType;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomTypeRepository extends PagingAndSortingRepository<RoomType, Long> {
}
