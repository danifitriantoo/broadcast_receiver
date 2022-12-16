package com.danifitrianto.intermediaterecyclerview.setups.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class PreferencesHelper {
    private static PreferencesHelper INSTANCE;
    private SharedPreferences sharedPreferences;

    public PreferencesHelper(Context context) {
        sharedPreferences = context.getApplicationContext()
                .getSharedPreferences("com.example.applicationactivity",0);
    }

    public static PreferencesHelper getInstance(Context context) {
        if(INSTANCE == null) {
            INSTANCE = new PreferencesHelper(context);
        }

        return INSTANCE;
    }

    public SharedPreferences PreferencesHelper() {
        return sharedPreferences;
    }


    public void setLogin(Boolean isLogin) {
        sharedPreferences.edit().putBoolean("isLogin",isLogin);
        sharedPreferences.edit().apply();
    }

    public Boolean isLogin() {
        return sharedPreferences.getBoolean("isLogin",false);
    }

    public void setNama(String nama) {
        sharedPreferences.edit().putString("nama",nama).commit();
    }

    public String getNama() {
        return sharedPreferences.getString("nama","John Doe");
    }

    public void setUserData(String[] data) {
        sharedPreferences.edit().putString("userName",data[0]).apply();
        sharedPreferences.edit().putString("keyPass",data[1]).apply();
    }

    public String[] getUserData() {
        String arr[] = {sharedPreferences.getString("userName","") ,
                sharedPreferences.getString("keyPass","")};

        return arr;
    }
}
