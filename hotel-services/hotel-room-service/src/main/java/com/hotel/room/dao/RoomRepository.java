package com.hotel.room.dao;

import com.hotel.dao.BaseRepository;
import com.hotel.room.entity.Room;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomRepository extends BaseRepository<Room, Long> {

    @Query("update Room r set r.state = :state where r.id = :id")
    @Modifying
    int changeState(@Param("id") Long id, @Param("state") String state);
}
