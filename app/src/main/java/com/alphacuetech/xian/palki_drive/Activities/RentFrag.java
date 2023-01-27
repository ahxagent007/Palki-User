package com.alphacuetech.xian.palki_drive.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.alphacuetech.xian.palki_drive.R;

public class RentFrag extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragrent);
        LinearLayout sedan=findViewById(R.id.sedan);
        LinearLayout mini=findViewById(R.id.mini);
        LinearLayout micro=findViewById(R.id.micro);

        sedan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(RentFrag.this, Rental.class);
                i.putExtra("carT","Sedan");
                i.putExtra("seatC","4 seat");
                startActivity(i);
            }
        });
        mini.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(RentFrag.this, Rental.class);
                i.putExtra("carT","Mini");
                i.putExtra("seatC","8 seat");
                startActivity(i);
            }
        });
        micro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(RentFrag.this, Rental.class);
                i.putExtra("carT","Micro");
                i.putExtra("seatC","12 seat");
                startActivity(i);
            }
        });
    }
}