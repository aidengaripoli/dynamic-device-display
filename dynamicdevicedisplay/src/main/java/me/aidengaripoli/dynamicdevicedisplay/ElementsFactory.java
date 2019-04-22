package me.aidengaripoli.dynamicdevicedisplay;

import android.support.v4.app.Fragment;

import org.w3c.dom.Element;

import java.util.ArrayList;

import me.aidengaripoli.dynamicdevicedisplay.elements.ButtonGroupFragment;
import me.aidengaripoli.dynamicdevicedisplay.elements.ButtonToggleFragment;
import me.aidengaripoli.dynamicdevicedisplay.elements.DirectionalButtonsFragment;
import me.aidengaripoli.dynamicdevicedisplay.elements.PasswordFragment;
import me.aidengaripoli.dynamicdevicedisplay.elements.StepperFragment;
import me.aidengaripoli.dynamicdevicedisplay.elements.ProgressFragment;
import me.aidengaripoli.dynamicdevicedisplay.elements.RangeInputFragment;
import me.aidengaripoli.dynamicdevicedisplay.elements.SchedulerFragment;
import me.aidengaripoli.dynamicdevicedisplay.elements.SelectionFragment;
import me.aidengaripoli.dynamicdevicedisplay.elements.StatusFragment;
import me.aidengaripoli.dynamicdevicedisplay.elements.SwitchToggleFragment;
import me.aidengaripoli.dynamicdevicedisplay.elements.TextInputFragment;

class ElementsFactory {
    static Fragment getElement(Element element) {
        XmlParser xmlParser = new XmlParser();
        ArrayList<String> displaySettings = xmlParser.getDisplaySettings(element);
        String label = xmlParser.getLabel(element);
        String type = xmlParser.getElementType(element);

        switch (type) {
            case ButtonToggleFragment.BUTTON_TOGGLE: {
                return ButtonToggleFragment.newInstance(label, displaySettings);
            }

            case ProgressFragment.PROGRESS: {
                return ProgressFragment.newInstance(label, displaySettings);
            }

            case SelectionFragment.SELECTION: {
                return SelectionFragment.newInstance(label, displaySettings);
            }

            case RangeInputFragment.RANGE_INPUT: {
                return RangeInputFragment.newInstance(label, displaySettings);
            }

            case StepperFragment.STEPPER: {
                return StepperFragment.newInstance(label, displaySettings);
            }

            case DirectionalButtonsFragment.DIRECTIONAL_BUTTONS: {
                return DirectionalButtonsFragment.newInstance(label, displaySettings);
            }

            case SwitchToggleFragment.SWITCH_TOGGLE: {
                return SwitchToggleFragment.newInstance(label, displaySettings);
            }

            case StatusFragment.STATUS: {
                return StatusFragment.newInstance(label, displaySettings);
            }

            case TextInputFragment.TEXT_INPUT: {
                return TextInputFragment.newInstance(label, displaySettings);
            }

            case ButtonGroupFragment.BUTTON_GROUP: {
                return ButtonGroupFragment.newInstance(label, displaySettings);
            }

            case PasswordFragment.PASSWORD: {
                return PasswordFragment.newInstance(label, displaySettings);
            }

            case SchedulerFragment.SCHEDULER: {
                return SchedulerFragment.newInstance(label, displaySettings);
            }

            default: {
                // TODO: handle invalid element type
                return null;
            }
        }
    }
}