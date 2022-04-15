package com.jmunoz.comicsform.AppComicsFormulario.config;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.sql.Connection;
import java.sql.SQLException;

@Configuration
public class ConexionBaseDatos {

    @Value("${jdbc.driver}")
    private String driver;
    @Value("${jdbc.url}")
    private String url;
    @Value("${jdbc.user}")
    private String username;
    @Value("${jdbc.password}")
    private String password;

    @Value("${connection.pool.initialPoolSize}")
    private int initialPoolSize;

    @Value("${connection.pool.minIdleSize}")
    private int minIdleSize;

    @Value("${connection.pool.maxIdleSize}")
    private int maxIdleSize;

    @Value("${connection.pool.maxPoolSize}")
    private int maxPoolSize;

    @Bean
    public BasicDataSource getInstance() {
        BasicDataSource pool = new BasicDataSource();
        pool.setDriverClassName(driver);
        pool.setUrl(url);
        pool.setUsername(username);
        pool.setPassword(password);

        pool.setInitialSize(initialPoolSize);
        pool.setMinIdle(minIdleSize);
        pool.setMaxIdle(maxIdleSize);
        pool.setMaxTotal(maxPoolSize);

        return pool;
    }

    @Bean
    public Connection getConnection() throws SQLException {
        return getInstance().getConnection();
    }
}
