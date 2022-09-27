package com.example.test;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.google.firebase.auth.FirebaseAuth;

public class SplashActivity extends AppCompatActivity {


    FirebaseAuth mAuth ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAuth = FirebaseAuth.getInstance();
        if (mAuth.getCurrentUser() != null){

            startActivity( new Intent(SplashActivity.this , ProfileActivity.class));
            finish();

        }
        else {
            startActivity( new Intent(SplashActivity.this , MainActivity.class));
            finish();
        }
    }
}