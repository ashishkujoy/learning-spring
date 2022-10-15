package org.example.service;

import org.example.model.BookingStatus;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service
//@Primary
@Qualifier("JSONReporter")
public class JsonReportingServiceImpl implements ReportingService {
    @Override
    public void report(BookingStatus status) {
        System.out.println("Reporting from the json service");
    }
}
