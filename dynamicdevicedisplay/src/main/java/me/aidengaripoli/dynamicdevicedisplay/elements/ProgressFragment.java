package me.aidengaripoli.dynamicdevicedisplay.elements;

import android.content.Context;
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
public class ProgressFragment extends Fragment {
    public static final String PROGRESS = "progress";

    private static final String ARG_LABEL = "label";

    private String mLabel;
    private int mValue;

    private OnFragmentInteractionListener interactionListener;

    public ProgressFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param label           Parameter 1.
     * @param displaySettings Parameter 2.
     * @return A new instance of fragment ProgressFragment.
     */
    public static ProgressFragment newInstance(String label, ArrayList<String> displaySettings) {
        ProgressFragment fragment = new ProgressFragment();

        Bundle args = new Bundle();
        args.putString(ARG_LABEL, label);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mLabel = getArguments().getString(ARG_LABEL);
            mValue = 0;
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_progress, container, false);

        TextView label = view.findViewById(R.id.progress_label);
        label.setText(mLabel);

        ProgressBar progressValue = view.findViewById(R.id.progress_value);
        progressValue.setProgress(mValue);

        return view;
    }

    public void onProgressChanged() {
        if (interactionListener != null) {
            interactionListener.onFragmentMessage(mLabel);
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
}
