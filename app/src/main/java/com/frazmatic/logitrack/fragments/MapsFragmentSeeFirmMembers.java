package com.frazmatic.logitrack.fragments;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.amplifyframework.api.graphql.model.ModelQuery;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.generated.model.User;
import com.frazmatic.logitrack.activities.MainActivity;
import com.frazmatic.logitrack.activities.MainActivity;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import com.frazmatic.logitrack.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class MapsFragmentSeeFirmMembers extends Fragment {

    private CompletableFuture<List<User>> teamMembers;
    private SharedPreferences settings;
    private SharedPreferences.Editor editor;
    private HashMap<Marker, User> markerUsers;
    public static final String ADD_DRIVER_TRIP_ID = "tripID";

    private OnMapReadyCallback callback = new OnMapReadyCallback() {

        /**
         * Manipulates the map once available.
         * This callback is triggered when the map is ready to be used.
         * This is where we can add markers or lines, add listeners or move the camera.
         * In this case, we just add a marker near Sydney, Australia.
         * If Google Play services is not installed on the device, the user will be prompted to
         * install it inside the SupportMapFragment. This method will only be triggered once the
         * user has installed Google Play services and returned to the app.
         */
        @Override
        public void onMapReady(GoogleMap googleMap) {
            googleMap.setOnMarkerClickListener(marker -> {
                User u = markerUsers.get(marker);
                Log.i("USER NAME: ", u.getName());
                return false;
            });
            try {
                ArrayList<User> users = (ArrayList<User>)teamMembers.get();
                for (User u : users){
                    LatLng loc = new LatLng(u.getLat(), u.getLon());
                    Marker m = googleMap.addMarker(new MarkerOptions().position(loc).title(u.getName()));
                    markerUsers.put(m, u);
                }
            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Double currentLat = (double)settings.getFloat(MainActivity.CURRENT_LAT,0);
            Double currentLon = (double)settings.getFloat(MainActivity.CURRENT_LON, 0);
            LatLng currentLocation = new LatLng(currentLat, currentLon);
            googleMap.addMarker(new MarkerOptions().position(currentLocation).title("Your Location"));
            googleMap.moveCamera(CameraUpdateFactory.newLatLng(currentLocation));
        }
    };

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_maps_see_firm_members, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SupportMapFragment mapFragment =
                (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        if (mapFragment != null) {
            mapFragment.getMapAsync(callback);
        }
        settings = PreferenceManager.getDefaultSharedPreferences(getContext());
        editor = settings.edit();
        teamMembers = new CompletableFuture<>();
        markerUsers = new HashMap<>();
        completeTeamMembers();
    }

    private void completeTeamMembers(){
        String userId = settings.getString(MainActivity.USER_ID_TAG, "");
        if (!userId.isEmpty()){
            Amplify.API.query(
                    ModelQuery.get(User.class, userId),
                    response -> {
                        ArrayList<User> users = new ArrayList<>();
                        users = (ArrayList<User>)response.getData().getFirm().getUsers();
                        teamMembers.complete(users);
                    },
                    error -> {
                        teamMembers.complete(null);
                    }
            );
        }
    }

}