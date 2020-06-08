/*package com.codecool.tripplanner.db2;

import android.content.Context;

import com.codecool.tripplanner.MainActivity;
import com.codecool.tripplanner.MapsActivity;

import java.util.List;

public class DraftRoomRepository {

    private TripDao tripDao = MainActivity.database.tripDao();
    private List<Trip> allTrips;

    public DraftRoomRepository(Context context) { allTrips = tripDao.getAlphabetizedTrips();}

// async tasks to implement here

    // /
    // !saveTrip is already implemented under newTripPresenter

    @Override
    public void saveTrip(Trip trip) {
        tripDao.insert(trip);
        /*
            TripRoomDatabase.databaseWriteExecutor.execute(() -> {
                tripDao.insert(trip);
            });


        }

    @Override
    public List<Trip> getAllTrips() {
        return allTrips;
    }

    @Override
    public void clearAllTrips() {
        tripDao.deleteAll();
    }


}

 */

