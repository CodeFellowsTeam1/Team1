package com.frazmatic.logitrack.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.amplifyframework.api.graphql.model.ModelMutation;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.generated.model.Trip;
import com.frazmatic.logitrack.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link createTripSupervisor#newInstance} factory method to
 * create an instance of this fragment.
 */
public class createTripSupervisor extends Fragment {

    View view = null;

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

        resetForm();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_create_trip_supervisor, container, false);
        Button redirect = view.findViewById(R.id.createTripBtn);

        redirect.setOnClickListener(v ->{
            saveTrip();

        });
        return view;
    }

    public void saveTrip(){

        String where = ((EditText) view.findViewById(R.id.whereEditTV)).getText().toString();

        EditText edt = (EditText) view.findViewById(R.id.milesNumberDecimal);
        Double miles = Double.valueOf(edt.getText().toString());

        String hours = ((EditText) view.findViewById(R.id.timeEditTextTime)).getText().toString();

        String dropOff = ((EditText) view.findViewById(R.id.dropOffEditTV)).getText().toString();


        EditText deadheadtext = (EditText) view.findViewById(R.id.deadHeadNumberDecimal);
        Double deadHead = Double.valueOf(deadheadtext.getText().toString());

        EditText ratetext = (EditText) view.findViewById(R.id.rateNumberDecimal);
        Double rate = Double.valueOf(ratetext.getText().toString());

        String deliveryNotes = ((EditText) view.findViewById(R.id.deliveryNotesMLET)).getText().toString();


        Trip newTrip = Trip.builder()
                .where(where)
                .dropOff(dropOff)
                .hours(hours)
                .miles(miles)
                .deadHead(deadHead)
                .rate(rate)
                .deliveryNotes(deliveryNotes)
                .build();
        Amplify.API.mutate(
                ModelMutation.create(newTrip),
                success -> Log.i(CREATE_TRIP_TAG, "New trip info created."),
                failure -> Log.i(CREATE_TRIP_TAG, "Unable to create new trip " + failure)
        );
    }

    public void resetForm(){
        Button reset = view.findViewById(R.id.createTripFloatingActionButton);
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText where = view.findViewById(R.id.whereEditTV);
                where.setText("");
                EditText miles = view.findViewById(R.id.milesNumberDecimal);
                miles.setText("");
                EditText time = view.findViewById(R.id.timeEditTextTime);
                time.setText("");
                EditText dropOff = view.findViewById(R.id.dropOffEditTV);
                dropOff.setText("");
                EditText deadHead = view.findViewById(R.id.deadHeadNumberDecimal);
                deadHead.setText("");
                EditText notes = view.findViewById(R.id.deliveryNotesMLET);
                notes.setText("");
                EditText rate = view.findViewById(R.id.rateNumberDecimal);
                rate.setText("");
            }
        });

    }
}