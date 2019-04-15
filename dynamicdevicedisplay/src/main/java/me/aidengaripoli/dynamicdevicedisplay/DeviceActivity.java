package me.aidengaripoli.dynamicdevicedisplay;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;

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
import me.aidengaripoli.dynamicdevicedisplay.elements.TimePickerFragment;
import me.aidengaripoli.dynamicdevicedisplay.elements.ToggleFragment;

public class DeviceActivity extends FragmentActivity implements
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
        PasswordFragment.OnFragmentInteractionListener,
        TimePickerFragment.OnFragmentInteractionListener{

    private static final String TAG = "DeviceActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        FragmentManager fragmentManager = getSupportFragmentManager();
        ScrollView scrollView = new ScrollView(this);
        UiGenerator uiGenerator = new UiGenerator(fragmentManager);

        try {
            InputStream inputStream = getAssets().open("ui.xml");
            LinearLayout rootLayout = uiGenerator.generateUi(this, inputStream);
            scrollView.addView(rootLayout);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // add the root layout to the content view
        setContentView(scrollView);
    }

    @Override
    public void onFragmentInteraction(String label, boolean state) {
        Toast.makeText(
                getApplicationContext(),
                label + " state is now: " + state,
                Toast.LENGTH_SHORT
        ).show();
    }

    @Override
    public void onFragmentInteraction(String label, int value) {
        Toast.makeText(
                getApplicationContext(),
                label + " progress is now: " + value,
                Toast.LENGTH_SHORT
        ).show();
    }

    @Override
    public void onFragmentInteraction(String label, String value) {
        Toast.makeText(
                getApplicationContext(),
                label + " spinner is now: " + value,
                Toast.LENGTH_SHORT
        ).show();
    }

    @Override
    public void onFragmentInteraction(Uri uri) {
        Toast.makeText(
                getApplicationContext(),
                " slider is now: ",
                Toast.LENGTH_SHORT
        ).show();
    }

    @Override
    public void onFragmentInteraction(String buttonPressed) {
        Toast.makeText(
                getApplicationContext(),
                buttonPressed + " pressed",
                Toast.LENGTH_SHORT
        ).show();
    }
}
