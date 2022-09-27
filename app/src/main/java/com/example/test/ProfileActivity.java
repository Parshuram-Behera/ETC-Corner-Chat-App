package com.example.test;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;


public class ProfileActivity extends AppCompatActivity {

    ImageView select_img ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_setup);

//        getSupportActionBar().setBackgroundDrawable( new ColorDrawable(getResources().getColor(R.color.appcolour)));
        getSupportActionBar().hide();


        select_img = findViewById(R.id.profile_image);

        select_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                startActivityForResult(intent , 45);

            }
        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if ( data != null){

            if ( data.getData() != null){
                select_img.setImageURI(data.getData());
            }
        }
    }
}