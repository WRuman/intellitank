package com.ruman.android.intellitank.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.ruman.android.intellitank.R;

public class TankList extends Fragment implements View.OnClickListener {
    private OnFragmentInteractionListener mListener;

    public TankList() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_tank_list, container, false);
        Button a = (Button) v.findViewById(R.id.button_1);
        Button b = (Button) v.findViewById(R.id.button_2);
        a.setOnClickListener(this);
        b.setOnClickListener(this);
        return v;
    }

    @Override
    public void onClick(View v) {
        mListener.onFragmentInteraction(((Button) v).getText().toString());
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

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(String s);
    }
}
