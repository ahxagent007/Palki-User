package com.alphacuetech.xian.palki_drive;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class SharedPreff {

    private final SharedPreferences mPrefs;


    public String getOnGoingAuctionTimeRemain() {
        String str = mPrefs.getString("TimeRemain", "#");
        return str;
    }
    public void setOnGoingAuctionTimeRemain(String TimeRemain){
        SharedPreferences.Editor mEditor = mPrefs.edit();
        mEditor.putString("TimeRemain", TimeRemain);
        mEditor.apply();
    }



    public int getOnGoingAuctionCount() {

        int str= mPrefs.getInt("auction_count", 0);
        return str;
    }
    public void setOnGoingAuctionCount(int auction_count){
        SharedPreferences.Editor mEditor = mPrefs.edit();

        mEditor.putInt("auction_count", auction_count);
        mEditor.apply();
    }



    public String getOnGoingAuction() {
        String str = mPrefs.getString("auction_id", "start");
        return str;
    }
    public void setOnGoingAuction(String auction_id){
        SharedPreferences.Editor mEditor = mPrefs.edit();
        mEditor.putString("auction_id", auction_id);
        mEditor.apply();
    }

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
    public void setUserName(String name){
        SharedPreferences.Editor mEditor = mPrefs.edit();
        mEditor.putString("Name", name);
        mEditor.apply();
    }
    public String getUserName() {
        String str = mPrefs.getString("Name", "#");
        return str;
    }
    public void setUserPhn(String phn){
        SharedPreferences.Editor mEditor = mPrefs.edit();
        mEditor.putString("Phn", phn);
        mEditor.apply();
    }
    public String getUserPhn() {
        String str = mPrefs.getString("Phn", "#");
        return str;
    }
    public void setDOB(String dob){
        SharedPreferences.Editor mEditor = mPrefs.edit();
        mEditor.putString("Dob", dob);
        mEditor.apply();
    }
    public String getDOB() {
        String str = mPrefs.getString("Dob", "#");
        return str;
    }
    public void setMail(String mail){
        SharedPreferences.Editor mEditor = mPrefs.edit();
        mEditor.putString("Mail", mail);
        mEditor.apply();
    }
    public String getMail() {
        String str = mPrefs.getString("Mail", "#");
        return str;
    }
    public void setAddress(String add){
        SharedPreferences.Editor mEditor = mPrefs.edit();
        mEditor.putString("Address", add);
        mEditor.apply();
    }
    public String getAddress() {
        String str = mPrefs.getString("Address", "#");
        return str;
    }
    public void setGender(String gender){
        SharedPreferences.Editor mEditor = mPrefs.edit();
        mEditor.putString("Gender", gender);
        mEditor.apply();
    }
    public String getGender() {
        String str = mPrefs.getString("Gender", "#");
        return str;
    }
    public void setReferral(String ref){
        SharedPreferences.Editor mEditor = mPrefs.edit();
        mEditor.putString("Referral", ref);
        mEditor.apply();
    }


}
