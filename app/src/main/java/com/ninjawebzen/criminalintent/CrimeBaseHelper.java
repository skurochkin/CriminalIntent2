package com.ninjawebzen.criminalintent;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by slavkurochkin on 1/11/17.
 */

public class CrimeBaseHelper extends SQLiteOpenHelper {

    public static final int VERSION = 1;
    public static final String DATABASE_NAME = "crimeBase.db";

    public CrimeBaseHelper(Context context){
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + CrimeDbSchema.CrimeTable.NAME + "("+
        " _id integer primary key autoincremnt, " +
                CrimeDbSchema.CrimeTable.Cols.UUID + ", " +
                CrimeDbSchema.CrimeTable.Cols.TITLE + ", " +
                CrimeDbSchema.CrimeTable.Cols.DATE + ", " +
                CrimeDbSchema.CrimeTable.Cols.SOLVED +
                ")"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {

    }
}