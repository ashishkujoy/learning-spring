package org.example.repository;

public interface BookingRepository {
    boolean hasSeatFrom(String sourceStation, String destinationStation);
}
