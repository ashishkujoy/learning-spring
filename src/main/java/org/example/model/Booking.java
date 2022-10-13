package org.example.model;

public class Booking {
    final BookingStatus status;
    final String pnr;

    public Booking(BookingStatus status, String pnr) {
        this.status = status;
        this.pnr = pnr;
    }

    @Override
    public String toString() {
        return "Booking{" +
                "status=" + status +
                ", pnr='" + pnr + '\'' +
                '}';
    }

}
