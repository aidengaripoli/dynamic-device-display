package me.aidengaripoli.dynamicdevicedisplay;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;

import java.util.List;

public class DeviceDiscoveryActivity extends AppCompatActivity {
    IotNetworkDiscovery iotNetworkDiscovery;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LinearLayout linearLayout = new LinearLayout(this);
        linearLayout.setOrientation(LinearLayout.VERTICAL);

        iotNetworkDiscovery = new IotNetworkDiscovery(getAssets());
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
        Intent intent = new Intent(this, DeviceActivity.class);
        intent.putExtra("deviceName", device.deviceName);
        startActivity(intent);
    }
}
