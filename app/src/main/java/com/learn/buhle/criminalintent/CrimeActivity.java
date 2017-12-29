package com.learn.buhle.criminalintent;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;

public class CrimeActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crime);

        //Get the Fragment Manager for this activity
        FragmentManager manager = getSupportFragmentManager();
        Fragment fragment = manager.findFragmentById(R.id.fragment_container); //Find fragment with the view container of where it will be held by using the ID

        if (fragment == null)
        {
            fragment = new CrimeFragment(); //Create a new instance of our Fragment
            //Create and commit a fragment Transcation
            manager.beginTransaction() //Create an instance of the Fragment Transcation
                    .add(R.id.fragment_container, fragment)
                    .commit();
        }

    }
}
