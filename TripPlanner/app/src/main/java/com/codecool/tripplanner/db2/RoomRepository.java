package com.codecool.tripplanner.db2;

import android.content.Context;
import android.os.AsyncTask;

import com.codecool.tripplanner.main.MainActivity;
import com.codecool.tripplanner.db2.Trip;
import com.codecool.tripplanner.db2.TripDao;
import com.codecool.tripplanner.db2.TripRoomDatabase;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class
RoomRepository implements TripRepository {

    TripRoomDatabase db;

    public RoomRepository(Context context) {
        db = TripRoomDatabase.getDatabase(context);
    }

    public List<Trip> getAllTrips() {
        try {
            return new GetTripsAsyncTask().execute().get();
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    private class GetTripsAsyncTask extends AsyncTask<Void,Void,List<Trip>>{

        @Override
        protected List<Trip> doInBackground(Void... voids) {
            return db.tripDao().getAlphabetizedTrips();
        }
    }

    @Override
    public void clearAllTrips() {
        if (db.tripDao().getAlphabetizedTrips() != null){
            db.tripDao().deleteAll();
        }
    }

    // You must call this on a non-UI thread or your app will throw an exception. Room ensures
    // that you're not doing any long running operations on the main thread, blocking the UI.
    @Override
    public void insert(Trip trip) {
        TripRoomDatabase.databaseWriteExecutor.execute(() -> {
            db.tripDao().insert(trip);
        });
    }


}

