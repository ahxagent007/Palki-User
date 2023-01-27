package com.alphacuetech.xian.palki_drive.ui.rating;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alphacuetech.xian.palki_drive.R;
import com.alphacuetech.xian.palki_drive.databinding.FragmentConfirmationBinding;
import com.alphacuetech.xian.palki_drive.databinding.FragmentRatingBinding;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link RatingFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RatingFragment extends Fragment {

    private FragmentRatingBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentRatingBinding.inflate(inflater, container, false);
        View root = binding.getRoot();


        return root;
    }
}