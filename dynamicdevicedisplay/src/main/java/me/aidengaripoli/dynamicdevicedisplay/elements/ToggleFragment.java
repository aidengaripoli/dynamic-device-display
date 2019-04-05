package me.aidengaripoli.dynamicdevicedisplay.elements;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Element;

import java.util.ArrayList;

import me.aidengaripoli.dynamicdevicedisplay.R;
import me.aidengaripoli.dynamicdevicedisplay.XmlDataExtractor;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ToggleFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ToggleFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ToggleFragment extends Fragment implements View.OnClickListener {

    private static final String ARG_LABEL = "label";
    private static final String ARG_STATE = "state";

    private static final int ARG_LABEL_INDEX = 0;
    private static final int ARG_STATE_INDEX = 1;

    private String mLabel;
    private boolean mState;

    private Button toggleButton;
    private TextView label;

    private OnFragmentInteractionListener mListener;

    public ToggleFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param element Parameter 1.
     * @return A new instance of fragment ToggleFragment.
     */
    public static ToggleFragment newInstance(Element element) {
        ToggleFragment fragment = new ToggleFragment();

        XmlDataExtractor xmlDataExtractor = new XmlDataExtractor();

        ArrayList<String> displaySettings = xmlDataExtractor.getDisplaySettings(element);

        Bundle args = new Bundle();
        args.putString(ARG_LABEL, displaySettings.get(ARG_LABEL_INDEX));
        args.putBoolean(ARG_STATE, Boolean.parseBoolean(displaySettings.get(ARG_STATE_INDEX)));
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mLabel = getArguments().getString(ARG_LABEL);
            mState = getArguments().getBoolean(ARG_STATE);
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_toggle, container, false);

        label = view.findViewById(R.id.toggle_label);
        label.setText(mLabel);

        toggleButton = view.findViewById(R.id.toggle_button);
        toggleButton.setText(mState ? "ON" : "OFF"); // TODO: change this
        toggleButton.setOnClickListener(this);

        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed() {
        if (mListener != null) {
            mListener.onFragmentInteraction(mLabel, mState);
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
        mState = !mState;
        toggleButton.setText(mState ? "ON" : "OFF");

        onButtonPressed();
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
        void onFragmentInteraction(String label, boolean state);
    }
}
