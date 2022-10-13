package org.example.service;

import org.example.model.Booking;
import org.example.model.BookingRequest;
import org.example.model.BookingStatus;
import org.example.repository.BookingRepository;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class TicketBookingService {
    private final BookingRepository bookingRepository;
    private final PaymentService paymentService;

    private final ReportingService reportingService;

    public TicketBookingService(BookingRepository bookingRepository, PaymentService paymentService, ReportingService reportingService) {
        this.bookingRepository = bookingRepository;
        this.paymentService = paymentService;
        this.reportingService = reportingService;
    }

    public Booking bookTicket(BookingRequest bookingRequest) {
        boolean isSeatAvailable = bookingRepository.hasSeatFrom(
                bookingRequest.sourceStation,
                bookingRequest.destinationStation
        );
        BookingStatus status = isSeatAvailable ? BookingStatus.CONFIRM : BookingStatus.RAC;

        this.paymentService.executePayment(new PaymentRequest(new BigDecimal(1200)));
        this.reportingService.report(status);

        return new Booking(status, "abcd");
    }
}
