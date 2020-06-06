package com.codecool.tripplanner.newtrip;

import android.content.Context;

import com.codecool.tripplanner.Trip;
import com.codecool.tripplanner.TripRepository;
import com.codecool.tripplanner.newtrip.NewTripContract;

public class NewTripPresenter<V extends NewTripContract> {

    V view;
    TripRepository tripRepository;

    public NewTripPresenter(Context context) {
        tripRepository = new TripRepository(context);
    }

    void onAttach(V view){
        this.view = view;
    }
    void onDetach(){
        this.view = null;
    }

    void saveTrip(Trip trip){
        tripRepository.insert(trip);
        view.successfullySaved();
    }
}