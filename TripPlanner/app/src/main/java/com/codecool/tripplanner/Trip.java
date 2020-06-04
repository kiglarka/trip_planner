package com.codecool.tripplanner;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "trip_table")
public class Trip {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "city")
    private String city;

    //private String continent;
    //private String imgUrl;

    public Trip(@NonNull String city) {
        this.city = city;
    }

    @NonNull
    public String getCity() {
        return city;
    }

}
