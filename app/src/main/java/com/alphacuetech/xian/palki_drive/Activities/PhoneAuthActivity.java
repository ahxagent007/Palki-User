package com.alphacuetech.xian.palki_drive.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.animation.ValueAnimator;
import android.app.Dialog;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alphacuetech.xian.palki_drive.R;
import com.alphacuetech.xian.palki_drive.SharedPreff;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.TimeUnit;

public class PhoneAuthActivity extends AppCompatActivity {
    String TAG = "XIAN";
    String phoneNumber;
    String check="";

    LinearLayout wait;
    private FirebaseAuth mAuth;


    // variable for our text input
    // field for phone and OTP.
    private EditText  ET_OTP;

    // buttons for generating OTP and verifying OTP
    private Button btn_verifyOTPBtn;
    TextView tv_generateOTPBtn;

    // string for storing our verification ID
    private String verificationId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_auth);

        btn_verifyOTPBtn = findViewById(R.id.btn_verifyOTPBtn);
        tv_generateOTPBtn = findViewById(R.id.tv_generateOTPBtn);
        ET_OTP = findViewById(R.id.ET_OTP);

        phoneNumber = getIntent().getStringExtra("PHONE");

        phoneNumber = "+88"+phoneNumber;
        wait=findViewById(R.id.wait);
        wait.setVisibility(View.GONE);

        Log.d(TAG, "onCreate: "+phoneNumber);

        // below line is for getting instance
        // of our FirebaseAuth.
        mAuth = FirebaseAuth.getInstance();

        sendVerificationCode(phoneNumber);

        btn_verifyOTPBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              // Intent i = new Intent(getApplicationContext(), CarDetails.class);
               //startActivity(i);





               // Intent i = new Intent(getApplicationContext(), UserDetails.class);
              //  startActivity(i);
                FirebaseUser currentUser = mAuth.getCurrentUser();
               // Log.i("jap",currentUser.getUid());
                SharedPreff s=new SharedPreff(getApplicationContext());

                //new SharedPreff(getApplicationContext()).setFirebaseUID(currentUser.getUid());

              //  s.setFirebaseUID("tlOD3NoH8sP5P3M1j4kKmZQuiBg2");

                // validating if the OTP text field is empty or not.
                if (TextUtils.isEmpty(ET_OTP.getText().toString())) {
                    // if the OTP text field is empty display
                    // a message to user to enter OTP
                    Toast.makeText(getApplicationContext(), "Please enter OTP", Toast.LENGTH_SHORT).show();
                } else {
                    // if OTP field is not empty calling
                    // method to verify the OTP.
                    waitAnim();
                    btn_verifyOTPBtn.setVisibility(View.GONE);
                    wait.setVisibility(View.VISIBLE);
                    verifyCode(ET_OTP.getText().toString());


                }
            }
        });

    }

    private void signInWithCredential(PhoneAuthCredential credential) {
        // inside this method we are checking if
        // the code entered is correct or not.
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // if the code is correct and the task is successful
                            // we are sending our user to new activity.
                           // Intent i = new Intent(getApplicationContext(), MainActivity.class);
                           // startActivity(i);

                            FirebaseUser currentUser = mAuth.getCurrentUser();
                            SharedPreff s=new SharedPreff(getApplicationContext());
                            Log.i("ja",currentUser.getUid());
                            //new SharedPreff(getApplicationContext()).setFirebaseUID(currentUser.getUid());
                            //s.setFirebaseUID(currentUser.getUid());

                            String url="https://Dolnabd.com/api/Rider/Get/Details/"+currentUser.getUid();
                            StringRequest stringRequest=new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {

                                    JSONObject obj ;
                                    try {
                                        obj = new JSONObject(response);

                                        check=obj.toString();
                                        Log.i("jap","age"+check.length());
                                        // System.out.println("sd"+obj.toString());
                                        //  det.setVisibility(View.VISIBLE);
                                    } catch (JSONException e) {
                                        e.printStackTrace();

                                    }

                                    if(check.length()>10){
                                        // Log.i("jap","hurre");
                                        Intent i = new Intent(getApplicationContext(), MainActivity.class);
                                        s.setFirebaseUID(currentUser.getUid());
                                        i.putExtra("user_id",currentUser.getUid());
                                        startActivity(i);
                                    }
                                    else{
                                        // Log.i("jap","nothurre");
                                        Intent i = new Intent(getApplicationContext(), UserDetails.class);
                                        i.putExtra("user_id",currentUser.getUid());

                                        startActivity(i);
                                    }


                                }
                            },new Response.ErrorListener(){

                                @Override
                                public void onErrorResponse(VolleyError error) {

                                    // Log.i("yap","Yeap");

                                }
                            });
                            RequestQueue requestQueue= Volley.newRequestQueue(PhoneAuthActivity.this);
                            requestQueue.add(stringRequest);

                            Log.i("jap","page"+check.length());






                            //finish();
                        } else {
                            // if the code is not correct then we are
                            // displaying an error message to the user.
                            Toast.makeText(getApplicationContext(), task.getException().getMessage(), Toast.LENGTH_LONG).show();
                            Log.i("xin",task.getException().getMessage());
                            btn_verifyOTPBtn.setVisibility(View.VISIBLE);
                            wait.setVisibility(View.GONE);
                            //System.out.println( task.getException().getMessage());
                        }
                    }
                });
    }

    private void sendVerificationCode(String number) {
        // this method is used for getting
        // OTP on user phone number.
        PhoneAuthOptions options =
                PhoneAuthOptions.newBuilder(mAuth)
                        .setPhoneNumber(number)            // Phone number to verify
                        .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
                        .setActivity(this)                 // Activity (for callback binding)
                        .setCallbacks(mCallBack)           // OnVerificationStateChangedCallbacks
                        .build();
        PhoneAuthProvider.verifyPhoneNumber(options);

        Toast.makeText(getApplicationContext(), "SMS sent to "+phoneNumber, Toast.LENGTH_LONG).show();
    }

    // callback method is called on Phone auth provider.
    private PhoneAuthProvider.OnVerificationStateChangedCallbacks

    // initializing our callbacks for on
    // verification callback method.
    mCallBack = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

    // below method is used when
    // OTP is sent from Firebase
    @Override
    public void onCodeSent(String s, PhoneAuthProvider.ForceResendingToken forceResendingToken) {
        super.onCodeSent(s, forceResendingToken);
        // when we receive the OTP it
        // contains a unique id which
        // we are storing in our string
        // which we have already created.
        verificationId = s;
    }

    // this method is called when user
    // receive OTP from Firebase.
    @Override
    public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {
        // below line is used for getting OTP code
        // which is sent in phone auth credentials.
        final String code = phoneAuthCredential.getSmsCode();

        // checking if the code
        // is null or not.
        if (code != null) {
            // if the code is not null then
            // we are setting that code to
            // our OTP edittext field.
            ET_OTP.setText(code);
            Toast.makeText(getApplicationContext(), "CODE is "+code, Toast.LENGTH_LONG).show();

            // after setting this code
            // to OTP edittext field we
            // are calling our verifycode method.
            verifyCode(code);
        }
    }

    // this method is called when firebase doesn't
    // sends our OTP code due to any error or issue.

    @Override
    public void onVerificationFailed(FirebaseException e) {
        // displaying error message with firebase exception.
        Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        Log.i("xin",e.getMessage());
        btn_verifyOTPBtn.setVisibility(View.VISIBLE);
        wait.setVisibility(View.GONE);
    }
};

    // below method is use to verify code from Firebase.
    private void verifyCode(String code) {
        // below line is used for getting
        // credentials from our verification id and code.
        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(verificationId, code);

        // after getting credential we are
        // calling sign in method.
        signInWithCredential(credential);
    }
    public void waitAnim(){
        ImageView i2=findViewById(R.id.wait_b);
        ImageView i1=findViewById(R.id.wait_a);
        ImageView i3=findViewById(R.id.wait_c);
        Animation anim2 = new ScaleAnimation(
                1f, 1f, // Start and end values for the X axis scaling
                1f, 0.5f, // Start and end values for the Y axis scaling
                Animation.RELATIVE_TO_SELF, 0.5f, // Pivot point of X scaling
                Animation.RELATIVE_TO_SELF, 0.5f); // Pivot point of Y scaling
        anim2.setFillAfter(true);
        anim2.setRepeatCount(15);
        anim2.setRepeatMode(ValueAnimator.REVERSE);// Needed to keep the result of the animation
        anim2.setDuration(500);

        Animation anim1 = new ScaleAnimation(
                1f, 1f, // Start and end values for the X axis scaling
                1f, 2f, // Start and end values for the Y axis scaling
                Animation.RELATIVE_TO_SELF, 0.5f, // Pivot point of X scaling
                Animation.RELATIVE_TO_SELF, 0.5f); // Pivot point of Y scaling
        anim1.setFillAfter(true);
        anim1.setRepeatCount(15);
        anim1.setRepeatMode(ValueAnimator.REVERSE);// Needed to keep the result of the animation
        anim1.setDuration(500);

        Animation anim3 = new ScaleAnimation(
                1f, 1f, // Start and end values for the X axis scaling
                1f, 2f, // Start and end values for the Y axis scaling
                Animation.RELATIVE_TO_SELF, 0.5f, // Pivot point of X scaling
                Animation.RELATIVE_TO_SELF, 0.5f); // Pivot point of Y scaling
        anim3.setFillAfter(true);
        anim3.setRepeatCount(15);
        anim3.setRepeatMode(ValueAnimator.REVERSE);// Needed to keep the result of the animation
        anim3.setDuration(500);







        i2.startAnimation(anim2);
        i1.startAnimation(anim1);
        i3.startAnimation(anim3);
    }

}