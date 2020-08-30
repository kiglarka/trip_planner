package com.codecool.tripplanner.newtrip;

import android.content.Context;
import android.os.AsyncTask;

import com.codecool.tripplanner.db2.RoomRepository;
import com.codecool.tripplanner.db2.Trip;

public class NewTripPresenter<V extends NewTripContract> {

    V view;
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

    void insert(Trip trip){
        new SaveTripAsyncTask().execute(trip);
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
