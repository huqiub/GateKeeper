package com.jbr.gatekeeper;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.jbr.gatekeeper.constants.Constants;
import com.jbr.gatekeeper.utils.BuildProperties;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;

public class MainActivity extends AppCompatActivity {

    private Button btnRestore;

    private TextView manufacturer;
    private TextView brand;
    private TextView model;
    private TextView device;
    private TextView product;
    private TextView release;
    private TextView user;
    private TextView sdk;

    private String strManufacturer;
    private String strBrand;
    private String strModel;
    private String strDevice;
    private String strProduct;
    private String strRelease;
    private String strUser;
    private String strSdk;

    private SharedPreferences defaultSharedPref;
    private SharedPreferences randomSharedPref;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        defaultSharedPref = getPreferences(Context.MODE_PRIVATE);
        randomSharedPref = getSharedPreferences(Constants.PREFS_FILE,Context.MODE_PRIVATE);

        if(!defaultSharedPref.getBoolean(Constants.KEY_IS_SET,false)){
            setDefaultValues();
        //TODO：只赋值了手机BUILD信息，其他信息待补全
        }
        initSharedPref(randomSharedPref);
        getTextView();
        setText();
        btnRestore = findViewById(R.id.btn_restore);
        btnRestore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //setDefaultValues();
                Toast.makeText(MainActivity.this,"ttttttttttttt",Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initSharedPref(SharedPreferences randomSharedPref){
        this.strManufacturer = randomSharedPref.getString(Constants.KEY_MANUFACTURER, Build.MANUFACTURER);
        this.strBrand = randomSharedPref.getString(Constants.KEY_BRAND,Build.BRAND);
        this.strModel = randomSharedPref.getString(Constants.KEY_MODEL,Build.MODEL);
        this.strDevice = randomSharedPref.getString(Constants.KEY_DEVICE,Build.DEVICE);
        this.strProduct = randomSharedPref.getString(Constants.KEY_PRODUCT,Build.PRODUCT);
        this.strRelease =  randomSharedPref.getString(Constants.KEY_RELEASE,Build.VERSION.RELEASE);
        this.strUser = randomSharedPref.getString(Constants.KEY_USER,Build.USER);
        this.strSdk = randomSharedPref.getString(Constants.KEY_SDK,Build.VERSION.SDK);

    }

    private void setText(){
        this.manufacturer.setText(this.strManufacturer);
        this.brand.setText(this.strBrand);
        this.model.setText(this.strModel);
        this.device.setText(this.strDevice);
        this.product.setText(this.strProduct);
        this.release.setText(this.strRelease);
        this.user.setText(this.strUser);
        this.sdk.setText(this.strSdk);
    }

    private void getTextView(){
        this.manufacturer = findViewById(R.id.manufacturer);
        this.brand = findViewById(R.id.brand);
        this.model = findViewById(R.id.model);
        this.device = findViewById(R.id.device);
        this.product = findViewById(R.id.product);
        this.release = findViewById(R.id.release);
        this.user = findViewById(R.id.user);
        this.sdk = findViewById(R.id.sdk);
    }

    private void setDefaultValues(){
        BuildProperties properties = new BuildProperties();
        String manufacturer = properties.getProperty(Constants.BUILD_PRODUCT_MANUFACTURER,"");
        String brand = properties.getProperty(Constants.BUILD_PRODUCT_BRAND,"");
        String model = properties.getProperty(Constants.BUILD_PRODUCT_MODEL,"");
        String device = properties.getProperty(Constants.BUILD_PRODUCT_DEVICE,"");
        String product = properties.getProperty(Constants.BUILD_PRODUCT,"");
        String release = properties.getProperty(Constants.BUILD_VERSION_RELEASE,"");
        String user = properties.getProperty(Constants.BUILD_USER,"");
        String sdk =  properties.getProperty(Constants.BUILD_VERSION_SDK,"");

        SharedPreferences.Editor defaultEditor = defaultSharedPref.edit();
        defaultEditor.putString(Constants.KEY_MANUFACTURER,manufacturer);
        defaultEditor.putString(Constants.KEY_BRAND,brand);
        defaultEditor.putString(Constants.KEY_MODEL,model);
        defaultEditor.putString(Constants.KEY_DEVICE,device);
        defaultEditor.putString(Constants.KEY_PRODUCT,product);
        defaultEditor.putString(Constants.KEY_RELEASE,release);
        defaultEditor.putString(Constants.KEY_USER,user);
        defaultEditor.putString(Constants.KEY_SDK,sdk);
        defaultEditor.putBoolean(Constants.KEY_IS_SET,true);
        defaultEditor.apply();
    }
}
