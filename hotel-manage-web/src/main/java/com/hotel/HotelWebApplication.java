package com.hotel;

import com.hotel.config.HotelWebConfig;
import com.hotel.config.ShiroConfig;
import com.hotel.room.config.HotelRoomServiceConfig;
import com.hotel.user.config.HotelUserServiceConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.autoconfigure.liquibase.LiquibaseAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication(exclude = {LiquibaseAutoConfiguration.class})
@Import({HotelRoomServiceConfig.class, HotelUserServiceConfig.class, HotelWebConfig.class, ShiroConfig.class})
@EnableConfigurationProperties(DataSourceProperties.class)
@EnableTransactionManagement
public class HotelWebApplication {
    public static void main(String[] args) {
        SpringApplication.run(HotelWebApplication.class);
    }
}
