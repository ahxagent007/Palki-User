package com.alphacuetech.xian.palki_drive.Activities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.alphacuetech.xian.palki_drive.R;
import com.alphacuetech.xian.palki_drive.SharedPreff;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Locale;

public class UserDetails extends AppCompatActivity {
    String user_photo;
    Uri user_photo_uri;
    String user_id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_details);
        EditText user_name=findViewById(R.id.user_name);
        EditText user_mail=findViewById(R.id.user_mail);
        EditText user_phn=findViewById(R.id.user_phn);
        EditText user_address=findViewById(R.id.user_address);
        EditText user_gender=findViewById(R.id.user_gender);
        EditText user_age=findViewById(R.id.user_age);
        ImageButton imageselect=findViewById(R.id.imageselect);
        SharedPreff s=new SharedPreff(getApplicationContext());
        Bundle extras=getIntent().getExtras();

        if(extras == null) {

        } else {
            user_id= extras.getString("user_id");
        }


        imageselect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Intent.ACTION_PICK,android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, 1);
            }
        });
        Button confirm_user=findViewById(R.id.confirm_user);
        confirm_user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseStorage firebaseStorage=FirebaseStorage.getInstance();
                StorageReference storageReference=firebaseStorage.getReference(user_id);
                storageReference.putFile(user_photo_uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        storageReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {
                                 Log.i("xin","hurrekdjfkdjfkdjfkd");
                                String postUrl = "https://Dolnabd.com/api/Rider/Create";
                                RequestQueue requestQueue = Volley.newRequestQueue(UserDetails.this);

                                JSONObject postData = new JSONObject();
                                try {
                                    postData.put("user_id", user_id.toString());
                                    postData.put("user_name", user_name.getText().toString());
                                    postData.put("user_phn", user_phn.getText().toString());
                                    postData.put("user_mail", user_mail.getText().toString());

                                    postData.put("user_address", user_address.getText().toString());
                                    postData.put("user_gender", user_gender.getText().toString());
                                    postData.put("user_dob", user_age.getText().toString());

                                    postData.put("user_photo", uri.toString());

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                                System.out.println(postData);
                                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, postUrl, postData, new Response.Listener<JSONObject>() {
                                    @Override
                                    public void onResponse(JSONObject response) {
                                        Toast.makeText(getApplicationContext(), "Response: "+response, Toast.LENGTH_LONG).show();
                                        Log.i("xin","Response: "+response);
                                        String res;
                                        try {
                                             res=response.getString("msg");
                                            if(res.equals("Success")){
                                                Intent i =new Intent(getApplicationContext(),MainActivity.class);
                                                i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                                                startActivity(i);
                                                finish();

                                            }
                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }

                                    }
                                }, new Response.ErrorListener() {


                                    @Override
                                    public void onErrorResponse(VolleyError error) {
                                        error.printStackTrace();
                                    }
                                });

                                requestQueue.add(jsonObjectRequest);
                            }
                        });
                        Toast.makeText(UserDetails.this, "This is my Toast message!",
                                Toast.LENGTH_LONG).show();
                    }
                });


            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK && null != data) {
            System.out.println("sddddddddddddddddddddddddddddddd");
            Uri selectedImage = data.getData();
            user_photo_uri=selectedImage;
            System.out.println(selectedImage);
            String[] filePathColumn = { MediaStore.Images.Media.DATA };
            Cursor cursor = getContentResolver().query(selectedImage,
                    filePathColumn, null, null, null);
            cursor.moveToFirst();
            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            String picturePath = cursor.getString(columnIndex);
            Log.i("nah",picturePath);
            user_photo=picturePath.toString();
            cursor.close();
           ImageView profileimage=findViewById(R.id.profileimage);
          profileimage.setImageBitmap(BitmapFactory.decodeFile(picturePath));
        }
    }
}