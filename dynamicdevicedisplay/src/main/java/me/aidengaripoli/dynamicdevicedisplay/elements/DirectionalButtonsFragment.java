package me.aidengaripoli.dynamicdevicedisplay.elements;

import android.content.Context;
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
public class DirectionalButtonsFragment extends Fragment implements View.OnClickListener {
    public static final String DIRECTIONAL_BUTTONS = "directionalbuttons";

    private OnFragmentInteractionListener mListener;

    private static final String ARG_LABEL = "label";
    private static final String ARG_TOP = "top";
    private static final String ARG_RIGHT = "right";
    private static final String ARG_BOTTOM = "bottom";
    private static final String ARG_LEFT = "left";

    private static final int ARG_TOP_INDEX = 0;
    private static final int ARG_RIGHT_INDEX = 1;
    private static final int ARG_BOTTOM_INDEX = 2;
    private static final int ARG_LEFT_INDEX = 3;

    public DirectionalButtonsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param label           Parameter 1.
     * @param displaySettings Parameter 2.
     * @return A new instance of fragment DirectionalButtonsFragment.
     */
    public static DirectionalButtonsFragment newInstance(String label, ArrayList<String> displaySettings) {
        DirectionalButtonsFragment fragment = new DirectionalButtonsFragment();

        Bundle args = new Bundle();
        args.putString(ARG_LABEL, label);
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
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_directional_buttons, container, false);

        TextView labelView = view.findViewById(R.id.direcButtonLabel);
        Button nButton = view.findViewById(R.id.direcButtonN);
        Button eButton = view.findViewById(R.id.direcButtonE);
        Button sButton = view.findViewById(R.id.direcButtonS);
        Button wButton = view.findViewById(R.id.direcButtonW);

        if (getArguments() != null) {
            String label = getArguments().getString(ARG_LABEL);
            if (label != null) //label is and optional field
                labelView.setText(getArguments().getString(ARG_LABEL));

            nButton.setText(getArguments().getString(ARG_TOP));
            eButton.setText(getArguments().getString(ARG_RIGHT));
            sButton.setText(getArguments().getString(ARG_BOTTOM));
            wButton.setText(getArguments().getString(ARG_LEFT));
        }

        nButton.setOnClickListener(this);
        eButton.setOnClickListener(this);
        sButton.setOnClickListener(this);
        wButton.setOnClickListener(this);

        return view;
    }

    public void onButtonPressed(String buttonPressed) {
        if (mListener != null) {
            mListener.onFragmentMessage(buttonPressed);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.direcButtonN) {
            onButtonPressed("N");
        } else if (i == R.id.direcButtonS) {
            onButtonPressed("S");
        } else if (i == R.id.direcButtonE) {
            onButtonPressed("E");
        } else if (i == R.id.direcButtonW) {
            onButtonPressed("W");
        }
    }
}
