package com.codecool.tripplanner;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class NewTripActivity extends AppCompatActivity {


    public static final String EXTRA_CITY = "com.example.android.wordlistsql.REPLY";
    public static final String EXTRA_CONTINENT = "com.example.android.wordlistsql.REPLY1";
    public static final String EXTRA_COUNTRY = "com.example.android.wordlistsql.REPLY2";

    private EditText addCity;
    private EditText addCountry;
    private EditText addContinent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_trip);

        addCity = findViewById(R.id.add_city);
        addContinent = findViewById(R.id.add_continent);
        addCountry = findViewById(R.id.add_country);


        final Button button = findViewById(R.id.button_save);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent replyIntent = new Intent();
                if (TextUtils.isEmpty(addCity.getText())) {
                    setResult(RESULT_CANCELED, replyIntent);
                } else {
                    String city = addCity.getText().toString();
                    String continent = addContinent.getText().toString();
                    String country = addCountry.getText().toString();

                    replyIntent.putExtra(EXTRA_CITY, city);
                    replyIntent.putExtra(EXTRA_CONTINENT, continent);
                    replyIntent.putExtra(EXTRA_COUNTRY, country);
                    setResult(RESULT_OK, replyIntent);
                }
                finish();
            }
        });
    }

}
