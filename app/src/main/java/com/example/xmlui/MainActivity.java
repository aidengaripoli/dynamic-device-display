package com.example.xmlui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.LinearLayout;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import me.aidengaripoli.dynamicdevicedisplay.IoTDevice;
import me.aidengaripoli.dynamicdevicedisplay.IotNetworkDiscovery;

public class MainActivity extends AppCompatActivity {
    IotNetworkDiscovery iotNetworkDiscovery;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LinearLayout linearLayout = new LinearLayout(this);
        linearLayout.setOrientation(LinearLayout.VERTICAL);

        iotNetworkDiscovery = new IotNetworkDiscovery();
        List<IoTDevice> devices = iotNetworkDiscovery.findDevices();

        for (IoTDevice device : devices) {
            Button button = new Button(this);
            button.setText(device.deviceName);
            linearLayout.addView(button);
            button.setOnClickListener(v -> createDisplay(device));
        }

        setContentView(linearLayout);
    }

    private void createDisplay(IoTDevice device){
        Intent intent = new Intent(this, IotDeviceActivity.class);
        intent.putExtra("deviceName", device.deviceName);
        startActivity(intent);
    }
}
