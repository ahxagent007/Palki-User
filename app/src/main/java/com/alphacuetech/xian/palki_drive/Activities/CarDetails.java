package com.alphacuetech.xian.palki_drive.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.alphacuetech.xian.palki_drive.DataModel.BidRes;
import com.alphacuetech.xian.palki_drive.DataModel.RentalData;
import com.alphacuetech.xian.palki_drive.R;
import com.alphacuetech.xian.palki_drive.SharedPreff;
import com.alphacuetech.xian.palki_drive.utills.ImageFromNet;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;

public class CarDetails extends AppCompatActivity {
      BidRes bidRes;
      String pictures;
      String[] pics;
    LinearLayout wait_load;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_car_details);
        TextView model_car=findViewById(R.id.model_car);
        TextView color_car=findViewById(R.id.color_car);
        TextView type_car=findViewById(R.id.type_car);
        TextView reg_car=findViewById(R.id.reg_car);
        TextView ac_car=findViewById(R.id.ac_car);
        TextView condition_car=findViewById(R.id.condition_car);
        wait_load=findViewById(R.id.wait_load);
        LinearLayout det=findViewById(R.id.car_det_load);

      //  new ImageFromNet((ImageView) findViewById(R.id.car_det_1),CarDetails.this).execute("https://pbs.twimg.com/profile_images/630285593268752384/iD1MkFQ0.png");

        Bundle extras=getIntent().getExtras();
        bidRes=new BidRes();
        if(extras == null) {

        } else {
             bidRes.setBid_id(extras.getString("bid_id"));
             bidRes.setDriver_id(extras.getString("driver_id"));
             bidRes.setAuction_id(extras.getString("auction_id"));
             bidRes.setAmount(extras.getString("amount"));
             bidRes.setFrom("sdsds");
             bidRes.setTo("sdsdsd");
        }
        LinearLayout driver_info=findViewById(R.id.driver_info);
        driver_info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(CarDetails.this, driverInfo.class);
               i.putExtra("driver_id",bidRes.getDriver_id());



                startActivity(i);

            }
        });

        wait_load.setVisibility(View.VISIBLE);
        det.setVisibility(View.GONE);

        model_car.setText("obj.getString");
        color_car.setText("obj.getString");
        type_car.setText("sdsd");
        reg_car.setText("obj.getString)");
        ac_car.setText("sdsdsd");
        condition_car.setText("sdsdsdsdsd");

        String url="https://Dolnabd.com/api/Car/Get/Details/"+extras.getString("driver_id");
        StringRequest stringRequest=new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.e("aa",response);
                JSONObject obj ;
                try {
                     obj = new JSONObject(response);
                     pictures=obj.getString("Pictures");
                     pics=pictures.split(",");
                     Log.i("japp",pics.toString());

                    new ImageFromNet((ImageView) findViewById(R.id.car_det_0),CarDetails.this).execute(pics[1]);
                    new ImageFromNet((ImageView) findViewById(R.id.car_det_1),CarDetails.this).execute(pics[2]);
                    new ImageFromNet((ImageView) findViewById(R.id.car_det_2),CarDetails.this).execute(pics[3]);

                     model_car.setText(obj.getString("Model"));
                     color_car.setText(obj.getString("Color"));
                     type_car.setText(obj.getString("Type"));
                     reg_car.setText(obj.getString("RegistrationNumber"));
                     ac_car.setText(obj.getString("isAC"));
                     condition_car.setText(obj.getString("Condition"));
                     wait_load.setVisibility(View.GONE);
                     det.setVisibility(View.VISIBLE);
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
        RequestQueue requestQueue= Volley.newRequestQueue(CarDetails.this);
        requestQueue.add(stringRequest);


        FirebaseDatabase firebaseDatabase;
        DatabaseReference databaseReference;
        firebaseDatabase=FirebaseDatabase.getInstance();
        databaseReference=firebaseDatabase.getReference("AUCTION2").child(bidRes.getAuction_id());
        databaseReference.get().addOnSuccessListener(new OnSuccessListener<DataSnapshot>() {
            @Override
            public void onSuccess(DataSnapshot dataSnapshot) {
                RentalData rd=dataSnapshot.getValue(RentalData.class);
               bidRes.setFrom(rd.getCurrentLoc());
               bidRes.setTo(rd.getDestLoc());
               bidRes.setTo_latLng(rd.getTo_latLng());
            }
        });


        Button confirm=findViewById(R.id.confirm_bid);
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseDatabase firebaseDatabase;
                DatabaseReference databaseReference;
                firebaseDatabase=FirebaseDatabase.getInstance();
                databaseReference=firebaseDatabase.getReference("BIDRESULT2");
                String id=bidRes.getAuction_id();

                stopService(new Intent(getApplicationContext(),PushNotificationService.class));
                SharedPreff s=new SharedPreff(getApplicationContext());
                s.setOnGoingAuction("start");

                databaseReference.child(id).setValue(bidRes);
            }
        });

    }

}