package org.example.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.model.Booking;
import org.example.model.BookingRequest;
import org.example.model.BookingStatus;
import org.example.service.PnrNumberGenerator;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
class TicketControllerTest {

    private final MockMvc mockMvc;
    private final ObjectMapper objectMapper;

    @MockBean
    private PnrNumberGenerator pnrNumberGenerator;

    @Autowired
    TicketControllerTest(MockMvc mockMvc, ObjectMapper objectMapper) {
        this.mockMvc = mockMvc;
        this.objectMapper = objectMapper;
    }

    @Test
    void createBookingForGivenBookingRequest() throws Exception {
        BookingRequest bookingRequest = new BookingRequest("Delhi", "Pune");
        Booking booking = new Booking(BookingStatus.CONFIRM, "abcd-0");

        Mockito.when(pnrNumberGenerator.generatePnrNumber()).thenReturn("abcd-0");

        mockMvc.perform(MockMvcRequestBuilders
                .post("/api/ticket/book")
                .content(objectMapper.writeValueAsBytes(bookingRequest))
                .header("content-type","application/json")
        )
                .andExpectAll(
                        MockMvcResultMatchers.status().is2xxSuccessful(),
                        MockMvcResultMatchers.content().json(objectMapper.writeValueAsString(booking))
                );
    }

    @Test
    void getBookingStatusForGivenPnr() throws Exception {
        BookingRequest bookingRequest = new BookingRequest("Delhi", "Pune");
        Booking booking = new Booking(BookingStatus.CONFIRM, "abcd-0");

        Mockito.when(pnrNumberGenerator.generatePnrNumber()).thenReturn("abcd-0");

        mockMvc.perform(MockMvcRequestBuilders
                .post("/api/ticket/book")
                .content(objectMapper.writeValueAsBytes(bookingRequest))
                .header("content-type","application/json")
        );

        mockMvc.perform(
                MockMvcRequestBuilders.get("/api/ticket/abcd-0/status")
        ).andExpectAll(
                MockMvcResultMatchers.status().is2xxSuccessful(),
                MockMvcResultMatchers.content().json(objectMapper.writeValueAsString(booking)),
                MockMvcResultMatchers.header().string("content-type", "application/json")
        );
    }

    @Test
    void giveNotFoundForUnknownPnr() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.get("/api/ticket/abcd-unknown/status")
        ).andExpectAll(
                MockMvcResultMatchers.status().is4xxClientError()
        );
    }

    @Test
    void handleDBConnectionException() throws Exception {
        BookingRequest bookingRequest = new BookingRequest("Unknown", "Pune");

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/api/ticket/book")
                        .content(objectMapper.writeValueAsBytes(bookingRequest))
                        .header("content-type","application/json")
                )
                .andExpectAll(
                        MockMvcResultMatchers.status().is5xxServerError(),
                        MockMvcResultMatchers.content().json("{\"message\": \"Temporary Error, Try again in sometime\"}")
                );
    }
}