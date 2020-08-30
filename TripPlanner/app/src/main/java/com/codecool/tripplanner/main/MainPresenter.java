package com.codecool.tripplanner.main;

import android.content.Context;

import com.codecool.tripplanner.db2.RoomRepository;
import com.codecool.tripplanner.db2.Trip;
import com.codecool.tripplanner.newtrip.NewTripPresenter;

import java.util.List;

public class MainPresenter<V extends MainContract> {

    V view;
    RoomRepository roomRepository;
    private List<Trip> trips;

    public MainPresenter(Context context) {
        roomRepository = new RoomRepository(context);
    }

    public List<Trip> getTrips() {
        return roomRepository.getAllTrips();
    }

    void onAttach(V view) { this.view = view; }
    void onDetach() { this.view = null; }

}
