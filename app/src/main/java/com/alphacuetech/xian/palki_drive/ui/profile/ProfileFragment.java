package com.alphacuetech.xian.palki_drive.ui.profile;

import static android.app.Activity.RESULT_OK;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.alphacuetech.xian.palki_drive.Activities.CarDetails;
import com.alphacuetech.xian.palki_drive.Activities.UserDetails;
import com.alphacuetech.xian.palki_drive.R;
import com.alphacuetech.xian.palki_drive.SharedPreff;
import com.alphacuetech.xian.palki_drive.databinding.FragmentProfileBinding;
import com.alphacuetech.xian.palki_drive.ui.profile.ProfileViewModel;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.tabs.TabLayout;
import com.squareup.picasso.Picasso;
import org.json.JSONException;
import org.json.JSONObject;

public class ProfileFragment extends Fragment {

    private FragmentProfileBinding binding;
    String user_photo;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        ProfileViewModel profileViewModel =
                new ViewModelProvider(this).get(ProfileViewModel.class);

        binding = FragmentProfileBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

       // SharedPreff s=new SharedPreff(binding.getRoot().getContext());
        Button user_change_photo=binding.userChangePhoto;
        Button user_save=binding.userSave;
        user_change_photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(
                        Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(i, 1);
            }
        });

        ImageView propic=binding.IVPropic;
        ImageView user_name_edit=binding.userNameEdit;
        ImageView user_mail_edit=binding.userMailEdit;
        ImageView user_phone_edit=binding.userPhnEdit;
        ImageView user_address_edit=binding.userAddressEdit;
        ImageView user_gender_edit=binding.userGenderEdit;
        ImageView user_age_edit=binding.userAgeEdit;

        EditText user_name=binding.userName;
        EditText user_mail=binding.userMail;
        EditText user_phone=binding.userPhn;
        EditText user_address=binding.userAddress;
        EditText user_gender=binding.userGender;
        EditText user_age=binding.userAge;

        Picasso.get().load("https://firebasestorage.googleapis.com/v0/b/dolna-ride.appspot.com/o/UserPhoto?alt=media&token=c768eb93-4175-43f3-86c4-6bca7808f742").into(propic);

        ///API read
       /* String url="https://Dolnabd.com/api/Car/Get/Details/";
        StringRequest stringRequest=new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.e("aa",response);
                JSONObject obj ;
                try {
                    obj = new JSONObject(response);
                    user_name.setText(obj.getString("user_name"));
                    user_mail.setText(obj.getString("user_mail"));
                    user_phone.setText(obj.getString("user_phone"));
                    user_address.setText(obj.getString("user_address"));
                    user_gender.setText(obj.getString("user_gender"));
                    user_age.setText(obj.getString("user_age"));
                    user_photo.setText(obj.getString("user_photo"));

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
        RequestQueue requestQueue= Volley.newRequestQueue(binding.getRoot().getContext());
        requestQueue.add(stringRequest);

        */



        user_name_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                user_name.setEnabled(true);
            }
        });
        user_mail_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                user_mail.setEnabled(true);
            }
        });
        user_phone_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                user_phone.setEnabled(true);
            }
        });
        user_address_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                user_address.setEnabled(true);
            }
        });
        user_gender_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                user_gender.setEnabled(true);
            }
        });
        user_age_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                user_age.setEnabled(true);
            }
        });


        user_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String postUrl = "yourURL.....";
                RequestQueue requestQueue = Volley.newRequestQueue(binding.getRoot().getContext());

                JSONObject postData = new JSONObject();
                try {
                    postData.put("user_name", user_name.getText().toString());
                    postData.put("user_mail", user_mail.getText().toString());
                    postData.put("user_phone", user_phone.getText().toString());
                    postData.put("user_address", user_address.getText().toString());
                    postData.put("user_gender", user_gender.getText().toString());
                    postData.put("user_age", user_age.getText().toString());
                    postData.put("user_photo", user_photo);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                Log.i("nh",postData.toString());
                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, postUrl, postData, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        //Toast.makeText(getApplicationContext(), "Response: "+response, Toast.LENGTH_LONG).show();
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


        /*final TextView textView = binding.textProfile;
        profileViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);*/
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK && null != data) {
            System.out.println("sddddddddddddddddddddddddddddddd");
            Uri selectedImage = data.getData();
            System.out.println(selectedImage);
            String[] filePathColumn = { MediaStore.Images.Media.DATA };
            Cursor cursor = binding.getRoot().getContext().getContentResolver().query(selectedImage,
                    filePathColumn, null, null, null);
            cursor.moveToFirst();
            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            String picturePath = cursor.getString(columnIndex);
            Log.i("nah",picturePath);
            user_photo=picturePath.toString();
            cursor.close();
            ImageView profileimage=binding.IVPropic;

            profileimage.setImageBitmap(BitmapFactory.decodeFile(picturePath));
        }
    }
}