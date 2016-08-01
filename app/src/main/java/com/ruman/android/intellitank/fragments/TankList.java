package com.ruman.android.intellitank.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ruman.android.intellitank.R;
import com.ruman.android.intellitank.TankListAdaptor;
import com.ruman.android.intellitank.Tank;

import java.util.ArrayList;
import java.util.List;

public class TankList extends ListFragment {
    private OnFragmentInteractionListener mListener;
    private List tankList;
    private TankListAdaptor tankListAdaptor;

    public TankList() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        tankList = new ArrayList<Tank>();
        tankList.add(new Tank("Living Room", "Saltwater"));
        tankList.add(new Tank("Master bedroom", "Freshwater"));
        tankList.add(new Tank("Guest bedroom", "Brackish"));
        tankListAdaptor = new TankListAdaptor(getActivity(), tankList);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setListAdapter(tankListAdaptor);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tank_list, container, false);
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
