package org.example.repository;

import org.springframework.stereotype.Component;

@Component
public class DBClientImpl implements DBClient {
    private int remainingSeat = 3;

    @Override
    public int executeQuery(String s) {
        return remainingSeat--;
    }
}
