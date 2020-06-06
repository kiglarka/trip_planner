package com.codecool.tripplanner.newtrip;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.codecool.tripplanner.R;
import com.codecool.tripplanner.Trip;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NewTripActivity extends AppCompatActivity implements NewTripContract {


    @BindView(R.id.add_city)
    EditText addCity;
    @BindView(R.id.add_country)
    EditText addCountry;
    @BindView(R.id.add_continent)
    EditText addContinent;
    @BindView(R.id.add_image)
    EditText addImage;

    NewTripPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_trip);
        ButterKnife.bind(this);

        presenter = new NewTripPresenter(this);
        presenter.onAttach(this);


        final Button button = findViewById(R.id.button_save);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {

                String city = addCity.getText().toString();
                String continent = addContinent.getText().toString();
                String country = addCountry.getText().toString();
                String image = addImage.getText().toString();

                Trip trip = new Trip(city, continent,country, image);
                presenter.saveTrip(trip);
            }


        });
    }

    @Override
    public void successfullySaved() {
        Toast.makeText(this, "Successfully saved",Toast.LENGTH_SHORT).show();
        finish();
    }
}
