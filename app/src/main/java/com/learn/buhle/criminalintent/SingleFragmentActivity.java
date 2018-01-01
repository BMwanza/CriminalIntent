package com.learn.buhle.criminalintent;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by BMwanza on 12/29/2017.
 */

public abstract class SingleFragmentActivity extends AppCompatActivity {

    protected abstract Fragment createFragment();

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);

        FragmentManager manager = getSupportFragmentManager();
        Fragment frag = manager.findFragmentById(R.id.fragment_container);

        if(frag == null)
        {
            frag = createFragment();
            manager.beginTransaction().add(R.id.fragment_container, frag).commit();
        }
    }
}
