package com.ruman.android.intellitank;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.ruman.android.intellitank.persistence.TankStoreContract;

public class NewTankActivity extends AppCompatActivity {

    EditText tankName;
    Spinner tankType;
    TankStoreContract dbContract;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_tank);

        tankName = (EditText) findViewById(R.id.input_tank_name);
        tankType = (Spinner) findViewById(R.id.input_tank_type);
        ArrayAdapter<CharSequence> spinadapter = ArrayAdapter.createFromResource(this,
                R.array.tank_types_array,
                android.R.layout.simple_spinner_item);
        spinadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        tankType.setAdapter(spinadapter);

        // enable up icon
        if(getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        dbContract = new TankStoreContract();
    }

    private SQLiteDatabase getDb(Context ctx) {
        SQLiteOpenHelper dbHelper = dbContract.new TankStoreDbHelper(ctx);
        return dbHelper.getWritableDatabase();
    }

    public void saveTank(View v) {
        Intent retval = new Intent();
        String newTankName = tankName.getText().toString();
        String newTankType = tankType.getSelectedItem().toString();
        Tank t = new Tank(newTankName, newTankType);
        SQLiteDatabase db = getDb(v.getContext());
        ContentValues vals = new ContentValues();
        vals.put(TankStoreContract.TankTable.COL_TANK_NAME, newTankName);
        vals.put(TankStoreContract.TankTable.COL_TANK_TYPE, newTankType);
        db.insert(TankStoreContract.TankTable.TABLE_NAME,
                  null,
                  vals);
        db.close();
        retval.putExtra("Tank", t);
        setResult(RESULT_OK, retval);
        finish();
    }
}
