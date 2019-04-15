package com.example.xmlui;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import java.io.IOException;
import java.io.InputStream;

import me.aidengaripoli.dynamicdevicedisplay.UiGenerator;
import me.aidengaripoli.dynamicdevicedisplay.elements.ButtonGroupFragment;
import me.aidengaripoli.dynamicdevicedisplay.elements.DirectionalArrowsFragment;
import me.aidengaripoli.dynamicdevicedisplay.elements.InputFragment;
import me.aidengaripoli.dynamicdevicedisplay.elements.PasswordFragment;
import me.aidengaripoli.dynamicdevicedisplay.elements.PlusMinusFragment;
import me.aidengaripoli.dynamicdevicedisplay.elements.ProgressFragment;
import me.aidengaripoli.dynamicdevicedisplay.elements.SelectionFragment;
import me.aidengaripoli.dynamicdevicedisplay.elements.SliderFragment;
import me.aidengaripoli.dynamicdevicedisplay.elements.StatusFragment;
import me.aidengaripoli.dynamicdevicedisplay.elements.SwitchToggleFragment;
import me.aidengaripoli.dynamicdevicedisplay.elements.ToggleFragment;

public class IotDeviceActivity extends FragmentActivity implements
        ToggleFragment.OnFragmentInteractionListener,
        ProgressFragment.OnFragmentInteractionListener,
        SelectionFragment.OnFragmentInteractionListener,
        SliderFragment.OnFragmentInteractionListener,
        PlusMinusFragment.OnFragmentInteractionListener,
        DirectionalArrowsFragment.OnFragmentInteractionListener,
        SwitchToggleFragment.OnFragmentInteractionListener,
        StatusFragment.OnFragmentInteractionListener,
        InputFragment.OnFragmentInteractionListener,
        ButtonGroupFragment.OnFragmentInteractionListener,
        PasswordFragment.OnFragmentInteractionListener {

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
        }else{
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
    public void onFragmentInteraction(String buttonPressed) {

    }

    @Override
    public void onFragmentInteraction(String label, int value) {

    }

    @Override
    public void onFragmentInteraction(String label, String value) {

    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    public void onFragmentInteraction(String label, boolean state) {

    }
}
