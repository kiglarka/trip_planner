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

    private String continent;
    private String country;
    private String image;

    //private String imgUrl;

    public Trip(@NonNull String city, String continent, String country, String image) {
        this.city = city;
        this.continent = continent;
        this.country = country;
        this.image = image;
    }


    @NonNull
    public String getCity() {
        return city;
    }


    public String getContinent() {
        return continent;
    }


    public String getCountry() {
        return country;
    }

    public String getImage() {
        return image;
    }
}
