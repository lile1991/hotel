package com.hotel.user.dao;

import com.hotel.dao.BaseRepository;
import com.hotel.user.entity.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends BaseRepository<User, Long> {

    User findByUsernameOrMobile(String username, String mobile);

    @Query("update User set locked = :locked where id = :id")
    @Modifying
    int updateLockState(@Param("id") Long id, @Param("locked") boolean locked);
}
