package com.packs.counproc.MysqlServer.configurations;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef = "customeEntityManagerFactory",
        transactionManagerRef = "customeTransactionManager",
        basePackages = {"com.packs.counproc.MysqlServer.repositories"}
)
public class MysqlConfig {

    @Primary
    @Bean(name = "mysqlDS")
    @ConfigurationProperties(prefix = "spring.dsmysql")
    public DataSource customeDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Primary
    @Bean(name = "customeEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean
    entityManagerFactory(
            EntityManagerFactoryBuilder builder,
            @Qualifier("mysqlDS") DataSource dataSource
    ) {
        return builder
                .dataSource(dataSource)
                .packages("com.packs.counproc.MysqlServer.models")
                .persistenceUnit("db1")
                .build();
    }

    @Primary
    @Bean(name = "customeTransactionManager")
    public PlatformTransactionManager customeTransactionManager(
            @Qualifier("customeEntityManagerFactory") EntityManagerFactory customeEntityManagerFactory
    ) {
        return new JpaTransactionManager(customeEntityManagerFactory);
    }
}
