package com.alphacuetech.xian.palki_drive.ui.about_us;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.alphacuetech.xian.palki_drive.databinding.FragmentAboutUsBinding;

public class AboutUsFragment extends Fragment {

private FragmentAboutUsBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
            ViewGroup container, Bundle savedInstanceState) {
        AboutUsViewModel homeViewModel =
                new ViewModelProvider(this).get(AboutUsViewModel.class);

    binding = FragmentAboutUsBinding.inflate(inflater, container, false);
    View root = binding.getRoot();

        final TextView textView = binding.textAboutUs;
        homeViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

@Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}