package me.aidengaripoli.dynamicdevicedisplay;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import java.io.IOException;
import java.io.InputStream;

public class DeviceActivity extends AppCompatActivity implements OnFragmentInteractionListener{
    FragmentManager fragmentManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        fragmentManager = getSupportFragmentManager();
        ScrollView scrollView = new ScrollView(this);
        Bundle extras = getIntent().getExtras();
        IotNetworkDiscovery iotNetworkDiscovery = new IotNetworkDiscovery(getAssets());

        String deviceName = extras != null ? extras.getString("deviceName") : null;
        IoTDevice device = iotNetworkDiscovery.connectToDevice(deviceName);
        String file = device.displayXml;

        // TODO: The file should be retrieved over the network and converted to an inputStream.
        InputStream inputStream = null;
        try {
            assert file != null;
            inputStream = getAssets().open(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        device.setDisplayStream(inputStream);

        UiGenerator uiGenerator = new UiGenerator(fragmentManager, this);
        LinearLayout rootLayout = uiGenerator.generateUi(device);
        scrollView.addView(rootLayout);

        // add the root layout to the content view
        setContentView(scrollView);
    }

    @Override
    public void onFragmentMessage(String tag, String data) {
        Log.i("Fragment Message", tag + ": " + data);
    }
}
