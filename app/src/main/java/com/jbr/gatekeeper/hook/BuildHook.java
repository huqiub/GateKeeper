package com.jbr.gatekeeper.hook;

import android.os.Build;

import com.jbr.gatekeeper.constants.Constants;
import com.jbr.gatekeeper.utils.XSharedPrefUtil;

import de.robv.android.xposed.XposedHelpers;

public class BuildHook {

    public static void hook() throws Throwable {

        XposedHelpers.findField(Build.class, "MANUFACTURER").set(null, XSharedPrefUtil.get(Constants.KEY_MANUFACTURER,Build.MANUFACTURER));
        XposedHelpers.findField(Build.class, "BRAND").set(null,  XSharedPrefUtil.get(Constants.KEY_BRAND,Build.BRAND));
        XposedHelpers.findField(Build.class, "MODEL").set(null, XSharedPrefUtil.get(Constants.KEY_MODEL,Build.MODEL));
        XposedHelpers.findField(Build.class, "DEVICE").set(null,  XSharedPrefUtil.get(Constants.KEY_DEVICE,Build.DEVICE));
        XposedHelpers.findField(Build.class, "PRODUCT").set(null, XSharedPrefUtil.get(Constants.KEY_PRODUCT,Build.PRODUCT));
        XposedHelpers.findField(Build.class, "USER").set(null, XSharedPrefUtil.get(Constants.KEY_USER,Build.USER));
        XposedHelpers.findField(Build.VERSION.class, "RELEASE").set(null,  XSharedPrefUtil.get(Constants.KEY_RELEASE,Build.VERSION.RELEASE));
        XposedHelpers.findField(Build.VERSION.class, "SDK").set(null, XSharedPrefUtil.get(Constants.KEY_SDK,Build.VERSION.SDK));
    }
}
