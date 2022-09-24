package com.example.test;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;


import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;


import com.google.android.material.tabs.TabLayout;


public class DefaultActivity extends AppCompatActivity {

    TabLayout tab ;
    ViewPager mypage ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_default);

        getSupportActionBar().setBackgroundDrawable( new ColorDrawable(getResources().getColor(R.color.appcolour)));

        tab = findViewById(R.id.tab);
        mypage = findViewById(R.id.mypage);

        mypage.setAdapter( new FragmentAdapter(getSupportFragmentManager()));
        tab.setupWithViewPager(mypage);

    }
}