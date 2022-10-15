package org.example.config;

import org.example.repository.DBClient;
import org.example.repository.DBClientImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CustomBean {

    @Bean
    public DBClient createDBClient(DBConfig config) {
        return new DBClientImpl(config);
    }
}
