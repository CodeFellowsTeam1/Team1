package com.frazmatic.logitrack.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.amplifyframework.datastore.generated.model.Trip;
import com.frazmatic.logitrack.R;
import com.frazmatic.logitrack.adapter.TripStatusRecyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;

public class TripStatusActivity extends AppCompatActivity {

    TripStatusRecyclerViewAdapter adapter;
    List<Trip> Trips = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trip_status_view);


        findViewById(R.id.TripStatusAddTripButton).setOnClickListener(view -> {
            Intent goToCreateTrip = new Intent(TripStatusActivity.this, TripNavHostActivity.class);
            startActivity(goToCreateTrip);
        });

        setUpTripStatusRecyclerView();
    }

    private void setUpTripStatusRecyclerView() {
        // 1. Grabbing recycler
        RecyclerView TripStatusRecyclerView = findViewById(R.id.TripStatusRecyclerView);

        //2. setup layout manager
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        TripStatusRecyclerView.setLayoutManager(layoutManager);

        adapter = new TripStatusRecyclerViewAdapter(Trips, this);
        TripStatusRecyclerView.setAdapter(adapter);

    }
}