package com.alphacuetech.xian.palki_drive.ui.bidding;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alphacuetech.xian.palki_drive.Activities.BiddingOngoingActivity;
import com.alphacuetech.xian.palki_drive.Activities.RentConfirmActivity;
import com.alphacuetech.xian.palki_drive.DataModel.Bid;
import com.alphacuetech.xian.palki_drive.DataModel.RentalData;
import com.alphacuetech.xian.palki_drive.R;
import com.alphacuetech.xian.palki_drive.SharedPreff;
import com.alphacuetech.xian.palki_drive.databinding.FragmentBiddingBinding;
import com.alphacuetech.xian.palki_drive.databinding.FragmentRentalBinding;
import com.alphacuetech.xian.palki_drive.utills.MyAdapter;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class BiddingFragment extends Fragment {

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

    private FragmentBiddingBinding binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentBiddingBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        //Intent i = new Intent(binding.getRoot().getContext(), BiddingOngoingActivity.class);
        //startActivity(i);
        SharedPreff sharedPreff=new SharedPreff(binding.getRoot().getContext());
        auction_id=sharedPreff.getOnGoingAuction();
        LinearLayout bottomtripreview=binding.bottomTripReview;
        recyclerView=binding.lst;
        databaseReference= FirebaseDatabase.getInstance().getReference("BIDS2").child(auction_id);
        recyclerView.setHasFixedSize(false);
        recyclerView.setLayoutManager(new LinearLayoutManager(binding.getRoot().getContext()));

        bids=new ArrayList<>();
        adapter=new MyAdapter(binding.getRoot().getContext(),bids);
        recyclerView.setAdapter(adapter);

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dsnapshot:snapshot.getChildren()){
                    Bid bid=dsnapshot.getValue(Bid.class);
                    bids.add(bid);
                    // Log.i("nh",user.name);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        bottomtripreview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BottomSheetdialogue();
            }
        });


        return root;
    }
    private void BottomSheetdialogue(){
        final BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(binding.getRoot().getContext());
        if(!auction_id.equals("start")){



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
                databaseReference=firebaseDatabase.getReference("AUCTION2").child(auction_id);
                databaseReference.removeValue().addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Log.i("jap","hurre1");
                    }
                });
                databaseReference=firebaseDatabase.getReference("BIDS2").child(auction_id);
                databaseReference.removeValue().addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Log.i("jap","hurre2");
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
            Bitmap bImage = BitmapFactory.decodeResource(binding.getRoot().getResources(), R.drawable.prvt);
            bottom_sheet_car_pic.setImageBitmap(bImage);
        }
        else  if(car_type.equals("Mini")){
            // Log.i("nah","B");
            Bitmap bImage = BitmapFactory.decodeResource(binding.getRoot().getResources(), R.drawable.mini);
            bottom_sheet_car_pic.setImageBitmap(bImage);
        }else  if(car_type.equals("Micro")){
            // Log.i("nah","C");
            Bitmap bImage = BitmapFactory.decodeResource(binding.getRoot().getResources(), R.drawable.micro);
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
    }
}