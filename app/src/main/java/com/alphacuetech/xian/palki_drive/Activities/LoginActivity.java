package com.alphacuetech.xian.palki_drive.Activities;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.alphacuetech.xian.palki_drive.R;
import com.alphacuetech.xian.palki_drive.SharedPreff;

public class LoginActivity extends AppCompatActivity {

    EditText ET_phoneNumber;
    Button btn_done;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ET_phoneNumber = findViewById(R.id.ET_phoneNumber);
        btn_done = findViewById(R.id.btn_done);
        btn_done.setVisibility(View.VISIBLE);


        btn_done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String phoneNumber = ET_phoneNumber.getText().toString();

                if(phoneNumber.equals("")){
                    Toast.makeText(getApplicationContext(), "Please enter phone number!", Toast.LENGTH_LONG).show();
                }else {
                    if(phoneNumber.charAt(0) == '0' && phoneNumber.charAt(1) == '1'){
                        if(phoneNumber.length() == 11){

                            btn_done.setVisibility(View.GONE);
                            Intent i = new Intent(getApplicationContext(), PhoneAuthActivity.class);
                            i.putExtra("PHONE", phoneNumber);
                            startActivity(i);
                        }else{
                            Toast.makeText(getApplicationContext(), "Invalid phone number length", Toast.LENGTH_LONG).show();

                        }
                    }else{
                        Toast.makeText(getApplicationContext(), "Invalid phone number", Toast.LENGTH_LONG).show();
                    }
                }
          //      Intent i = new Intent(getApplicationContext(), MainActivity.class);
            //    startActivity(i);
            }
        });


    }



    @Override
    protected void onResume() {
        super.onResume();
        btn_done.setVisibility(View.VISIBLE);
    }
}