package com.codecool.tripplanner.main;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.codecool.tripplanner.R;
import com.codecool.tripplanner.db2.Trip;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.TripViewHolder> {

    class TripViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.image)
        CircleImageView imageView;

        @BindView(R.id.city)
        TextView cityView;
        @BindView(R.id.country)
        TextView countryView;
        @BindView(R.id.continent)
        TextView continentView;


        private TripViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
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

            try {
                Picasso.get()
                        .load(current.getImage())
                        .placeholder(R.drawable.ic_launcher_background)
                        .resize(80, 80)
                        .centerCrop()
                        .into(holder.imageView);
            } catch (Exception e) {
                Picasso.get().load(R.drawable.ic_launcher_background).into(holder.imageView);
                e.toString();
            }

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
