package com.example.xmlui;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import java.io.IOException;
import java.io.InputStream;

import me.aidengaripoli.dynamicdevicedisplay.OnFragmentInteractionListener;
import me.aidengaripoli.dynamicdevicedisplay.UiGenerator;

public class IotDeviceActivity extends FragmentActivity implements OnFragmentInteractionListener {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        FragmentManager fragmentManager = getSupportFragmentManager();
        ScrollView scrollView = new ScrollView(this);
        UiGenerator uiGenerator = new UiGenerator(fragmentManager);
        Bundle extras = getIntent().getExtras();

        String file;
        if (extras != null) {
            file = extras.getString("xmlFile");
        } else {
            return;
        }

        try {
            assert file != null;
            InputStream inputStream = getAssets().open(file);
            LinearLayout rootLayout = uiGenerator.generateUi(this, inputStream);
            scrollView.addView(rootLayout);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // add the root layout to the content view
        setContentView(scrollView);
    }

    @Override
    public void onFragmentMessage(String data) {
        Log.e("PRESSED", data);
    }
}
