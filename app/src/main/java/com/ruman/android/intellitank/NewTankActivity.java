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
    SQLiteDatabase db;

    private void initFormFields() {
        tankName = (EditText) findViewById(R.id.input_tank_name);
        tankType = (Spinner) findViewById(R.id.input_tank_type);

        // Spinner adapter
        ArrayAdapter<CharSequence> spinadapter = ArrayAdapter.createFromResource(this,
                R.array.tank_types_array,
                android.R.layout.simple_spinner_item);
        spinadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        tankType.setAdapter(spinadapter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_tank);
        initFormFields();

        // enable up icon
        if(getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        db = getDb();
    }

    private SQLiteDatabase getDb() {
        TankStoreContract tcon = new TankStoreContract();
        SQLiteOpenHelper dbHelper = tcon.getDBHelper(this);
        return dbHelper.getWritableDatabase();
    }

    private ContentValues getTankInsertValues(Tank t) {
        ContentValues vals = new ContentValues();
        vals.put(TankStoreContract.TankTable.COL_TANK_NAME, t.getTankName());
        vals.put(TankStoreContract.TankTable.COL_TANK_TYPE, t.getTankType());
        return vals;
    }

    public void saveTank(View v) {
        Intent retval = new Intent();
        String newTankName = tankName.getText().toString();
        String newTankType = tankType.getSelectedItem().toString();
        Tank t = new Tank(newTankName, newTankType);
        retval.putExtra("Tank", t);

        db.insert(TankStoreContract.TankTable.TABLE_NAME,
                  null,
                  getTankInsertValues(t));
        db.close();
        setResult(RESULT_OK, retval);
        finish();
    }
}
