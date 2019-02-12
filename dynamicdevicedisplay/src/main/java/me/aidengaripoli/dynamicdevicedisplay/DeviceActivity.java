package me.aidengaripoli.dynamicdevicedisplay;

import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.HashMap;

import me.aidengaripoli.dynamicdevicedisplay.elements.ToggleFragment;

public class DeviceActivity extends FragmentActivity
        implements ToggleFragment.OnFragmentInteractionListener {

    private static final String TAG = "DeviceActivity";

    private HashMap<String, Integer> elements = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);

        int rootLayoutId = View.generateViewId();
        LinearLayout rootLayout = new LinearLayout(this);
        rootLayout.setOrientation(LinearLayout.VERTICAL);
        rootLayout.setId(rootLayoutId);

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        ToggleFragment toggleFragment =  ToggleFragment.newInstance("one", "two");

        fragmentTransaction.add(rootLayoutId, toggleFragment);
        fragmentTransaction.commit();

//        // XML parsing
//        // TODO: refactor xml parsing of UI to a library
//        try {
//            InputStream inputStream = getAssets().open("ui.xml");
//
//            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
//            DocumentBuilder builder = factory.newDocumentBuilder();
//            Document document = builder.parse(inputStream);
//
//            Element deviceElement = document.getDocumentElement();
//            deviceElement.normalize();
//
//            // get all of the <group> elements
//            NodeList groupNodeList = document.getElementsByTagName("group");
//
//            // iterate through all the <group> elements
//            for (int i = 0; i < groupNodeList.getLength(); i++) {
//                Node node = groupNodeList.item(i);
//
//                if (node.getNodeType() == Node.ELEMENT_NODE) {
//                    Element groupElement = (Element) node;
//
//                    // generate a layout for each group
//                    ViewGroup groupLayout = generateGroupLayout(groupElement);
//
//                    // add the layout to the root layout
//                    rootLayout.addView(groupLayout);
//                }
//            }
//        } catch (Exception e) {
//            Log.e(TAG, "ERROR WHILST GENERATING UI: " + e.getMessage());
//        }
//
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
        LinearLayout layout = new LinearLayout(this);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        layoutParams.setMargins(40, 20, 0, 20);
        layout.setOrientation(LinearLayout.HORIZONTAL);
        layout.setLayoutParams(layoutParams);

        // iterate through all the <gui_element> elements in the group
        for (int i = 0; i < guiNodeList.getLength(); i++) {
            Node node = guiNodeList.item(i);

            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element guiElement = (Element) node;

                // generate a view (widget) for each gui_element
                View view = generateGuiElement(guiElement);

                // add the view to the groups layout
                if (view != null) {
                    layout.addView(view);
                }
            }
        }
        return layout;
    }

    private View generateGuiElement(Element element) {
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
                int id = View.generateViewId();
                // TODO: refactor the identifier from label to something more practical
                elements.put(labelValue, id);

                Button button = new Button(this);
                button.setText(labelValue);
                button.setId(id);

                button.setOnClickListener(v -> {
                    Toast.makeText(
                            getApplicationContext(),
                            labelValue,
                            Toast.LENGTH_SHORT
                    ).show();

                    // as this element is a 'toggle' element, all it would have to do is know
                    // is its current value which is either true or false, and then send the
                    // negation of its current value to the IoT device, and update it's UI.
                    // Whenever the IoT device's data updates for this control, this toggle will
                    // update its internal state and therefore its UI
                });

                return button;
            }

            case "progress": {
                int id = View.generateViewId();
                // TODO: refactor the identifier from label to something more practical
                elements.put(labelValue, id);

                // TODO: should the IOT device determine what type of view is required?
                // e.g. horizontal or indeterminate progress bar etc.
                ProgressBar progressBar = new ProgressBar(
                        this,
                        null,
                        android.R.attr.progressBarStyleHorizontal
                );
                progressBar.setId(id);

                return progressBar;
            }

            case "spinner": {
                // a SpinnerAdapter is required to populate the spinner,
                // how should the IOT device declare the data for the spinner?
                Spinner spinner = new Spinner(this);

                int id = View.generateViewId();
                // TODO: refactor the identifier from label to something more practical
                elements.put(labelValue, id);
                spinner.setId(id);

                return spinner;
            }

            default: {
                // TODO: handle invalid element type
                return null;
            }
        }
    }

    @Override
    public void onFragmentInteraction() {
        Toast.makeText(getApplicationContext(), "onFragmentInteraction", Toast.LENGTH_SHORT).show();
    }
}
