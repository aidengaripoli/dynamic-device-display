package me.aidengaripoli.dynamicdevicedisplay.elements;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

import me.aidengaripoli.dynamicdevicedisplay.OnFragmentInteractionListener;

public abstract class DynamicFragment extends Fragment {
    static final String ARG_LABEL = "label";
    static final int ARG_LABEL_INDEX = 0;
    static final String NO_LABEL = "~";

    TextView labelView;
    String label;

    OnFragmentInteractionListener interactionListener;

    public abstract void updateFragmentData(ArrayList<String> updateData);

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            interactionListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString() + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        interactionListener = null;
    }

    public void sendMessage(String data) {
        if (interactionListener != null) {
            interactionListener.onFragmentMessage(getTag(), data);
        }
    }

    void addLabel(){
        if(label == null)
            return;

        if(label.equals(NO_LABEL)){
            labelView.setVisibility(View.GONE);
        }else{
            labelView.setText(label);
        }
    }
}
