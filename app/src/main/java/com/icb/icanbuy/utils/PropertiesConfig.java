
package com.icb.icanbuy.utils;

import android.content.Context;
import android.content.res.AssetManager;
import android.util.Log;

import com.icb.icanbuy.constants.FirebaseConstant;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import static com.icb.icanbuy.constants.FirebaseConstant.INITIALIZE_PROPERTIES_ERROR_MESSAGE;

public class PropertiesConfig {

    private Context context;
    private Properties properties;
    private InputStream inputStream;
    private final static String ICANBUY_PROPERTIES = "icanbuy.properties";
    private final static String FIREBASE_URL = "firebase_URL";
    private final static String MAC_ADDRESS = "mac_adress";
    private final static String SERVER_URL= "server_URL";
    private final static String AIRTABLE_API_KEY = "airtable_api_key";
    private final static String AIRTABLE_BASE_URL= "airtable_base_url";

    public PropertiesConfig(Context context){
        this.context = context;
        properties = new Properties();
        initializeProperties();
    }

    public String getProperty(String propertyName) {
        return String.valueOf(properties.get(propertyName));
    }

    private void initializeProperties() {
        AssetManager assetManager = context.getAssets();
        try {
            inputStream = assetManager.open(ICANBUY_PROPERTIES);
            properties.load(inputStream);

        } catch (IOException e) {
            Log.e(FirebaseConstant.APPLICATION_TAG, INITIALIZE_PROPERTIES_ERROR_MESSAGE + e.getMessage());
        }
    }

    public String getFirebaseURL(){
        return getProperty(FIREBASE_URL);
    }

    public String getMacAddress(){
        return getProperty(MAC_ADDRESS);
    }

    public String getServerUrl(){
        return getProperty(SERVER_URL);
    }

    public String getAirtableApiKey(){
        return getProperty(AIRTABLE_API_KEY);
    }

    public String getAirtableBaseUrl(){
        return getProperty(AIRTABLE_BASE_URL);
    }
}
