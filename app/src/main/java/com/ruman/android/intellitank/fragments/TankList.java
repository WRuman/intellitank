package com.ruman.android.intellitank.fragments;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ruman.android.intellitank.R;
import com.ruman.android.intellitank.Tank;
import com.ruman.android.intellitank.persistence.TankStoreContract;

import java.util.ArrayList;
import java.util.List;

public class TankList extends ListFragment {
    private OnFragmentInteractionListener mListener;
    private List tankList;
    private TankListAdaptor tankListAdaptor;

    public TankList() {
        // Required empty public constructor
    }

    private SQLiteDatabase getDB(Context ctx) {
        TankStoreContract tankCon = new TankStoreContract();
        TankStoreContract.TankStoreDbHelper dbHelper = tankCon.new TankStoreDbHelper(ctx);
        return dbHelper.getReadableDatabase();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SQLiteDatabase db = getDB(getContext());
        String[] cols = {
                "_id",
                TankStoreContract.TankTable.COL_TANK_NAME,
                TankStoreContract.TankTable.COL_TANK_TYPE,
                TankStoreContract.TankTable.COL_TANK_ID
        };
        String sort = TankStoreContract.TankTable.COL_TANK_NAME + " asc";
        System.out.println(sort);
        Cursor tankCursor = db.query(TankStoreContract.TankTable.TABLE_NAME, cols, null, null, null, null, sort, null);

        tankListAdaptor = new TankListAdaptor(getActivity(), tankCursor, 0);
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
