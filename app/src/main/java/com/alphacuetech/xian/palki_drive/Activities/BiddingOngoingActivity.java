package com.alphacuetech.xian.palki_drive.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alphacuetech.xian.palki_drive.DataModel.Bid;
import com.alphacuetech.xian.palki_drive.DataModel.RentalData;
import com.alphacuetech.xian.palki_drive.R;
import com.alphacuetech.xian.palki_drive.SharedPreff;
import com.alphacuetech.xian.palki_drive.utills.MyAdapter;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class BiddingOngoingActivity extends AppCompatActivity {
    String auction_id;
    RecyclerView recyclerView;
    DatabaseReference databaseReference;
    MyAdapter adapter;
    ArrayList<Bid> bids;

    String car_type="";
    String seat_cap="";
    String from="";
    String to="";
    String date="";
    String time="";
    String r_t="";
    TextView time_remain;

    SharedPreff s;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bidding_ongoing);
        LinearLayout bottomtripreview=findViewById(R.id.bottom_trip_review);
        Bundle extras = getIntent().getExtras();
        waitAnim();
        if(extras == null) {

        } else {
            auction_id=extras.getString("auction_id");
            car_type=extras.getString("car_type");
            seat_cap=extras.getString("seat_cap");
           from=extras.getString("from");
            to=extras.getString("to");
            date=extras.getString("date");
            time=extras.getString("time");
            r_t=extras.getString("r_t");

        }
        s=new SharedPreff(getApplicationContext());
        auction_id=s.getOnGoingAuction();
        ImageButton back=findViewById(R.id.backbtn);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    //  /*
        FirebaseDatabase firebaseDatabase;
        DatabaseReference databaseReference;
        firebaseDatabase=FirebaseDatabase.getInstance();
        databaseReference=firebaseDatabase.getReference("BIDS2").child(auction_id);
        for(int i=0;i<1;i++){
            String id=databaseReference.push().getKey();
            Bid b=new Bid(id,auction_id,"Mcwv4mj4lURygyvMCoNJuKdOMKk2","sedan","4 seat","500","4.4","ac");
            databaseReference.child(id).setValue(b);
        }
//*/
        recyclerView=findViewById(R.id.lst);
        databaseReference= FirebaseDatabase.getInstance().getReference("BIDS2").child(auction_id);
        recyclerView.setHasFixedSize(false);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        bids=new ArrayList<>();
        adapter=new MyAdapter(this,bids);
        recyclerView.setAdapter(adapter);

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dsnapshot:snapshot.getChildren()){
                    Bid bid=dsnapshot.getValue(Bid.class);
                    bids.add(bid);
                    s.setOnGoingAuctionCount(s.getOnGoingAuctionCount()+1);
                   // Log.i("nh",user.name);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        time_remain=findViewById(R.id.time_remain);

       // countDownTimer.cancel();


        bottomtripreview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BottomSheetdialogue();
            }
        });
    }
    private void BottomSheetdialogue(){
        final BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(BiddingOngoingActivity.this);

        bottomSheetDialog.setContentView(R.layout.bottom_sheet_review_trip);
        LinearLayout bottom=bottomSheetDialog.findViewById(R.id.Rental_Details_bottom);
        TextView b_car_type=bottomSheetDialog.findViewById(R.id.bottom_sheet_car_type);
        TextView b_seat_cap=bottomSheetDialog.findViewById(R.id.bottom_sheet_seat_cap);
        TextView b_from=bottomSheetDialog.findViewById(R.id.bottom_sheet_start_trip);
        TextView b_to=bottomSheetDialog.findViewById(R.id.bottom_sheet_dest_trip);
        TextView b_date=bottomSheetDialog.findViewById(R.id.bottom_sheet_date);
        TextView b_time=bottomSheetDialog.findViewById(R.id.bottom_sheet_time);
        TextView b_r_t=bottomSheetDialog.findViewById(R.id.bottom_sheet_r_t);
        ImageView bottom_sheet_car_pic=bottomSheetDialog.findViewById(R.id.bottom_sheet_car_pic);
        Button cancel=bottomSheetDialog.findViewById(R.id.cancel_rent);


        b_car_type.setText(car_type);
        b_seat_cap.setText(seat_cap);
        b_from.setText(from);
        b_to.setText(to);
        b_date.setText(date);
        b_time.setText(time);
        b_r_t.setText(r_t);


        FirebaseDatabase firebaseDatabase;
        DatabaseReference databaseReference;
        firebaseDatabase=FirebaseDatabase.getInstance();
        databaseReference=firebaseDatabase.getReference("AUCTION2").child(auction_id);
        databaseReference.get().addOnSuccessListener(new OnSuccessListener<DataSnapshot>() {
            @Override
            public void onSuccess(DataSnapshot dataSnapshot) {
                RentalData rd=dataSnapshot.getValue(RentalData.class);
                b_car_type.setText(rd.getCarType());
                b_seat_cap.setText(rd.getSeatcap());
                b_from.setText(rd.getCurrentLoc());
                b_to.setText(rd.getDestLoc());
                b_date.setText(rd.getDate_());
                b_time.setText(rd.getTime_());
                b_r_t.setText(rd.getRoundTrip());
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("jap",auction_id);

                FirebaseDatabase firebaseDatabase;
                DatabaseReference databaseReference;
                firebaseDatabase=FirebaseDatabase.getInstance();
                s.setOnGoingAuction("#");
                databaseReference=firebaseDatabase.getReference("BIDS2").child(auction_id);
                databaseReference.removeValue().addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        bottomSheetDialog.cancel();
                        Intent i =new Intent(getApplicationContext(),MainActivity.class);
                        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(i);

                    }
                });

             /*   databaseReference.get().addOnSuccessListener(new OnSuccessListener<DataSnapshot>() {
                    @Override
                    public void onSuccess(DataSnapshot dataSnapshot) {
                        RentalData rd=dataSnapshot.getValue(RentalData.class);
                        Log.i("jap",rd.getCarType());
                    }
                });
                */

            }
        });

        if(car_type.equals("Sedan")){

            // Log.i("nah","A");
            Bitmap bImage = BitmapFactory.decodeResource(BiddingOngoingActivity.this.getResources(), R.drawable.prvt);
            bottom_sheet_car_pic.setImageBitmap(bImage);
        }
        else  if(car_type.equals("Mini")){
            // Log.i("nah","B");
            Bitmap bImage = BitmapFactory.decodeResource(BiddingOngoingActivity.this.getResources(), R.drawable.mini);
            bottom_sheet_car_pic.setImageBitmap(bImage);
        }else  if(car_type.equals("Micro")){
            // Log.i("nah","C");
            Bitmap bImage = BitmapFactory.decodeResource(BiddingOngoingActivity.this.getResources(), R.drawable.micro);
            bottom_sheet_car_pic.setImageBitmap(bImage);
        }

        bottomSheetDialog.show();
        bottom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bottomSheetDialog.cancel();
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent i =new Intent(getApplicationContext(),MainActivity.class);
        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(i);
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
        anim2.setRepeatCount(ValueAnimator.INFINITE);
        anim2.setRepeatMode(ValueAnimator.REVERSE);// Needed to keep the result of the animation
        anim2.setDuration(500);

        Animation anim1 = new ScaleAnimation(
                1f, 1f, // Start and end values for the X axis scaling
                1f, 2f, // Start and end values for the Y axis scaling
                Animation.RELATIVE_TO_SELF, 0.5f, // Pivot point of X scaling
                Animation.RELATIVE_TO_SELF, 0.5f); // Pivot point of Y scaling
        anim1.setFillAfter(true);
        anim1.setRepeatCount(ValueAnimator.INFINITE);
        anim1.setRepeatMode(ValueAnimator.REVERSE);// Needed to keep the result of the animation
        anim1.setDuration(500);

        Animation anim3 = new ScaleAnimation(
                1f, 1f, // Start and end values for the X axis scaling
                1f, 2f, // Start and end values for the Y axis scaling
                Animation.RELATIVE_TO_SELF, 0.5f, // Pivot point of X scaling
                Animation.RELATIVE_TO_SELF, 0.5f); // Pivot point of Y scaling
        anim3.setFillAfter(true);
        anim3.setRepeatCount(ValueAnimator.INFINITE);
        anim3.setRepeatMode(ValueAnimator.REVERSE);// Needed to keep the result of the animation
        anim3.setDuration(500);







        i2.startAnimation(anim2);
        i1.startAnimation(anim1);
        i3.startAnimation(anim3);
    }
}