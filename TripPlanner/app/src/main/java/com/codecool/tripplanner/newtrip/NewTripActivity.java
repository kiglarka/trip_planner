package com.codecool.tripplanner.newtrip;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.codecool.tripplanner.R;
import com.codecool.tripplanner.Trip;

public class NewTripActivity extends AppCompatActivity implements NewTripContract {


    public static final String EXTRA_CITY = "com.example.android.wordlistsql.REPLY";
    public static final String EXTRA_CONTINENT = "com.example.android.wordlistsql.REPLY1";
    public static final String EXTRA_COUNTRY = "com.example.android.wordlistsql.REPLY2";
    public static final String EXTRA_IMAGE = "com.example.android.wordlistsql.REPLY3";

    private EditText addCity;
    private EditText addCountry;
    private EditText addContinent;
    private EditText addImage;

    NewTripPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_trip);

        addCity = findViewById(R.id.add_city);
        addContinent = findViewById(R.id.add_continent);
        addCountry = findViewById(R.id.add_country);
        addImage = findViewById(R.id.add_image);

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


                /*
                Intent replyIntent = new Intent();
                if (TextUtils.isEmpty(addCity.getText())) {
                    setResult(RESULT_CANCELED, replyIntent);
                } else {
                    String city = addCity.getText().toString();
                    String continent = addContinent.getText().toString();
                    String country = addCountry.getText().toString();
                    String image = addImage.getText().toString();

                    replyIntent.putExtra(EXTRA_CITY, city);
                    replyIntent.putExtra(EXTRA_CONTINENT, continent);
                    replyIntent.putExtra(EXTRA_COUNTRY, country);
                    replyIntent.putExtra(EXTRA_IMAGE, image);
                    setResult(RESULT_OK, replyIntent);
                }
                finish();
                */
            }


        });
    }

    @Override
    public void successfullySaved() {
        Toast.makeText(this, "Successfully saved",Toast.LENGTH_SHORT).show();
        finish();
    }
}
