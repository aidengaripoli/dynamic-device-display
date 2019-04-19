package me.aidengaripoli.dynamicdevicedisplay.elements;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import me.aidengaripoli.dynamicdevicedisplay.OnFragmentInteractionListener;
import me.aidengaripoli.dynamicdevicedisplay.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ButtonGroupFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ButtonGroupFragment extends DynamicFragment {
    public static final String BUTTON_GROUP = "buttongroup";
    private static final String ARG_BUTTON_LABELS = "buttonlabels";

    private String[] buttonLabels;

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
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_button_group, container, false);

        TextView labelView = view.findViewById(R.id.buttonGroupLabel);
        if (getArguments() != null) {
            String label = getArguments().getString(ARG_LABEL);
            labelView.setText(label);
        }

        LinearLayout buttonLayout = view.findViewById(R.id.buttonGroup_layout);

        for (String buttonLabel : buttonLabels) {
            Button button = new Button(view.getContext());
            button.setText(buttonLabel);
            button.setOnClickListener(v -> sendMessage(buttonLabel));
            buttonLayout.addView(button);
        }

        return view;
    }

    @Override
    public void updateFragmentData(String data) {

    }
}
