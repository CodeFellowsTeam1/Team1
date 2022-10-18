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
 * Use the {@link userSelect#newInstance} factory method to
 * create an instance of this fragment.
 */
public class userSelect extends Fragment {

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public userSelect() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment userSelect.
     */
    // TODO: Rename and change types and number of parameters
//    public static userSelect newInstance(String param1, String param2) {
//        userSelect fragment = new userSelect();
//        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
//        fragment.setArguments(args);
//        return fragment;
//    }
//
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        if (getArguments() != null) {
//            mParam1 = getArguments().getString(ARG_PARAM1);
//            mParam2 = getArguments().getString(ARG_PARAM2);
//        }
//
//
//    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_user_select, container, false);
        Button redirect = view.findViewById(R.id.userSelectSupBtn);
        redirect.setOnClickListener((v -> {
            Navigation.findNavController(v).navigate(R.id.action_userSelect_to_supervisorProfile);
        }));

        Button redirect1 = view.findViewById(R.id.userSelectCarrierButton);
        redirect1.setOnClickListener((v -> {
            Navigation.findNavController(v).navigate(R.id.action_userSelect_to_driverProfile);
        }));

        return view;
    }
}