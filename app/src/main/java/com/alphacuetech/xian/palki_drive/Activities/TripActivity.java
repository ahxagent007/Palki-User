package com.alphacuetech.xian.palki_drive.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.alphacuetech.xian.palki_drive.R;

public class TripActivity extends AppCompatActivity {

    LinearLayout LL_sedan, LL_premium_sedan, LL_mini_microbus, LL_microbus, LL_minibus, LL_bike;
    String vehicle_models[] = {"Sedan", "Premium Sedan", "Mini Microbus", "Microbus", "Minibus", "Bike"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trip);

        LL_sedan = findViewById(R.id.LL_sedan);
        LL_premium_sedan = findViewById(R.id.LL_premium_sedan);
        LL_mini_microbus = findViewById(R.id.LL_mini_microbus);
        LL_minibus = findViewById(R.id.LL_minibus);
        LL_microbus = findViewById(R.id.LL_microbus);
        LL_bike = findViewById(R.id.LL_bike);

        Intent at =new Intent(getApplicationContext(), MapsActivity.class);

        LL_sedan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                at.putExtra("MODEL", vehicle_models[0]);
                startActivity(at);
            }
        });

        LL_premium_sedan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                at.putExtra("MODEL", vehicle_models[1]);
                startActivity(at);
            }
        });

        LL_mini_microbus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                at.putExtra("MODEL", vehicle_models[2]);
                startActivity(at);
            }
        });

        LL_minibus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                at.putExtra("MODEL", vehicle_models[3]);
                startActivity(at);
            }
        });

        LL_bike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                at.putExtra("MODEL", vehicle_models[4]);
                startActivity(at);
            }
        });


    }
}