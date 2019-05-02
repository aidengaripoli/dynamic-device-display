package com.example.xmlui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.LinearLayout;

import java.util.List;
import java.util.Map;

import me.aidengaripoli.dynamicdevicedisplay.IoTDevice;
import me.aidengaripoli.dynamicdevicedisplay.IotNetworkDiscovery;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LinearLayout linearLayout = new LinearLayout(this);
        linearLayout.setOrientation(LinearLayout.VERTICAL);

        IotNetworkDiscovery iotNetworkDiscovery = new IotNetworkDiscovery();
        List<IoTDevice> devices = iotNetworkDiscovery.findDevices();

        for (IoTDevice device : devices) {
            Button button = new Button(this);
            button.setText(device.deviceName);
            linearLayout.addView(button);
            button.setOnClickListener(v -> {
                Intent intent = new Intent(this, IotDeviceActivity.class);
                intent.putExtra("xmlFile", device.displayXml);
                startActivity(intent);
            });
        }

        setContentView(linearLayout);
    }
}
