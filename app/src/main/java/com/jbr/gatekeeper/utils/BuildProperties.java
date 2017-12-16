package com.jbr.gatekeeper.utils;

import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class BuildProperties {

    private Properties properties = null;

    public BuildProperties(){
        try {
            properties = new Properties();
            FileInputStream is = new FileInputStream(new File(Environment.getRootDirectory(), "build.prop"));
            properties.load(is);
            is.close();
        }catch (IOException exception){
            Log.e("IO","获取/system/build.prop文件出错--"+exception);
        }

    }

    public String getProperty(final String name, final String defaultValue) {
        return properties.getProperty(name, defaultValue);
    }
}
