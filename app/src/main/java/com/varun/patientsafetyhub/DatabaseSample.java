package com.varun.patientsafetyhub;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DatabaseSample extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "SampleReg3.db";
    public static final String REG_TABLE_NAME = "SampleTAble";
    public static final String COLUMN_EMAIL = "email";
    public static final String COLUMN_PASS = "pass";


    private static final String TABLE_CREATE_REG =
            "CREATE TABLE " + REG_TABLE_NAME + "( " + COLUMN_EMAIL + " TEXT UNIQUE not null,  " + COLUMN_PASS + " TEXTnot null)";


    public DatabaseSample(Context context)

    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_CREATE_REG);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS" + TABLE_CREATE_REG);

    }
}