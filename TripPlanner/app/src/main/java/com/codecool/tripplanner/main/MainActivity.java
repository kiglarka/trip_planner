package com.codecool.tripplanner.main;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.codecool.tripplanner.R;
import com.codecool.tripplanner.databinding.ActivityMainBinding;
import com.codecool.tripplanner.db2.Trip;
import com.codecool.tripplanner.newtrip.NewTripActivity;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity implements MainContract {

    private RecyclerViewAdapter adapter;
    MainPresenter<MainActivity> presenter;
    private List<Trip> trips;
    private ActivityMainBinding binding;

    private static final int ERROR_DIALOG_REQUEST = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new MainPresenter<MainActivity>(this);
        presenter.onAttach(this);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main);
        addClickListenerToFloatingButton();
    }

    @Override
    protected void onResume() {
        super.onResume();
        adapter = new RecyclerViewAdapter(this, trips);
        binding.recyclerview.setLayoutManager(new LinearLayoutManager(this));
        binding.recyclerview.setAdapter(adapter);
        trips = presenter.getTrips();
        defaultView();
        adapter.setWords(trips);
    }

    private void defaultView() {
        try {
            TimeUnit.SECONDS.sleep(2);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (trips.size() == 0) binding.statusMessage.setText(R.string.no_trips);
        else {
            binding.statusMessage.setVisibility(View.GONE);
            binding.loadingPanel.setVisibility(View.GONE);
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

    @Override
    public void openMaps(String uri) {
        Intent mapIntent = new Intent(Intent.ACTION_VIEW);
        mapIntent.setData(Uri.parse(uri));
        if (isServicesOK()) {
            startActivity(mapIntent);
        }
    }
}