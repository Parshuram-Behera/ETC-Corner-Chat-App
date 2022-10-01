package com.example.test;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


public class ProfileActivity extends AppCompatActivity {

    ImageView select_img ;
    TextView btn_next3;
    EditText user_name;
    EditText user_about;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_setup);

//        getSupportActionBar().setBackgroundDrawable( new ColorDrawable(getResources().getColor(R.color.appcolour)));
        getSupportActionBar().hide();


        select_img = findViewById(R.id.profile_image);

        btn_next3 = findViewById(R.id.btn_next3);

        user_name = findViewById(R.id.User_name);

        user_about = findViewById(R.id.User_about);








        select_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                startActivityForResult(intent , 45);

            }
        });


        btn_next3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (user_name.getText().toString().trim().isEmpty()){
                    Toast.makeText(ProfileActivity.this, "Please Set Your Name", Toast.LENGTH_SHORT).show();
                }
                else {
                    Intent intent = new Intent(ProfileActivity.this ,UserInterActionActivity.class);
                    intent.putExtra("UserAbout" ,user_about.getText().toString().trim());
                    startActivity(intent);

                }

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