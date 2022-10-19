package com.frazmatic.logitrack;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import androidx.core.app.ActivityCompat;
import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.os.Looper;
import android.util.Log;
import android.widget.Button;
import com.amplifyframework.auth.AuthProvider;
import com.amplifyframework.core.Amplify;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
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

        Amplify.Auth.fetchAuthSession(
                result -> {
                    if(result.isSignedIn()){
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
                                oauth();
                                finish();
                                startActivity(getIntent());
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
                    String lat = ((Double)location.getLatitude()).toString();
                    Log.i("Lat: ", lat);
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

    private void oauth(){
        Amplify.Auth.signInWithSocialWebUI(AuthProvider.google(), this,
                result -> Log.i("AuthQuickstart", result.toString()),
                error -> Log.e("AuthQuickstart", error.toString())
        );
    }

//    private void setupLoginLogoutButton(){
//        Button b = findViewById(R.id.buttonLoginLogout);
//        Amplify.Auth.fetchAuthSession(
//                result -> {
//                    if (result.isSignedIn()){
//                        runOnUiThread(() -> {
//                            b.setText("Log Out");
//                            b.setOnClickListener(view -> {
//                                signOutUser();
//                                finish();
//                                startActivity(getIntent());
//                            });
//                        });
//                    } else {
//                        runOnUiThread(() -> {
//                            b.setText("Log In");
//                            b.setOnClickListener(view -> {
//                                Intent intent = new Intent(this, LogIn.class);
//                                startActivity(intent);
//                            });
//                        });
//                    }
//                },
//                error -> Log.e("AmplifyQuickstart", error.toString())
//        );
//    }
}