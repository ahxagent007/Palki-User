package com.alphacuetech.xian.palki_drive.ui.about_us;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class AboutUsViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public AboutUsViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is About Us fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}