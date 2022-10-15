package org.example.repository;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

//@Component
@Repository
public class BookingRepositoryImpl implements BookingRepository {
    private final DBClient dbClient;

    public BookingRepositoryImpl(DBClient dbClient) {
        this.dbClient = dbClient;
    }

    @Override
    public boolean hasSeatFrom(String sourceStation, String destinationStation) {
        int availableSeatCount = dbClient.executeQuery("select count(*) from seats where status = available");

        return availableSeatCount > 0;
    }
}
