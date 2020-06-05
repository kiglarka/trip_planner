package com.codecool.tripplanner;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    private TripViewModel tripViewModel;
    private RecyclerView recyclerView;

    public static final int NEW_WORD_ACTIVITY_REQUEST_CODE = 1;
    private static final int ERROR_DIALOG_REQUEST = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerview);
        final RecyclerViewAdapter adapter = new RecyclerViewAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        loadTripsToAdapter(adapter);
        addClickListenerToFloatingButton();

    }

    private void loadTripsToAdapter(RecyclerViewAdapter adapter) {
        tripViewModel = new ViewModelProvider(this).get(TripViewModel.class);
        tripViewModel.getAllTrips().observe(this, new Observer<List<Trip>>() {
            @Override
            public void onChanged(@Nullable final List<Trip> trips) {
                findViewById(R.id.loadingPanel).setVisibility(View.GONE);
                TextView status_message = findViewById(R.id.status_message);
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
        });
    }

    public void init(View view) {
        if (isServicesOK()){
            Intent intent = new Intent(MainActivity.this, MapsActivity.class);
            startActivity(intent);
        }
    }


    private void addClickListenerToFloatingButton() {
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, NewTripActivity.class);
                startActivityForResult(intent, NEW_WORD_ACTIVITY_REQUEST_CODE);
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




    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == NEW_WORD_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK) {
            Trip trip = new Trip(Objects.requireNonNull(data.getStringExtra(NewTripActivity.EXTRA_CITY)), data.getStringExtra(NewTripActivity.EXTRA_CONTINENT), data.getStringExtra(NewTripActivity.EXTRA_COUNTRY), data.getStringExtra(NewTripActivity.EXTRA_IMAGE));
            tripViewModel.insert(trip);
        } else {
            Toast.makeText(
                    getApplicationContext(),
                    R.string.empty_not_saved,
                    Toast.LENGTH_LONG).show();
        }
    }

    public void initiate(View view) {
    }
}