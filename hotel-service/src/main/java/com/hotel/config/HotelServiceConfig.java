package com.hotel.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = "com.hotel.dao")
@ComponentScan(basePackages = "com.hotel.service")
public class HotelServiceConfig {

}
