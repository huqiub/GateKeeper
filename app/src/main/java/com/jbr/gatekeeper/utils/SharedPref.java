package com.jbr.gatekeeper.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.jbr.gatekeeper.constants.Constants;

import de.robv.android.xposed.XSharedPreferences;

/**
 * Created by jack on 2017/12/17.
 */

public class SharedPref {

    private Context shareContext;
    private SharedPreferences defaultSharedPref;
    private SharedPreferences mySharedPref;
    private static XSharedPreferences myXsharedPref;

    public SharedPref(Context appContext) {
        shareContext = appContext;
        mySharedPref = shareContext.getSharedPreferences(Constants.PREFS_FILE,Context.MODE_PRIVATE);
    }

    public void setSharedPref(String key, String value) {
        try {
            mySharedPref.edit().putString(key, value).apply();
        } catch (Exception e) {
            Log.e("SharedPref","setSharedPref ERROR: " + e.getMessage());
        }
    }
    public void setIntSharedPref(String key, int value) {
        try {
            mySharedPref.edit().putInt(key, value).apply();
        } catch (Exception e) {
            Log.e("SharedPref","setIntSharedPref ERROR: " + e.getMessage());
        }
    }

    public void setFloatharedPref(String key, float value) {
        try {
            mySharedPref.edit().putFloat(key, value).apply();
        } catch (Exception e) {
            System.out.println("setFlaotSharedPref ERROR: " + e.getMessage());
        }
    }
}
