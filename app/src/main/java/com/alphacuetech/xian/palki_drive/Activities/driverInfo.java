package com.alphacuetech.xian.palki_drive.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.ValueAnimator;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.alphacuetech.xian.palki_drive.R;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class driverInfo extends AppCompatActivity {
    String driver_id;
    EditText driver_name;
    EditText driver_phone;
    EditText driver_mail;
    LinearLayout wait_load;
    LinearLayout det_driver;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_info);
        ImageButton back=findViewById(R.id.backbtn);


        driver_name=findViewById(R.id.user_name);
        driver_mail=findViewById(R.id.user_mail);
        driver_phone=findViewById(R.id.user_phn);
        wait_load=findViewById(R.id.wait_load);
        det_driver=findViewById(R.id.det_driver);


        wait_load.setVisibility(View.VISIBLE);
        det_driver.setVisibility(View.GONE);


        waitAnim();
        Bundle extras = getIntent().getExtras();
        if(extras == null) {

        } else {
          driver_id=extras.getString("driver_id");
        }
        String url="https://Dolnabd.com/api/Driver/Get/Details/"+driver_id;
        StringRequest stringRequest=new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.e("aa",response);
                JSONObject obj ;
                wait_load.setVisibility(View.GONE);
                det_driver.setVisibility(View.VISIBLE);
                try {
                    obj = new JSONObject(response);
                    Log.i("jap",obj.toString());
                    driver_name.setText(obj.getString("Name"));
                    driver_phone.setText(obj.getString("Phone"));
                    driver_mail.setText(obj.getString("Email"));

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
        RequestQueue requestQueue= Volley.newRequestQueue(driverInfo.this);
        requestQueue.add(stringRequest);


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

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
        anim2.setRepeatCount(20);
        anim2.setRepeatMode(ValueAnimator.REVERSE);// Needed to keep the result of the animation
        anim2.setDuration(500);

        Animation anim1 = new ScaleAnimation(
                1f, 1f, // Start and end values for the X axis scaling
                1f, 2f, // Start and end values for the Y axis scaling
                Animation.RELATIVE_TO_SELF, 0.5f, // Pivot point of X scaling
                Animation.RELATIVE_TO_SELF, 0.5f); // Pivot point of Y scaling
        anim1.setFillAfter(true);
        anim1.setRepeatCount(20);
        anim1.setRepeatMode(ValueAnimator.REVERSE);// Needed to keep the result of the animation
        anim1.setDuration(500);

        Animation anim3 = new ScaleAnimation(
                1f, 1f, // Start and end values for the X axis scaling
                1f, 2f, // Start and end values for the Y axis scaling
                Animation.RELATIVE_TO_SELF, 0.5f, // Pivot point of X scaling
                Animation.RELATIVE_TO_SELF, 0.5f); // Pivot point of Y scaling
        anim3.setFillAfter(true);
        anim3.setRepeatCount(20);
        anim3.setRepeatMode(ValueAnimator.REVERSE);// Needed to keep the result of the animation
        anim3.setDuration(500);







        i2.startAnimation(anim2);
        i1.startAnimation(anim1);
        i3.startAnimation(anim3);
    }
}