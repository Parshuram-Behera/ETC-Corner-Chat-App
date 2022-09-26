package com.example.test;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.database.FirebaseDatabase;

import java.util.concurrent.TimeUnit;


public class VerifyActivity extends AppCompatActivity {

    TextView btn_next2 ;
    EditText otp1 , otp2 , otp3 , otp4 , otp5 , otp6 ;
    String otpid;
    ProgressBar progressBar2 ;
    private FirebaseAuth mAuth ;
    FirebaseDatabase database ;
    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify);
        getSupportActionBar().hide();

        btn_next2 = findViewById(R.id.btn_next2);

        database = FirebaseDatabase.getInstance();
        otp1 = findViewById(R.id.otp1);
        otp2 = findViewById(R.id.otp2);
        otp3 = findViewById(R.id.otp3);
        otp4 = findViewById(R.id.otp4);
        otp5 = findViewById(R.id.otp5);
        otp6 = findViewById(R.id.otp6);
        mAuth = FirebaseAuth.getInstance();

        progressBar2 = findViewById(R.id.progressBar2);
        progressBar2.setVisibility(View.GONE);
        otpid = getIntent().getStringExtra("otpid");



        btn_next2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!otp1.getText()
                        .toString().trim()
                        .isEmpty() && !otp2.getText()
                        .toString().trim().isEmpty() && !otp3
                        .getText().toString().trim().isEmpty() &&
                        !otp4.getText().toString().trim().isEmpty() && !otp5
                        .getText().toString().trim().isEmpty() && !otp6.getText()
                        .toString().trim().isEmpty())
                {

                    String otpcode =
                            otp1.getText().toString() +
                                    otp2.getText().toString() +
                                    otp3.getText().toString() +
                                    otp4.getText().toString() +
                                    otp5.getText().toString() +
                                    otp6.getText().toString() ;

                    if ( otpid != null){

                        progressBar2.setVisibility(View.VISIBLE);
                        btn_next2.setVisibility(View.GONE);

                        PhoneAuthCredential phoneAuthCredential = PhoneAuthProvider.getCredential( otpid ,otpcode);
                        FirebaseAuth.getInstance().signInWithCredential(phoneAuthCredential)
                                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                    @Override
                                    public void onComplete(@NonNull Task<AuthResult> task) {
                                        progressBar2.setVisibility(View.GONE);
                                        btn_next2.setVisibility(View.VISIBLE);

                                        if ( task.isSuccessful()){

//                                            Usersdata usersdata = new Usersdata(getIntent().getStringExtra("usernumber") );
//                                            String userid = task.getResult().getUser().getUid();

//                                            database.getReference().child("Userdata").child(userid).setValue(usersdata);


                                            Intent intent = new Intent( VerifyActivity.this , DefaultActivity.class);
                                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                            startActivity(intent);

                                        }
                                        else {
                                            Toast.makeText(VerifyActivity.this, "Incorrect OTP", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });

                    }
                    else {
                        Toast.makeText(VerifyActivity.this, "Check Internet", Toast.LENGTH_SHORT).show();

                    }


                }
                else {


                    Toast.makeText(VerifyActivity.this, "Fill All The OTP", Toast.LENGTH_SHORT).show();
                }
            }
        });



        numotpmove();




        findViewById(R.id.resendotp).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressBar2.setVisibility(View.VISIBLE);
                mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

                    @Override
                    public void onVerificationCompleted(PhoneAuthCredential credential) {

                    }

                    @Override
                    public void onVerificationFailed(FirebaseException e) {
                        Toast.makeText(VerifyActivity.this,"Check Internet", Toast.LENGTH_SHORT).show();

                    }

                    @Override
                    public void onCodeSent(@NonNull String verificationId,
                                           @NonNull PhoneAuthProvider.ForceResendingToken token) {

                        Toast.makeText(VerifyActivity.this, " OTP Sent", Toast.LENGTH_SHORT).show();
                        otpid = verificationId ;
                        progressBar2.setVisibility(View.GONE);
                    }
                };

                PhoneAuthOptions options =
                        PhoneAuthOptions.newBuilder(mAuth)
                                .setPhoneNumber("+91" + getIntent().getStringExtra("usernumber"))       // Phone number to verify
                                .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
                                .setActivity(VerifyActivity.this)                 // Activity (for callback binding)
                                .setCallbacks(mCallbacks)          // OnVerificationStateChangedCallbacks
                                .build();
                PhoneAuthProvider.verifyPhoneNumber(options);
            }
        });

//        btn_next2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if (enterotp.getText().toString().trim().isEmpty()){
//
//                    Toast.makeText(ResisterActivity.this, "Enter OTP", Toast.LENGTH_SHORT).show();
//                }
//                else if (password.getText().toString().trim().isEmpty()){
//                    Toast.makeText(ResisterActivity.this, " Enter Password", Toast.LENGTH_SHORT).show();
//                }
//
//                else{
//                    if (verificationcode != null){
//                        progressDialog.setTitle("Creating Account");
//                        progressDialog.setMessage("Checking OTP");
//                        progressDialog.show();
//                        String code = enterotp.getText().toString().trim();
//                        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(verificationcode , code);
//                        FirebaseAuth.getInstance().signInWithCredential(credential).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
//                            @Override
//                            public void onComplete(@NonNull Task<AuthResult> task) {
//                                progressDialog.dismiss();
//                                if (task.isSuccessful()){
//                                    Intent intent = new Intent(ResisterActivity.this , MainActivity.class);
//                                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
//                                    startActivity(intent);
//                                }
//                                else {
//                                    Toast.makeText(ResisterActivity.this, "OTP is not Valid", Toast.LENGTH_SHORT).show();
//                                }
//                            }
//                        });
//                    }
//
//
//                }
//            }
//        });
    }

    private void numotpmove() {

        otp1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                if (!charSequence.toString().trim().isEmpty()){

                    otp2.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        otp2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                if (!charSequence.toString().trim().isEmpty()){
                    otp3.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        otp3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                if (!charSequence.toString().trim().isEmpty()){

                    otp4.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        otp4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                if (!charSequence.toString().trim().isEmpty()){
                    otp5.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        otp5.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                if (!charSequence.toString().trim().isEmpty()){

                    otp6.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }
}