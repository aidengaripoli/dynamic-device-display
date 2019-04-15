package me.aidengaripoli.dynamicdevicedisplay;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.InputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

public class UiGenerator {
    private static final String TAG = "UiGenerator";
    private FragmentManager fragmentManager;
    private XmlParser xmlParser;


    public UiGenerator(FragmentManager fragmentManager) {
        this.fragmentManager = fragmentManager;
        xmlParser = new XmlParser();
    }

    public LinearLayout generateUi(Context context, InputStream inputStream) {
        LinearLayout rootLayout = new LinearLayout(context);
        rootLayout.setOrientation(LinearLayout.VERTICAL);
        rootLayout.setId(View.generateViewId());

        try {
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
                    ViewGroup groupLayout = generateGroupLayout(groupElement, context);

                    // add the layout to the root layout
                    rootLayout.addView(groupLayout);
                }
            }
        } catch (Exception e) {
            Log.e(TAG, "ERROR WHILST GENERATING UI: " + e.getMessage());
        }
        return rootLayout;
    }

    private ViewGroup generateGroupLayout(Element element, Context context) {
        NodeList guiNodeList = element.getElementsByTagName("gui_element");

        int layoutId = View.generateViewId();
        LinearLayout layout = new LinearLayout(context);
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
        String type = xmlParser.getElementType(element);
        return ElementsFactory.getElement(type, element);
    }
}
