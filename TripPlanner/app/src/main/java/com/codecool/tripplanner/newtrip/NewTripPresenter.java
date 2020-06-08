package com.codecool.tripplanner.newtrip;

import android.content.Context;
import android.os.AsyncTask;

import com.codecool.tripplanner.db2.RoomRepository;
import com.codecool.tripplanner.db2.Trip;
import com.codecool.tripplanner.db2.TripRepository;


// presenter<V extends contract>
public class NewTripPresenter<V extends NewTripContract> {

    // initialize V view which will be created attached to actual view upon attach or will be set to null upon detach
    V view;
    //  initiate tripRepository (create db) and create a brand new instance in the constructor
    RoomRepository roomRepository;

    public NewTripPresenter(Context context) {
        roomRepository = new RoomRepository(context);
    }

    void onAttach(V view){
        this.view = view;
    }
    void onDetach(){
        this.view = null;
    }

    // to save data, create a new async task

    void insert(Trip trip){
        new SaveTripAsyncTask().execute(trip);
        //tripRepository.insert(trip);
        //view.successfullySaved();
    }

    private class SaveTripAsyncTask extends AsyncTask<Trip,Void,Void> {

        @Override
        protected Void doInBackground(Trip... trips) {
            roomRepository.insert(trips[0]);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            view.successfullySaved();
        }
    }
}
