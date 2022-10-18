package com.frazmatic.logitrack;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import com.amplifyframework.auth.AuthProvider;
import com.amplifyframework.core.Amplify;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button loginBtn = findViewById(R.id.MainActivityLoginBtn);
        loginBtn.setOnClickListener( view ->{
            Amplify.Auth.signInWithSocialWebUI(AuthProvider.google(), this,
                    result -> Log.i("AuthQuickstart", result.toString()),
                    error -> Log.e("AuthQuickstart", error.toString())
            );
            Intent goToNavHost = new Intent(MainActivity.this, NavHostActivity.class);
            startActivity(goToNavHost);
        });



    }



}