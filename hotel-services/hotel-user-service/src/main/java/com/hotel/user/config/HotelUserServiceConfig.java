package com.hotel.user.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = "com.hotel.user.dao")
@EntityScan(basePackages = "com.hotel.user.entity")
@ComponentScan(basePackages = "com.hotel.user.service")
//@EnableConfigurationProperties(DataSourceProperties.class)
//@EnableTransactionManagement
public class HotelUserServiceConfig {

    /*@Bean
    @ConditionalOnClass(SimpleDriverDataSource.class)
    public DataSource createDataSource(DataSourceProperties dataSourceProperties) throws ClassNotFoundException {
        SimpleDriverDataSource dataSource = new SimpleDriverDataSource();
        dataSource.setDriverClass((Class<? extends Driver>) Class.forName(dataSourceProperties.getDriverClassName()));
        dataSource.setUrl(dataSourceProperties.getUrl());
        dataSource.setUsername(dataSourceProperties.getUsername());
        dataSource.setPassword(dataSourceProperties.getPassword());
        return dataSource;
    }

    @Bean
    public SessionFactory sessionFactory(EntityManagerFactory emf) {
        return emf.unwrap(SessionFactory.class);
    }*/

}
