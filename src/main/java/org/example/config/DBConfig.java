package org.example.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

@ConfigurationProperties(value = "app.db")
@ConstructorBinding
public class DBConfig {
    public final String connectionUrl;
    public final Integer maxIdleTimeout;

    public DBConfig(String connectionUrl, Integer maxIdleTimeout) {
        this.connectionUrl = connectionUrl;
        this.maxIdleTimeout = maxIdleTimeout;
    }
}
