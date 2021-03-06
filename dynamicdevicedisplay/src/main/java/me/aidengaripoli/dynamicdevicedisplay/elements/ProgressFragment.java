package me.aidengaripoli.dynamicdevicedisplay.elements;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;

import me.aidengaripoli.dynamicdevicedisplay.OnFragmentInteractionListener;
import me.aidengaripoli.dynamicdevicedisplay.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ProgressFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProgressFragment extends DynamicFragment {
    public static final String PROGRESS = "progress";

    private int value;

    private ProgressBar progressValue;

    public ProgressFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param displaySettings Parameter 1.
     * @return A new instance of fragment ProgressFragment.
     */
    public static ProgressFragment newInstance(ArrayList<String> displaySettings) {
        ProgressFragment fragment = new ProgressFragment();

        Bundle args = new Bundle();
        args.putString(ARG_LABEL, displaySettings.get(ARG_LABEL_INDEX));
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            label = getArguments().getString(ARG_LABEL);
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_progress, container, false);

        labelView = view.findViewById(R.id.progress_label);
        addLabel();

        progressValue = view.findViewById(R.id.progress_value);
        progressValue.setProgress(value);

        return view;
    }

    @Override
    public void updateFragmentData(ArrayList<String> updateData) {
        if(updateData.isEmpty()){
            return;
        }

        value = Integer.parseInt(updateData.get(0));
    }
}
