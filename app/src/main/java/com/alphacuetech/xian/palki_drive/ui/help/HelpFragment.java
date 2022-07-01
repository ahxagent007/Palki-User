package com.alphacuetech.xian.palki_drive.ui.help;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.alphacuetech.xian.palki_drive.databinding.FragmentHelpBinding;

public class HelpFragment extends Fragment {

private FragmentHelpBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
            ViewGroup container, Bundle savedInstanceState) {
        HelpViewModel homeViewModel =
                new ViewModelProvider(this).get(HelpViewModel.class);

    binding = FragmentHelpBinding.inflate(inflater, container, false);
    View root = binding.getRoot();

        final TextView textView = binding.textHelp;
        homeViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

@Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}