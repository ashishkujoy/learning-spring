package org.example.service;

import org.example.model.Booking;
import org.example.model.BookingRequest;
import org.example.model.BookingStatus;
import org.example.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.math.BigDecimal;

//@Component
@Service
public class TicketBookingService {
    private final BookingRepository bookingRepository;
    private final PaymentService paymentService;

    private final ReportingService reportingService;
    private final String maxBookingAllowed;

    public TicketBookingService(BookingRepository bookingRepository,
                                PaymentService paymentService,
                                @Qualifier("JSONReporter")
                                ReportingService reportingService,
                                @Value("${app.ticket-service.max-booking-allowed}")
                                String maxBookingAllowed) {
        this.bookingRepository = bookingRepository;
        this.paymentService = paymentService;
        this.reportingService = reportingService;
        this.maxBookingAllowed = maxBookingAllowed;
    }

    public Booking bookTicket(BookingRequest bookingRequest) {
        boolean isSeatAvailable = bookingRepository.hasSeatFrom(
                bookingRequest.sourceStation,
                bookingRequest.destinationStation
        );
        BookingStatus status = isSeatAvailable ? BookingStatus.CONFIRM : BookingStatus.RAC;
        System.out.println("---------->>>>>>" + this.maxBookingAllowed);
        this.paymentService.executePayment(new PaymentRequest(new BigDecimal(1200)));
        this.reportingService.report(status);

        return new Booking(status, "abcd");
    }

    @PostConstruct
    public void onCreation() {
        System.out.println("========> Bean creation of ticket booking service");
    }


    @PreDestroy
    public void onDestroy() {
        System.out.println("========> Bean destruction of ticket booking service");
    }

}
