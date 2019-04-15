package me.aidengaripoli.dynamicdevicedisplay;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.ArrayList;
import java.util.StringTokenizer;

public class XmlParser {

    /**
     * Used to retrieve the string found inside the <Type/> tag.
     * Will return the string in the first <Type/> tag if multiple are specified.
     *
     * @param element Parameter 1.
     * @return A string with the type of element.
     */
    String getElementType(Element element) {
        String value = getTagData(element, "type");
        return value.toLowerCase().trim();
    }

    /**
     * Used to retrieve the comma separated values in the <disp_settings/> tag.
     *
     * @param element Parameter 1.
     * @return An Arraylist of the comma separated data found in the display settings tag.
     */
    public ArrayList<String> getDisplaySettings(Element element) {
        ArrayList<String> displaySettings = new ArrayList<>();

        String value = getTagData(element, "disp_settings");

        StringTokenizer st = new StringTokenizer(value, ",");
        while (st.hasMoreTokens()) {
            displaySettings.add(st.nextToken());
        }

        return displaySettings;
    }

    /**
     * Used to retrieve the string found inside the a specific tag.
     *
     * @param element Parameter 1.
     * @param tag     Parameter 2.
     * @return A string with the data found inside the tag.
     */
    private String getTagData(Element element, String tag) {
        if (element == null)
            return null;

        NodeList nodeList = element.getElementsByTagName(tag);
        Node node = nodeList.item(0);

        if (node == null)
            return null;

        Node typeNode = node.getFirstChild();

        if (typeNode == null)
            return null;

        return typeNode.getNodeValue();
    }
}
