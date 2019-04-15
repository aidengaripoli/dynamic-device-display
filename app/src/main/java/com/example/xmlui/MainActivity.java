package com.example.xmlui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LinearLayout linearLayout = new LinearLayout(this);
        linearLayout.setOrientation(LinearLayout.VERTICAL);

        String devices[] = {"oven.xml", "TV.xml", "SecCamera.xml", "Thermostat.xml"};

        for (String device : devices) {
            Button button = new Button(this);
            button.setText(device);
            linearLayout.addView(button);
            button.setOnClickListener(v -> {
                Intent intent = new Intent(this, IotDeviceActivity.class);
                intent.putExtra("xmlFile", device);
                startActivity(intent);
            });
        }

        setContentView(linearLayout);
    }
}
