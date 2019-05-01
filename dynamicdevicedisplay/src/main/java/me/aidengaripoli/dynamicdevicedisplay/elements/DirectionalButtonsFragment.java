package me.aidengaripoli.dynamicdevicedisplay.elements;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

import me.aidengaripoli.dynamicdevicedisplay.OnFragmentInteractionListener;
import me.aidengaripoli.dynamicdevicedisplay.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link DirectionalButtonsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DirectionalButtonsFragment extends DynamicFragment {
    public static final String DIRECTIONAL_BUTTONS = "directionalbuttons";

    private static final String ARG_TOP = "top";
    private static final String ARG_RIGHT = "right";
    private static final String ARG_BOTTOM = "bottom";
    private static final String ARG_LEFT = "left";

    private static final int ARG_TOP_INDEX = 1;
    private static final int ARG_RIGHT_INDEX = 2;
    private static final int ARG_BOTTOM_INDEX = 3;
    private static final int ARG_LEFT_INDEX = 4;

    public DirectionalButtonsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param displaySettings Parameter 1.
     * @return A new instance of fragment DirectionalButtonsFragment.
     */
    public static DirectionalButtonsFragment newInstance(ArrayList<String> displaySettings) {
        DirectionalButtonsFragment fragment = new DirectionalButtonsFragment();

        Bundle args = new Bundle();
        args.putString(ARG_LABEL, displaySettings.get(ARG_LABEL_INDEX));
        args.putString(ARG_TOP, displaySettings.get(ARG_TOP_INDEX));
        args.putString(ARG_RIGHT, displaySettings.get(ARG_RIGHT_INDEX));
        args.putString(ARG_BOTTOM, displaySettings.get(ARG_BOTTOM_INDEX));
        args.putString(ARG_LEFT, displaySettings.get(ARG_LEFT_INDEX));
        fragment.setArguments(args);

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
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_directional_buttons, container, false);

        labelView = view.findViewById(R.id.direcButtonLabel);
        Button nButton = view.findViewById(R.id.direcButtonN);
        Button eButton = view.findViewById(R.id.direcButtonE);
        Button sButton = view.findViewById(R.id.direcButtonS);
        Button wButton = view.findViewById(R.id.direcButtonW);

        addLabel();

        if (getArguments() != null) {
            nButton.setText(getArguments().getString(ARG_TOP));
            eButton.setText(getArguments().getString(ARG_RIGHT));
            sButton.setText(getArguments().getString(ARG_BOTTOM));
            wButton.setText(getArguments().getString(ARG_LEFT));

            nButton.setOnClickListener(v -> sendMessage(getArguments().getString(ARG_TOP)));
            eButton.setOnClickListener(v -> sendMessage(getArguments().getString(ARG_RIGHT)));
            sButton.setOnClickListener(v -> sendMessage(getArguments().getString(ARG_BOTTOM)));
            wButton.setOnClickListener(v -> sendMessage(getArguments().getString(ARG_LEFT)));
        }

        return view;
    }

    @Override
    public void updateFragmentData(String data) {

    }
}
