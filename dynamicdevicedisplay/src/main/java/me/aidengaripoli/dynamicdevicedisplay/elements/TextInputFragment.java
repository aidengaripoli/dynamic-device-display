package me.aidengaripoli.dynamicdevicedisplay.elements;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

import me.aidengaripoli.dynamicdevicedisplay.OnFragmentInteractionListener;
import me.aidengaripoli.dynamicdevicedisplay.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link TextInputFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TextInputFragment extends DynamicFragment {
    public static final String TEXT_INPUT = "textinput";

    private static final String ARG_BUTTON_LABEL = "buttonLabel";

    private static final int ARG_BUTTON_LABEL_INDEX = 1;

    private String buttonLabel;

    public TextInputFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param displaySettings Parameter 1.
     * @return A new instance of fragment TextInputFragment.
     */
    public static TextInputFragment newInstance(ArrayList<String> displaySettings) {
        TextInputFragment fragment = new TextInputFragment();

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
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_text_input, container, false);

        labelView = view.findViewById(R.id.input_label);
        addLabel();

        EditText textInput = view.findViewById(R.id.input_value);

        Button button = view.findViewById(R.id.input_button);
        button.setText(buttonLabel);
        button.setOnClickListener(v -> sendMessage(String.valueOf(textInput.getText())));

        return view;
    }

    @Override
    public void updateFragmentData(String data) {

    }
}
