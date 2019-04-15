package me.aidengaripoli.dynamicdevicedisplay.elements;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

import org.w3c.dom.Element;

import java.util.ArrayList;

import me.aidengaripoli.dynamicdevicedisplay.R;
import me.aidengaripoli.dynamicdevicedisplay.XmlParser;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link SliderFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link SliderFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SliderFragment extends Fragment {
    private static final String ARG_LABEL = "label";
    private static final String ARG_BUTTON_LABEL = "buttonLabel";
    private static final String ARG_MIN = "min";
    private static final String ARG_MAX = "max";
    private static final String ARG_VALUE = "value";

    private static final int ARG_LABEL_INDEX = 0;
    private static final int ARG_BUTTON_LABEL_INDEX = 1;
    private static final int ARG_MIN_INDEX = 2;
    private static final int ARG_MAX_INDEX = 3;
    private static final int ARG_VALUE_INDEX = 4;

    private String label;
    private String buttonLabel;
    private int value;
    private int max;
    private int min;
    private int range;

    private TextView labelView;
    private Button buttonView;
    private EditText valueView;
    private SeekBar seekBarView;

    private OnFragmentInteractionListener mListener;

    public SliderFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param element Parameter 1.
     * @return A new instance of fragment SliderFragment.
     */
    public static SliderFragment newInstance(Element element) {
        SliderFragment fragment = new SliderFragment();

        XmlParser xmlParser = new XmlParser();

        ArrayList<String> displaySettings = xmlParser.getDisplaySettings(element);

        Bundle args = new Bundle();
        args.putString(ARG_LABEL, displaySettings.get(ARG_LABEL_INDEX));
        args.putString(ARG_BUTTON_LABEL, displaySettings.get(ARG_BUTTON_LABEL_INDEX));
        args.putInt(ARG_MIN, Integer.parseInt(displaySettings.get(ARG_MIN_INDEX)));
        args.putInt(ARG_MAX, Integer.parseInt(displaySettings.get(ARG_MAX_INDEX)));
        args.putInt(ARG_VALUE, Integer.parseInt(displaySettings.get(ARG_VALUE_INDEX)));
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            label = getArguments().getString(ARG_LABEL);
            buttonLabel = getArguments().getString(ARG_BUTTON_LABEL);
            min = getArguments().getInt(ARG_MIN);
            max = getArguments().getInt(ARG_MAX);
            value = getArguments().getInt(ARG_VALUE);

            range = max - min;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_slider, container, false);

        labelView = view.findViewById(R.id.slider_label);
        labelView.setText(label);

        buttonView = view.findViewById(R.id.slider_button);
        buttonView.setText(buttonLabel);

        valueView = view.findViewById(R.id.slider_value);
        seekBarView = view.findViewById(R.id.slider);

        valueView.setText(String.valueOf(value));

        float progress = (float) (value - min) / range * 100;
        seekBarView.setProgress((int) progress);

        valueView.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                String value = valueView.getText().toString();
                int numValue;

                if (value.isEmpty()) {
                    numValue = min;
                } else {
                    numValue = Integer.parseInt(value);

                    if (numValue < min) {
                        numValue = min;
                    } else if (numValue > max) {
                        numValue = max;
                    }
                }
                valueView.setText(String.valueOf(numValue));

                final float sliderProgress = (float) (numValue - min) / range * 100;
                seekBarView.setProgress((int) sliderProgress);
            }
            return true;
        });

        seekBarView.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
            }

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                int value = (int) (range * ((float) progress / 100) + min);
                valueView.setText(String.valueOf(value));
            }
        });

        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        Log.d("slider", "slider moved");
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
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

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
