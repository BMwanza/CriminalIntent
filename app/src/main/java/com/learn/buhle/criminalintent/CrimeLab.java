package com.learn.buhle.criminalintent;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.learn.buhle.criminalintent.database.CrimeBaseHelper;

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
//        return mCrimes;
        return new ArrayList<Crime>();

    }

    public void addCrime(Crime newCrime)
    {
//        mCrimes.add(newCrime);
    }


    public Crime getCrime(UUID cID)
    {
//        Crime crime = null;
//        boolean found = false;
//        for(int i = 0; i < mCrimes.size() && !found; i++)
//        {
//            if(mCrimes.get(i).getId().equals(cID))
//            {
//                found = true;
//                crime = mCrimes.get(i);
//            }
//        }

        return null;
    }


}
