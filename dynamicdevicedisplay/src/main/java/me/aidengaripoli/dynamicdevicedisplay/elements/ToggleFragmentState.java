package me.aidengaripoli.dynamicdevicedisplay.elements;

public class ToggleFragmentState {

    private boolean state;

    private String label;

    ToggleFragmentState(boolean state, String label) {
        this.state = state;
        this.label = label;
    }

    public boolean getState() {
        return state;
    }

    public String getLabel() {
        return label;
    }
}
