package com.example.test;

import androidx.appcompat.app.AppCompatActivity;


import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;


public class ProfileActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_setup);

//        getSupportActionBar().setBackgroundDrawable( new ColorDrawable(getResources().getColor(R.color.appcolour)));
        getSupportActionBar().hide();




    }
}