package me.aidengaripoli.dynamicdevicedisplay;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.InputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

public class UiGenerator {
    private static final String TAG = "UiGenerator";
    private FragmentManager fragmentManager;

    public UiGenerator(FragmentManager fragmentManager) {
        this.fragmentManager = fragmentManager;
    }

    public LinearLayout generateUi(Context context, InputStream inputStream) {
        LinearLayout rootLayout = new LinearLayout(context);
        rootLayout.setOrientation(LinearLayout.VERTICAL);
        rootLayout.setId(View.generateViewId());
        rootLayout.setGravity(Gravity.CENTER);

        try {
            DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();

            // get all of the <group> elements
            NodeList groupNodeList = builder.parse(inputStream).getElementsByTagName("group");

            addTitle((Element) groupNodeList.item(0), rootLayout, context);

            // iterate through all the <group> elements
            for (int i = 1; i < groupNodeList.getLength(); i++) {
                Element element = (Element) groupNodeList.item(i);
                NodeList guiNodeList = element.getElementsByTagName("gui_element");

                if (guiNodeList.getLength() == 1) {
                    addElementToLayout((Element) guiNodeList.item(0), rootLayout);
                } else {
                    createGroupOfElements(rootLayout, guiNodeList, context);
                }
            }

        } catch (Exception e) {
            Log.e(TAG, "ERROR WHILST GENERATING UI: " + e.getMessage());
        }

        return rootLayout;
    }

    private void addTitle(Element element,LinearLayout rootLayout, Context context){
        XmlParser xmlParser = new XmlParser();
        String name = xmlParser.getName(element);

        TextView title = new TextView(context);
        title.setTextSize(TypedValue.COMPLEX_UNIT_SP,36);
        title.setText(name);

        LinearLayout groupLayout = createLinearLayout(context);
        groupLayout.addView(title);
        rootLayout.addView(groupLayout);
    }

    private void createGroupOfElements(LinearLayout layout, NodeList guiNodeList, Context context) {
        // Generate a horizontal Linear layout and add the elements to that.
        LinearLayout groupLayout = createLinearLayout(context);

        // iterate through all the <gui_element> elements in the group
        for (int i = 0; i < guiNodeList.getLength(); i++) {
            Element element = (Element) guiNodeList.item(i);
            addElementToLayout(element, groupLayout);
        }

        // add new horizontal layout to root layout
        layout.addView(groupLayout);
    }

    private void addElementToLayout(Element element, LinearLayout layout) {
        // generate a view (widget) for each gui_element
        Fragment fragment = ElementsFactory.getElement(element);

        // add the view to the groups layout
        if (fragment != null) {
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

            fragmentTransaction.add(layout.getId(), fragment);
            fragmentTransaction.commit();
        }
    }

    private LinearLayout createLinearLayout(Context context) {
        LinearLayout layout = new LinearLayout(context);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        layout.setOrientation(LinearLayout.HORIZONTAL);
        layout.setLayoutParams(layoutParams);
        layout.setId(View.generateViewId());

        return layout;
    }
}
