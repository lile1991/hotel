package com.hotel.room;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.liquibase.LiquibaseAutoConfiguration;

@SpringBootApplication(exclude = LiquibaseAutoConfiguration.class)
public class HotelRoomServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(HotelRoomServiceApplication.class);
    }
}
