package com.frazmatic.logitrack.fragments;

import android.nfc.Tag;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.amplifyframework.api.graphql.model.ModelMutation;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.generated.model.Company;
import com.amplifyframework.datastore.generated.model.User;
import com.frazmatic.logitrack.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link supervisorProfileForm#newInstance} factory method to
 * create an instance of this fragment.
 */
public class supervisorProfileForm extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    public static final String Tag = "SupervisorProfileForm";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public supervisorProfileForm() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment supervisorProfileForm.
     */
    // TODO: Rename and change types and number of parameters
    public static supervisorProfileForm newInstance(String param1, String param2) {
        supervisorProfileForm fragment = new supervisorProfileForm();
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
        View view = inflater.inflate(R.layout.fragment_supervisor_profile_form, container, false);



        view.findViewById(R.id.supervisorFormSaveBtn).setOnClickListener(v -> {

            String name = ((EditText) view.findViewById(R.id.supervisorFormNameField)).getText().toString();
            String CompanyName = ((EditText) view.findViewById(R.id.supervisorFormCompanyField)).getText().toString();
            String CompanyLocation = ((EditText) view.findViewById(R.id.supervisorFormCompanyCityStateField)).getText().toString();

            saveForm(name, CompanyName, CompanyLocation);
            Navigation.findNavController(v).navigate(R.id.action_supervisorProfileForm_to_supervisorProfile);
        });


        return view;
    }

    private void saveForm(String name, String company, String companyLocation){
        //TODO: create database entry with user info
        Company newCompany = Company.builder()
                .name(company)
                .cityAndState(companyLocation)
                .build();
        Amplify.API.mutate(
                ModelMutation.create(newCompany),
                success -> Log.i(Tag, "Updated company info"),
                failure -> Log.i(Tag,"Unable to save company info" + failure)
        );
        User newUser = User.builder()
                .name(name)
                .licensePlate("123456")
                .company(newCompany)
                .build();
        Amplify.API.mutate(
                ModelMutation.create(newUser),
                success -> Log.i(Tag, "Updated user info"),
                failure -> Log.i(Tag,"Unable to save user info" + failure)
        );

    }
}