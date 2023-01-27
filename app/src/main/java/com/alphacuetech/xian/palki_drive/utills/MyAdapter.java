package com.alphacuetech.xian.palki_drive.utills;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.alphacuetech.xian.palki_drive.Activities.CarDetails;
import com.alphacuetech.xian.palki_drive.DataModel.Bid;
import com.alphacuetech.xian.palki_drive.R;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    Context context;
    ArrayList<Bid> bids;
    String driver_id;
    String auction_id;
    String bid_id;
    String amount;
    String pictures;
    String tolatLng;
    public MyAdapter(Context context, ArrayList<Bid> bids) {
        this.context = context;
        this.bids = bids;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(context).inflate(R.layout.biditem,parent,false);
        LinearLayout itemclick=v.findViewById(R.id.car_details);
        itemclick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent (v.getContext(), CarDetails.class);
                i.putExtra("driver_id",driver_id);
                i.putExtra("auction_id",auction_id);
                i.putExtra("bid_id",bid_id);
                i.putExtra("amount",amount);


                v.getContext().startActivity(i);
            }
        });

        return new MyViewHolder(v);
    }


    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Bid bid=bids.get(position);
       // holder.name.setText(user.getName());///id in xml name
       holder.car_type.setText(bid.getCarType());
       holder.seat_cap.setText(bid.getSeatCap());
       holder.condition.setText(bid.getCondition());
       holder.bid_amount.setText("BDT "+bid.getBidAmount());
       holder.car_rating.setText(bid.getRating());



      driver_id= bid.getDriverID();
      auction_id=bid.getAuctionID();
      bid_id=bid.getBidID();
      amount=bid.getBidAmount();


        String url="https://Dolnabd.com/api/Car/Get/Details/"+driver_id;
        StringRequest stringRequest=new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.e("aa",response);
                JSONObject obj ;
                try {
                    obj = new JSONObject(response);
                    pictures=obj.getString("Pictures");
                    String[] pics=pictures.split(",");
                    new ImageFromNet((ImageView)  holder.car_pic,context).execute(pics[1]);
                    //  p.setVisibility(View.GONE);
                    //  det.setVisibility(View.VISIBLE);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        },new Response.ErrorListener(){

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("aa",error.toString());
            }
        });
        RequestQueue requestQueue= Volley.newRequestQueue(context.getApplicationContext());
        requestQueue.add(stringRequest);








    }

    @Override
    public int getItemCount() {
       return bids.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder
    {
        ImageView car_pic;
        TextView car_type;
        TextView seat_cap;
        TextView bid_amount;
        TextView condition;
        TextView car_rating;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            car_pic=itemView.findViewById(R.id.car_pic);
            car_type=itemView.findViewById(R.id.car_type);
            seat_cap=itemView.findViewById(R.id.seat_cap);
            bid_amount=itemView.findViewById(R.id.bid_amount);
            condition=itemView.findViewById(R.id.condition);
            car_rating=itemView.findViewById(R.id.car_rating);


        }
    }

}
