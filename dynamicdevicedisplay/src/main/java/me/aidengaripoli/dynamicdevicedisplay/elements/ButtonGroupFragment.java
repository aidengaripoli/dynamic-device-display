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

import java.util.ArrayList;

import me.aidengaripoli.dynamicdevicedisplay.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ButtonGroupFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ButtonGroupFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ButtonGroupFragment extends Fragment {
    public static final String BUTTON_GROUP = "buttongroup";

    private static final String ARG_LABEL = "label";
    private static final String ARG_BUTTON_LABELS = "buttonlabels";
    private String[] buttonLabels;

    private OnFragmentInteractionListener mListener;

    public ButtonGroupFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param label           Parameter 1.
     * @param displaySettings Parameter 2.
     * @return A new instance of fragment ButtonGroupFragment.
     */
    public static ButtonGroupFragment newInstance(String label, ArrayList<String> displaySettings) {
        ButtonGroupFragment fragment = new ButtonGroupFragment();

        Bundle args = new Bundle();

        String[] buttonLabels = displaySettings.toArray(new String[0]);
        args.putStringArray(ARG_BUTTON_LABELS, buttonLabels);
        args.putString(ARG_LABEL, label);

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            buttonLabels = getArguments().getStringArray(ARG_BUTTON_LABELS);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_button_group, container, false);

        TextView labelView = view.findViewById(R.id.buttonGroupLabel);
        if (getArguments() != null) {
            String label = getArguments().getString(ARG_LABEL);
            labelView.setText(label);
        }

        for (String buttonLabel : buttonLabels) {
            Button button = new Button(view.getContext());
            button.setText(buttonLabel);
            container.addView(button);
        }

        return view;
    }

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
