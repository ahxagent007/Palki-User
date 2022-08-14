package com.alphacuetech.xian.palki_drive;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class SharedPreff {

    private final SharedPreferences mPrefs;

    public SharedPreff(Context context) {
        mPrefs = PreferenceManager.getDefaultSharedPreferences(context);
    }


    public String getFirebaseUID() {
        String str = mPrefs.getString("FIREBASE_UID", "#");
        return str;
    }

    public void setFirebaseUID(String UID) {
        SharedPreferences.Editor mEditor = mPrefs.edit();
        mEditor.putString("FIREBASE_UID", UID);
        mEditor.apply();
    }
}
