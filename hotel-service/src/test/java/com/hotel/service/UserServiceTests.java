package com.hotel.service;

import com.hotel.HotelServiceApplication;
import com.hotel.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@Import(HotelServiceApplication.class)
public class UserServiceTests {

    @Autowired
    UserService userService;

    @Test
    public void testSave() {
        User user = new User();
        userService.save(user);
    }
}
