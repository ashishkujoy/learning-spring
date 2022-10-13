package org.example.model;

final public class BookingRequest {
    public final String sourceStation;
    public String destinationStation;

    public BookingRequest(String sourceStation, String destinationStation) {
        this.sourceStation = sourceStation;
        this.destinationStation = destinationStation;
    }
}
