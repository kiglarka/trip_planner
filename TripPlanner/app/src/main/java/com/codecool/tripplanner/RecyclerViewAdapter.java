package com.codecool.tripplanner;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.TripViewHolder> {

    class TripViewHolder extends RecyclerView.ViewHolder {

        //private final CircleImageView image;
        private final TextView cityView;
        private final TextView countryView;
        private final TextView continentView;

        private TripViewHolder(View itemView) {
            super(itemView);
            //image = itemView.findViewById(R.id.image);
            cityView = itemView.findViewById(R.id.city);
            countryView = itemView.findViewById(R.id.country);
            continentView = itemView.findViewById(R.id.continent);
        }
    }

    private final LayoutInflater inflater;
    private List<Trip> cities; // cached copy of words

    RecyclerViewAdapter(Context context) {
        inflater = LayoutInflater.from(context);
    }


    @NonNull
    @Override
    public TripViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.layout_listitem, parent, false);
        return new TripViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TripViewHolder holder, int position) {

        /*
        Glide.with(context)
                .asBitmap()
                .load(images.get(position))
                .into(holder.image);
         */

        if (cities != null) {
            Trip current = cities.get(position);
            holder.cityView.setText(current.getCity());
            holder.continentView.setText(current.getContinent());
            holder.countryView.setText(current.getCountry());

        } else {
            holder.cityView.setText("No city found");
        }

    }

    void setWords(List<Trip> trips) {
        cities = trips;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (cities != null) {
            return cities.size();
        } else return 0;


    }
}
