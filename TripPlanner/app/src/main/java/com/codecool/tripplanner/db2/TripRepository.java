package com.codecool.tripplanner.db2;

import java.util.List;

public interface TripRepository {
    void insert(Trip trip);
    List<Trip> getAllTrips();
    void clearAllTrips();
}
