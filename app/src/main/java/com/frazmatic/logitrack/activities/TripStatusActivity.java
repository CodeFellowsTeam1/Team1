package com.frazmatic.logitrack.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.amplifyframework.api.graphql.model.ModelQuery;
import com.amplifyframework.core.Amplify;
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
        QueryDB();
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

    public void QueryDB() {
        Amplify.API.query(
                ModelQuery.list(Trip.class),
                success -> {
                    Log.i("QueryDB", "Read DB successfully");
                    Trips.clear();
                    for (Trip DBTrip : success.getData()){
                        Trips.add(DBTrip);
                    }
                    runOnUiThread(() ->{
                        adapter.notifyDataSetChanged();
                    });
                },
                failure -> {
                    Log.i("QueryDB", "Query DB Failed");
                });
    }


}