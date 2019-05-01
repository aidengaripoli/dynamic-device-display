package com.example.xmlui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.LinearLayout;

import java.util.Map;

import me.aidengaripoli.dynamicdevicedisplay.IotNetworkDiscovery;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LinearLayout linearLayout = new LinearLayout(this);
        linearLayout.setOrientation(LinearLayout.VERTICAL);

        IotNetworkDiscovery iotNetworkDiscovery = new IotNetworkDiscovery();
        Map<String,String> devices = iotNetworkDiscovery.findDevices();

        for (Map.Entry<String, String> device : devices.entrySet()) {
            Button button = new Button(this);
            button.setText(device.getValue());
            linearLayout.addView(button);
            button.setOnClickListener(v -> {
                Intent intent = new Intent(this, IotDeviceActivity.class);
                intent.putExtra("xmlFile", device.getKey());
                startActivity(intent);
            });
        }

        setContentView(linearLayout);
    }
}
