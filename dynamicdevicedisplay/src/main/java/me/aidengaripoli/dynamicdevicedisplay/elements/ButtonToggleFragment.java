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

import java.util.ArrayList;

import me.aidengaripoli.dynamicdevicedisplay.OnFragmentInteractionListener;
import me.aidengaripoli.dynamicdevicedisplay.R;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ButtonToggleFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ButtonToggleFragment extends Fragment implements View.OnClickListener {
    public static final String BUTTON_TOGGLE = "buttontoggle";

    private static final String ARG_LABEL = "label";
    private static final String ARG_POS_LABEL = "pos_label";
    private static final String ARG_NEG_LABEL = "neg_label";

    private static final int ARG_POS_LABEL_INDEX = 0;
    private static final int ARG_NEG_LABEL_INDEX = 1;

    private String label;
    private String buttonPosLabel;
    private String buttonNegLabel;
    private boolean mState;

    private Button toggleButton;

    private OnFragmentInteractionListener interactionListener;

    public ButtonToggleFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param label           Parameter 1.
     * @param displaySettings Parameter 2.
     * @return A new instance of fragment ButtonToggleFragment.
     */
    public static ButtonToggleFragment newInstance(String label, ArrayList<String> displaySettings) {
        ButtonToggleFragment fragment = new ButtonToggleFragment();

        Bundle args = new Bundle();
        args.putString(ARG_LABEL, label);
        args.putString(ARG_POS_LABEL, displaySettings.get(ARG_POS_LABEL_INDEX));
        args.putString(ARG_NEG_LABEL, displaySettings.get(ARG_NEG_LABEL_INDEX));

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            label = getArguments().getString(ARG_LABEL);
            mState = true;
            buttonPosLabel = getArguments().getString(ARG_POS_LABEL);
            buttonNegLabel = getArguments().getString(ARG_NEG_LABEL);
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_button_toggle, container, false);

        TextView label = view.findViewById(R.id.toggle_label);
        label.setText(this.label);

        toggleButton = view.findViewById(R.id.toggle_button);
        toggleButton.setText(mState ? buttonPosLabel : buttonNegLabel);
        toggleButton.setOnClickListener(this);

        return view;
    }

    public void onButtonPressed() {
        if (interactionListener != null) {
            interactionListener.onFragmentMessage(getTag(), mState ? buttonPosLabel : buttonNegLabel);
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

    @Override
    public void onClick(View v) {
        mState = !mState;
        toggleButton.setText(mState ? buttonPosLabel : buttonNegLabel);
        onButtonPressed();
    }
}
