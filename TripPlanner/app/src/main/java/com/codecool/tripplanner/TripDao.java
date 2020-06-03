package com.codecool.tripplanner;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface TripDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void inserts (Trip trip);

    @Query("SELECT * from trip_table ORDER BY city ASC")
    List<Trip> getAlphabetizedWords();
}
