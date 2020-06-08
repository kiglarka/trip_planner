package com.codecool.tripplanner.db2;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.codecool.tripplanner.db2.Trip;

import java.util.List;

@Dao
public interface TripDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Trip trip);

    @Query("DELETE FROM trip_table")
    void deleteAll();

    @Query("SELECT * from trip_table ORDER BY city ASC")
    List<Trip> getAlphabetizedTrips();
}
