package com.codecool.tripplanner;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    /*
    RecyclerView recyclerView = findViewById(R.id.recyclerview);
final WordListAdapter adapter = new WordListAdapter(this);
recyclerView.setAdapter(adapter);
recyclerView.setLayoutManager(new LinearLayoutManager(this));
     */

    private TripViewModel tripViewModel;


    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "onCreate: started ");


        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        final RecyclerViewAdapter adapter = new RecyclerViewAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }


    /*
    private void initImageBitmaps(){
        Log.d(TAG, "initImageBitmaps: preparing bitmaps");
        imageUrls.add("https://africana.arizona.edu/sites/africana.arizona.edu/files/Eiffel-Tower-Paris-France.jpg");
        names.add("Paris");

        imageUrls.add("https://img.washingtonpost.com/blogs/wonkblog/files/2016/08/Nyhavn_copenhagen.jpg");
        names.add("Copenhagen");

        imageUrls.add("https://data.jigsawpuzzle.co.uk/clementoni.8/virtual-reality-new-york-jigsaw-puzzle-1000-pieces.60917-1.fs.jpg");
        names.add("New York");

        initRecyclerView();

    }

     */
}