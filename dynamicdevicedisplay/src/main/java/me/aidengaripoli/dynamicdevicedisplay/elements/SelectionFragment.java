package me.aidengaripoli.dynamicdevicedisplay.elements;

import android.content.Context;
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

import me.aidengaripoli.dynamicdevicedisplay.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link SelectionFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link SelectionFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SelectionFragment extends Fragment implements AdapterView.OnItemSelectedListener {
    public static final String SELECTION = "selection";

    private static final String ARG_LABEL = "label";
    private static final String ARG_ITEMS = "items";

    private String mLabel;
    private String mValue;
    private String[] mItems;

    private OnFragmentInteractionListener mListener;

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
            mLabel = getArguments().getString(ARG_LABEL);
            mItems = getArguments().getStringArray(ARG_ITEMS);
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_selection, container, false);

        TextView label = view.findViewById(R.id.selection_label);
        label.setText(mLabel);

        Spinner selection = view.findViewById(R.id.selection_value);
        selection.setOnItemSelectedListener(this);
        selection.setAdapter(new ArrayAdapter<>(
                getActivity(),
                android.R.layout.simple_dropdown_item_1line,
                mItems
        ));

        selection.setSelection(0);

        return view;
    }

    public void onItemSelected() {
        if (mListener != null) {
            mListener.onFragmentInteraction(mLabel, mValue);
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
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        mValue = (String) parent.getItemAtPosition(position);

        onItemSelected();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

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
        void onFragmentInteraction(String label, String value);
    }
}
