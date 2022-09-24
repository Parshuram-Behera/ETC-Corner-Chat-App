package com.example.test;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.FirebaseException;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

TextView btn_next ;
EditText ph_num ;
private FirebaseAuth mAuth ;
ProgressBar progressBar ;
private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        btn_next = findViewById(R.id.btn_next);
        ph_num = findViewById(R.id.phone_number);
        mAuth = FirebaseAuth.getInstance();
        progressBar = findViewById(R.id.progressBar);
        progressBar.setVisibility( View.GONE);



        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if ( ph_num.getText().toString().trim().isEmpty()){

                    Toast.makeText(MainActivity.this, "Enter Number ", Toast.LENGTH_SHORT).show();
                }
                else if (ph_num.getText().toString().trim().length() != 10){
                    Toast.makeText(MainActivity.this, "Enter Valid Number", Toast.LENGTH_SHORT).show();
                }
                else{



                    generateotp();

                }
            }
        });




    }

    private void generateotp() {

        btn_next.setVisibility(View.GONE);

        progressBar.setVisibility(View.VISIBLE);
        mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

            @Override
            public void onVerificationCompleted(PhoneAuthCredential credential) {

            }

            @Override
            public void onVerificationFailed(FirebaseException e) {
                Toast.makeText(MainActivity.this,"Check Internet", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onCodeSent(@NonNull String verificationId,
                                   @NonNull PhoneAuthProvider.ForceResendingToken token) {

                Toast.makeText(MainActivity.this, " OTP Sent", Toast.LENGTH_SHORT).show();
                progressBar.setVisibility(View.GONE);
                btn_next.setVisibility(View.VISIBLE);
                Intent intent = new Intent(MainActivity.this , VerifyActivity.class);
                intent.putExtra("otpid",verificationId);
                intent.putExtra("usernumber" ,ph_num.getText().toString());
                startActivity(intent);

            }
        };

        PhoneAuthOptions options =
                PhoneAuthOptions.newBuilder(mAuth)
                        .setPhoneNumber("+91" + ph_num.getText().toString().trim())       // Phone number to verify
                        .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
                        .setActivity(this)                 // Activity (for callback binding)
                        .setCallbacks(mCallbacks)          // OnVerificationStateChangedCallbacks
                        .build();
        PhoneAuthProvider.verifyPhoneNumber(options);
    }
}