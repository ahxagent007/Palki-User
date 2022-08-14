package com.alphacuetech.xian.palki_drive.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.alphacuetech.xian.palki_drive.R;

public class LoginActivity extends AppCompatActivity {

    EditText ET_phoneNumber;
    Button btn_done;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ET_phoneNumber = findViewById(R.id.ET_phoneNumber);
        btn_done = findViewById(R.id.btn_done);

        btn_done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String phoneNumber = ET_phoneNumber.getText().toString();

                if(phoneNumber.charAt(0) == '0' && phoneNumber.charAt(1) == '1'){
                    if(phoneNumber.length() == 11){

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
        });


    }

}