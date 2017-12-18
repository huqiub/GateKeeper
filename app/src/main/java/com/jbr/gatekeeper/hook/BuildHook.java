package com.jbr.gatekeeper.hook;

import android.content.ContentResolver;
import android.os.Build;
import android.provider.Settings;

import com.jbr.gatekeeper.constants.Constants;
import com.jbr.gatekeeper.utils.XSharedPrefUtil;

import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

public class BuildHook {

    public static void hook(XC_LoadPackage.LoadPackageParam loadPkgParam) throws Throwable {

        XposedHelpers.findField(Build.class, "MANUFACTURER").set(null, XSharedPrefUtil.get(Constants.KEY_MANUFACTURER,Build.MANUFACTURER));
        XposedHelpers.findField(Build.class, "BRAND").set(null,  XSharedPrefUtil.get(Constants.KEY_BRAND,Build.BRAND));
        XposedHelpers.findField(Build.class, "MODEL").set(null, XSharedPrefUtil.get(Constants.KEY_MODEL,Build.MODEL));
        XposedHelpers.findField(Build.class, "DEVICE").set(null,  XSharedPrefUtil.get(Constants.KEY_DEVICE,Build.DEVICE));
        XposedHelpers.findField(Build.class, "PRODUCT").set(null, XSharedPrefUtil.get(Constants.KEY_PRODUCT,Build.PRODUCT));
        XposedHelpers.findField(Build.class, "USER").set(null, XSharedPrefUtil.get(Constants.KEY_USER,Build.USER));
        XposedHelpers.findField(Build.VERSION.class, "RELEASE").set(null,  XSharedPrefUtil.get(Constants.KEY_RELEASE,Build.VERSION.RELEASE));
        XposedHelpers.findField(Build.VERSION.class, "SDK").set(null, XSharedPrefUtil.get(Constants.KEY_SDK,Build.VERSION.SDK));

//        try {
//            XposedHelpers.findAndHookMethod("android.provider.Settings.Secure", loadPkgParam.classLoader, "getString",ContentResolver.class, String.class, new XC_MethodHook() {
//                @Override
//                protected void afterHookedMethod(MethodHookParam param)throws Throwable {
//                    if (param.args[1].equals(Settings.Secure.ANDROID_ID)) {
//                        param.setResult(XSharedPrefUtil.get(Constants.KEY_ANDROID_ID,"tt7678hh"));
//                    }
//                }
//            });
//        } catch (Exception e) {
//            XposedBridge.log("获取 Android ID 错误: " + e.getMessage());
//        }
    }
}
