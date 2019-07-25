package com.packs.counproc.MongoServer.configurations;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
@EnableMongoRepositories(basePackages = {"com.packs.counproc.MongoServer.repositories"})
public class MongoConfig {

    @Bean(name = "mongoDS")
    @ConfigurationProperties(prefix = "spring.dsmongo")
    public DataSource dataSource() {
        return DataSourceBuilder.create().build();
    }
}
