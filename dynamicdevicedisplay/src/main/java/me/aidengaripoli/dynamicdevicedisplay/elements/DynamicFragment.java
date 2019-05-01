package me.aidengaripoli.dynamicdevicedisplay.elements;

import android.content.Context;
import android.support.v4.app.Fragment;

import me.aidengaripoli.dynamicdevicedisplay.OnFragmentInteractionListener;

public abstract class DynamicFragment extends Fragment {
    static final String ARG_LABEL = "label";
    static final int ARG_LABEL_INDEX = 0;

    String label;

    OnFragmentInteractionListener interactionListener;

    public abstract void updateFragmentData(String data);

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
}
