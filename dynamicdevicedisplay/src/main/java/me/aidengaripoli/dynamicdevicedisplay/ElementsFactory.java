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
        String type = xmlParser.getElementType(element);

        switch (type) {
            case ButtonToggleFragment.BUTTON_TOGGLE: {
                return ButtonToggleFragment.newInstance(displaySettings);
            }

            case ProgressFragment.PROGRESS: {
                return ProgressFragment.newInstance(displaySettings);
            }

            case SelectionFragment.SELECTION: {
                return SelectionFragment.newInstance(displaySettings);
            }

            case RangeInputFragment.RANGE_INPUT: {
                return RangeInputFragment.newInstance(displaySettings);
            }

            case StepperFragment.STEPPER: {
                return StepperFragment.newInstance(displaySettings);
            }

            case DirectionalButtonsFragment.DIRECTIONAL_BUTTONS: {
                return DirectionalButtonsFragment.newInstance(displaySettings);
            }

            case SwitchToggleFragment.SWITCH_TOGGLE: {
                return SwitchToggleFragment.newInstance(displaySettings);
            }

            case StatusFragment.STATUS: {
                return StatusFragment.newInstance(displaySettings);
            }

            case TextInputFragment.TEXT_INPUT: {
                return TextInputFragment.newInstance(displaySettings);
            }

            case ButtonGroupFragment.BUTTON_GROUP: {
                return ButtonGroupFragment.newInstance(displaySettings);
            }

            case PasswordFragment.PASSWORD: {
                return PasswordFragment.newInstance(displaySettings);
            }

            case SchedulerFragment.SCHEDULER: {
                return SchedulerFragment.newInstance(displaySettings);
            }

            default: {
                // TODO: handle invalid element type
                return null;
            }
        }
    }
}