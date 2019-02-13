package me.aidengaripoli.dynamicdevicedisplay;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.InputStream;
import java.util.HashMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import me.aidengaripoli.dynamicdevicedisplay.elements.ToggleFragment;
import me.aidengaripoli.dynamicdevicedisplay.elements.ToggleFragmentState;

public class DeviceActivity extends FragmentActivity
        implements ToggleFragment.OnFragmentInteractionListener {

    private static final String TAG = "DeviceActivity";

    private HashMap<String, Integer> elements = new HashMap<>();

    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_device);

        int rootLayoutId = View.generateViewId();
        LinearLayout rootLayout = new LinearLayout(this);
        rootLayout.setOrientation(LinearLayout.VERTICAL);
        rootLayout.setId(rootLayoutId);

        fragmentManager = getSupportFragmentManager();

        // XML parsing
        // TODO: refactor xml parsing of UI to another class
        try {
            InputStream inputStream = getAssets().open("ui.xml");

            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(inputStream);

            Element deviceElement = document.getDocumentElement();
            deviceElement.normalize();

            // get all of the <group> elements
            NodeList groupNodeList = document.getElementsByTagName("group");

            // iterate through all the <group> elements
            for (int i = 0; i < groupNodeList.getLength(); i++) {
                Node node = groupNodeList.item(i);

                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element groupElement = (Element) node;

                    // generate a layout for each group
                    ViewGroup groupLayout = generateGroupLayout(groupElement);

                    // add the layout to the root layout
                    rootLayout.addView(groupLayout);
                }
            }
        } catch (Exception e) {
            Log.e(TAG, "ERROR WHILST GENERATING UI: " + e.getMessage());
        }

        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
        );

        // add the root layout to the content view
        setContentView(rootLayout, layoutParams);
    }

    private ViewGroup generateGroupLayout(Element element) {
        NodeList guiNodeList = element.getElementsByTagName("gui_element");

        // TODO: remove hardcoded linear layout
        int layoutId = View.generateViewId();
        LinearLayout layout = new LinearLayout(this);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        layoutParams.setMargins(40, 20, 0, 20);
        layout.setOrientation(LinearLayout.HORIZONTAL);
        layout.setLayoutParams(layoutParams);
        layout.setId(layoutId);

        // iterate through all the <gui_element> elements in the group
        for (int i = 0; i < guiNodeList.getLength(); i++) {
            Node node = guiNodeList.item(i);

            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element guiElement = (Element) node;

                // generate a view (widget) for each gui_element
                Fragment fragment = generateGuiElement(guiElement);

                // add the view to the groups layout
                if (fragment != null) {
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                    fragmentTransaction.add(layoutId, fragment);
                    fragmentTransaction.commit();
                }
            }
        }
        return layout;
    }

    private Fragment generateGuiElement(Element element) {
        // get the elements label value
        NodeList nodeList = element.getElementsByTagName("label").item(0).getChildNodes();
        Node node = nodeList.item(0);
        String labelValue = node.getNodeValue();

        // get the element type
        Node typeNode = element.getElementsByTagName("type").item(0)
                .getChildNodes().item(0);
        String type = typeNode.getNodeValue().toLowerCase().trim();

        // return an android widget based on element type
        switch (type) {
            case "toggle": {
                return ToggleFragment.newInstance(labelValue, false);
            }

            default: {
                // TODO: handle invalid element type
                return null;
            }
        }
    }

    @Override
    public void onFragmentInteraction(ToggleFragmentState state) {
        Toast.makeText(
                getApplicationContext(),
                state.getLabel() + " state is now: " + state.getState(),
                Toast.LENGTH_SHORT
        ).show();
    }
}
