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
        Bundle extras = getIntent().getExtras();

        String file;
        if (extras != null) {
            file = extras.getString("xmlFile");
        } else {
            return;
        }

        InputStream inputStream = null;
        try {
            assert file != null;
            inputStream = getAssets().open(file);
        } catch (IOException e) {
            e.printStackTrace();
        }

        assert inputStream != null;
        UiGenerator uiGenerator = new UiGenerator(fragmentManager, this);
        LinearLayout rootLayout = uiGenerator.generateUi(inputStream);
        scrollView.addView(rootLayout);

        // add the root layout to the content view
        setContentView(scrollView);
    }

    @Override
    public void onFragmentMessage(String tag, String data) {
        Log.i("Fragment Message", tag + ": " + data);
    }
}
