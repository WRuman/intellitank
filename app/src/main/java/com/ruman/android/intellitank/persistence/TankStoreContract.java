package com.ruman.android.intellitank.persistence;

import android.provider.BaseColumns;

/**
 * Created by will.ruman on 8/1/16.
 */
public class TankStoreContract {

    public TankStoreContract() {}

    public static abstract class TankTable implements BaseColumns {
        public static final String TABLE_NAME = "tanks";
        public static final String COL_TANK_ID = "id";
        public static final String COL_TANK_NAME = "name";
        public static final String COL_TANK_TYPE = "type";
    }
}
