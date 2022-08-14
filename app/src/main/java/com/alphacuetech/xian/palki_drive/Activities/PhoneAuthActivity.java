package com.alphacuetech.xian.palki_drive.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import com.alphacuetech.xian.palki_drive.R;
import com.google.firebase.auth.FirebaseAuth;

public class PhoneAuthActivity extends AppCompatActivity {
    String TAG = "XIAN";
    String phoneNumber;

    private FirebaseAuth mAuth;

    // variable for our text input
    // field for phone and OTP.
    private EditText edtPhone, edtOTP;

    // buttons for generating OTP and verifying OTP
    private Button btn_verifyOTPBtn, tv_generateOTPBtn;

    // string for storing our verification ID
    private String verificationId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_auth);

        phoneNumber = getIntent().getStringExtra("PHONE");

        Log.d(TAG, "onCreate: "+phoneNumber);


    }
}