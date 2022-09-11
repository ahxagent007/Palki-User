package com.alphacuetech.xian.palki_drive.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;

import com.alphacuetech.xian.palki_drive.DataModel.Auction;
import com.alphacuetech.xian.palki_drive.R;
import com.alphacuetech.xian.palki_drive.DataModel.MapsData;
import com.alphacuetech.xian.palki_drive.utills.CommonFunctions;
import com.google.android.gms.maps.model.LatLng;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.gson.Gson;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;

public class ConfirmActivity extends AppCompatActivity {

    String TAG = "XIAN";

    TextView TV_carModel, TV_pickupPoint, TV_dropPoint;
    ImageView IV_car;
    ImageButton ibtn_editCar;
    Switch S_date, S_roundTrip;
    Button btn_confirm;

    LinearLayout LL_date, LL_roundTrip;

    EditText dpc_date, ET_comment;
    final Calendar myCalendar = Calendar.getInstance();

    HashMap<String, String> seatCapacity;

    String selectedDate, comment = "";
    boolean isFutureDate, isRoundTrip;

    // Write a message to the database
    FirebaseDatabase database;
    DatabaseReference myRef;

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
        btn_confirm = findViewById(R.id.btn_confirm);
        LL_date = findViewById(R.id.LL_date);
        LL_roundTrip = findViewById(R.id.LL_roundTrip);
        dpc_date = findViewById(R.id.dpc_date);
        ET_comment = findViewById(R.id.ET_comment);

        LL_date.setVisibility(View.GONE);
        LL_roundTrip.setVisibility(View.GONE);

        isRoundTrip = false;
        isFutureDate = false;

        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("AUCTION");

        seatCapacity = new HashMap<String, String>();
        seatCapacity.put("Sedan", "4 Seat Capacity");
        seatCapacity.put("Premium Sedan", "4 Seat Capacity");
        seatCapacity.put("Mini Microbus", "7 Seat Capacity");
        seatCapacity.put("Microbus", "11 Seat Capacity");
        seatCapacity.put("Minibus", "28 Seat Capacity");
        seatCapacity.put("Bike", "1 Pillion Capacity");

        String json_data = getIntent().getStringExtra("JSON_DATA");
        Gson gson = new Gson();
        //Transform a json to java object
        MapsData selectedData = gson.fromJson(json_data, MapsData.class);

        TV_carModel.setText(selectedData.getMODEL()+"\n"+seatCapacity.get(selectedData.getMODEL()));
        TV_pickupPoint.setText(selectedData.getSTART());
        TV_dropPoint.setText(selectedData.getEND());


        S_date.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b){
                    LL_date.setVisibility(View.VISIBLE);
                    isFutureDate = true;
                }else{
                    LL_date.setVisibility(View.GONE);
                    isFutureDate = false;
                }
            }
        });

        S_roundTrip.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b){
                    LL_roundTrip.setVisibility(View.VISIBLE);
                    isRoundTrip = true;
                }else{
                    LL_roundTrip.setVisibility(View.GONE);
                    isRoundTrip = false;
                }

            }
        });


        DatePickerDialog.OnDateSetListener date =new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH,month);
                myCalendar.set(Calendar.DAY_OF_MONTH,day);
                updateLabel();
            }
        };
        dpc_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(ConfirmActivity.this, date,myCalendar.get(Calendar.YEAR),myCalendar.get(Calendar.MONTH),myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        btn_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(isRoundTrip){
                    comment = ET_comment.getText().toString();
                }

                //Add to firebase
                createNewAuctionFirebase(selectedData.getSTART(), selectedData.getEND(), selectedData.getCurrentLatLng(),
                        selectedData.getDestLatLng(), isRoundTrip, isFutureDate, dateSelectedDate, comment, selectedData.getMODEL());

                //goto another activity
                startActivity(new Intent(getApplicationContext(), BiddingActivity.class));
                //finish this activity
                finish();

            }
        });

    }
    Date dateSelectedDate;
    private void updateLabel(){
        String myFormat="MM/dd/yy";
        SimpleDateFormat dateFormat=new SimpleDateFormat(myFormat, Locale.US);
        dpc_date.setText(dateFormat.format(myCalendar.getTime()));
        selectedDate = dateFormat.format(myCalendar.getTime());
        dateSelectedDate = myCalendar.getTime();
    }


    private void createNewAuctionFirebase(String loc_from, String loc_to, LatLng from_latLng, LatLng to_latLng, Boolean round_trip_bool, Boolean date_future, Date date, String round_trip, String vehicle){
        long milis = new CommonFunctions().getCurrentTimeMilis();
        String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();

        Auction auction = new Auction(milis, uid, loc_from, loc_to, from_latLng, to_latLng, round_trip_bool, date_future, date, round_trip, vehicle);

        myRef.child(""+milis).setValue(auction);
        Log.i(TAG, "FIREBASE ADDED");
    }


}