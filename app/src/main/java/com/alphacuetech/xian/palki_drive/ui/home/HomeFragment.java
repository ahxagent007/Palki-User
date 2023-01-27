package com.alphacuetech.xian.palki_drive.ui.home;

import android.animation.ValueAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import com.alphacuetech.xian.palki_drive.Activities.BiddingOngoingActivity;
import com.alphacuetech.xian.palki_drive.Activities.BodyRentActivity;
import com.alphacuetech.xian.palki_drive.Activities.MainActivity;
import com.alphacuetech.xian.palki_drive.Activities.PhoneAuthActivity;
import com.alphacuetech.xian.palki_drive.Activities.RentFrag;
import com.alphacuetech.xian.palki_drive.Activities.TripActivity;
import com.alphacuetech.xian.palki_drive.Activities.TripRequestActivity;
import com.alphacuetech.xian.palki_drive.Activities.UserDetails;
import com.alphacuetech.xian.palki_drive.DataModel.RentalData;
import com.alphacuetech.xian.palki_drive.R;
import com.alphacuetech.xian.palki_drive.SharedPreff;
import com.alphacuetech.xian.palki_drive.databinding.FragmentHomeBinding;
import com.alphacuetech.xian.palki_drive.ui.rental.RentalFragment;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Timer;
import java.util.TimerTask;

public class HomeFragment extends Fragment {

private FragmentHomeBinding binding;
    CardView main_act_on_going_req;
    TextView start;
    TextView end;
    ImageView wait;
    int waitX=2;

    public View onCreateView(@NonNull LayoutInflater inflater,
            ViewGroup container, Bundle savedInstanceState) {
        /*HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);*/




        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        main_act_on_going_req=binding.mainActOnGoingReq;
        start=binding.startTrip;
        end=binding.destTrip;





//        new Timer().scheduleAtFixedRate(new TimerTask() {
//            @Override
//            public void run() {
//                wait.animate().scaleX(2f).setDuration(2000);
//
//            }
//        }, 0, 100);





        SharedPreff s=new SharedPreff(binding.getRoot().getContext());
        Log.i("japp",s.getOnGoingAuction());
        if(!s.getOnGoingAuction().equals("start") && !s.getOnGoingAuction().equals("#")){
            waitAnim();
            main_act_on_going_req.setVisibility(View.VISIBLE);
            FirebaseDatabase firebaseDatabase;
            DatabaseReference databaseReference;
            firebaseDatabase=FirebaseDatabase.getInstance();
            databaseReference=firebaseDatabase.getReference("AUCTION2").child(s.getOnGoingAuction());
            databaseReference.get().addOnSuccessListener(new OnSuccessListener<DataSnapshot>() {
                @Override
                public void onSuccess(DataSnapshot dataSnapshot) {
                    RentalData rd=dataSnapshot.getValue(RentalData.class);

                    start.setText(rd.getCurrentLoc());
                    end.setText(rd.getDestLoc());

                }
            });
            main_act_on_going_req.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(binding.getRoot().getContext(), BiddingOngoingActivity.class);
                    startActivity(i);
                }
            });
        }
        else{
            main_act_on_going_req.setVisibility(View.GONE);
        }


        ImageView IV_trip = binding.IVTrip;
        ImageView IV_bodyRent = binding.IVBodyRent;
        ImageView IV_requestedTrips = binding.IVRequestedTrips;

        IV_trip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //startActivity(new Intent(container.getContext(), RentalFragment.class));
                if(s.getOnGoingAuction().equals("start") || s.getOnGoingAuction().equals("#")){
                    Intent i = new Intent(binding.getRoot().getContext(), RentFrag.class);

                    startActivity(i);
                }
                else{
                    Toast.makeText(binding.getRoot().getContext(), "Already a request pending", Toast.LENGTH_LONG).show();
                }


            }
        });

        IV_bodyRent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(container.getContext(), BodyRentActivity.class));
            }
        });

        IV_requestedTrips.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreff s=new SharedPreff(binding.getRoot().getContext());
                String url="https://Dolnabd.com/api/Rider/Delete/"+s.getFirebaseUID();
                StringRequest stringRequest=new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        JSONObject obj ;
                        try {
                            obj = new JSONObject(response);


                            Log.i("jap","age"+obj.toString());
                            // System.out.println("sd"+obj.toString());
                            //  det.setVisibility(View.VISIBLE);
                        } catch (JSONException e) {
                            e.printStackTrace();

                        }



                    }
                },new Response.ErrorListener(){

                    @Override
                    public void onErrorResponse(VolleyError error) {

                        // Log.i("yap","Yeap");

                    }
                });
                RequestQueue requestQueue= Volley.newRequestQueue(binding.getRoot().getContext());
                requestQueue.add(stringRequest);
            }
        });

        return root;
    }

@Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
    public void waitAnim(){
        ImageView i2=binding.getRoot().findViewById(R.id.wait_b);
        ImageView i1=binding.getRoot().findViewById(R.id.wait_a);
        ImageView i3=binding.getRoot().findViewById(R.id.wait_c);
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