package com.frazmatic.logitrack.fragments;

import android.annotation.SuppressLint;
import android.nfc.Tag;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.amplifyframework.api.graphql.model.ModelMutation;
import com.amplifyframework.core.Amplify;
import com.frazmatic.logitrack.R;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.navigation.NavigationView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link currentTrip#newInstance} factory method to
 * create an instance of this fragment.
 */
public class currentTrip extends Fragment {


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final String TRIP_INFO_TAG = "tripInfo";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public currentTrip() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment currentTrip.
     */
    // TODO: Rename and change types and number of parameters
    public static currentTrip newInstance(String param1, String param2) {
        currentTrip fragment = new currentTrip();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        MaterialToolbar toolbar = getView().findViewById(R.id.fragmentAppBar);
        DrawerLayout drawerLayout = getView().findViewById(R.id.drawer_layout);
        NavigationView navigationView = getView().findViewById(R.id.navigation_view);
        toolbar.setNavigationOnClickListener(v -> {
                drawerLayout.openDrawer(GravityCompat.START);
        });
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                item.setChecked(true);
                drawerLayout.closeDrawer(GravityCompat.START);
                switch (id) {
                    case R.id.profile:
                        replaceFragment(new driverProfile());
                        break;
                    case R.id.trips:
                        replaceFragment(new currentTrip());
                        break;
                    case R.id.map:
                        replaceFragment(new mapGPS());
                        break;
                    case R.id.logout:
                        Amplify.Auth.signOut(
                                () -> Log.i("AuthQuickstart", "Signed out successfully"),
                                error -> Log.e("AuthQuickstart", error.toString())
                        );
                        break;
                }
                return false;
            }
        });
    }

    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout, fragment);
        fragmentTransaction.commit();
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_current_trip, container, false);
        String currentTrip = ((TextView) view.findViewById(R.id.CurrentTripTV)).getText().toString();
        view.findViewById(R.id.userSelectSupBtn).setOnClickListener((v -> {
//        saveTrip(where, miles, hours, dropOff, deadHead, rate, deliveryNotes);
            Navigation.findNavController(v).navigate(R.id.action_userSelect_to_supervisorProfile);
        }));
        return view;
    }


}