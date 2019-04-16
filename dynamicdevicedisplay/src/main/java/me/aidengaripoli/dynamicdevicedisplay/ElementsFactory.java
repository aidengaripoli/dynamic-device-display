package me.aidengaripoli.dynamicdevicedisplay;

import android.support.v4.app.Fragment;

import org.w3c.dom.Element;

import java.util.ArrayList;

import me.aidengaripoli.dynamicdevicedisplay.elements.ButtonGroupFragment;
import me.aidengaripoli.dynamicdevicedisplay.elements.ButtonToggleFragment;
import me.aidengaripoli.dynamicdevicedisplay.elements.DirectionalButtonsFragment;
import me.aidengaripoli.dynamicdevicedisplay.elements.PasswordFragment;
import me.aidengaripoli.dynamicdevicedisplay.elements.PlusMinusFragment;
import me.aidengaripoli.dynamicdevicedisplay.elements.ProgressFragment;
import me.aidengaripoli.dynamicdevicedisplay.elements.RangeInputFragment;
import me.aidengaripoli.dynamicdevicedisplay.elements.SchedulerFragment;
import me.aidengaripoli.dynamicdevicedisplay.elements.SelectionFragment;
import me.aidengaripoli.dynamicdevicedisplay.elements.StatusFragment;
import me.aidengaripoli.dynamicdevicedisplay.elements.SwitchToggleFragment;
import me.aidengaripoli.dynamicdevicedisplay.elements.TextInputFragment;

class ElementsFactory {
    private static final String BUTTON_TOGGLE = "buttontoggle";
    private static final String PROGRESS = "progress";
    private static final String SELECTION = "selection";
    private static final String RANGE_INPUT = "rangeslider";
    private static final String PLUS_MINUS = "plusminus";
    private static final String DIRECTIONAL_BUTTONS = "directionalbuttons";
    private static final String SWITCH_TOGGLE = "switchtoggle";
    private static final String STATUS = "status";
    private static final String TEXT_INPUT = "textinput";
    private static final String BUTTON_GROUP = "buttongroup";
    private static final String PASSWORD = "password";
    private static final String SCHEDULER = "scheduler";

    static Fragment getElement(Element element) {
        XmlParser xmlParser = new XmlParser();
        ArrayList<String> displaySettings = xmlParser.getDisplaySettings(element);
        String label = xmlParser.getLabel(element);
        String type = xmlParser.getElementType(element);

        switch (type) {
            case BUTTON_TOGGLE: {
                return ButtonToggleFragment.newInstance(label, displaySettings);
            }

            case PROGRESS: {
                return ProgressFragment.newInstance(label, displaySettings);
            }

            case SELECTION: {
                return SelectionFragment.newInstance(label, displaySettings);
            }

            case RANGE_INPUT: {
                return RangeInputFragment.newInstance(label, displaySettings);
            }

            case PLUS_MINUS: {
                return PlusMinusFragment.newInstance(label, displaySettings);
            }

            case DIRECTIONAL_BUTTONS: {
                return DirectionalButtonsFragment.newInstance(label, displaySettings);
            }

            case SWITCH_TOGGLE: {
                return SwitchToggleFragment.newInstance(label, displaySettings);
            }

            case STATUS: {
                return StatusFragment.newInstance(label, displaySettings);
            }

            case TEXT_INPUT: {
                return TextInputFragment.newInstance(label, displaySettings);
            }

            case BUTTON_GROUP: {
                return ButtonGroupFragment.newInstance(label, displaySettings);
            }

            case PASSWORD: {
                return PasswordFragment.newInstance(label, displaySettings);
            }

            case SCHEDULER: {
                return SchedulerFragment.newInstance(label, displaySettings);
            }

            default: {
                // TODO: handle invalid element type
                return null;
            }
        }
    }
}