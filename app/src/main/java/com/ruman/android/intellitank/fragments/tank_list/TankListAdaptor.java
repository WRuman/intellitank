package com.ruman.android.intellitank.fragments.tank_list;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CursorAdapter;
import android.widget.TextView;

import com.ruman.android.intellitank.R;
import com.ruman.android.intellitank.Tank;
import com.ruman.android.intellitank.persistence.TankStoreContract;

import java.util.List;

/**
 * Created by will.ruman on 8/1/16.
 */
public class TankListAdaptor extends CursorAdapter {

    private Context context;
    private ViewHolder vHold;

    private class ViewHolder {
        TextView tankName;
        TextView tankType;
    }

    public TankListAdaptor(Context context, Cursor cursor, int flags) {
        super(context, cursor, flags);
        this.context = context;
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.tank_list_item, parent, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        vHold = new ViewHolder();
        vHold.tankName = (TextView) view.findViewById(R.id.tank_list_item_name);
        vHold.tankType = (TextView) view.findViewById(R.id.tank_list_item_type);
        String nameCol = TankStoreContract.TankTable.COL_TANK_NAME;
        String typeCol = TankStoreContract.TankTable.COL_TANK_TYPE;

        vHold.tankName.setText(cursor.getString(cursor.getColumnIndex(nameCol)));
        vHold.tankType.setText(cursor.getString(cursor.getColumnIndex(typeCol)));
    }
}
