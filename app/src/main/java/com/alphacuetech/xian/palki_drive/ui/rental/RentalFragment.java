package com.alphacuetech.xian.palki_drive.ui.rental;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TimePicker;

import com.alphacuetech.xian.palki_drive.Activities.MapsActivity;
import com.alphacuetech.xian.palki_drive.Activities.RentConfirmActivity;
import com.alphacuetech.xian.palki_drive.Activities.RentFrag;
import com.alphacuetech.xian.palki_drive.Activities.Rental;
import com.alphacuetech.xian.palki_drive.DataModel.RentalData;
import com.alphacuetech.xian.palki_drive.R;
import com.alphacuetech.xian.palki_drive.databinding.FragmentAboutUsBinding;
import com.alphacuetech.xian.palki_drive.databinding.FragmentRentalBinding;
import com.google.gson.Gson;

import java.util.Calendar;


public class RentalFragment extends Fragment {

    private FragmentRentalBinding binding;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        binding = FragmentRentalBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        LinearLayout sedan=binding.sedan;
        LinearLayout mini=binding.mini;
        LinearLayout micro=binding.micro;

        sedan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(binding.getRoot().getContext(), Rental.class);
                i.putExtra("carT","Sedan");
                i.putExtra("seatC","4 seat");
                startActivity(i);
            }
        });
        mini.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(binding.getRoot().getContext(), Rental.class);
                i.putExtra("carT","Mini");
                i.putExtra("seatC","8 seat");
                startActivity(i);
            }
        });
        micro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(binding.getRoot().getContext(), Rental.class);
                i.putExtra("carT","Micro");
                i.putExtra("seatC","12 seat");
                startActivity(i);
            }
        });


        return root;
    }


}