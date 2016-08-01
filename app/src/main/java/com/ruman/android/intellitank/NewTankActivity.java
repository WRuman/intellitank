package com.ruman.android.intellitank;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class NewTankActivity extends AppCompatActivity {

    EditText tankName;
    EditText tankType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_tank);

        tankName = (EditText) findViewById(R.id.input_tank_name);
        tankType = (EditText) findViewById(R.id.input_tank_type);

        // enable up icon
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public void saveTank(View v) {
        Intent retval = new Intent();
        Tank t = new Tank(tankName.getText().toString(), tankType.getText().toString());
        retval.putExtra("tank", t);
        setResult(RESULT_OK, retval);
        finish();
    }
}
