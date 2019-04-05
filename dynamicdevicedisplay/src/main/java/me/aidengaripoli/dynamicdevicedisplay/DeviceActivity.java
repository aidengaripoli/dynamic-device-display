package me.aidengaripoli.dynamicdevicedisplay;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.InputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import me.aidengaripoli.dynamicdevicedisplay.elements.DirectionalArrowsFragment;
import me.aidengaripoli.dynamicdevicedisplay.elements.PlusMinusFragment;
import me.aidengaripoli.dynamicdevicedisplay.elements.ProgressFragment;
import me.aidengaripoli.dynamicdevicedisplay.elements.SelectionFragment;
import me.aidengaripoli.dynamicdevicedisplay.elements.SliderFragment;
import me.aidengaripoli.dynamicdevicedisplay.elements.ToggleFragment;

public class DeviceActivity extends FragmentActivity implements
        ToggleFragment.OnFragmentInteractionListener,
        ProgressFragment.OnFragmentInteractionListener,
        SelectionFragment.OnFragmentInteractionListener,
        SliderFragment.OnFragmentInteractionListener,
        PlusMinusFragment.OnFragmentInteractionListener,
        DirectionalArrowsFragment.OnFragmentInteractionListener {

    private static final String TAG = "DeviceActivity";

    private static final String TOGGLE = "toggle";
    private static final String PROGRESS = "progress";
    private static final String SELECTION = "selection";
    private static final String SLIDER = "slider";
    private static final String PLUS_MINUS = "plusminus";
    private static final String DIRECTIONAL_ARROWS = "directionalarrows";

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
        XmlDataExtractor xmlDataExtractor = new XmlDataExtractor();
        String type = xmlDataExtractor.getElementType(element);

        // return an element based on xml element type
        switch (type) {
            case TOGGLE: {
                return ToggleFragment.newInstance(element);
            }

            case PROGRESS: {
                return ProgressFragment.newInstance(element);
            }

            case SELECTION: {
                return SelectionFragment.newInstance(element);
            }

            case SLIDER: {
                return SliderFragment.newInstance(element);
            }

            case PLUS_MINUS: {
                return PlusMinusFragment.newInstance(element);
            }

            case DIRECTIONAL_ARROWS: {
                return DirectionalArrowsFragment.newInstance(element);
            }

            default: {
                // TODO: handle invalid element type
                return null;
            }
        }
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
