package me.aidengaripoli.dynamicdevicedisplay.elements;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

import me.aidengaripoli.dynamicdevicedisplay.OnFragmentInteractionListener;
import me.aidengaripoli.dynamicdevicedisplay.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link SelectionFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SelectionFragment extends DynamicFragment implements AdapterView.OnItemSelectedListener {
    public static final String SELECTION = "selection";

    private static final String ARG_ITEMS = "items";

    private String[] items;

    public SelectionFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param label           Parameter 1.
     * @param displaySettings Parameter 2.
     * @return A new instance of fragment SelectionFragment.
     */
    public static SelectionFragment newInstance(String label, ArrayList<String> displaySettings) {
        SelectionFragment fragment = new SelectionFragment();

        Bundle args = new Bundle();
        args.putString(ARG_LABEL, label);

        String[] values = displaySettings.toArray(new String[0]);
        args.putStringArray(ARG_ITEMS, values);

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            label = getArguments().getString(ARG_LABEL);
            items = getArguments().getStringArray(ARG_ITEMS);
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_selection, container, false);

        TextView label = view.findViewById(R.id.selection_label);
        label.setText(this.label);

        Spinner selection = view.findViewById(R.id.selection_value);
        selection.setOnItemSelectedListener(this);
        selection.setAdapter(new ArrayAdapter<>(
                getActivity(),
                android.R.layout.simple_dropdown_item_1line,
                items
        ));

        selection.setSelection(0);

        return view;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String value = (String) parent.getItemAtPosition(position);
        sendMessage(value);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void updateFragmentData(String data) {

    }
}
