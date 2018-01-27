package com.hotel;

import com.hotel.config.HotelWebConfiguration;
import com.hotel.config.ShiroConfiguration;
import com.hotel.room.config.HotelRoomServiceConfiguration;
import com.hotel.user.config.HotelUserServiceConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.liquibase.LiquibaseAutoConfiguration;
import org.springframework.context.annotation.Import;

@SpringBootApplication(exclude = {LiquibaseAutoConfiguration.class})
@Import({HotelRoomServiceConfiguration.class, HotelUserServiceConfiguration.class, HotelWebConfiguration.class, ShiroConfiguration.class})
public class HotelWebApplication {
    public static void main(String[] args) {
        SpringApplication.run(HotelWebApplication.class);
    }
}
