package com.frazmatic.logitrack.fragments;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.amplifyframework.api.graphql.model.ModelMutation;
import com.amplifyframework.api.graphql.model.ModelQuery;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.generated.model.Firm;
import com.amplifyframework.datastore.generated.model.User;
import com.frazmatic.logitrack.MainActivity;
import com.frazmatic.logitrack.R;

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
    private CompletableFuture<Firm> firmCompletableFuture;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    public static final String Tag = "DriverProfileForm";
    View view = null;

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
        resetForm();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_driver_profile_form, container, false);
        firmCompletableFuture = new CompletableFuture<>();
        settings = PreferenceManager.getDefaultSharedPreferences(getContext());
        editor = settings.edit();

        view.findViewById(R.id.driverFormSaveBtn).setOnClickListener(v -> {
            String name = ((EditText) view.findViewById(R.id.driverFormNameField)).getText().toString();
            String CompanyName = ((EditText) view.findViewById(R.id.driverFormCompanyField)).getText().toString();
            String CompanyLocation = ((EditText) view.findViewById(R.id.driverFormCompanyCityStateField)).getText().toString();
            saveForm(name, CompanyName, CompanyLocation);
            Navigation.findNavController(v).navigate(R.id.action_driverProfile_to_driverProfileForm);

            });
        return view;
    }
    //TODO Chane to use methods from Supervisor Form
    private void saveForm(String name, String firm, String companyLocation){

        completeFirmFuture(firm);
        Firm newFirm = getFirm();

        if (newFirm == null){
            newFirm = Firm
                    .builder()
                    .name(firm)
                    .cityAndState(companyLocation)
                    .build();
            Amplify.API.mutate(
                    ModelMutation.create(newFirm),
                    success -> Log.i(Tag, "Updated company info"),
                    failure -> Log.i(Tag,"Unable to save company info" + failure)
            );
        }

        User newUser = User.builder()
                .name(name)
                .authId(settings.getString(MainActivity.AUTH_ID_TAG, ""))
                .firm(newFirm)
                .build();
        Amplify.API.mutate(
                ModelMutation.create(newUser),
                success -> {
                    String id = ((User)success.getData()).getId();
                    String firmId = ((User)success.getData()).getFirm().getId();
                    editor.putString(MainActivity.USER_ID_TAG, id);
                    editor.putString(MainActivity.FIRM_ID_TAG, firmId);
                    editor.apply();
                    Log.i(Tag, "Updated user info");
                },
                failure -> Log.i(Tag,"Unable to save user info" + failure)
        );

    };

    public void resetForm(){
        Button reset = view.findViewById(R.id.resetBtn);
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText dName = view.findViewById(R.id.driverFormNameField);
                dName.setText("");
                EditText coName = view.findViewById(R.id.driverFormCompanyField);
                coName.setText("");
                EditText csName = view.findViewById(R.id.driverFormCompanyCityStateField);
                csName.setText("");
            }
        });

    }

    private void completeFirmFuture(String firmName){
        Amplify.API.query(
                ModelQuery.list(Firm.class, Firm.NAME.contains(firmName)),
                response -> {
                    for (Firm f : response.getData()){
                        if (f.getName() == firmName){
                            firmCompletableFuture.complete(f);
                        }
                    }
                    firmCompletableFuture.complete(null);
                },
                error -> {
                    firmCompletableFuture.complete(null);
                }
        );
    }
    private Firm getFirm(){
        Firm f = null;
        try {
            f = firmCompletableFuture.get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return f;
    }
}