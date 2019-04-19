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
 * Use the {@link PlusMinusFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PlusMinusFragment extends Fragment {
    public static final String PLUS_MINUS = "plusminus";

    private static final String ARG_LABEL = "label";
    private static final String ARG_MIN = "min";
    private static final String ARG_MAX = "max";

    private static final int ARG_MIN_INDEX = 0;
    private static final int ARG_MAX_INDEX = 1;

    private TextView value;

    private String label;
    private int currentValue;
    private int max;
    private int min;

    private OnFragmentInteractionListener interactionListener;

    public PlusMinusFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param label           Parameter 1.
     * @param displaySettings Parameter 2.
     * @return A new instance of fragment PlusMinusFragment.
     */
    public static PlusMinusFragment newInstance(String label, ArrayList<String> displaySettings) {
        PlusMinusFragment fragment = new PlusMinusFragment();

        Bundle args = new Bundle();
        args.putString(ARG_LABEL, label);
        args.putInt(ARG_MIN, Integer.parseInt(displaySettings.get(ARG_MIN_INDEX)));
        args.putInt(ARG_MAX, Integer.parseInt(displaySettings.get(ARG_MAX_INDEX)));

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            label = getArguments().getString(ARG_LABEL);
            min = getArguments().getInt(ARG_MIN);
            max = getArguments().getInt(ARG_MAX);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_plus_minus, container, false);

        value = view.findViewById(R.id.plusMinusValue);
        Button plusButton = view.findViewById(R.id.plus);
        Button minusButton = view.findViewById(R.id.minus);
        TextView labelView = view.findViewById(R.id.plusMinusLabel);

        value.setText(String.valueOf(currentValue));
        labelView.setText(label);

        plusButton.setOnClickListener(v -> changeValue(true));
        minusButton.setOnClickListener(v -> changeValue(false));

        return view;
    }


    private void changeValue(boolean plusButton) {
        if (plusButton) {
            if (currentValue < max)
                currentValue++;
        } else {
            if (currentValue > min)
                currentValue--;
        }
        value.setText(String.valueOf(currentValue));

        onButtonPressed(String.valueOf(currentValue));
    }

    public void onButtonPressed(String data) {
        if (interactionListener != null) {
            interactionListener.onFragmentMessage(getTag(), data);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            interactionListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        interactionListener = null;
    }
}
