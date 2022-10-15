package org.example.service;

import org.example.model.BookingStatus;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
public class ConsoleReportingServiceImpl implements ReportingService {
    @Override
    public void report(BookingStatus status) {
        System.out.println("Reporting from the console service");
        System.out.println(status);
        System.out.println("------------->");
    }
}
