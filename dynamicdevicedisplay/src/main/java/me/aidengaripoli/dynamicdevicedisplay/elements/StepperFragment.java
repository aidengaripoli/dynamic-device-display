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
 * Use the {@link StepperFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class StepperFragment extends DynamicFragment {
    public static final String STEPPER = "stepper";

    private static final String ARG_MIN = "min";
    private static final String ARG_MAX = "max";

    private static final int ARG_MIN_INDEX = 0;
    private static final int ARG_MAX_INDEX = 1;

    private TextView value;

    private int currentValue;
    private int max;
    private int min;


    public StepperFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param label           Parameter 1.
     * @param displaySettings Parameter 2.
     * @return A new instance of fragment StepperFragment.
     */
    public static StepperFragment newInstance(String label, ArrayList<String> displaySettings) {
        StepperFragment fragment = new StepperFragment();

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
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_stepper, container, false);

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

        sendMessage(String.valueOf(currentValue));
    }

    @Override
    public void updateFragmentData(String data) {

    }
}
