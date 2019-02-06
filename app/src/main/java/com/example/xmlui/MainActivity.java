package com.example.xmlui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.InputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

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

                    // construct a layout for each group
                    ViewGroup groupLayout = constructGroupLayout(groupElement);

                    // add the layout to the root layout
                    rootLayout.addView(groupLayout);
                }
            }
        } catch (Exception e) {
            Log.e(TAG, "XML PARSE ERROR: " + e.getMessage());
        }

        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
        );

        // add the root layout to the content view
        addContentView(rootLayout, layoutParams);
    }

    private ViewGroup constructGroupLayout(Element element) {
        NodeList guiNodeList = element.getElementsByTagName("gui_element");

        // TODO: remove hardcoded linear layout
        LinearLayout layout = new LinearLayout(this);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        );
        layoutParams.setMargins(40, 20, 0, 20);
        layout.setOrientation(LinearLayout.HORIZONTAL);
        layout.setLayoutParams(layoutParams);

        // iterate through all the <gui_element> elements in the group
        for (int i = 0; i < guiNodeList.getLength(); i++) {
            Node node = guiNodeList.item(i);

            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element guiElement = (Element) node;

                // construct a view (widget) for each gui_element
                View view = constructGuiElement(guiElement);

                // add the view to the groups layout
                layout.addView(view);
            }
        }
        return layout;
    }

    private View constructGuiElement(Element element) {
        // TODO: replace dummy button contents with actual ui component creation
        NodeList nodeList = element.getElementsByTagName("label").item(0).getChildNodes();

        Node node = nodeList.item(0);

        String textValue = node.getNodeValue();

        Log.d(TAG, textValue);

        // create a button for each gui element for demonstration purposes
        Button button = new Button(this);
        button.setText(textValue);

        return button;
    }
}
