package org.example.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.model.Booking;
import org.example.model.BookingStatus;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
class StayBookingControllerTest {
    private final MockMvc mockMvc;
    private final ObjectMapper objectMapper;

    @Autowired
    StayBookingControllerTest(MockMvc mockMvc, ObjectMapper objectMapper) {
        this.mockMvc = mockMvc;
        this.objectMapper = objectMapper;
    }

    @Test
    void handleErrorForDBConnectionFailureOnRetiringRoomBooking() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/api/retiring-room")
                .content(objectMapper.writeValueAsString(new Booking(BookingStatus.RAC, "abcd")))
                .header("content-type", "application/json")
        ).andExpectAll(
                MockMvcResultMatchers.status().is5xxServerError(),
                MockMvcResultMatchers.content().json("{\"message\": \"Temporary Error, Try again in sometime\"}")
        );

    }
}