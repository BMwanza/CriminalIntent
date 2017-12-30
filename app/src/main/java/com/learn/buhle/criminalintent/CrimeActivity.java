package com.learn.buhle.criminalintent;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;

import java.util.UUID;

public class CrimeActivity extends SingleFragmentActivity {

    private static final String EXTRA_CRIME_ID = "criminalIntent_crime_id";

    public static Intent newIntent(Context packageContext, UUID crimeID)
    {
        Intent intent = new Intent(packageContext, CrimeActivity.class);
        intent.putExtra(EXTRA_CRIME_ID, crimeID);
        return intent;
    }


    /*
    The method that creates the Fragment that will be hosted in this Activity
    Now we are calling the NewInstance method we had created to bind the Fragment with it's arguments
     */
    @Override
    protected Fragment createFragment()
    {
        UUID crimeID = (UUID) getIntent().getSerializableExtra(EXTRA_CRIME_ID);
        return CrimeFragment.newInstance(crimeID);
    }

}
