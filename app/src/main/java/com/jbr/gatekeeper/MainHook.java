package com.jbr.gatekeeper;

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

/**
 * Created by jack on 2017/12/16.
 */

public class MainHook implements IXposedHookLoadPackage {
    @Override
    public void handleLoadPackage(XC_LoadPackage.LoadPackageParam loadPkgParam) throws Throwable {
        XposedBridge.log("进入拦截方法");
        XposedBridge.log("-----------------Found app: " + loadPkgParam.packageName);

//        XposedHelpers.setStaticObjectField(android.os.Build.class, "BOARD", "HUAWEI");
//        XposedHelpers.setStaticObjectField(android.os.Build.class, "CPU_ABI", "HUAWEI");
//        XposedHelpers.setStaticObjectField(android.os.Build.class, "BRAND",  "HUAWEI");
//        XposedHelpers.setStaticObjectField(android.os.Build.class, "MODEL", "HUAWEI");
//        XposedHelpers.setStaticObjectField(android.os.Build.class, "ID","HUAWEI");
//        XposedHelpers.setStaticObjectField(android.os.Build.class, "DISPLAY",  "HUAWEI");
//        XposedHelpers.setStaticObjectField(android.os.Build.class, "PRODUCT", "HUAWEI");
//        XposedHelpers.setStaticObjectField(android.os.Build.class, "MANUFACTURER",  "HUAWEI");
//        XposedHelpers.setStaticObjectField(android.os.Build.class, "DEVICE",  "HUAWEI");
//        XposedHelpers.setStaticObjectField(android.os.Build.class, "HARDWARE",  "HUAWEI");
//        XposedHelpers.setStaticObjectField(android.os.Build.class, "SERIAL",  "HUAWEI");
//        XposedHelpers.setStaticObjectField(android.os.Build.class, "FINGERPRINT", "HUAWEI");
    }
}
