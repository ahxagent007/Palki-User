package com.alphacuetech.xian.palki_drive.Activities;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.CountDownTimer;
import android.os.IBinder;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;

import com.alphacuetech.xian.palki_drive.DataModel.Bid;
import com.alphacuetech.xian.palki_drive.R;
import com.alphacuetech.xian.palki_drive.SharedPreff;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.installations.Utils;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.text.DecimalFormat;

public class PushNotificationService extends Service {
    public static final String NOTIFICATION_CHANNEL_ID = "10001" ;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    ChildEventListener childEventListener;
    SharedPreff s;
    int seconds=60;
    int minutes=4;


    DecimalFormat format = new DecimalFormat("00");

    CountDownTimer countDownTimer;
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        s=new SharedPreff(getApplicationContext());
        String auction_id=s.getOnGoingAuction();


        countDownTimer=new CountDownTimer(60000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                seconds--;
                Log.i("Jap",""+minutes+":"+format.format(seconds)+" Left");
                Log.i("Jap",""+millisUntilFinished);
                s.setOnGoingAuctionTimeRemain(""+minutes+":"+format.format(seconds)+" Left");
                if(seconds==0){
                    minutes--;
                    seconds=60;
                }


            }

            @Override
            public void onFinish() {

               if(s.getOnGoingAuctionCount()==0){
                   FirebaseDatabase firebaseDatabase;
                   DatabaseReference databaseReference;
                   firebaseDatabase=FirebaseDatabase.getInstance();

                   databaseReference=firebaseDatabase.getReference("BIDS2").child(auction_id);

                   databaseReference.removeValue().addOnSuccessListener(new OnSuccessListener<Void>() {
                       @Override
                       public void onSuccess(Void unused) {
                           s.setOnGoingAuction("start");
                           Intent i =new Intent(getApplicationContext(),MainActivity.class);
                           i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                           startActivity(i);

                       }
                   });
               }




                countDownTimer.cancel();
                onDestroy();

            }
        }.start();


        firebaseDatabase=FirebaseDatabase.getInstance();
        databaseReference=firebaseDatabase.getReference("BIDS2").child(auction_id);

       childEventListener= databaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {





                Toast.makeText(getApplicationContext(), "Service started by user.", Toast.LENGTH_LONG).show();
                NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(getApplicationContext(),
                        NOTIFICATION_CHANNEL_ID )
                        .setSmallIcon(R.drawable. ic_launcher_foreground )
                        .setContentTitle( snapshot.child("bidAmount").getValue(String.class) +"BDT")

                        .setContentText( snapshot.child("condition").getValue(String.class)+" "+ snapshot.child("rating").getValue(String.class));
                NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context. NOTIFICATION_SERVICE ) ;
                if (android.os.Build.VERSION. SDK_INT >= android.os.Build.VERSION_CODES. O ) {

                    int importance = NotificationManager. IMPORTANCE_HIGH ;
                    NotificationChannel notificationChannel = new
                            NotificationChannel( NOTIFICATION_CHANNEL_ID , "NOTIFICATION_CHANNEL_NAME" , importance) ;
                    notificationChannel.enableLights( true ) ;

                    notificationChannel.enableVibration( true ) ;
                  //  notificationChannel.setVibrationPattern( new long []{ 100 , 200 , 300 , 400 , 500 , 400 , 300 , 200 , 400 }) ;
                  //  notificationChannel.setSound(sound , audioAttributes) ;
                    mBuilder.setChannelId( NOTIFICATION_CHANNEL_ID ) ;
                    assert mNotificationManager != null;
                    mNotificationManager.createNotificationChannel(notificationChannel) ;
                }
                assert mNotificationManager != null;
                mNotificationManager.notify(( int ) System. currentTimeMillis (), mBuilder.build()) ;
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

       // return super.onStartCommand(intent, flags, startId);
        return  START_NOT_STICKY;

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Toast.makeText(this, "No Cars Found Try Again", Toast.LENGTH_LONG).show();
        databaseReference.removeEventListener(childEventListener);

    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
