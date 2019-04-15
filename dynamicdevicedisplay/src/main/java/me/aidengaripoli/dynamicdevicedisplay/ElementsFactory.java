package me.aidengaripoli.dynamicdevicedisplay;

import android.support.v4.app.Fragment;

import org.w3c.dom.Element;

import me.aidengaripoli.dynamicdevicedisplay.elements.ButtonGroupFragment;
import me.aidengaripoli.dynamicdevicedisplay.elements.DirectionalArrowsFragment;
import me.aidengaripoli.dynamicdevicedisplay.elements.InputFragment;
import me.aidengaripoli.dynamicdevicedisplay.elements.PlusMinusFragment;
import me.aidengaripoli.dynamicdevicedisplay.elements.ProgressFragment;
import me.aidengaripoli.dynamicdevicedisplay.elements.SelectionFragment;
import me.aidengaripoli.dynamicdevicedisplay.elements.SliderFragment;
import me.aidengaripoli.dynamicdevicedisplay.elements.StatusFragment;
import me.aidengaripoli.dynamicdevicedisplay.elements.SwitchToggleFragment;
import me.aidengaripoli.dynamicdevicedisplay.elements.ToggleFragment;

public class ElementsFactory {
    private static final String TOGGLE = "toggle";
    private static final String PROGRESS = "progress";
    private static final String SELECTION = "selection";
    private static final String SLIDER = "slider";
    private static final String PLUS_MINUS = "plusminus";
    private static final String DIRECTIONAL_ARROWS = "directionalarrows";
    private static final String SWITCH_TOGGLE = "switchtoggle";
    private static final String STATUS = "status";
    private static final String INPUT = "input";
    private static final String BUTTON_GROUP = "buttongroup";

    public static Fragment getElement(String type, Element element) {
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

            case SWITCH_TOGGLE: {
                return SwitchToggleFragment.newInstance(element);
            }

            case STATUS: {
                return StatusFragment.newInstance(element);
            }

            case INPUT: {
                return InputFragment.newInstance(element);
            }

            case BUTTON_GROUP: {
                return ButtonGroupFragment.newInstance(element);
            }

            default: {
                // TODO: handle invalid element type
                return null;
            }
        }
    }
}