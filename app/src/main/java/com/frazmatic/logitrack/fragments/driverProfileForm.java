package com.frazmatic.logitrack.fragments;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.amplifyframework.api.graphql.model.ModelMutation;
import com.amplifyframework.api.graphql.model.ModelQuery;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.generated.model.Company;
import com.amplifyframework.datastore.generated.model.User;
import com.frazmatic.logitrack.R;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link driverProfileForm#newInstance} factory method to
 * create an instance of this fragment.
 */
public class driverProfileForm extends Fragment {

    private SharedPreferences settings;
    private SharedPreferences.Editor editor;
    private CompletableFuture<List<Company>> companies;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    public static final String Tag = "DriverProfileForm";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public driverProfileForm() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment driverProfileForm.
     */
    // TODO: Rename and change types and number of parameters
    public static driverProfileForm newInstance(String param1, String param2) {
        driverProfileForm fragment = new driverProfileForm();
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

        settings = PreferenceManager.getDefaultSharedPreferences(getContext());
        editor = settings.edit();
        companies = new CompletableFuture<>();

        view.findViewById(R.id.driverFormSaveBtn).setOnClickListener(v -> {
            String name = ((EditText) view.findViewById(R.id.driverFormNameField)).getText().toString();
            String CompanyName = ((EditText) view.findViewById(R.id.driverFormCompanyField)).getText().toString();
            String CompanyLocation = ((EditText) view.findViewById(R.id.driverFormCompanyCityStateField)).getText().toString();

            saveForm(name, CompanyName, CompanyLocation);
            Navigation.findNavController(v).navigate(R.id.action_driverProfile_to_driverProfileForm);
        });
        return view;
    }

    private void saveForm(String name, String company, String companyLocation){
        //TODO: create database entry with user info
        //TODO: CHECK DB FIRST for company name

        Amplify.API.query(
                ModelQuery.list(Company.class, Company.NAME.contains(company)),
                response -> {
                    ArrayList<Company> DBCompanies = new ArrayList<>();
                    for (Company c : response.getData()) {
                        DBCompanies.add(c);
                        Log.i("DriverProfileForm", c.getName());
                    }
                    companies.complete(DBCompanies);
                },
                error -> {
                    companies.complete(null);
                    Log.e("DriverProfileForm", "Query failure", error);
                }
        );

        ArrayList<Company> companyArrayList = new ArrayList<>();
        try {
            companyArrayList = (ArrayList<Company>)companies.get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Company newCompany;
        if (companyArrayList.size() > 0){
            newCompany = companyArrayList.get(0);
        } else {
            newCompany = Company
                    .builder()
                    .name(company)
                    .cityAndState(companyLocation)
                    .build();
            Amplify.API.mutate(
                    ModelMutation.create(newCompany),
                    success -> Log.i(Tag, "Updated company info"),
                    failure -> Log.i(Tag,"Unable to save company info" + failure)
            );
        }
        User newUser = User.builder()
                .name(name)
                .licensePlate("123456")
                .company(newCompany)
                .build();
        Amplify.API.mutate(
                ModelMutation.create(newUser),
                success -> {
                    String id = ((User)success.getData()).getId();
                    editor.putString("id", id);
                    editor.apply();
                    Log.i(Tag, "Updated user info");
                },
                failure -> Log.i(Tag,"Unable to save user info" + failure)
        );
    }
}