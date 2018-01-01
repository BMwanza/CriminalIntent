package com.learn.buhle.criminalintent;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.learn.buhle.criminalintent.database.CrimeBaseHelper;
import com.learn.buhle.criminalintent.database.CrimeCursorWrapper;
import com.learn.buhle.criminalintent.database.CrimeDbSchema.CrimeTable;

import java.util.ArrayList;
import java.util.UUID;

/**
 * Created by BMwanza on 12/29/2017.
 */

/*
This will be a singleton class, so there will be only one Instance of CrimeLab
This is where we will store all of the crimes in our system
 */
public class CrimeLab {

    private static CrimeLab sCrimeLab;
//    private ArrayList<Crime> mCrimes;
    private Context mContext;
    private SQLiteDatabase mDatabase;

    //Private constructor
    private CrimeLab(Context context)
    {

//        mCrimes = new ArrayList<Crime>();

        /*
        THIS IS WHAT SETS UP THE DATABASE
         */
        mContext = context.getApplicationContext();
        mDatabase = new CrimeBaseHelper(mContext).getWritableDatabase();


    }

    public static CrimeLab get(Context context)
    {
        if(sCrimeLab == null)
        {
            sCrimeLab = new CrimeLab(context);
        }

        return sCrimeLab;
    }

    public ArrayList<Crime> getCrimes()
    {
        ArrayList<Crime> crimes = new ArrayList<Crime>();

        CrimeCursorWrapper cursor = queryCrimes(null, null);

        //Cursor works like a file reader
        try
        {
            cursor.moveToFirst();
            while(!cursor.isAfterLast())
            {
                crimes.add(cursor.getCrime());
                cursor.moveToNext();
            }
        }
        finally
        {
            cursor.close();
        }
        return crimes;
    }

    public void addCrime(Crime newCrime)
    {
        ContentValues values = getContentValues(newCrime);
        mDatabase.insert(CrimeTable.NAME, null, values);
    }


    public Crime getCrime(UUID cID)
    {
        CrimeCursorWrapper cursorWrapper = queryCrimes(CrimeTable.Cols.UUID + " = ?", new String[] {cID.toString()});

        try
        {
            if(cursorWrapper.getCount() == 0)
            {
                return null;
            }

            cursorWrapper.moveToFirst();
            return cursorWrapper.getCrime();
        }
        finally {
            cursorWrapper.close();
        }

    }

    private static ContentValues getContentValues(Crime crime)
    {
        ContentValues values = new ContentValues();
        values.put(CrimeTable.Cols.UUID, crime.getId().toString());
        values.put(CrimeTable.Cols.TITLE, crime.getTitle());
        values.put(CrimeTable.Cols.DATE, crime.getDateCommited().getTime());
        values.put(CrimeTable.Cols.SOLVED, crime.isSolved() ? 1:0);

        return values;
    }

    public void updateCrime(Crime crime)
    {
        String uuid = crime.getId().toString();
        ContentValues values = getContentValues(crime);

        mDatabase.update(CrimeTable.NAME, values, CrimeTable.Cols.UUID + " = ?", new String[] {uuid});
        //The question mark is oddd but it is for safety
    }

    private CrimeCursorWrapper queryCrimes(String whereClause, String[] whereArgs)
    {
        /*
        This is the structure of a query, for simplicity, We just want to select from all rows
         */
        Cursor cursor = mDatabase.query(CrimeTable.NAME, //FROM
                null, //SELECT ROWS
                whereClause, //WHERE
                whereArgs, //Conditions
                null, //Group By
                null, //Having
                null, //Order By
                null); //Limit

        return new CrimeCursorWrapper(cursor);
    }


}
