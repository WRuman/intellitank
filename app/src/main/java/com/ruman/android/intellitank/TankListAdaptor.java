package com.ruman.android.intellitank;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by will.ruman on 8/1/16.
 */
public class TankListAdaptor extends ArrayAdapter {

    private Context context;
    private ViewHolder vHold;

    private class ViewHolder {
        TextView tankName;
        TextView tankType;
    }

    public TankListAdaptor(Context context, List tanks) {
        super(context, android.R.layout.simple_list_item_2, tanks);
        this.context = context;
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        // First showing, do our view lookups
        if(convertView == null) {
            LayoutInflater inflator = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            convertView = inflator.inflate(R.layout.tank_list_item, null);
            vHold = new ViewHolder();
            vHold.tankName = (TextView) convertView.findViewById(R.id.tank_list_item_name);
            vHold.tankType = (TextView) convertView.findViewById(R.id.tank_list_item_type);
            convertView.setTag(vHold);
        } else {
            // Use existing view data
            vHold = (ViewHolder) convertView.getTag();
        }

        Tank tank = (Tank) getItem(position);
        vHold.tankName.setText(tank.getTankName());
        vHold.tankType.setText(tank.getTankType());

        return convertView;
    }
}
