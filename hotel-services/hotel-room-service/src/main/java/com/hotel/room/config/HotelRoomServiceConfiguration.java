package com.hotel.room.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = "com.hotel.room.dao")
@EntityScan(basePackages = "com.hotel.room.entity")
@ComponentScan(basePackages = "com.hotel.room.service")
public class HotelRoomServiceConfiguration {

    /*@Bean
    @ConditionalOnClass(SimpleDriverDataSource.class)
    public DataSource dataSource(DataSourceProperties dataSourceProperties) throws ClassNotFoundException {
        SimpleDriverDataSource dataSource = new SimpleDriverDataSource();
        dataSource.setDriverClass((Class<? extends Driver>) Class.forName(dataSourceProperties.getDriverClassName()));
        dataSource.setUrl(dataSourceProperties.getUrl());
        dataSource.setUsername(dataSourceProperties.getUsername());
        dataSource.setPassword(dataSourceProperties.getPassword());
        return dataSource;
    }*/

    // 其中 dataSource 框架会自动为我们注入
    /*@Bean
    @ConditionalOnBean(DataSource.class)
    public PlatformTransactionManager transactionManager(@Qualifier("dataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }*/

    /*@Bean
    public SessionFactory sessionFactory(EntityManagerFactory emf) {
        return emf.unwrap(SessionFactory.class);
    }*/

}
