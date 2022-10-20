package com.frazmatic.logitrack;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import androidx.core.app.ActivityCompat;
import android.Manifest;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.os.Looper;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.amplifyframework.api.graphql.model.ModelMutation;
import com.amplifyframework.api.graphql.model.ModelQuery;
import com.amplifyframework.auth.AuthProvider;
import com.amplifyframework.auth.AuthUser;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.generated.model.User;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.Priority;

public class MainActivity extends AppCompatActivity {

    protected LocationRequest locationRequest;
    protected LocationCallback locationCallback;
    private FusedLocationProviderClient fusedLocationProviderClient;
    private SharedPreferences settings;
    private SharedPreferences.Editor editor;
    public static final String AUTH_ID_TAG = "authId";
    public static final String USER_ID_TAG = "userId";
    public static final String FIRM_ID_TAG = "firmId";
    public static final String SUPERVISOR_ID_TAG = "supervisorId";
    public static final String DRIVER_ID_TAG = "driverId";
    public static final String USERNAME_TAG = "username";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        settings = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
        editor = settings.edit();
        requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 55555);
        createLocationRequest(20);
        createLocationCallback();
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(getApplicationContext());
    }

    @Override
    protected void onResume() {
        super.onResume();
        resumeLocationUpdates();
        setupNavButton();
    }

    private void createLocationRequest(int intervalSeconds){
        long milliseconds = intervalSeconds * 1000;
        locationRequest = LocationRequest.create();
        locationRequest.setInterval(milliseconds);
        locationRequest.setPriority(Priority.PRIORITY_HIGH_ACCURACY);
    }

    private void setupNavButton(){
        Button loginBtn = findViewById(R.id.MainActivityLoginBttn);
        Amplify.Auth.fetchUserAttributes(
                userAttributes -> Log.i("AuthDemo", "User attributes = " + userAttributes.toString()),

                error -> Log.e("AuthDemo", "Failed to fetch user attributes.", error)
        );

        Amplify.Auth.fetchAuthSession(
                result -> {
                    if(result.isSignedIn()){
                        AuthUser currentUser = Amplify.Auth.getCurrentUser();
                        editor.putString(AUTH_ID_TAG, currentUser.getUserId());
                        editor.apply();
                        runOnUiThread(() -> {
                            loginBtn.setText("User Selection");
                            loginBtn.setOnClickListener( view ->{
                                Intent goToNavHost = new Intent(MainActivity.this, NavHostActivity.class);
                                startActivity(goToNavHost);
                            });
                        });
                    } else {
                        runOnUiThread(() -> {
                            loginBtn.setText("LOGIN");
                            loginBtn.setOnClickListener(view -> {
                                oauth(loginBtn);
                            });
                        });
                    }
                },
                error -> {});
    }

    public void createLocationCallback(){
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        locationCallback = new LocationCallback() {
            @Override
            public void onLocationResult(LocationResult locationResult) {
                if (locationResult == null) {
                    return;
                }
                for (Location location : locationResult.getLocations()) {
                    //TODO: Call a method do something with location
                    updateUserLocation(location.getLatitude(), location.getLongitude());
                }
            }
        };
    }

    public void resumeLocationUpdates(){
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        fusedLocationProviderClient.requestLocationUpdates(locationRequest,
                locationCallback,
                Looper.getMainLooper());
    }

    private void oauth(Button loginBtn){
        Amplify.Auth.signInWithSocialWebUI(AuthProvider.google(), this,
                result -> {
                    String authId = Amplify.Auth.getCurrentUser().getUserId();
                    editor.putString(AUTH_ID_TAG, authId);
                    editor.apply();
                    runOnUiThread(() -> {
                        loginBtn.setText("User Selection");
                        loginBtn.setOnClickListener( view ->{
                            Intent goToNavHost = new Intent(MainActivity.this, NavHostActivity.class);
                            startActivity(goToNavHost);
                        });
                    });
                },
                error -> Log.e("AuthQuickstart", error.toString())
        );
    }

    private void updateUserLocation(Double lat, Double lon){
        String userId = settings.getString(USER_ID_TAG, "");
        if (!userId.isEmpty()){
            Amplify.API.query(
                    ModelQuery.get(User.class, userId),
                    response -> {
                        User currentUser = (User)response.getData();
                        User updatedUser = User.builder()
                                .id(currentUser.getId())
                                .name(currentUser.getName())
                                .authId(currentUser.getAuthId())
                                .firm(currentUser.getFirm())
                                .lat(lat)
                                .lon(lon)
                                .build();
                        Amplify.API.mutate(
                                ModelMutation.update(updatedUser),
                                updateResponse -> {
                                    String lt = updateResponse.getData().getLat().toString();
                                    String ln = updateResponse.getData().getLon().toString();
                                    Log.i("Updated Lat/Lon: ", lt + ", " + ln);
                                },
                                error -> {

                                }
                        );
                    },
                    error -> {

                    }
            );
        }
    }
}