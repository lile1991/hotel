package com.hotel;

import com.hotel.config.HotelServiceConfig;
import com.hotel.config.HotelWebConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.liquibase.LiquibaseAutoConfiguration;
import org.springframework.context.annotation.Import;

@SpringBootApplication(exclude = LiquibaseAutoConfiguration.class)
@Import({HotelServiceConfig.class, HotelWebConfig.class})
public class HotelWebApplication {
    public static void main(String[] args) {
        SpringApplication.run(HotelWebApplication.class);
    }
}
