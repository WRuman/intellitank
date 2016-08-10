package com.ruman.android.intellitank;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.ruman.android.intellitank.fragments.TankList;

public class MainActivity extends AppCompatActivity implements TankList.OnFragmentInteractionListener {

    static final int NEW_TANK_REQUEST = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(findViewById(R.id.tank_list_container) != null) {
            if(savedInstanceState != null) {
                return;
            }
            TankList tankFrag = new TankList();
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.tank_list_container, tankFrag).commit();
        }

    }

    public void createNewTank(View w) {
        Intent newTank = new Intent(this, NewTankActivity.class);
        startActivityForResult(newTank, NEW_TANK_REQUEST);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == NEW_TANK_REQUEST && resultCode == RESULT_OK) {
            Bundle retvals = data.getExtras();
            Tank nTank = retvals.getParcelable("Tank");
            if(nTank != null) {
                System.out.println(nTank.getTankName());
                System.out.println(nTank.getTankType());
            }
        }
    }

    @Override
    public void onFragmentInteraction(String s) {
        System.out.println(s);
    }
}
