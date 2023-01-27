package com.alphacuetech.xian.palki_drive.ui.confirmation;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alphacuetech.xian.palki_drive.R;
import com.alphacuetech.xian.palki_drive.databinding.FragmentBiddingBinding;
import com.alphacuetech.xian.palki_drive.databinding.FragmentConfirmationBinding;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ConfirmationFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ConfirmationFragment extends Fragment {

    private FragmentConfirmationBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentConfirmationBinding.inflate(inflater, container, false);
        View root = binding.getRoot();


        return root;
    }
}