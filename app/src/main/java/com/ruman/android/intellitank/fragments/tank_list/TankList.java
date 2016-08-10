package com.ruman.android.intellitank.fragments.tank_list;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Toast;

import com.ruman.android.intellitank.R;
import com.ruman.android.intellitank.persistence.TankStoreContract;

import java.util.List;

public class TankList extends ListFragment {
    private OnFragmentInteractionListener mListener;
    private TankListAdaptor tankListAdaptor;
    private SQLiteDatabase db;

    public TankList() {
        // Required empty public constructor
    }

    private SQLiteDatabase getDB(Context ctx) {
        TankStoreContract tankCon = new TankStoreContract();
        TankStoreContract.TankStoreDbHelper dbHelper = tankCon.new TankStoreDbHelper(ctx);
        return dbHelper.getReadableDatabase();
    }

    private Cursor getCursor(SQLiteDatabase db) {
        String[] cols = {
                "_id",
                TankStoreContract.TankTable.COL_TANK_NAME,
                TankStoreContract.TankTable.COL_TANK_TYPE,
                TankStoreContract.TankTable.COL_TANK_ID
        };
        String sort = TankStoreContract.TankTable.COL_TANK_NAME + " asc";
        return db.query(TankStoreContract.TankTable.TABLE_NAME, cols, null, null, null, null, sort, null);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        db = getDB(getContext());
        Cursor tankCursor = getCursor(db);

        tankListAdaptor = new TankListAdaptor(getActivity(), tankCursor, 0);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setListAdapter(tankListAdaptor);
        getListView().setOnItemLongClickListener(new TankLongClickListener());
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

    private class TankLongClickListener implements AdapterView.OnItemLongClickListener {
        @Override
        public boolean onItemLongClick(AdapterView<?> adv, View v, int pos, long id) {
            Log.d("long clicked", "pos: " + pos);
            Toast.makeText(getActivity(), "That's a long click!", Toast.LENGTH_SHORT).show();
            return true;
        }
    }

    /**
     * Signals that the tank list should check the database for new tanks
     */
    public void onNewTankAdded() {
        tankListAdaptor.changeCursor(getCursor(db));
    }

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(String s);
    }
}
