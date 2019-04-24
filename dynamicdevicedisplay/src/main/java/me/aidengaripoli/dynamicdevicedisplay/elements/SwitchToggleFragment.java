package me.aidengaripoli.dynamicdevicedisplay.elements;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import java.util.ArrayList;

import me.aidengaripoli.dynamicdevicedisplay.OnFragmentInteractionListener;
import me.aidengaripoli.dynamicdevicedisplay.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link SwitchToggleFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SwitchToggleFragment extends DynamicFragment implements CompoundButton.OnCheckedChangeListener {
    public static final String SWITCH_TOGGLE = "switchtoggle";

    public SwitchToggleFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param displaySettings Parameter 1.
     * @return A new instance of fragment SwitchToggleFragment.
     */
    public static SwitchToggleFragment newInstance(ArrayList<String> displaySettings) {
        SwitchToggleFragment fragment = new SwitchToggleFragment();

        Bundle args = new Bundle();
        args.putString(ARG_LABEL, displaySettings.get(ARG_LABEL_INDEX));
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            label = getArguments().getString(ARG_LABEL);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_switch_toggle, container, false);

        TextView labelView = view.findViewById(R.id.switch_toggle_label);
        labelView.setText(label);

        Switch switchView = view.findViewById(R.id.switch_toggle_switch);
        switchView.setOnCheckedChangeListener(this);

        return view;
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        sendMessage(String.valueOf(isChecked));
    }

    @Override
    public void updateFragmentData(String data) {

    }
}
