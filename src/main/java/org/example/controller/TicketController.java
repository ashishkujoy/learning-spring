package org.example.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.exception.DBConnectionError;
import org.example.model.Booking;
import org.example.model.BookingRequest;
import org.example.service.TicketBookingService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * POST /api/ticket/book
 * BODY {"sourceStation": "Delhi", "destinationStation": "PUNE"}
 * <p>
 * <p>
 * HTTP STATUS 200
 * RESPONSE BODY {"status": "CONFIRMED", "pnr": "abcd"}
 */

@RestController
@RequestMapping("/api/ticket")
public class TicketController {
    private final TicketBookingService ticketBookingService;
    private final ObjectMapper objectMapper;

    public TicketController(TicketBookingService ticketBookingService, ObjectMapper objectMapper) {
        this.ticketBookingService = ticketBookingService;
        this.objectMapper = objectMapper;
    }

    @PostMapping(value = "/book", produces = "application/json")
    public ResponseEntity<String> bookTicket(@RequestBody BookingRequest bookingRequest) throws Exception {
        Booking booking = this.ticketBookingService.bookTicket(bookingRequest);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("content-type", "application/json");
        httpHeaders.add("ping", "pong");
        return new ResponseEntity<>(objectMapper.writeValueAsString(booking), httpHeaders, HttpStatus.OK);
    }

    @GetMapping(value = "/{pnrNumber}/status")
    public ResponseEntity<Booking> getStatus(@PathVariable String pnrNumber) {
        Booking booking = this.ticketBookingService.getBookingDetails(pnrNumber);
        if (booking == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok().body(booking);
        }
    }

}
