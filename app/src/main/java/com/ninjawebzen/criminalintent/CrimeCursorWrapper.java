package com.ninjawebzen.criminalintent;

import android.database.Cursor;
import android.database.CursorWrapper;

import java.util.Date;
import java.util.UUID;

/**
 * Created by slavkurochkin on 1/11/17.
 */

public class CrimeCursorWrapper extends CursorWrapper{
    public CrimeCursorWrapper(Cursor cursor){
        super(cursor);
    }

    public Crime getCrime() {
        String uuidString =
                getString(getColumnIndex(CrimeDbSchema.CrimeTable.Cols.UUID));
        String title =
                getString(getColumnIndex(CrimeDbSchema.CrimeTable.Cols.TITLE));
        long date =
                getLong(getColumnIndex(CrimeDbSchema.CrimeTable.Cols.DATE));
        int isSolved =
                getInt(getColumnIndex(CrimeDbSchema.CrimeTable.Cols.SOLVED));

        Crime crime = new
                Crime(UUID.fromString(uuidString));
        crime.setmTitle(title);
        crime.setmDate(new Date(date));
        crime.setmSolved(isSolved != 0);
        return crime;
    }
}
