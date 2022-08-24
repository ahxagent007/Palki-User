package com.alphacuetech.xian.palki_drive.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.Switch;
import android.widget.TextView;

import com.alphacuetech.xian.palki_drive.R;

public class ConfirmActivity extends AppCompatActivity {

    TextView TV_carModel, TV_pickupPoint, TV_dropPoint;
    ImageView IV_car;
    ImageButton ibtn_editCar;
    Switch S_date, S_roundTrip;

    LinearLayout LL_date, LL_roundTrip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm);

        TV_carModel = findViewById(R.id.TV_carModel);
        TV_pickupPoint = findViewById(R.id.TV_pickupPoint);
        TV_dropPoint = findViewById(R.id.TV_dropPoint);
        IV_car = findViewById(R.id.IV_car);
        ibtn_editCar = findViewById(R.id.ibtn_editCar);
        S_date = findViewById(R.id.S_date);
        S_roundTrip = findViewById(R.id.S_roundTrip);

        S_date.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b == true){

                }
            }
        });

        S_date.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b == true){

                }

            }
        });


    }
}