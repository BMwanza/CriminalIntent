package com.learn.buhle.criminalintent;

import android.content.Context;

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
    private ArrayList<Crime> mCrimes;
    //Private constructor
    private CrimeLab(Context context)
    {
        Crime crime;
        mCrimes = new ArrayList<Crime>();
        for(int i = 0; i < 100; i++)
        {
            crime = new Crime();
            crime.setTitle("Crime # " + i);
            crime.setSolved(i % 2 == 0);
            mCrimes.add(crime);
        }

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
        return mCrimes;

    }


    public Crime getCrime(UUID cID)
    {
        Crime crime = null;
        boolean found = false;
        for(int i = 0; i < mCrimes.size() && !found; i++)
        {
            if(mCrimes.get(i).getId() == cID)
            {
                found = true;
                crime = mCrimes.get(i);
            }
        }

        return crime;
    }


}
