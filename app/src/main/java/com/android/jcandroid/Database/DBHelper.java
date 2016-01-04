package com.android.jcandroid.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    public static final String COLUMN_NAME = "Name";
    public static final String COLUMN_PHONE = "Phone";
    public static final String COLUMN_ADDRESS = "Address";

    public static final String COLUMN_L = "L";
    public static final String COLUMN_C = "C";
    public static final String COLUMN_W = "W";
    public static final String COLUMN_H = "H";
    public static final String COLUMN_T = "T";
    public static final String COLUMN_S = "S";
    public static final String COLUMN_B = "B";
    public static final String COLUMN_M = "M";
    public static final String COLUMN_NF = "NF";
    public static final String COLUMN_NB = "NB";
    public static final String COLUMN_CHK = "CHK";
    public static final String COLUMN_GHR = "GHR";
    public static final String COLUMN_SLVR = "SLVR";
    public static final String COLUMN_P = "P";
    public static final String COLUMN_TROUSER = "TROUSER";
    public static final String COLUMN_THG = "THG";
    public static final String COLUMN_PAJAMI = "PAJAMI";
    public static final String COLUMN_R = "R";
    public static final String COLUMN_BR = "BR";
    public static final String COLUMN_WR = "WR";
    public static final String COLUMN_RW = "RW";
    public static final String COLUMN_RH = "RH";
    public static final String COLUMN_EXTRACOMMENTS = "EXTRACOMMENTS";

    public static final String COLUMN_ROWID = "rowid";

    public static final String TABLE_NAME = "JC";
    public static final String DATABASE_NAME = "JC.db";
    private static final int DATABASE_VERSION = 1;

    private static final String CREATE_TABLE_JC = "CREATE TABLE "
            + TABLE_NAME + " (" + COLUMN_NAME + " VARCHAR(255), "
            + COLUMN_PHONE + " VARCHAR(255) PRIMARY KEY, "
            + COLUMN_ADDRESS + " VARCHAR(255), "
            + COLUMN_L + " VARCHAR(255), "
            + COLUMN_C + " VARCHAR(255), "
            + COLUMN_W + " VARCHAR(255), "
            + COLUMN_H + " VARCHAR(255), "
            + COLUMN_T + " VARCHAR(255), "
            + COLUMN_S + " VARCHAR(255), "
            + COLUMN_B + " VARCHAR(255), "
            + COLUMN_M + " VARCHAR(255), "
            + COLUMN_NF + " VARCHAR(255), "
            + COLUMN_NB + " VARCHAR(255), "
            + COLUMN_CHK + " VARCHAR(255), "
            + COLUMN_GHR + " VARCHAR(255), "
            + COLUMN_SLVR + " VARCHAR(255), "
            + COLUMN_P + " VARCHAR(255), "
            + COLUMN_TROUSER + " VARCHAR(255), "
            + COLUMN_THG + " VARCHAR(255), "
            + COLUMN_PAJAMI + " VARCHAR(255), "
            + COLUMN_R + " VARCHAR(255), "
            + COLUMN_BR + " VARCHAR(255), "
            + COLUMN_WR + " VARCHAR(255), "
            + COLUMN_RW + " VARCHAR(255), "
            + COLUMN_RH + " VARCHAR(255), "
            + COLUMN_EXTRACOMMENTS + " VARCHAR(255));";


    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL(CREATE_TABLE_JC);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}