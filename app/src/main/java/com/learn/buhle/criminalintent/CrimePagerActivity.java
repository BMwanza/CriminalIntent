package com.learn.buhle.criminalintent;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.UUID;

public class CrimePagerActivity extends AppCompatActivity {

    private ViewPager mViewPager;
    private ArrayList<Crime> mCrimes;
    private static final String EXTRA_CRIME_ID = "criminalIntent_crime_id";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crime_pager);

        mViewPager = (ViewPager) findViewById(R.id.crime_pager_view_pager);
        mCrimes = CrimeLab.get(this).getCrimes(); //Get the Dataset of crimes that we will work with

        UUID crimeID = (UUID) getIntent().getSerializableExtra(EXTRA_CRIME_ID);


        FragmentManager manager = getSupportFragmentManager();

        /*
        The Fragment State Pager Adapter needs a Fragment manager
        The State pager wil handel the communication of handeling our View pager
         */
        mViewPager.setAdapter(new FragmentStatePagerAdapter(manager) {
            @Override
            public Fragment getItem(int position) {
                return CrimeFragment.newInstance(mCrimes.get(position).getId());
            }

            @Override
            public int getCount() {
                return mCrimes.size();
            }
        });

        boolean foundIndex = false;
        for(int i = 0; i < mCrimes.size() && !foundIndex; i ++)
        {
            if(crimeID.equals(mCrimes.get(i).getId()))
            {
                foundIndex = true;
                mViewPager.setCurrentItem(i);
            }
        }
    }

    /*
    Returns an intent together with the data necessary to start this particular activity
     */
    public static Intent newIntent(Context packageContext, UUID crimeID)
    {
                             //The Activity who called it  //The Activity you want started
        Intent intent = new Intent(packageContext, CrimePagerActivity.class);
        intent.putExtra(EXTRA_CRIME_ID, crimeID); //match the hash ket with this value
        return intent;
    }
}
