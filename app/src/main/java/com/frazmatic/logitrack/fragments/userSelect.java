package com.frazmatic.logitrack.fragments;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.frazmatic.logitrack.R;

///**
// * A simple {@link Fragment} subclass.
// * Use the {@link userSelect#newInstance} factory method to
// * create an instance of this fragment.
// */
public class userSelect extends Fragment {

    private SharedPreferences settings;

    public userSelect() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_user_select, container, false);
        settings = PreferenceManager.getDefaultSharedPreferences(getContext());
        String id = settings.getString("id", "");
        Button supervisor = view.findViewById(R.id.userSelectSupBtn);
        if (id.isEmpty()){
            supervisor.setOnClickListener(v -> {
                Navigation.findNavController(v).navigate(R.id.action_userSelect_to_supervisorProfileForm);
            });
        } else {
            supervisor.setOnClickListener((v -> {
                Navigation.findNavController(v).navigate(R.id.action_userSelect_to_supervisorProfile);
            }));
        }


        Button driver = view.findViewById(R.id.userSelectCarrierButton);
        driver.setOnClickListener((v -> {
            Navigation.findNavController(v).navigate(R.id.action_userSelect_to_driverProfileForm3);
        }));

        return view;
    }
}