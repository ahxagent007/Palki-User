package com.alphacuetech.xian.palki_drive.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.alphacuetech.xian.palki_drive.DataModel.Auction;
import com.alphacuetech.xian.palki_drive.DataModel.Bid;
import com.alphacuetech.xian.palki_drive.ItemClickListener;
import com.alphacuetech.xian.palki_drive.R;
import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class BiddingActivity extends AppCompatActivity {

    TextView TV_pickupPoint, TV_dropPoint;

    RecyclerView RV_biddings;
    DatabaseReference biddingDatabaseReference;

    ArrayList<Bid> bids = new ArrayList<Bid>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bidding);

        RV_biddings = findViewById(R.id.RV_biddings);
        TV_pickupPoint = findViewById(R.id.TV_pickupPoint);
        TV_dropPoint = findViewById(R.id.TV_dropPoint);


        Auction auction = (Auction) getIntent().getSerializableExtra("auction");

        TV_pickupPoint.setText(auction.getFrom());
        TV_dropPoint.setText(auction.getTo());


        biddingDatabaseReference = FirebaseDatabase.getInstance().getReference("BIDS").child(""+auction.getAuction_id());

        getBiddingsFirebase();

    }
    String TAG = "XIAN";
    public void getBiddingsFirebase(){

        biddingDatabaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                bids.clear();

                for(DataSnapshot ds: dataSnapshot.getChildren()){
                    Bid bid = ds.getValue(Bid.class);
                    bids.add(bid);
                   // Log.i(TAG, bid.getCar_registration_no());
                }


                new Handler().post(new Runnable() {
                    @Override
                    public void run() {

                        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
                        RV_biddings.setLayoutManager(mLayoutManager);

                        //Collections.reverse(FoodMixDB);

                        RecyclerView.Adapter mRecycleAdapter = new CustomAdapterRVBids(bids);
                        RV_biddings.setAdapter(mRecycleAdapter);

                    }
                });

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                new Handler().post(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(), "Internet Error", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

    public class CustomAdapterRVBids extends RecyclerView.Adapter<CustomAdapterRVBids.ViewHolder> {

        private ArrayList<Bid> bids;

        public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener{
            private TextView TV_carModel, TV_carReg, TV_carDetails, TV_bidAmount;
            ImageView IV_carPic;

            public ViewHolder(View view) {
                super(view);
                // Define click listener for the ViewHolder's View

                TV_carModel = view.findViewById(R.id.TV_carModel);
                TV_carReg = view.findViewById(R.id.TV_carReg);
                TV_carDetails = view.findViewById(R.id.TV_carDetails);
                TV_bidAmount = view.findViewById(R.id.TV_bidAmount);
                IV_carPic = view.findViewById(R.id.IV_carPic);

                view.setOnClickListener(this);
                view.setOnLongClickListener(this);

            }

            private ItemClickListener clickListener;

            public void setClickListener(ItemClickListener itemClickListener) {
                this.clickListener = itemClickListener;
            }

            @Override
            public void onClick(View view) {
                clickListener.onClick(view, getPosition(), false);

            }

            @Override
            public boolean onLongClick(View view) {
                clickListener.onClick(view, getPosition(), true);
                return true;
            }
        }

        public CustomAdapterRVBids(ArrayList<Bid> bids) {
            this.bids = bids;
        }

        // Create new views (invoked by the layout manager)
        @Override
        public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
            // Create a new view, which defines the UI of the list item
            View view = LayoutInflater.from(viewGroup.getContext())
                    .inflate(R.layout.single_bid, viewGroup, false);

            return new CustomAdapterRVBids.ViewHolder(view);
        }

        // Replace the contents of a view (invoked by the layout manager)
        @Override
        public void onBindViewHolder(ViewHolder viewHolder, @SuppressLint("RecyclerView") int position) {

           // viewHolder.TV_carModel.setText(bids.get((position)).getCar_model());
          //  viewHolder.TV_carReg.setText(bids.get((position)).getCar_registration_no());
           // viewHolder.TV_carDetails.setText(bids.get((position)).getCar_condition()+"AC:"+bids.get(position).isCar_ac());
          //  viewHolder.TV_bidAmount.setText(""+bids.get((position)).getBid_amount());

           // Glide.with(getApplicationContext()).load(bids.get(position).getCar_image()).into(viewHolder.IV_carPic);

            viewHolder.setClickListener(new ItemClickListener() {
                @Override
                public void onClick(View view, final int position, boolean isLongClick) {
                    if (isLongClick) {

                    } else {

                    }
                }
            });

        }

        // Return the size of your dataset (invoked by the layout manager)
        @Override
        public int getItemCount() {
            return bids.size();
        }
    }
}