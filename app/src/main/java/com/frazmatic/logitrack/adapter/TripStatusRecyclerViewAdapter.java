package com.frazmatic.logitrack.adapter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.generated.model.Trip;
import com.frazmatic.logitrack.R;

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
        TextView TripStatusTitleFragment = holder.itemView.findViewById(R.id.TripRecyclerViewTextView);

        //TODO: Put DB Data here
        String taskTitle = trips.get(position).getWhere();

//        String taskDescription = tasks.get(position).getDescription();
//        String TaskStatus = tasks.get(position).getState().toString();


        TripStatusTitleFragment.setText(taskTitle);

        // Make an on click handler so we can interact with recyclerview items
        View taskViewHolder = holder.itemView;

        taskViewHolder.setOnClickListener(view -> {

            //TODO: pass data to task details here
//            goToTaskDetails.putExtra(MainActivity.TASK_TITLE_TAG,taskTitle);
//            goToTaskDetails.putExtra(MainActivity.TASK_DESCRIPTION_TAG,taskDescription);
//            goToTaskDetails.putExtra(MainActivity.TASK_STATUS_TAG,TaskStatus);

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
