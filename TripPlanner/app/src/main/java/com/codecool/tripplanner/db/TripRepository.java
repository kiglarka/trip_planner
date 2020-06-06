package com.codecool.tripplanner.db;

import android.content.Context;

import androidx.lifecycle.LiveData;

import java.util.List;

public class TripRepository {

    private TripDao tripDao;
    private LiveData<List<Trip>> allTrips;

    public TripRepository(Context context) {
        TripRoomDatabase db = TripRoomDatabase.getDatabase(context);
        tripDao = db.tripDao();
        allTrips = tripDao.getAlphabetizedTrips();
    }

    // Room executes all queries on a separate thread.
    // Observed LiveData will notify the observer when the data has changed.
    LiveData<List<Trip>> getAllTrips() {
        return allTrips;
    }

    // You must call this on a non-UI thread or your app will throw an exception. Room ensures
    // that you're not doing any long running operations on the main thread, blocking the UI.
    public void insert(Trip trip) {
        TripRoomDatabase.databaseWriteExecutor.execute(() -> {
            tripDao.insert(trip);
        });
    }


}
