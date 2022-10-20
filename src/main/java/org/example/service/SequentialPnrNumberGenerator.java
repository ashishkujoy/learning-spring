package org.example.service;

import org.springframework.stereotype.Component;

@Component
public class SequentialPnrNumberGenerator implements PnrNumberGenerator {
    private int counter = 0;

    @Override
    public String generatePnrNumber() {
        return "abcd-" + counter++;
    }
}
