package com.codecool.tripplanner.main;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
 import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.codecool.tripplanner.R;
import com.codecool.tripplanner.databinding.LayoutListitemBinding;
import com.codecool.tripplanner.db2.Trip;
import com.squareup.picasso.Picasso;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.TripViewHolder> {

    public class TripViewHolder extends RecyclerView.ViewHolder {

        private LayoutListitemBinding binding;

        public TripViewHolder(@NonNull LayoutListitemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    private List<Trip> trips; // cached copy of words

    public RecyclerViewAdapter(List<Trip> trips) {
        this.trips = trips;
    }

    @NonNull
    @Override
    public TripViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        LayoutListitemBinding binding = DataBindingUtil.inflate(inflater, R.layout.layout_listitem, parent, false);
        return new TripViewHolder(binding );
    }

    @Override
    public void onBindViewHolder(@NonNull TripViewHolder holder, int position) {

        if (trips != null) {
            Trip current = trips.get(position);
            holder.binding.city.setText(current.getCity());
            holder.binding.continent.setText(current.getContinent());
            holder.binding.country.setText(current.getCountry());

            try {
                Picasso.get()
                        .load(current.getImage())
                        .placeholder(R.drawable.ic_launcher_background)
                        .resize(80, 80)
                        .centerCrop()
                        .into(holder.binding.image);
            } catch (Exception e) {
                Picasso.get().load(R.drawable.ic_launcher_background).into(holder.binding.image);
                e.toString();
            }

        } else {
            holder.binding.city.setText("No city found");
        }

    }

    void setWords(List<Trip> trips) {
        this.trips = trips;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (trips != null) {
            return trips.size();
        } else return 0;
    }
}
