package com.codecool.tripplanner.db2;

import android.content.Context;

import com.codecool.tripplanner.db2.Trip;
import com.codecool.tripplanner.db2.TripDao;
import com.codecool.tripplanner.db2.TripRoomDatabase;

import java.util.List;

public class RoomRepository implements TripRepository {

    private TripDao tripDao;
    private List<Trip> allTrips;

    public RoomRepository(Context context) {
        TripRoomDatabase db = TripRoomDatabase.getDatabase(context);
        tripDao = db.tripDao();
        allTrips = tripDao.getAlphabetizedTrips();
    }



    // Room executes all queries on a separate thread.
    // Observed LiveData will notify the observer when the data has changed.
    public List<Trip> getAllTrips() {
        return allTrips;
    }

    @Override
    public void clearAllTrips() {
        if (allTrips != null){
            tripDao.deleteAll();
        }
    }

    // You must call this on a non-UI thread or your app will throw an exception. Room ensures
    // that you're not doing any long running operations on the main thread, blocking the UI.
    public void insert(Trip trip) {
        TripRoomDatabase.databaseWriteExecutor.execute(() -> {
            tripDao.insert(trip);
        });
    }


}

