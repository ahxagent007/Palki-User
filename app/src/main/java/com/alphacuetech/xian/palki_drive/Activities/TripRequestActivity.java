package com.alphacuetech.xian.palki_drive.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

import java.util.ArrayList;

public class TripRequestActivity extends AppCompatActivity {

    String user_id;
    DatabaseReference auctionsDatabaseReference;
    RecyclerView RV_auctions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trip_request);

        RV_auctions = findViewById(R.id.RV_auctions);

        user_id = FirebaseAuth.getInstance().getUid();
        auctionsDatabaseReference = FirebaseDatabase.getInstance().getReference("AUCTION");

        getUserRequestedTrips(user_id);

    }

    String TAG = "XIAN";

    void getUserRequestedTrips(String uid){
        ArrayList<Auction> auctions = new ArrayList<>();
        Log.i(TAG, "user_id: "+uid);
        Query query = auctionsDatabaseReference.orderByChild("user_id").equalTo(uid);

        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                auctions.clear();

                for(DataSnapshot ds: dataSnapshot.getChildren()){
                    Auction auc = ds.getValue(Auction.class);
                    auctions.add(auc);
                }


                new Handler().post(new Runnable() {
                    @Override
                    public void run() {

                        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
                        RV_auctions.setLayoutManager(mLayoutManager);

                        //Collections.reverse(FoodMixDB);

                        RecyclerView.Adapter mRecycleAdapter = new CustomAdapterAuction(auctions);
                        RV_auctions.setAdapter(mRecycleAdapter);

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


    public class CustomAdapterAuction extends RecyclerView.Adapter<CustomAdapterAuction.ViewHolder> {

        private ArrayList<Auction> auctions;

        public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener{
            private TextView TV_dropPoint, TV_pickupPoint;

            public ViewHolder(View view) {
                super(view);
                // Define click listener for the ViewHolder's View

                TV_pickupPoint = view.findViewById(R.id.TV_pickupPoint);
                TV_dropPoint = view.findViewById(R.id.TV_dropPoint);

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

        public CustomAdapterAuction(ArrayList<Auction> auctions) {
            this.auctions = auctions;
        }

        // Create new views (invoked by the layout manager)
        @Override
        public CustomAdapterAuction.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
            // Create a new view, which defines the UI of the list item
            View view = LayoutInflater.from(viewGroup.getContext())
                    .inflate(R.layout.single_auction, viewGroup, false);

            return new CustomAdapterAuction.ViewHolder(view);
        }

        // Replace the contents of a view (invoked by the layout manager)
        @Override
        public void onBindViewHolder(CustomAdapterAuction.ViewHolder viewHolder, @SuppressLint("RecyclerView") int position) {

            viewHolder.TV_pickupPoint.setText(auctions.get((position)).getFrom());
            viewHolder.TV_dropPoint.setText(auctions.get((position)).getTo());

            viewHolder.setClickListener(new ItemClickListener() {
                @Override
                public void onClick(View view, final int position, boolean isLongClick) {
                    if (isLongClick) {

                    } else {
                        Intent at = new Intent(getApplicationContext(), BiddingActivity.class);
                        at.putExtra("auction", auctions.get(position));
                        startActivity(at);
                    }
                }
            });

        }

        // Return the size of your dataset (invoked by the layout manager)
        @Override
        public int getItemCount() {
            return auctions.size();
        }
    }
}