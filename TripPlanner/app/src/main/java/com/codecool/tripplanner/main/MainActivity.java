package com.codecool.tripplanner;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.app.Dialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.codecool.tripplanner.db2.RoomRepository;
import com.codecool.tripplanner.db2.Trip;
import com.codecool.tripplanner.db2.TripRoomDatabase;
import com.codecool.tripplanner.newtrip.NewTripActivity;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {


    // private TripViewModel tripViewModel;

    private List<Trip> trips;
    private TripRoomDatabase database;
    private RoomRepository roomRepository;

    @BindView(R.id.fab)
    FloatingActionButton fab;
    @BindView(R.id.recyclerview)
    RecyclerView recyclerView;
    @BindView(R.id.loadingPanel)
    RelativeLayout loadingPanel;
    @BindView(R.id.status_message)
    TextView status_message;


    private static final int ERROR_DIALOG_REQUEST = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);


        final com.codecool.tripplanner.RecyclerViewAdapter adapter = new com.codecool.tripplanner.RecyclerViewAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        //database = TripRoomDatabase.getDatabase(this);
        RoomRepository roomRepository = new RoomRepository(this);
        List<Trip> trips = roomRepository.getAllTrips();
        adapter.setWords(trips);



        //loadTripsToAdapter(adapter);
        addClickListenerToFloatingButton();

    }


    /*
    private void loadTripsToAdapter(RecyclerViewAdapter adapter) {
        database = Room.databaseBuilder(this, TripRoomDatabase.class, "trip_database").build();
        defaultView((List<Trip>) database, adapter);
    }

     */


    private void defaultView(@Nullable List<Trip> trips, com.codecool.tripplanner.RecyclerViewAdapter adapter) {
        loadingPanel.setVisibility(View.GONE);

        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (trips.size() == 0) status_message.setText(R.string.no_trips);
        else {
            status_message.setVisibility(View.GONE);
            adapter.setWords(trips);
        }
    }



    private void addClickListenerToFloatingButton() {
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, NewTripActivity.class);
                startActivity(intent);
            }
        });
    }


    public boolean isServicesOK() {
        int available = GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(MainActivity.this);
        if (available == ConnectionResult.SUCCESS) {
            return true;
        } else if (GoogleApiAvailability.getInstance().isUserResolvableError(available)) {
            Dialog dialog = GoogleApiAvailability.getInstance().getErrorDialog(MainActivity.this, available, ERROR_DIALOG_REQUEST);
            dialog.show();

        } else {
            Toast.makeText(this, "You can't make maps request", Toast.LENGTH_SHORT).show();
        }
        return false;
    }

}