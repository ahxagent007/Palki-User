package com.alphacuetech.xian.palki_drive.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.alphacuetech.xian.palki_drive.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class BiddingActivity extends AppCompatActivity {

    RecyclerView RV_biddings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bidding);

        RV_biddings = findViewById(R.id.RV_biddings);


    }

   /* public void getBiddingsFirebase(){
        Query query = databaseRefUserTree.child(uid);

        //ArrayList<Tree> user_trees = new ArrayList<>();

        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                user_trees.clear();

                for(DataSnapshot ds: dataSnapshot.getChildren()){
                    Tree tree = ds.getValue(Tree.class);
                    user_trees.add(tree);
                }

                ArrayList<Tree> RVTrees = new ArrayList<>();

                for (int i=0; i <user_trees.size(); i++){
                    Tree t = user_trees.get(i);

                    for (Tree tree : trees){
                        if (tree.getTree_id() == t.getTree_id()){
                            RVTrees.add(tree);
                        }
                    }
                }

                new Handler().post(new Runnable() {
                    @Override
                    public void run() {

                        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
                        RV_tree.setLayoutManager(mLayoutManager);

                        //Collections.reverse(FoodMixDB);

                        RecyclerView.Adapter mRecycleAdapter = new CustomAdapterRVTree(RVTrees);
                        RV_tree.setAdapter(mRecycleAdapter);

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

    public class CustomAdapterRVTree extends RecyclerView.Adapter<CustomAdapterRVTree.ViewHolder> {

        private ArrayList<Tree> trees;

        public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener{
            private TextView TV_name, TV_water;
            ImageView IV_img;
            ImageButton IB_delete;

            public ViewHolder(View view) {
                super(view);
                // Define click listener for the ViewHolder's View

                TV_name = (TextView) view.findViewById(R.id.TV_name);
                TV_water = (TextView) view.findViewById(R.id.TV_water);
                IV_img = (ImageView) view.findViewById(R.id.IV_img);
                IB_delete = (ImageButton) view.findViewById(R.id.IB_delete);

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

        public CustomAdapterRVTree(ArrayList<Tree> trees) {
            this.trees = trees;
        }

        // Create new views (invoked by the layout manager)
        @Override
        public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
            // Create a new view, which defines the UI of the list item
            View view = LayoutInflater.from(viewGroup.getContext())
                    .inflate(R.layout.single_tree, viewGroup, false);

            return new CustomAdapterRVTree.ViewHolder(view);
        }

        // Replace the contents of a view (invoked by the layout manager)
        @Override
        public void onBindViewHolder(ViewHolder viewHolder, @SuppressLint("RecyclerView") int position) {

            viewHolder.IB_delete.setVisibility(View.VISIBLE);
            viewHolder.TV_name.setText(trees.get((position)).getTree_name());

            DecimalFormat df = new DecimalFormat("0.00");

            double w_min = (trees.get(position).getWater_min()/trees.get(position).getTemp_min())*daily.getTemp().getMin();
            double w_max = (trees.get(position).getWater_max()/trees.get(position).getTemp_max())*daily.getTemp().getMax();

            viewHolder.TV_water.setText(df.format(w_max)+"-"+df.format(w_min)+" ml");


            Glide.with(getApplicationContext()).load(trees.get(position).getImg()).into(viewHolder.IV_img);

            viewHolder.setClickListener(new ItemClickListener() {
                @Override
                public void onClick(View view, final int position, boolean isLongClick) {
                    if (isLongClick) {

                    } else {

                    }
                }
            });

            viewHolder.IB_delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    deleteFromFirebase(trees.get(position).getTree_id(), uid);
                }
            });
        }

        // Return the size of your dataset (invoked by the layout manager)
        @Override
        public int getItemCount() {
            return trees.size();
        }
    }*/
}