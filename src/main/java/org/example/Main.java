package org.example;

import org.example.model.Booking;
import org.example.model.BookingRequest;
import org.example.service.TicketBookingService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
@ConfigurationPropertiesScan(value = "org.example.config")
public class Main {
    public static void main(String[] args) {
        ApplicationContext applicationContext = SpringApplication.run(Main.class);

        TicketBookingService ticketBookingService = applicationContext.getBean(TicketBookingService.class);
        BookingRequest bookingRequest = new BookingRequest("Delhi", "Pune");

        Booking booking = ticketBookingService.bookTicket(bookingRequest);

        System.out.println("=======================");
        System.out.println(booking);
        System.out.println("=======================");
    }
}