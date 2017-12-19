package com.jbr.gatekeeper;

import android.Manifest;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Build;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.jbr.gatekeeper.constants.Constants;
import com.jbr.gatekeeper.utils.BuildProperties;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnRestore;
    private Button btnRandom;

    private TextView manufacturer;
    private TextView brand;
    private TextView model;
    private TextView device;
    private TextView product;
    private TextView release;
    private TextView user;
    private TextView sdk;
    private TextView serial;
    private TextView androidID;
    private TextView imei;
    private TextView phoneNumber;
    private TextView simserialNumber;
    private TextView imsi;
    private TextView simCountry;
    private TextView simOperator;
    private TextView simOperatorName;
    private TextView networkCountry;
    private TextView networkOperator;
    private TextView netWorkOperatorName;

    private Toast taost;

    private String strManufacturer;
    private String strBrand;
    private String strModel;
    private String strDevice;
    private String strProduct;
    private String strRelease;
    private String strUser;
    private String strSdk;
    private String strSerial;
    private String strAndroidID;
    private String strImei;
    private String strPhoneNumber;
    private String strSimserialNumber;
    private String strImsi;
    private String strSimCountry;
    private String strSimOperator;
    private String strSimOperatorName;
    private String strNetworkCountry;
    private String strNetworkOperator;
    private String strNetworkOperatorName;

    private SharedPreferences defaultSharedPref;
    private SharedPreferences randomSharedPref;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        defaultSharedPref = getPreferences(Context.MODE_PRIVATE);
        randomSharedPref = getSharedPreferences(Constants.PREFS_FILE, Context.MODE_WORLD_READABLE);

        if (!defaultSharedPref.getBoolean(Constants.KEY_IS_SET, false)) {
            setDefaultValues();
        }
        getTextView();
        initTextValue(randomSharedPref);
        setText();
        btnRestore = findViewById(R.id.btn_restore);
        btnRandom = findViewById(R.id.btn_random);
        btnRestore.setOnClickListener(this);
        btnRandom.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_restore:
                initTextValue(this.defaultSharedPref);
                setRandomSharedPref();
                setText();
                taostMessage("成功还原！");
                break;
            case R.id.btn_random:
                testRandom();
                setRandomSharedPref();
                setText();
                taostMessage("成功生成信息！");
                break;
            default:
                break;
        }
    }

    private void testRandom() {
        this.strManufacturer = "HUAWEI";
        this.strBrand = "HUAWEI";
        this.strModel = "HUAWEI";
        this.strDevice = "HUAWEI";
        this.strProduct = "HUAWEI";
        this.strRelease = "HUAWEI";
        this.strUser = "HUAWEI";
        this.strSdk = "HUAWEI";
        this.strSerial = "SERIAL";
        this.strAndroidID = "HUAWEI";
        this.strImei = "HUAWEI";
        this.strPhoneNumber = "13800138000";
        this.strSimserialNumber = "78686787";
        this.strImsi = "345453453453";
        this.strSimCountry = "cn";
        this.strSimOperator = "46002";
        this.strSimOperatorName = "中国移动";
        this.strNetworkCountry = "cn";
        this.strNetworkOperator = "46002";
        this.strNetworkOperatorName = "中国移动";

    }

    private void initTextValue(SharedPreferences sharedPref) {
        this.strManufacturer = sharedPref.getString(Constants.KEY_MANUFACTURER, Build.MANUFACTURER);
        this.strBrand = sharedPref.getString(Constants.KEY_BRAND, Build.BRAND);
        this.strModel = sharedPref.getString(Constants.KEY_MODEL, Build.MODEL);
        this.strDevice = sharedPref.getString(Constants.KEY_DEVICE, Build.DEVICE);
        this.strProduct = sharedPref.getString(Constants.KEY_PRODUCT, Build.PRODUCT);
        this.strRelease = sharedPref.getString(Constants.KEY_RELEASE, Build.VERSION.RELEASE);
        this.strUser = sharedPref.getString(Constants.KEY_USER, Build.USER);
        this.strSdk = sharedPref.getString(Constants.KEY_SDK, Build.VERSION.SDK);
        this.strSerial = sharedPref.getString(Constants.KEY_SERIAL, Build.SERIAL);
        this.strAndroidID = sharedPref.getString(Constants.KEY_ANDROID_ID, getAndroidID());
        this.strImei = sharedPref.getString(Constants.KEY_IMEI, getIMEI());
        this.strPhoneNumber = sharedPref.getString(Constants.KEY_PHONE_NUMBER, getPhoneNumber());
        this.strSimserialNumber = sharedPref.getString(Constants.KEY_SIMSERIAL_NUMBER, getSimserialNumber());
        this.strImsi = sharedPref.getString(Constants.KEY_IMSI, getIMSI());
        this.strSimCountry = sharedPref.getString(Constants.KEY_SIM_COUNTRY, getSimCountry());
        this.strSimOperator = sharedPref.getString(Constants.KEY_SIM_OPERATOR, getSimOperator());
        this.strSimOperatorName = sharedPref.getString(Constants.KEY_SIM_OPERATOR_NAME, getSimOperatorName());
        this.strNetworkCountry = sharedPref.getString(Constants.KEY_NETWORK_COUNTRY, getNetworkCountry());
        this.strNetworkOperator = sharedPref.getString(Constants.KEY_NETWORK_OPERATOR, getNetWorkOperator());
        this.strNetworkOperatorName = sharedPref.getString(Constants.KEY_NETWORK_OPERATOR_NAME, getNetWorkOperatorName());

    }

    private void setRandomSharedPref() {
        SharedPreferences.Editor editor = this.randomSharedPref.edit();
        editor.putString(Constants.KEY_MANUFACTURER, this.strManufacturer);
        editor.putString(Constants.KEY_BRAND, this.strBrand);
        editor.putString(Constants.KEY_MODEL, this.strModel);
        editor.putString(Constants.KEY_DEVICE, this.strDevice);
        editor.putString(Constants.KEY_PRODUCT, this.strProduct);
        editor.putString(Constants.KEY_RELEASE, this.strRelease);
        editor.putString(Constants.KEY_USER, this.strUser);
        editor.putString(Constants.KEY_SDK, this.strSdk);
        editor.putString(Constants.KEY_SERIAL, this.strSerial);
        editor.putString(Constants.KEY_ANDROID_ID, this.strAndroidID);
        editor.putString(Constants.KEY_IMEI, this.strImei);
        editor.putString(Constants.KEY_PHONE_NUMBER, this.strPhoneNumber);
        editor.putString(Constants.KEY_SIMSERIAL_NUMBER, this.strSimserialNumber);
        editor.putString(Constants.KEY_IMSI, this.strImsi);
        editor.putString(Constants.KEY_SIM_COUNTRY, this.strSimCountry);
        editor.putString(Constants.KEY_SIM_OPERATOR, this.strSimOperator);
        editor.putString(Constants.KEY_SIM_OPERATOR_NAME, this.strSimOperatorName);
        editor.putString(Constants.KEY_NETWORK_COUNTRY, this.strNetworkCountry);
        editor.putString(Constants.KEY_NETWORK_OPERATOR, this.strNetworkOperator);
        editor.putString(Constants.KEY_NETWORK_OPERATOR_NAME, this.strNetworkOperatorName);
        editor.apply();
    }

    private void setText() {
        this.manufacturer.setText(this.strManufacturer);
        this.brand.setText(this.strBrand);
        this.model.setText(this.strModel);
        this.device.setText(this.strDevice);
        this.product.setText(this.strProduct);
        this.release.setText(this.strRelease);
        this.user.setText(this.strUser);
        this.sdk.setText(this.strSdk);
        this.serial.setText(this.strSerial);
        this.androidID.setText(this.strAndroidID);
        this.imei.setText(this.strImei);
        this.phoneNumber.setText(this.strPhoneNumber);
        this.simserialNumber.setText(this.strSimserialNumber);
        this.imsi.setText(this.strImsi);
        this.simCountry.setText(this.strSimCountry);
        this.simOperator.setText(this.strSimOperator);
        this.simOperatorName.setText(this.strSimOperatorName);
        this.networkCountry.setText(this.strNetworkCountry);
        this.networkOperator.setText(this.strNetworkOperator);
        this.netWorkOperatorName.setText(this.strNetworkOperatorName);
    }

    private void getTextView() {
        this.manufacturer = findViewById(R.id.manufacturer);
        this.brand = findViewById(R.id.brand);
        this.model = findViewById(R.id.model);
        this.device = findViewById(R.id.device);
        this.product = findViewById(R.id.product);
        this.release = findViewById(R.id.release);
        this.user = findViewById(R.id.user);
        this.sdk = findViewById(R.id.sdk);
        this.serial = findViewById(R.id.serial);
        this.androidID = findViewById(R.id.android_id);
        this.imei = findViewById(R.id.imei);
        this.phoneNumber = findViewById(R.id.phone_number);
        this.simserialNumber = findViewById(R.id.simserial_number);
        this.imsi = findViewById(R.id.imsi);
        this.simCountry = findViewById(R.id.sim_country);
        this.simOperator = findViewById(R.id.sim_operator);
        this.simOperatorName = findViewById(R.id.sim_operator_name);
        this.networkCountry = findViewById(R.id.network_country);
        this.networkOperator = findViewById(R.id.network_operator);
        this.netWorkOperatorName = findViewById(R.id.network_operator_name);
    }

    private void setDefaultValues() {
        BuildProperties properties = new BuildProperties();
        SharedPreferences.Editor defaultEditor = defaultSharedPref.edit();
        defaultEditor.putString(Constants.KEY_MANUFACTURER, properties.getProperty(Constants.BUILD_PRODUCT_MANUFACTURER, ""));
        defaultEditor.putString(Constants.KEY_BRAND, properties.getProperty(Constants.BUILD_PRODUCT_BRAND, ""));
        defaultEditor.putString(Constants.KEY_MODEL, properties.getProperty(Constants.BUILD_PRODUCT_MODEL, ""));
        defaultEditor.putString(Constants.KEY_DEVICE, properties.getProperty(Constants.BUILD_PRODUCT_DEVICE, ""));
        defaultEditor.putString(Constants.KEY_PRODUCT, properties.getProperty(Constants.BUILD_PRODUCT, ""));
        defaultEditor.putString(Constants.KEY_RELEASE, properties.getProperty(Constants.BUILD_VERSION_RELEASE, ""));
        defaultEditor.putString(Constants.KEY_USER, properties.getProperty(Constants.BUILD_USER, ""));
        defaultEditor.putString(Constants.KEY_SDK, properties.getProperty(Constants.BUILD_VERSION_SDK, ""));
        defaultEditor.putString(Constants.KEY_SERIAL, Build.SERIAL);

        defaultEditor.putString(Constants.KEY_ANDROID_ID, getAndroidID());
        defaultEditor.putString(Constants.KEY_IMEI, getIMEI());
        defaultEditor.putString(Constants.KEY_PHONE_NUMBER, getPhoneNumber());
        defaultEditor.putString(Constants.KEY_SIMSERIAL_NUMBER, getSimserialNumber());
        defaultEditor.putString(Constants.KEY_IMSI, getIMSI());
        defaultEditor.putString(Constants.KEY_SIM_COUNTRY, getSimCountry());
        defaultEditor.putString(Constants.KEY_SIM_OPERATOR, getSimOperator());
        defaultEditor.putString(Constants.KEY_SIM_OPERATOR_NAME, getSimOperatorName());
        defaultEditor.putString(Constants.KEY_NETWORK_COUNTRY, getNetworkCountry());
        defaultEditor.putString(Constants.KEY_NETWORK_OPERATOR, getNetWorkOperator());
        defaultEditor.putString(Constants.KEY_NETWORK_OPERATOR, getNetWorkOperatorName());
        defaultEditor.putBoolean(Constants.KEY_IS_SET, true);
        defaultEditor.apply();
    }

    private void taostMessage(String message) {
        if (this.taost != null) {
            this.taost.cancel();
        }
        this.taost = Toast.makeText(getBaseContext(), message, Toast.LENGTH_SHORT);
        this.taost.show();
    }

    private String getAndroidID() {
        return Settings.Secure.getString(this.getContentResolver(), Settings.Secure.ANDROID_ID);
    }

    private String getIMEI() {
        TelephonyManager tm = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        String imei = "NA";
        try {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
                imei = getAndroidID();
            } else {
                imei = tm.getDeviceId();
            }
        } catch (Exception e) {
            Log.e("PHONE", "获取IMEI出错=" + e.getMessage());
        }
        return imei;
    }

    private String getPhoneNumber() {
        TelephonyManager tm = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        String phoneNumber = "";
        try {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
                phoneNumber = "13800138000";
            } else {
                phoneNumber = tm.getLine1Number();
            }
        } catch (Exception e) {
            Log.e("PHONE", "获取手机号码出错=" + e.getMessage());
        }
        return phoneNumber;
    }

    private String getSimserialNumber() {
        TelephonyManager tm = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        String simserialNumber = "";

        try {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
                simserialNumber = "NA";
            } else {
                simserialNumber = tm.getSimSerialNumber();
            }
        } catch (Exception e) {
            Log.e("PHONE", "获取手机卡序列号出错=" + e.getMessage());
        }
        return simserialNumber;
    }
    private String getIMSI() {
        TelephonyManager tm = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        String imsi = "";

        try {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
                imsi = "NA";
            } else {
                imsi = tm.getSubscriberId();
            }
        } catch (Exception e) {
            Log.e("PHONE", "获取IMSI出错=" + e.getMessage());
        }
        return imsi;
    }

    private String getSimCountry() {
        TelephonyManager tm = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        return tm.getSimCountryIso();
    }
    private String getSimOperator() {
        TelephonyManager tm = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        return tm.getSimOperator();
    }
    private String getSimOperatorName() {
        TelephonyManager tm = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        return tm.getSimOperatorName();
    }
    private String getNetworkCountry() {
        TelephonyManager tm = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        return tm.getNetworkCountryIso();
    }
    private String getNetWorkOperator() {
        TelephonyManager tm = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        return tm.getNetworkOperator();
    }
    private String getNetWorkOperatorName() {
        TelephonyManager tm = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        return tm.getNetworkOperatorName();
    }

}
