package me.aidengaripoli.dynamicdevicedisplay.elements;

import android.app.TimePickerDialog;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;

import org.w3c.dom.Element;

import java.util.ArrayList;

import me.aidengaripoli.dynamicdevicedisplay.R;
import me.aidengaripoli.dynamicdevicedisplay.XmlParser;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link TimePickerFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link TimePickerFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TimePickerFragment extends Fragment {
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_LABEL = "label";
    private static final int ARG_LABEL_INDEX = 0;

    private String label;

    private OnFragmentInteractionListener mListener;

    private int hr;
    private int min;
    private TextView time;

    private TimePickerDialog.OnTimeSetListener timePickerListener = new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minutes) {
            hr = hourOfDay;
            min = minutes;
            updateTime(hr, min);
        }
    };

    public TimePickerFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param label Parameter 1.
     * @param displaySettings Parameter 2.
     * @return A new instance of fragment TimePickerFragment.
     */
    public static TimePickerFragment newInstance(String label, ArrayList<String> displaySettings) {
        TimePickerFragment fragment = new TimePickerFragment();

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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_time_picker, container, false);

        TextView labelView = view.findViewById(R.id.TimePicker_Label);
        labelView.setText(label);

        time = view.findViewById(R.id.TimePicker_output);

        Button button = view.findViewById(R.id.btnClick);
        button.setOnClickListener(v -> new TimePickerDialog(getActivity(), timePickerListener, hr, min, false).show());
        return view;
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

    private void updateTime(int hours, int mins) {
        String timeSet;
        if (hours > 12) {
            hours -= 12;
            timeSet = "PM";
        } else if (hours == 0) {
            hours += 12;
            timeSet = "AM";
        } else if (hours == 12)
            timeSet = "PM";
        else
            timeSet = "AM";
        String minutes;
        if (mins < 10)
            minutes = "0" + mins;
        else
            minutes = String.valueOf(mins);
        String aTime = String.valueOf(hours) + ':' + minutes + " " + timeSet;
        time.setText(aTime);
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