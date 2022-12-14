package com.frazmatic.logitrack.fragments;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.amplifyframework.api.graphql.model.ModelQuery;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.generated.model.Trip;
import com.amplifyframework.datastore.generated.model.User;
import com.frazmatic.logitrack.MainActivity;
import com.frazmatic.logitrack.R;
import com.frazmatic.logitrack.TripStatusActivity;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link driverProfile#newInstance} factory method to
 * create an instance of this fragment.
 */
public class driverProfile extends Fragment {

    private SharedPreferences settings;
    private SharedPreferences.Editor editor;
    private CompletableFuture<User> userCompletableFuture;
    private CompletableFuture<Trip> tripCompletableFuture;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public driverProfile() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment supervisorProfile.
     */
    // TODO: Rename and change types and number of parameters
    public static supervisorProfile newInstance(String param1, String param2) {
        supervisorProfile fragment = new supervisorProfile();
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
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        settings = PreferenceManager.getDefaultSharedPreferences(getContext());
        editor = settings.edit();
        userCompletableFuture = new CompletableFuture<>();
        tripCompletableFuture = new CompletableFuture<>();

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_driver_profile, container, false);

        saveNames();
        fillInTextFields(view);
        setTripNameText(view);
        setupSignoutButton(view);
        return view;
    }

    private void saveNames(){

        String userId = settings.getString(MainActivity.USER_ID_TAG, "");
        if (!userId.isEmpty()){
            Amplify.API.query(
                    ModelQuery.get(User.class, userId),
                    response -> {
                        userCompletableFuture.complete(response.getData());
                    },
                    error -> {

                    }
            );
        }
    }

    private void fillInTextFields(View view){
        TextView user = view.findViewById(R.id.textViewDriverProfileUserName);
        TextView firm = view.findViewById(R.id.textViewDriverProfileCompanyName);
        try {
            User u = userCompletableFuture.get();
            getTrip(u);
            getActivity().runOnUiThread(() -> {
                user.setText("Name: " + u.getName());
                firm.setText("Company: " + u.getFirm().getName());
            });
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void getTrip(User u){
        Amplify.API.query(
                ModelQuery.list(Trip.class),
                success -> {
                    for (Trip t : success.getData()){
                        if (!(t.getUserId() == null) && t.getUserId().equals(u.getId())){
                            tripCompletableFuture.complete(t);
                        }
                    }
                    tripCompletableFuture.complete(null);
                },
                error -> {
                    tripCompletableFuture.complete(null);
                }
        );
    }

    private void setTripNameText(View view){
        try {
            Trip t = tripCompletableFuture.get();
            TextView name = view.findViewById(R.id.textViewDriverProfileTrip);
            getActivity().runOnUiThread(()->{
                if (t != null){
                    name.setText("Current Trip: " + t.getWhere());
                } else {
                    name.setText("No Trips");
                }
            });
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void setupSignoutButton(View view){
        Button signout = view.findViewById(R.id.buttonSignOutDriver);
        signout.setOnClickListener(v -> {
            Amplify.Auth.signOut(
                    () -> {
                        Intent returnToMain = new Intent(view.getContext(), MainActivity.class );
                        startActivity(returnToMain);
                        Log.i("AuthQuickstart", "Signed out successfully");
                    },
                    error -> Log.e("AuthQuickstart", error.toString())
            );
        });
    }
}