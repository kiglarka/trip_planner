package com.codecool.tripplanner;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "trip_table")
public class Trip {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "City")
    private String city;
    private String continent;

    public Trip(String city, String continent) {
        this.city = city;
        this.continent = continent;
    }

    public String getCity() {
        return city;
    }
}
