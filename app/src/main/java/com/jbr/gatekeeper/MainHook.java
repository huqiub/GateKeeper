package com.jbr.gatekeeper;

import com.jbr.gatekeeper.hook.BuildHook;

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

public class MainHook implements IXposedHookLoadPackage {
    @Override
    public void handleLoadPackage(XC_LoadPackage.LoadPackageParam loadPkgParam) throws Throwable {
        BuildHook.hook(loadPkgParam);

    }
}
