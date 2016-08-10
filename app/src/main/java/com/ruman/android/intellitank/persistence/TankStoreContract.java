package com.ruman.android.intellitank.persistence;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

/**
 * Created by will.ruman on 8/1/16.
 */
public class TankStoreContract {

    private static final String TEXT_TYPE = " TEXT";
    private static final String INT_TYPE = " INTEGER";
    private static final String NOT_NULL = " NOT NULL";
    private static final String INT_PRIMARY_KEY = " INTEGER PRIMARY KEY";
    private static final String COMMA_SEP = ",";
    private static final String SQL_CREATE =
            "CREATE TABLE " + TankTable.TABLE_NAME + " (" +
            TankTable._ID + INT_PRIMARY_KEY + COMMA_SEP +
            TankTable.COL_TANK_ID + INT_TYPE + COMMA_SEP +
            TankTable.COL_TANK_NAME + TEXT_TYPE + NOT_NULL + COMMA_SEP +
            TankTable.COL_TANK_TYPE + TEXT_TYPE + NOT_NULL + ")";
    private static final String SQL_DROP =
            "DROP TABLE IF EXISTS " + TankTable.TABLE_NAME;

    public TankStoreContract() {}

    private TankStoreDbHelper dbHelperInstance;

    public static abstract class TankTable implements BaseColumns {
        public static final String TABLE_NAME = "tanks";
        public static final String COL_TANK_ID = "id";
        public static final String COL_TANK_NAME = "name";
        public static final String COL_TANK_TYPE = "type";
    }

    public TankStoreDbHelper getDBHelper(Context ctx) {
        if(dbHelperInstance == null) {
            dbHelperInstance = new TankStoreDbHelper(ctx);
        }
        return dbHelperInstance;
    }

    public class TankStoreDbHelper extends SQLiteOpenHelper {

        private final static int DATABASE_VERSION = 1;
        private final static String DATABASE_NAME = "TankStore.db";

        public TankStoreDbHelper(Context ctx) {
            super(ctx, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(SQL_CREATE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int i, int i1) {
            db.execSQL(SQL_DROP);
            onCreate(db);
        }
    }
}
