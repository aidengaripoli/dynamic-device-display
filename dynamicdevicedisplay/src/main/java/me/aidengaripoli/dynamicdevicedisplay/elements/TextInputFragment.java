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
 * {@link InputFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link InputFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class InputFragment extends Fragment {
    private static final String ARG_LABEL = "label";
    private static final String ARG_BUTTON_LABEL = "buttonLabel";

    private static final int ARG_LABEL_INDEX = 0;
    private static final int ARG_BUTTON_LABEL_INDEX = 1;

    private String label;
    private String buttonLabel;

    private TextView labelView;
    private Button button;

    private OnFragmentInteractionListener mListener;

    public InputFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param element Parameter 1.
     * @return A new instance of fragment InputFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static InputFragment newInstance(Element element) {
        InputFragment fragment = new InputFragment();

        XmlParser xmlParser = new XmlParser();

        ArrayList<String> displaySettings = xmlParser.getDisplaySettings(element);

        Bundle args = new Bundle();
        args.putString(ARG_LABEL, displaySettings.get(ARG_LABEL_INDEX));
        args.putString(ARG_BUTTON_LABEL, displaySettings.get(ARG_BUTTON_LABEL_INDEX));
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            label = getArguments().getString(ARG_LABEL);
            buttonLabel = getArguments().getString(ARG_BUTTON_LABEL);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_input, container, false);

        labelView = view.findViewById(R.id.input_label);
        labelView.setText(label);

        button = view.findViewById(R.id.input_button);
        button.setText(buttonLabel);

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
