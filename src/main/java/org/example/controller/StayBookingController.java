package org.example.controller;

import org.example.exception.DBConnectionError;
import org.example.model.Booking;
import org.example.model.BookingStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/retiring-room")
public class StayBookingController {

    @PostMapping
    public Booking bookRetiringRoom(@RequestBody Booking booking) throws Exception {
        if (booking.getStatus() != BookingStatus.CONFIRM) {
            throw new DBConnectionError();
        } else {
            return booking;
        }
    }
}
