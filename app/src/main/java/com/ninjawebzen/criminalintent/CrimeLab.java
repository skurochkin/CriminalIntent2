package com.ninjawebzen.criminalintent;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by slavkurochkin on 1/7/17.
 */

public class CrimeLab {

    private static CrimeLab sCrimeLab;

    private Context mContext;
    private SQLiteDatabase mDatabase;

    public static CrimeLab get(Context context) {
        if (sCrimeLab == null) {
            sCrimeLab = new CrimeLab(context);
        }
        return sCrimeLab;
    }

    private CrimeLab(Context context){
        mContext = context.getApplicationContext();
        mDatabase = new CrimeBaseHelper(mContext)
                .getWritableDatabase();
    }

    public void addCrime(Crime c){
        ContentValues values = getContentValues(c);
        mDatabase.insert(CrimeDbSchema.CrimeTable.NAME, null, values);
    }

    public List<Crime> getCrimes() {
        List<Crime> crimes = new ArrayList<>();

        CrimeCursorWrapper cursor = queryCrimes(null, null);

        try{
            cursor.moveToFirst();
            while (!cursor.moveToFirst()){
            crimes.add(cursor.getCrime());
            cursor.moveToNext();
        }
    }finally {
     cursor.close();
        }
        return crimes;
    }

    private static ContentValues getContentValues(Crime crime){
        ContentValues values = new ContentValues();
        values.put(CrimeDbSchema.CrimeTable.Cols.UUID, crime.getmId().toString());
        values.put(CrimeDbSchema.CrimeTable.Cols.TITLE, crime.getmTitle());
        values.put(CrimeDbSchema.CrimeTable.Cols.DATE, crime.getmDate().getTime());
        values.put(CrimeDbSchema.CrimeTable.Cols.SOLVED, crime.ismSolved() ? 1 : 0);

        return values;
    }

    private CrimeCursorWrapper queryCrimes(String whereClause, String[] whereArgs){
        Cursor cursor = mDatabase.query(
                CrimeDbSchema.CrimeTable.NAME,
                null,
                whereClause,
                whereArgs,
                null,
                null,
                null
        );
        return new CrimeCursorWrapper(cursor);
    }

    public Crime getCrime(UUID id) {

        CrimeCursorWrapper cursor = queryCrimes(
                CrimeDbSchema.CrimeTable.Cols.UUID + " = ?",
                new String[] {id.toString()}
        );

        try{
            if(cursor.getCount() == 0){
                return null;
            }

            cursor.moveToFirst();
            return cursor.getCrime();
        }finally {
            cursor.close();
        }
    }

    public void updateCrime(Crime crime){
        String uuidString = crime.getmId().toString();
        ContentValues values = getContentValues(crime);

        mDatabase.update(CrimeDbSchema.CrimeTable.NAME, values,
                CrimeDbSchema.CrimeTable.Cols.UUID + " = ?",
                new String[]{uuidString});
    }
}
