package me.aidengaripoli.dynamicdevicedisplay.elements;

import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.ArrayList;

import me.aidengaripoli.dynamicdevicedisplay.OnFragmentInteractionListener;
import me.aidengaripoli.dynamicdevicedisplay.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link SchedulerFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SchedulerFragment extends DynamicFragment implements AdapterView.OnItemSelectedListener {
    public static final String SCHEDULER = "scheduler";

    private static final String ARG_BUTTON_LABEL = "buttonLabel";
    private static final String ARG_ITEMS = "items";

    private static final int ARG_BUTTON_LABEL_INDEX = 1;

    private String[] spinnerItems;

    private int hr;
    private int min;
    private String time;
    private Button timeButton;

    private int currentPosition;

    private TimePickerDialog.OnTimeSetListener timePickerListener = new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minutes) {
            hr = hourOfDay;
            min = minutes;
            updateTime(hr, min);
        }
    };

    public SchedulerFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param displaySettings Parameter 1.
     * @return A new instance of fragment SchedulerFragment.
     */
    public static SchedulerFragment newInstance(ArrayList<String> displaySettings) {
        SchedulerFragment fragment = new SchedulerFragment();

        Bundle args = new Bundle();
        args.putString(ARG_LABEL, displaySettings.get(ARG_LABEL_INDEX));
        args.putString(ARG_BUTTON_LABEL, displaySettings.get(ARG_BUTTON_LABEL_INDEX));
        displaySettings.remove(ARG_BUTTON_LABEL_INDEX);

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
            spinnerItems = getArguments().getStringArray(ARG_ITEMS);
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_scheduler, container, false);

        TextView labelView = view.findViewById(R.id.SchedulerLabel);
        labelView.setText(label);

        timeButton = view.findViewById(R.id.SchedulerButtonTime);
        timeButton.setOnClickListener(v -> new TimePickerDialog(getActivity(), timePickerListener, hr, min, false).show());

        Spinner selection = view.findViewById(R.id.SchedulerSpinner);
        selection.setOnItemSelectedListener(this);
        selection.setAdapter(new ArrayAdapter<>(
                getActivity(),
                android.R.layout.simple_dropdown_item_1line,
                spinnerItems
        ));

        Button button = view.findViewById(R.id.SchedulerButtonSubmit);
        if (getArguments() != null) {
            button.setText(getArguments().getString(ARG_BUTTON_LABEL));
        }
        button.setOnClickListener(v -> interactionListener.onFragmentMessage(getTag(), spinnerItems[currentPosition] + "," + time));

        return view;
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
        time = String.valueOf(hours) + ':' + minutes + " " + timeSet;
        timeButton.setText(time);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        currentPosition = position;
        sendMessage(spinnerItems[currentPosition]);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void updateFragmentData(String data) {

    }
}