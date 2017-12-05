package com.hotel.dao;

import com.hotel.entity.RoomType;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomTypeRepository extends CrudRepository<RoomType, Long> {
}
