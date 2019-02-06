package com.example.xmlui;

import android.support.v7.app.AppCompatActivity;
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

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private HashMap<String, Integer> elements = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);

        LinearLayout rootLayout = new LinearLayout(this);
        rootLayout.setOrientation(LinearLayout.VERTICAL);

        // XML parsing
        // TODO: refactor xml parsing of UI to a library
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
        addContentView(rootLayout, layoutParams);

        // example use
        // when the view elements are dynamically created, their android resource IDs are put into
        // a hashmap which contains the label, ID pair. To get a reference of the view element,
        // use the label string as the key, which will return the view ID
        int lockButtonId = elements.get("Lock/Unlock");
        Button lockButton = findViewById(lockButtonId);
        lockButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(
                        getApplicationContext(),
                        "Lock/Unlock button clicked.",
                        Toast.LENGTH_SHORT
                ).show();
            }
        });

        int openButtonId = elements.get("Open/Close");
        Button openButton = findViewById(openButtonId);
        openButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(
                        getApplicationContext(),
                        "Open/Close button clicked.",
                        Toast.LENGTH_SHORT
                ).show();
            }
        });
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

        Log.d(TAG, labelValue);

        // get the element type
        Node typeNode = element.getElementsByTagName("type").item(0)
                .getChildNodes().item(0);
        String type = typeNode.getNodeValue().toLowerCase().trim();

        Log.d(TAG, type);

        // return an android widget based on element type
        switch (type) {
            case "toggle": {
                int id = View.generateViewId();
                // TODO: refactor the identifier from label to something more practical
                elements.put(labelValue, id);

                Button button = new Button(this);
                button.setText(labelValue);
                button.setId(id);

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
}
