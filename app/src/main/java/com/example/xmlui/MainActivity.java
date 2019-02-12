package com.example.xmlui;

import android.os.Bundle;
import android.util.Log;

import me.aidengaripoli.dynamicdevicedisplay.DeviceActivity;

public class MainActivity extends DeviceActivity {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.d(TAG, "MainActivity onCreate()");
    }
}
