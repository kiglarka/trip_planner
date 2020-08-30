package com.codecool.tripplanner.newtrip;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.codecool.tripplanner.R;
import com.codecool.tripplanner.databinding.ActivityNewTripBinding;
import com.codecool.tripplanner.db2.Trip;

public class NewTripActivity extends AppCompatActivity implements NewTripContract {

    ActivityNewTripBinding binding;
    NewTripPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_new_trip);

        presenter = new NewTripPresenter(this);
        presenter.onAttach(this);

        gatherUserInputUponClickOnSave();
    }

    private void gatherUserInputUponClickOnSave() {
        binding.buttonSave.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {

                String city = binding.addCity.getText().toString();
                String continent =  binding.addContinent.getText().toString();
                String country =  binding.addCountry.getText().toString();
                String image =  binding.addImage.getText().toString();

                Trip trip = new Trip(city, continent,country, image);
                presenter.insert(trip);
            }

        });
    }

    @Override
    public void successfullySaved() {
        Toast.makeText(this, "Successfully saved",Toast.LENGTH_SHORT).show();
        finish();
    }
}
