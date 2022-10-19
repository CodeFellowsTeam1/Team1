package com.frazmatic.logitrack.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.frazmatic.logitrack.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link supervisorProfile#newInstance} factory method to
 * create an instance of this fragment.
 */
public class supervisorProfile extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public supervisorProfile() {
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
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_supervisor_profile, container, false);
        Button currentTrips = view.findViewById(R.id.supervisorProfileCurrentTripsBtn);
        currentTrips.setOnClickListener(v -> {
            Navigation.findNavController(v).navigate(R.id.action_supervisorProfile_to_supervisorTripStatus);
        });
        Button createProfile = view.findViewById(R.id.supervisorProfileCreateBtn);
        createProfile.setOnClickListener(v ->{
            Navigation.findNavController(v).navigate(R.id.action_supervisorProfile_to_supervisorProfileForm);
        });

        return view;
    }
}