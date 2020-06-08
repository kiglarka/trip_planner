/*package com.codecool.tripplanner.db;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;

import com.codecool.tripplanner.db2.Trip;
import com.codecool.tripplanner.db2.TripRepository;

import java.util.List;

public class TripViewModel extends AndroidViewModel {

    private TripRepository mRepository;
    private List<Trip> allTrips;

    public TripViewModel (Application application) {
        super(application);
        mRepository = new TripRepository(application);
        allTrips = mRepository.getAllTrips();
    }

    public List<Trip> getAllTrips() { return allTrips; }

    public void insert(Trip trip) { mRepository.insert(trip); }
}

 */
