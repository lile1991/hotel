package com.hotel.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "alibaba.druid")
public class DruidProperties {
    private String url;
    private String username;
    private String password;
    private String driverClassName;
    private long timeBetweenEvictionRunsMillis;
    private int initialSize;
    private int minIdle;
    private int maxActive;
    private boolean testOnBorrow;
}
