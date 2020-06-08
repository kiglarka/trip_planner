package com.codecool.tripplanner.newtrip;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.codecool.tripplanner.R;
import com.codecool.tripplanner.db2.Trip;

import butterknife.BindView;
import butterknife.ButterKnife;

// activity implements the contract
public class NewTripActivity extends AppCompatActivity implements NewTripContract {


    @BindView(R.id.add_city)
    EditText addCity;
    @BindView(R.id.add_country)
    EditText addCountry;
    @BindView(R.id.add_continent)
    EditText addContinent;
    @BindView(R.id.add_image)
    EditText addImage;

    @BindView(R.id.button_save)
    Button button;

    // initiate a presenter as a field as a brand new instance of presenter
    NewTripPresenter presenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_trip);
        ButterKnife.bind(this);

        presenter = new NewTripPresenter(this);
        presenter.onAttach(this);

        gatherUserInputUponClickOnSave();
    }

    private void gatherUserInputUponClickOnSave() {
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {

                String city = addCity.getText().toString();
                String continent = addContinent.getText().toString();
                String country = addCountry.getText().toString();
                String image = addImage.getText().toString();

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
