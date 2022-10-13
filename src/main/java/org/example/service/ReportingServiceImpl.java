package org.example.service;

import org.example.model.BookingStatus;
import org.springframework.stereotype.Component;

@Component
public class ReportingServiceImpl implements ReportingService {
    @Override
    public void report(BookingStatus status) {
        System.out.println("------------->");
        System.out.println(status);
        System.out.println("------------->");
    }
}
