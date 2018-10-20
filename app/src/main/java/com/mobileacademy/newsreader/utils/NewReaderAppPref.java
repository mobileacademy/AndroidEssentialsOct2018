package com.mobileacademy.newsreader.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class NewReaderAppPref {

    private static final String PREF_NAME = "newsreaderpreferences";
    private SharedPreferences sharedPreferences;

    public NewReaderAppPref(Context context) {
        sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
    }

    public void addString(String key, String value) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key, value);

        editor.apply();
    }

    public String readString(String key) {
        return sharedPreferences.getString(key, "");
    }
}
