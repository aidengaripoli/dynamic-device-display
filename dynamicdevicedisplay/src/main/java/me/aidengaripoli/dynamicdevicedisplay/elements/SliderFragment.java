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
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

import org.w3c.dom.Element;
import org.w3c.dom.Node;

import me.aidengaripoli.dynamicdevicedisplay.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link SliderFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link SliderFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SliderFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "label";
    private static final String ARG_PARAM2 = "value";
    private static final String ARG_PARAM3 = "max";
    private static final String ARG_PARAM4 = "min";

    // TODO: Rename and change types of parameters
    private String label;
    private int value;
    private int max;
    private int min;
    private int range;

    private TextView labelView;
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
    // TODO: Rename and change types and number of parameters
    public static SliderFragment newInstance(Element element) {
        SliderFragment fragment = new SliderFragment();

        Node node = element.getElementsByTagName(ARG_PARAM1).item(0).getChildNodes().item(0);
        String label = node.getNodeValue().toLowerCase().trim();

        node = element.getElementsByTagName(ARG_PARAM2).item(0).getChildNodes().item(0);
        String value = node.getNodeValue().toLowerCase().trim();

        node = element.getElementsByTagName(ARG_PARAM3).item(0).getChildNodes().item(0);
        String max = node.getNodeValue().toLowerCase().trim();

        node = element.getElementsByTagName(ARG_PARAM4).item(0).getChildNodes().item(0);
        String min = node.getNodeValue().toLowerCase().trim();

        // convert parameters to Int
        int minInt = min.isEmpty() ? 0 : Integer.parseInt(min);
        int maxInt = max.isEmpty() ? 100 : Integer.parseInt(max);
        int valInt = value.isEmpty() ? minInt : Integer.parseInt(value);

        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, label);
        args.putInt(ARG_PARAM2, valInt);
        args.putInt(ARG_PARAM3, maxInt);
        args.putInt(ARG_PARAM4, minInt);
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            label = getArguments().getString(ARG_PARAM1);
            value = getArguments().getInt(ARG_PARAM2);
            max = getArguments().getInt(ARG_PARAM3);
            min = getArguments().getInt(ARG_PARAM4);

            range = max - min;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_slider, container, false);

        labelView = view.findViewById(R.id.slider_label);
        labelView.setText(label);

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
