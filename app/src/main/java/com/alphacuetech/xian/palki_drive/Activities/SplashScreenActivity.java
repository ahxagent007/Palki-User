package com.alphacuetech.xian.palki_drive.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.Button;

import com.alphacuetech.xian.palki_drive.DataModel.Auction;
import com.alphacuetech.xian.palki_drive.R;
import com.alphacuetech.xian.palki_drive.SharedPreff;
import com.alphacuetech.xian.palki_drive.utills.CommonFunctions;
import com.google.firebase.FirebaseApp;
//import com.google.firebase.appcheck.FirebaseAppCheck;
//import com.google.firebase.appcheck.safetynet.SafetyNetAppCheckProviderFactory;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SplashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        //initializeAppCheck();

        //-------------------
        /*FirebaseDatabase database;
        DatabaseReference myRef;


        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("AUCTION");

        long milis = new CommonFunctions().getCurrentTimeMilis();
        String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();

        Auction auction = new Auction(milis, uid, "loc_from", "loc_to", null, null, true, null,
                null, "round_trip", "vehicle");

        myRef.child(""+milis).setValue(auction);
        Log.i("XIAN", "FIREBASE ADDED");*/

        //--------------

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {

                if (!new SharedPreff(getApplicationContext()).getFirebaseUID().equals("#")){
                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                    finish();
                }else{
                    startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                }

                finish();
            }
        }, 3000);   //5 seconds


    }

   /* public void initializeAppCheck(){
        FirebaseApp.initializeApp(getApplicationContext());
        FirebaseAppCheck firebaseAppCheck = FirebaseAppCheck.getInstance();
        firebaseAppCheck.installAppCheckProviderFactory(
                SafetyNetAppCheckProviderFactory.getInstance());
    }
    */

}