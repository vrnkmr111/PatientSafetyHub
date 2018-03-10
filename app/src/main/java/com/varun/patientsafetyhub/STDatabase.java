package com.varun.patientsafetyhub;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by varun on 6/4/2016.
 */
public class STDatabase extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "pat3.db";
    public static final String PATIENT_TABLE_NAME = "patable";
    public static final String PATIENT_ID = "_id";
    public static final String PATIENT_NAME = "name";
    public static final String PATIENT_EMAIL = "emailid";
    public static final String PATIENT_DATE = "doc";
    public static final String PATIENT_SYMPTOMS = "symptoms";
    public static final String PATIENT_DOCINFO = "docinfo";

    private static final String TABLE_CREATE_SQL =
            "CREATE TABLE " + PATIENT_TABLE_NAME + "( " + PATIENT_ID +
                    " integer primary key autoincrement, " + PATIENT_NAME + " TEXT,  "+ PATIENT_EMAIL
                    + " TEXT,  "+ PATIENT_DATE + " TEXT,  " +
                    PATIENT_SYMPTOMS + " TEXT, " + PATIENT_DOCINFO + " TEXT)";

    public STDatabase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(TABLE_CREATE_SQL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

}

