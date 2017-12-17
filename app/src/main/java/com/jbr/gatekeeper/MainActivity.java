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

    private Toast taost;

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
        randomSharedPref = getSharedPreferences(Constants.PREFS_FILE,Context.MODE_WORLD_READABLE);

        if(!defaultSharedPref.getBoolean(Constants.KEY_IS_SET,false)){
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
        switch (view.getId()){
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
    private void testRandom(){
        this.strManufacturer = "HUAWEI";
        this.strBrand = "HUAWEI";
        this.strModel = "HUAWEI";
        this.strDevice = "HUAWEI";
        this.strProduct = "HUAWEI";
        this.strRelease =  "HUAWEI";
        this.strUser = "HUAWEI";
        this.strSdk = "HUAWEI";

    }
    private void initTextValue(SharedPreferences sharedPref){
        this.strManufacturer = sharedPref.getString(Constants.KEY_MANUFACTURER, Build.MANUFACTURER);
        this.strBrand = sharedPref.getString(Constants.KEY_BRAND,Build.BRAND);
        this.strModel = sharedPref.getString(Constants.KEY_MODEL,Build.MODEL);
        this.strDevice = sharedPref.getString(Constants.KEY_DEVICE,Build.DEVICE);
        this.strProduct = sharedPref.getString(Constants.KEY_PRODUCT,Build.PRODUCT);
        this.strRelease =  sharedPref.getString(Constants.KEY_RELEASE,Build.VERSION.RELEASE);
        this.strUser = sharedPref.getString(Constants.KEY_USER,Build.USER);
        this.strSdk = sharedPref.getString(Constants.KEY_SDK,Build.VERSION.SDK);

    }

    private void setRandomSharedPref(){
        SharedPreferences.Editor editor = this.randomSharedPref.edit();
        editor.putString(Constants.KEY_MANUFACTURER,this.strManufacturer);
        editor.putString(Constants.KEY_BRAND,this.strBrand);
        editor.putString(Constants.KEY_MODEL,this.strModel);
        editor.putString(Constants.KEY_DEVICE,this.strDevice);
        editor.putString(Constants.KEY_PRODUCT,this.strProduct);
        editor.putString(Constants.KEY_RELEASE,this.strRelease);
        editor.putString(Constants.KEY_USER,this.strUser);
        editor.putString(Constants.KEY_SDK,this.strSdk);
        editor.apply();
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
        SharedPreferences.Editor defaultEditor = defaultSharedPref.edit();
        defaultEditor.putString(Constants.KEY_MANUFACTURER,properties.getProperty(Constants.BUILD_PRODUCT_MANUFACTURER,""));
        defaultEditor.putString(Constants.KEY_BRAND,properties.getProperty(Constants.BUILD_PRODUCT_BRAND,""));
        defaultEditor.putString(Constants.KEY_MODEL,properties.getProperty(Constants.BUILD_PRODUCT_MODEL,""));
        defaultEditor.putString(Constants.KEY_DEVICE,properties.getProperty(Constants.BUILD_PRODUCT_DEVICE,""));
        defaultEditor.putString(Constants.KEY_PRODUCT,properties.getProperty(Constants.BUILD_PRODUCT,""));
        defaultEditor.putString(Constants.KEY_RELEASE,properties.getProperty(Constants.BUILD_VERSION_RELEASE,""));
        defaultEditor.putString(Constants.KEY_USER,properties.getProperty(Constants.BUILD_USER,""));
        defaultEditor.putString(Constants.KEY_SDK,properties.getProperty(Constants.BUILD_VERSION_SDK,""));
        defaultEditor.putBoolean(Constants.KEY_IS_SET,true);
        defaultEditor.apply();
    }



    private void taostMessage(String message)
    {
        if (this.taost != null) {
            this.taost.cancel();
        }
        this.taost = Toast.makeText(getBaseContext(), message, Toast.LENGTH_SHORT);
        this.taost.show();
    }
}
