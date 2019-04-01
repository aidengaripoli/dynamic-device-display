package me.aidengaripoli.dynamicdevicedisplay.elements;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Switch;

import me.aidengaripoli.dynamicdevicedisplay.R;

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

    public DirectionalArrowsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment DirectionalArrowsFragment.
     */
    public static DirectionalArrowsFragment newInstance() {
        DirectionalArrowsFragment fragment = new DirectionalArrowsFragment();
        Bundle args = new Bundle();
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
        Button sButton = view.findViewById(R.id.direcButtonS);
        Button eButton = view.findViewById(R.id.direcButtonE);
        Button wButton = view.findViewById(R.id.direcButtonW);

        nButton.setOnClickListener(this);
        sButton.setOnClickListener(this);
        eButton.setOnClickListener(this);
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
