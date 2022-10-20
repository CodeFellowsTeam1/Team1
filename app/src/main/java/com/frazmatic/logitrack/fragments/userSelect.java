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

import com.amplifyframework.api.graphql.model.ModelQuery;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.generated.model.User;
import com.frazmatic.logitrack.MainActivity;
import com.frazmatic.logitrack.R;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

///**
// * A simple {@link Fragment} subclass.
// * Use the {@link userSelect#newInstance} factory method to
// * create an instance of this fragment.
// */
public class userSelect extends Fragment {

    private SharedPreferences settings;
    private SharedPreferences.Editor editor;
    private CompletableFuture<User> currentUserFuture;

    public userSelect() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_user_select, container, false);
        settings = PreferenceManager.getDefaultSharedPreferences(getContext());
        editor = settings.edit();

        //Searches AWS database for user based on AuthID; completes future with
        // User Object if exists & adds ID to settings.
        currentUserFuture = new CompletableFuture<>();
        completeUserByAuthId();
        setSupervisorButton(view);

        //TODO Change to use OAuth & User (DynamoDB) object to check instead of id string
        // for both supervisor form directing


        Button driver = view.findViewById(R.id.userSelectCarrierButton);
        driver.setOnClickListener((v -> {
            Navigation.findNavController(v).navigate(R.id.action_userSelect_to_driverProfileForm3);
        }));

        return view;
    }

    private void completeUserByAuthId(){
        String authId = settings.getString(MainActivity.AUTH_ID_TAG, "");
        Amplify.API.query(
                ModelQuery.list(User.class),
                response ->{
                    for(User u : response.getData()){
                        if (u.getAuthId().equals(authId)){
                            currentUserFuture.complete(u);
                            editor.putString(MainActivity.USER_ID_TAG, u.getId());
                            editor.putString(MainActivity.FIRM_ID_TAG, u.getFirm().getId());
                            editor.apply();
                            return;
                        }
                    }
                    currentUserFuture.complete(null);
                },
                error->{
                  currentUserFuture.complete(null);
                }
        );
    }

    private void setSupervisorButton(View view){
        getActivity().runOnUiThread(()->{
            try {
                User u = currentUserFuture.get();
                Button supervisor = view.findViewById(R.id.userSelectSupBtn);
                if (u == null){
                    supervisor.setOnClickListener(v -> {
                        Navigation.findNavController(v).navigate(R.id.action_userSelect_to_supervisorProfileForm);
                    });
                } else {
                    supervisor.setOnClickListener((v -> {
                        Navigation.findNavController(v).navigate(R.id.action_userSelect_to_supervisorProfile);
                    }));
                }
            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

    }

}