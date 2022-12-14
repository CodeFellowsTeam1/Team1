package com.frazmatic.logitrack.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.amplifyframework.datastore.generated.model.Trip;
import com.frazmatic.logitrack.MapNavHostActivity;
import com.frazmatic.logitrack.R;
import com.frazmatic.logitrack.fragments.MapsFragmentSeeFirmMembers;

import java.util.List;

public class TripStatusRecyclerViewAdapter extends RecyclerView.Adapter<TripStatusRecyclerViewAdapter.TripStatusViewHolder> {

    List<Trip> trips;

    Context callingActivity;

    //11 create a constructor

    public TripStatusRecyclerViewAdapter(List<Trip> trips, Context callingActivity) {
        this.trips = trips;
        this.callingActivity = callingActivity;
    }

    @NonNull
    @Override
    public TripStatusViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //6. Inflate fragment. This is taking the XML and converting it to java objects. Basically a constructor for the fragment. assigned in taskfragment.java
        View tripFragment = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_trip_recycler_view, parent,false);
        return new TripStatusViewHolder(tripFragment);// 8 bring in the class here and pass the fragment
    }

    @Override
    public void onBindViewHolder(@NonNull TripStatusViewHolder holder, int position) {
        // 11 bind the data to our fragment


        //TODO: Put DB Data here
        String taskTitle = trips.get(position).getWhere();
        String tripID = trips.get(position).getId();
        String driverName = "";
        if (trips.get(position).getUser() != null){
            driverName = trips.get(position).getUser().getName();
        }
        if(!driverName.isEmpty()){
            TextView viewDrivername = holder.itemView.findViewById(R.id.TripRecyclerViewDriverName);
            viewDrivername.setText(driverName);
        }
        TextView TripStatusTitleFragment = holder.itemView.findViewById(R.id.TripRecyclerViewTextView);
        TripStatusTitleFragment.setText(taskTitle);

        // Make an on click handler so we can interact with recyclerview items
        View TripViewHolder = holder.itemView;

        TripViewHolder.setOnClickListener(view -> {
            Intent goToMap = new Intent(callingActivity, MapNavHostActivity.class);
            goToMap.putExtra(MapsFragmentSeeFirmMembers.ADD_DRIVER_TRIP_ID,tripID);
            callingActivity.startActivity(goToMap);
        });


    }

    @Override
    public int getItemCount() {
        return trips.size();
    }


    public static class TripStatusViewHolder extends RecyclerView.ViewHolder{

        public TripStatusViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

}
