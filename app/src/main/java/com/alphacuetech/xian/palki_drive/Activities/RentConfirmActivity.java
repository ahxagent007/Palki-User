package com.alphacuetech.xian.palki_drive.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.alphacuetech.xian.palki_drive.DataModel.RentalData;
import com.alphacuetech.xian.palki_drive.R;
import com.alphacuetech.xian.palki_drive.SharedPreff;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RentConfirmActivity extends AppCompatActivity {

    RentalData rentalData;
    CardView promo_card;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rent_confirm);
        Button confirm=findViewById(R.id.confirm_trip);
        ImageButton back=  findViewById(R.id.backbtn);
        Button add_promo=findViewById(R.id.add_promo);
        promo_card=findViewById(R.id.promo_card);

        ImageView car_photo=findViewById(R.id.car_photo);

        TextView car_type=findViewById(R.id.car_type);
        TextView seat_cap=findViewById(R.id.seat_cap);
       TextView start=findViewById(R.id.start_trip);
        TextView end=findViewById(R.id.dest_trip);

        TextView date=findViewById(R.id.date);
        TextView time=findViewById(R.id.time);
        TextView r_t=findViewById(R.id.round_trip);
        TextView min_bdt=findViewById(R.id.min_bdt);


        SharedPreff shared=new SharedPreff(getApplicationContext());
       rentalData=new RentalData();
        Bundle extras = getIntent().getExtras();

        if(extras == null) {

        } else {
            Log.i("jap",extras.getString("start"));
            String s=extras.getString("car_type");
            car_type.setText(s);
            rentalData.setCarType(car_type.getText().toString());

            seat_cap.setText(extras.getString("seat_cap"));
            rentalData.setSeatcap(seat_cap.getText().toString());

            start.setText(extras.getString("start"));
            rentalData.setCurrentLoc(start.getText().toString());

            end.setText(extras.getString("end"));
            rentalData.setDestLoc(end.getText().toString());

            date.setText(extras.getString("date"));
            rentalData.setDate_(date.getText().toString());

            time.setText(extras.getString("time"));
            rentalData.setTime_(time.getText().toString());

            r_t.setText(extras.getString("r_t"));
            min_bdt.setText(extras.getString("min_fare"));


            rentalData.setRoundTrip(r_t.getText().toString());

            rentalData.setNote(extras.getString("note"));
            rentalData.setUser_id(shared.getFirebaseUID());
            rentalData.setFrom_latLng(extras.getString("start_latLng"));
            rentalData.setTo_latLng(extras.getString("end_latLng"));




            if(s.equals("Sedan")){

                // Log.i("nah","A");
                Bitmap bImage = BitmapFactory.decodeResource(this.getResources(), R.drawable.prvt);
                car_photo.setImageBitmap(bImage);
            }
            else  if(s.equals("Mini")){
                // Log.i("nah","B");
                Bitmap bImage = BitmapFactory.decodeResource(this.getResources(), R.drawable.mini);
                car_photo.setImageBitmap(bImage);
            }else  if(s.equals("Micro")){
                // Log.i("nah","C");
                Bitmap bImage = BitmapFactory.decodeResource(this.getResources(), R.drawable.micro);
                car_photo.setImageBitmap(bImage);
            }

        }
        add_promo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog(RentConfirmActivity.this);
                dialog.setContentView(R.layout.customdialog);
                Button dialogButton = (Button) dialog.findViewById(R.id.dialog_add_promo);
                EditText enter_promo=dialog.findViewById(R.id.enter_promo);
                // if button is clicked, close the custom dialog
                dialogButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(enter_promo.getText().toString().length()>0){
                            rentalData.setPromo(enter_promo.getText().toString());
                            promo_card.setVisibility(View.GONE);
                        }


                        dialog.dismiss();
                        Toast.makeText(getApplicationContext(),"Dismissed..!!",Toast.LENGTH_SHORT).show();
                    }
                });
                dialog.show();
            }
        });


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i =new Intent(getApplicationContext(),Rental.class);

                startActivity(i);
                finish();

            }
        });
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), BiddingOngoingActivity.class);
                Log.i("jap",rentalData.getUser_id());
                FirebaseDatabase firebaseDatabase;
                DatabaseReference databaseReference;
                firebaseDatabase=FirebaseDatabase.getInstance();
                databaseReference=firebaseDatabase.getReference("AUCTION2");
                String id=databaseReference.push().getKey();
                rentalData.setAuction_id(id);
                i.putExtra("auction_id",id);
                i.putExtra("car_type",rentalData.getCarType());
                i.putExtra("seat_cap",rentalData.getSeatcap());
                i.putExtra("from",rentalData.getCurrentLoc());
                i.putExtra("to",rentalData.getDestLoc());
                i.putExtra("date",rentalData.getDate_());
                i.putExtra("time",rentalData.getTime_());
                i.putExtra("r_t",rentalData.getRoundTrip());
                rentalData.setRunning("Yes");

                startService(new Intent(getApplicationContext(),PushNotificationService.class));

                databaseReference.child(id).setValue(rentalData);
                SharedPreff sharedPreff=new SharedPreff(RentConfirmActivity.this);
                sharedPreff.setOnGoingAuction(id);
                i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(i);
                finish();
            }
        });
    }
}