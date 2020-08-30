package com.codecool.tripplanner.main;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.codecool.tripplanner.R;
import com.codecool.tripplanner.databinding.ActivityMainBinding;
import com.codecool.tripplanner.db2.RoomRepository;
import com.codecool.tripplanner.db2.Trip;
import com.codecool.tripplanner.newtrip.NewTripActivity;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    private RecyclerViewAdapter adapter;
    private List<Trip> trips;
    private RoomRepository roomRepository;
    private ActivityMainBinding binding;

    private static final int ERROR_DIALOG_REQUEST = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main);
        //ButterKnife.bind(this);
        roomRepository = new RoomRepository(this);
        trips = roomRepository.getAllTrips();

        adapter = new RecyclerViewAdapter(trips);
        binding.recyclerview.setLayoutManager(new LinearLayoutManager(this));
        binding.recyclerview .setAdapter(adapter);


        defaultView();
        adapter.setWords(trips);

        addClickListenerToFloatingButton();

    }

    @Override
    protected void onResume() {
        super.onResume();
        adapter.setWords(roomRepository.getAllTrips());

    }

    private void defaultView() {
        binding.loadingPanel.setVisibility(View.GONE);

        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (trips.size() == 0) binding.statusMessage.setText(R.string.no_trips);
        else {
            binding.statusMessage.setVisibility(View.GONE);
            //adapter.setWords(trips);
        }
    }



    private void addClickListenerToFloatingButton() {
        binding.fab.setOnClickListener(new View.OnClickListener() {
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