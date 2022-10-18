package com.frazmatic.logitrack;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.amplifyframework.api.graphql.model.ModelMutation;
import com.amplifyframework.auth.AuthProvider;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.generated.model.Todo;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Amplify.Auth.signInWithSocialWebUI(AuthProvider.google(), this,
                result -> Log.i("AuthQuickstart", result.toString()),
                error -> Log.e("AuthQuickstart", error.toString())
        );

    }



}