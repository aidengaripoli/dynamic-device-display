package me.aidengaripoli.dynamicdevicedisplay.elements;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import org.w3c.dom.Element;

import java.util.ArrayList;

import me.aidengaripoli.dynamicdevicedisplay.R;
import me.aidengaripoli.dynamicdevicedisplay.XmlDataExtractor;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link DirectionalArrowsFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link DirectionalArrowsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DirectionalArrowsFragment extends Fragment implements View.OnClickListener {
    private OnFragmentInteractionListener mListener;

    private static final String ARG_TOP = "top";
    private static final String ARG_RIGHT = "right";
    private static final String ARG_BOTTOM = "bottom";
    private static final String ARG_LEFT = "left";

    private static final int ARG_TOP_INDEX = 0;
    private static final int ARG_RIGHT_INDEX = 1;
    private static final int ARG_BOTTOM_INDEX = 2;
    private static final int ARG_LEFT_INDEX = 3;

    public DirectionalArrowsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment DirectionalArrowsFragment.
     */
    public static DirectionalArrowsFragment newInstance(Element element) {
        DirectionalArrowsFragment fragment = new DirectionalArrowsFragment();

        XmlDataExtractor xmlDataExtractor = new XmlDataExtractor();

        ArrayList<String> displaySettings = xmlDataExtractor.getDisplaySettings(element);

        Bundle args = new Bundle();
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
        View view = inflater.inflate(R.layout.fragment_directional_arrows, container, false);

        Button nButton = view.findViewById(R.id.direcButtonN);
        Button eButton = view.findViewById(R.id.direcButtonE);
        Button sButton = view.findViewById(R.id.direcButtonS);
        Button wButton = view.findViewById(R.id.direcButtonW);

        if (getArguments() != null) {
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

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(String buttonPressed) {
        if (mListener != null) {
            mListener.onFragmentInteraction(buttonPressed);
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
        void onFragmentInteraction(String buttonPressed);
    }
}
