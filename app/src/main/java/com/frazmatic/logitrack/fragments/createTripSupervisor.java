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
import android.widget.EditText;

import com.amplifyframework.api.graphql.model.ModelMutation;
import com.amplifyframework.api.graphql.model.ModelQuery;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.generated.model.Firm;
import com.amplifyframework.datastore.generated.model.Trip;
import com.amplifyframework.datastore.generated.model.User;
import com.frazmatic.logitrack.MainActivity;
import com.frazmatic.logitrack.R;
import com.frazmatic.logitrack.TripStatusActivity;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link createTripSupervisor#newInstance} factory method to
 * create an instance of this fragment.
 */
public class createTripSupervisor extends Fragment {

    private SharedPreferences settings;
    private SharedPreferences.Editor editor;
    View view;
    private CompletableFuture<Firm> FirmFuture;
    private CompletableFuture<User> UserFuture;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    public static final String CREATE_TRIP_TAG = "CreateTrip";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public createTripSupervisor() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment createTripSupervisor.
     */
    // TODO: Rename and change types and number of parameters
    public static createTripSupervisor newInstance(String param1, String param2) {
        createTripSupervisor fragment = new createTripSupervisor();
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

//        resetForm();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_create_trip_supervisor, container, false);
        Button redirect = view.findViewById(R.id.createTripBtn);
        settings = PreferenceManager.getDefaultSharedPreferences(view.getContext());
        FirmFuture = new CompletableFuture<>();
        redirect.setOnClickListener(v -> {
            saveTrip();
            Intent goToTripStatus = new Intent(view.getContext(), TripStatusActivity.class);
            startActivity(goToTripStatus);

        });
        return view;
    }

    public void saveTrip() {

        String where = ((EditText) view.findViewById(R.id.createTripWhereInput)).getText().toString();

        EditText edt = (EditText) view.findViewById(R.id.createTripMilesInput);
        Double miles = Double.valueOf(edt.getText().toString());

        String hours = ((EditText) view.findViewById(R.id.createTripTravelTimeInput)).getText().toString();

        String dropOff = ((EditText) view.findViewById(R.id.createTripDropOffInput)).getText().toString();


        EditText deadheadtext = (EditText) view.findViewById(R.id.createTripDeadHeadInput);
        Double deadHead = Double.valueOf(deadheadtext.getText().toString());

        EditText ratetext = (EditText) view.findViewById(R.id.createTripRateInput);
        Double rate = Double.valueOf(ratetext.getText().toString());

        String deliveryNotes = ((EditText) view.findViewById(R.id.createTripDeliverNotesInput)).getText().toString();
        String FirmId = settings.getString(MainActivity.FIRM_ID_TAG, "");
        String UserId = settings.getString(MainActivity.USER_ID_TAG, "");
        Amplify.API.query(
                ModelQuery.get(Firm.class, FirmId),
                response -> {
                    FirmFuture.complete((Firm) response.getData());
                },
                error -> Log.i("errors", "no data to get.")
        );

        try {
            Firm currentFirm = FirmFuture.get();
            tripBuilder(where, dropOff, hours, miles, deadHead, rate, deliveryNotes, currentFirm);

        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }

    public static void tripBuilder(String where, String dropOff, String hours, Double miles, Double deadHead, Double rate, String deliveryNotes, Firm currentFirm) {

        Trip newTrip = Trip.builder()
                .where(where)
                .dropOff(dropOff)
                .hours(hours)
                .miles(miles)
                .deadHead(deadHead)
                .rate(rate)
                .deliveryNotes(deliveryNotes)
                .firm(currentFirm)
                .build();
        Amplify.API.mutate(
                ModelMutation.create(newTrip),
                success -> Log.i(CREATE_TRIP_TAG, "New trip info created."),
                failure -> Log.i(CREATE_TRIP_TAG, "Unable to create new trip " + failure)
        );
    }
}