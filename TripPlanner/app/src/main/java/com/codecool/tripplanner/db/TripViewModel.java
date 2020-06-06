package com.codecool.tripplanner.db;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class TripViewModel extends AndroidViewModel {

    private TripRepository mRepository;
    private LiveData<List<Trip>> allTrips;

    public TripViewModel (Application application) {
        super(application);
        mRepository = new TripRepository(application);
        allTrips = mRepository.getAllTrips();
    }

    public LiveData<List<Trip>> getAllTrips() { return allTrips; }

    public void insert(Trip trip) { mRepository.insert(trip); }
}
