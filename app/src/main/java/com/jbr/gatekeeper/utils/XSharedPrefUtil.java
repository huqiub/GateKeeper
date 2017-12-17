package com.jbr.gatekeeper.utils;

import com.jbr.gatekeeper.constants.Constants;

import de.robv.android.xposed.XSharedPreferences;


public class XSharedPrefUtil {

    private static XSharedPreferences pref;

    public static String get(String key, String defaultValue)
    {
        return init().getString(key, defaultValue);
    }

    public static XSharedPreferences init()
    {
        if (pref == null) {
            pref = new XSharedPreferences(Constants.PACKAGE_NAME, Constants.PREFS_FILE);
        }
        return pref;
    }
}
