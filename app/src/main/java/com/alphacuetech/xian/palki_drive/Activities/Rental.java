package com.alphacuetech.xian.palki_drive.Activities;



import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.app.DatePickerDialog;
import android.app.FragmentManager;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.alphacuetech.xian.palki_drive.DataModel.RentalData;
import com.alphacuetech.xian.palki_drive.R;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;

import java.util.Calendar;

public class Rental extends AppCompatActivity {

    EditText start;

    EditText end;
    TextView date;
    TextView time;
    EditText note;
    Switch round_trip;
    String r_t=new String();
    TextView cartype;
    TextView seatcap;
    String start_latLng;
    String end_latLng;
    CardView promo_card;
    String min_fare;
    int allok=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rental);
        ImageView carimageforrent=findViewById(R.id.carimageforrent);
        start=findViewById(R.id.start);
        end=findViewById(R.id.dest);
        date=findViewById(R.id.date_click_settext);
        time=findViewById(R.id.time_click_settext);
        note=findViewById(R.id.req_trip_note);
        round_trip=findViewById(R.id.round_trip);
        cartype=findViewById(R.id.cartype);
        seatcap=findViewById(R.id.seatcap);
       promo_card=findViewById(R.id.promo_card);

        round_trip.setChecked(false);
        r_t="No";
        start.setText("start");
        end.setText("end");
        time.setText("time");
        date.setText("date");


        Bundle extras = getIntent().getExtras();

        if(extras == null) {

        } else {

            if(extras.getString("carT")!=null){
                String s=extras.getString("carT");
                cartype.setText(s);
                seatcap.setText(extras.getString("seatC"));
               // Log.i("nah",s);
                if(s.equals("Sedan")){

                   // Log.i("nah","A");
                    Bitmap bImage = BitmapFactory.decodeResource(this.getResources(), R.drawable.prvt);
                    carimageforrent.setImageBitmap(bImage);
                }
                else  if(s.equals("Mini")){
                   // Log.i("nah","B");
                    Bitmap bImage = BitmapFactory.decodeResource(this.getResources(), R.drawable.mini);
                    carimageforrent.setImageBitmap(bImage);
                }else  if(s.equals("Micro")){
                   // Log.i("nah","C");
                    Bitmap bImage = BitmapFactory.decodeResource(this.getResources(), R.drawable.micro);
                    carimageforrent.setImageBitmap(bImage);
                }
            }
            else{
                start_latLng=extras.getString("start_latLng");
                end_latLng=extras.getString("end_latLng");
                start.setText(extras.getString("currentPositionName"));
                end.setText(extras.getString("destinationName"));
                date.setText(extras.getString("date"));
                time.setText(extras.getString("time"));
                note.setText(extras.getString("note"));
                r_t=extras.getString("round_trip");
                min_fare=extras.getString("min_fare");
                Log.i("jap",start+"s"+end);
                if(r_t.equals("Yes")){
                    round_trip.setChecked(true);
                }
                else{
                    round_trip.setChecked(false);
                }
                String s=extras.getString("carType");
                cartype.setText(s);
                if(s.equals("Sedan")){

                    // Log.i("nah","A");
                    Bitmap bImage = BitmapFactory.decodeResource(this.getResources(), R.drawable.prvt);
                    carimageforrent.setImageBitmap(bImage);
                }
                else  if(s.equals("Mini")){
                    // Log.i("nah","B");
                    Bitmap bImage = BitmapFactory.decodeResource(this.getResources(), R.drawable.mini);
                    carimageforrent.setImageBitmap(bImage);
                }else  if(s.equals("Micro")){
                    // Log.i("nah","C");
                    Bitmap bImage = BitmapFactory.decodeResource(this.getResources(), R.drawable.micro);
                    carimageforrent.setImageBitmap(bImage);
                }
            }


        }
        //carimageforrent.setBackground(getDrawable(R.drawable.prvt));

        ImageButton back=findViewById(R.id.backbtn);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });




        Button req_trip_review=findViewById(R.id.req_trip_review);
        req_trip_review.setVisibility(View.VISIBLE);
        LinearLayout place_selector=findViewById(R.id.place_selector);
        place_selector.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent i = new Intent(Rental.this, MapsActivity.class);
                i.putExtra("date",date.getText());
                i.putExtra("time",time.getText());
                i.putExtra("note",note.getText());
                i.putExtra("carType",cartype.getText());
                i.putExtra("seatcap",seatcap.getText());
                i.putExtra("round_trip",r_t);

                Log.i("Nahid",date.getText()+" "+time.getText()+" "+note.getText()+" "+cartype.getText()+" "+seatcap.getText()+" "+r_t);

                startActivity(i);
                finish();
            }
        });


        LinearLayout l=findViewById(R.id.date_click);

        l.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar c = Calendar.getInstance();

                // on below line we are getting
                // our day, month and year.
                int year = c.get(Calendar.YEAR);
                int month = c.get(Calendar.MONTH);
                int day = c.get(Calendar.DAY_OF_MONTH);
                String m[] = {"Jan", "Feb", "Mar", "Apr","May", "Jun","Jul","Aug","Sep","Oct","Nov","Dec"};
                // on below line we are creating a variable for date picker dialog.
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        // on below line we are passing context.
                        Rental.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                                date.setText(dayOfMonth+", "+m[month]);
                                //rentalData.setDate_(dayOfMonth+", "+m[month]);


                                    req_trip_review.setVisibility(View.VISIBLE);


                            }
                        },
                        // on below line we are passing year,
                        // month and day for selected date in our date picker.
                        year, month, day);
                // at last we are calling show to
                // display our date picker dialog.
                datePickerDialog.getDatePicker().setMinDate(Calendar.getInstance().getTimeInMillis());
                datePickerDialog.show();

            }
        });

        LinearLayout t=findViewById(R.id.time_click);

        t.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar c = Calendar.getInstance();

                int hour = c.get(Calendar.HOUR_OF_DAY);
                int minute = c.get(Calendar.MINUTE);

                TimePickerDialog timePickerDialog = new TimePickerDialog(Rental.this,
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay,
                                                  int minute) {
                                // on below line we are setting selected time
                                // in our text view.
                                time.setText(hourOfDay+" : "+minute);
                                //rentalData.setTime_(hourOfDay+" : "+minute);

                                    req_trip_review.setVisibility(View.VISIBLE);


                            }
                        }, hour, minute, false);
                // at last we are calling show to
                // display our time picker dialog.

                timePickerDialog.show();
            }
        });

        round_trip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (round_trip.isChecked()) {
                  r_t="Yes";

                } else {
                    r_t="No";

                }
            }
        });
        req_trip_review.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(!start.getText().equals("start") && !end.getText().equals("end") && !date.getText().equals("date") && !time.getText().equals("time") ){
                    Intent i = new Intent(Rental.this, RentConfirmActivity.class);
                    i.putExtra("car_type",cartype.getText());
                    i.putExtra("seat_cap",seatcap.getText());
                    i.putExtra("start",start.getText().toString());

                    i.putExtra("end",end.getText().toString());

                    i.putExtra("date",date.getText());
                    i.putExtra("time",time.getText());
                    i.putExtra("r_t",r_t);
                    i.putExtra("start_latLng",start_latLng);

                    i.putExtra("end_latLng",end_latLng);

                    i.putExtra("note",note.getText());
                    i.putExtra("min_fare",min_fare);
                    Log.i("jap",i.getExtras().toString());

               /* FirebaseDatabase firebaseDatabase;
                DatabaseReference databaseReference;
                firebaseDatabase = FirebaseDatabase.getInstance("https://dolna-ride-default-rtdb.asia-southeast1.firebasedatabase.app");
                databaseReference = firebaseDatabase.getReference("AUCTION2");
                Log.i("nahid",databaseReference.toString());
               databaseReference.setValue("rentalData");
*/

                    startActivity(i);
                    finish();
                }
                else{
                    Toast.makeText(getApplicationContext(), "Please Enter All the Fields", Toast.LENGTH_LONG).show();
                }


            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        end.setHint(data.getStringExtra("Bla"));
        System.out.println("sddddddddddddddddddddddddddddddddddddddddddddddd");
    }
}