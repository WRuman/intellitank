package com.ruman.android.intellitank;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.ruman.android.intellitank.fragments.TankList;

public class MainActivity extends AppCompatActivity implements TankList.OnFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(findViewById(R.id.tank_list_container) != null) {
            if(savedInstanceState != null) {
                return;
            }
            TankList tanks = new TankList();
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.tank_list_container, tanks).commit();
        }

    }

    public void createNewTank(View w) {
        System.out.println("Create new tank triggered");
        Intent newTank = new Intent(this, NewTankActivity.class);
        startActivity(newTank);
    }

    @Override
    public void onFragmentInteraction(String s) {
        System.out.println(s);
    }
}
