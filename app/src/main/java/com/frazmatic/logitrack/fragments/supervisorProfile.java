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
import android.widget.TextView;

import com.amplifyframework.api.graphql.model.ModelQuery;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.generated.model.User;
import com.frazmatic.logitrack.MainActivity;
import com.frazmatic.logitrack.R;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link supervisorProfile#newInstance} factory method to
 * create an instance of this fragment.
 */
public class supervisorProfile extends Fragment {

    private SharedPreferences settings;
    private SharedPreferences.Editor editor;
    private CompletableFuture<User> userCompletableFuture;

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
        settings = PreferenceManager.getDefaultSharedPreferences(getContext());
        editor = settings.edit();
        userCompletableFuture = new CompletableFuture<>();

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_supervisor_profile, container, false);

        saveNames();
        fillInTextFields(view);

        Button currentTrips = view.findViewById(R.id.supervisorProfileCurrentTripsBtn);
        currentTrips.setOnClickListener(v -> {
            Navigation.findNavController(v).navigate(R.id.action_supervisorProfile_to_supervisorTripStatus);
        });

        Button seeTeamMap = view.findViewById(R.id.buttonSupervisorTeamMembers);
        seeTeamMap.setOnClickListener(v -> {
            Navigation.findNavController(v).navigate(R.id.action_supervisorProfile_to_mapsFragmentSeeFirmMembers);
        });
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
        TextView user = view.findViewById(R.id.textViewSupervisorProfileUserName);
        TextView firm = view.findViewById(R.id.textViewSupervisorProfileCompanyName);
        try {
            User u = userCompletableFuture.get();
            getActivity().runOnUiThread(() -> {
                user.setText(u.getName());
                firm.setText(u.getFirm().getName());
            });
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}