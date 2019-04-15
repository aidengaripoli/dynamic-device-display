package me.aidengaripoli.dynamicdevicedisplay.elements;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Element;

import java.util.ArrayList;

import me.aidengaripoli.dynamicdevicedisplay.R;
import me.aidengaripoli.dynamicdevicedisplay.XmlParser;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link PlusMinusFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link PlusMinusFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PlusMinusFragment extends Fragment implements View.OnClickListener {
    private static final String ARG_LABEL = "label";
    private static final String ARG_MIN = "min";
    private static final String ARG_MAX = "max";

    private static final int ARG_LABEL_INDEX = 0;
    private static final int ARG_MIN_INDEX = 1;
    private static final int ARG_MAX_INDEX = 2;

    private TextView value;

    private String label;
    private int currentValue;
    private int max;
    private int min;

    private OnFragmentInteractionListener mListener;

    public PlusMinusFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param element Parameter 1.
     * @return A new instance of fragment PlusMinusFragment.
     */
    public static PlusMinusFragment newInstance(Element element) {
        PlusMinusFragment fragment = new PlusMinusFragment();

        XmlParser xmlParser = new XmlParser();

        ArrayList<String> displaySettings = xmlParser.getDisplaySettings(element);

        Bundle args = new Bundle();
        args.putString(ARG_LABEL, displaySettings.get(ARG_LABEL_INDEX));
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

        plusButton.setOnClickListener(this);
        minusButton.setOnClickListener(this);

        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
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

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.plus) {
            if (currentValue < max)
                currentValue++;
        } else if (v.getId() == R.id.minus) {
            if (currentValue > min)
                currentValue--;
        }
        value.setText(String.valueOf(currentValue));
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
