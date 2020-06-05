package com.codecool.tripplanner;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Abstract class extending RoomDatabase for creating DB
 */
@Database(entities = {Trip.class}, version = 1, exportSchema = false)
public abstract class TripRoomDatabase extends RoomDatabase {

    private static RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback() {
        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);

            // If you want to keep data through app restarts,
            // comment out the following block
            databaseWriteExecutor.execute(() -> {
                // Populate the database in the background.
                // If you want to start with more words, just add them.
                TripDao dao = INSTANCE.tripDao();
                dao.deleteAll();


                Trip trip = new Trip("Paris", "Europe", "France", "https://africana.arizona.edu/sites/africana.arizona.edu/files/Eiffel-Tower-Paris-France.jpg");
                dao.insert(trip);
                trip = new Trip("Copenhagen", "Europe", "Denmark", "https://img.washingtonpost.com/blogs/wonkblog/files/2016/08/Nyhavn_copenhagen.jpg");
                dao.insert(trip);
                trip = new Trip("New York", "North America", "USA", "https://data.jigsawpuzzle.co.uk/clementoni.8/virtual-reality-new-york-jigsaw-puzzle-1000-pieces.60917-1.fs.jpg");
                dao.insert(trip);
                trip = new Trip("London", "Europe", "United Kingdom", "https://m.blog.hu/ku/kulturpart/2015-12-08/london-penzugyi-kozpont-londoniapro-62746.jpeg");
                dao.insert(trip);
                trip = new Trip("Rejkjavik", "Europe", "Iceland", "https://www.telegraph.co.uk/content/dam/Travel/Destinations/Europe/Iceland/Reykjavik/reykjavik-guide-lead-image-48-hours-xlarge.jpg");
                dao.insert(trip);
                trip = new Trip("Istanbul", "Europe", "Turkey", "https://idsb.tmgrup.com.tr/ly/uploads/images/2020/04/17/thumbs/800x531/31299.jpg");
                dao.insert(trip);



                /*
                Trip trip = new Trip("Paris","Europe", "https://africana.arizona.edu/sites/africana.arizona.edu/files/Eiffel-Tower-Paris-France.jpg");
                dao.insert(trip);
                Trip trip2 = new Trip("Copenhagen","Europe", "https://img.washingtonpost.com/blogs/wonkblog/files/2016/08/Nyhavn_copenhagen.jpg");
                dao.insert(trip2);
                Trip trip3 = new Trip("New York","North America", "https://data.jigsawpuzzle.co.uk/clementoni.8/virtual-reality-new-york-jigsaw-puzzle-1000-pieces.60917-1.fs.jpg");
                dao.insert(trip2);
                 */
            });
        }
    };

    public abstract TripDao tripDao();

    private static volatile TripRoomDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    static TripRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (TripRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            TripRoomDatabase.class, "trip_database")
                            .addCallback(sRoomDatabaseCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

}
