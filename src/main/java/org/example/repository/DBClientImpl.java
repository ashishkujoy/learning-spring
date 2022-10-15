package org.example.repository;

import org.example.config.DBConfig;

public class DBClientImpl implements DBClient {
    private final DBConfig dbConfig;
    private int remainingSeat = 3;

    public DBClientImpl(DBConfig dbConfig) {
        this.dbConfig = dbConfig;
    }

    @Override
    public int executeQuery(String s) {
        return remainingSeat--;
    }

//    @PostConstruct
//    public void createDBConnection() {
//        System.out.println("------> Creating db connection on bean creation");
//        System.out.println("Connection will be done at " + this.dbConfig.connectionUrl);
//        System.out.println("Connection max idle timeout " + this.dbConfig.maxIdleTimeout);
//    }
//
//    @PreDestroy
//    public void closeDBConnection() {
//        System.out.println("------> Closing db connection on bean destruction");
//    }
}
