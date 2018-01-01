package com.learn.buhle.criminalintent.database;

import android.database.Cursor;
import android.database.CursorWrapper;

import com.learn.buhle.criminalintent.Crime;
import com.learn.buhle.criminalintent.database.CrimeDbSchema.CrimeTable;

import java.util.Date;
import java.util.UUID;

/**
 * Created by BMwanza on 1/1/2018.
 */

public class CrimeCursorWrapper extends CursorWrapper {
    public CrimeCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    public Crime getCrime()
    {
        /*
        This is calling the cursor.get* Methods from the Database and extracting them to the
        appropriate data types.
         */
        String uuid = getString(getColumnIndex(CrimeTable.Cols.UUID));
        String title = getString(getColumnIndex(CrimeTable.Cols.TITLE));
        long date = getLong(getColumnIndex(CrimeTable.Cols.DATE));
        int isSolved = getInt(getColumnIndex(CrimeTable.Cols.SOLVED));

        Crime crime = new Crime(UUID.fromString(uuid));
        crime.setTitle(title);
        crime.setDateCommited(new Date(date));
        crime.setSolved(isSolved != 0);

        return crime; //For now
    }
}
