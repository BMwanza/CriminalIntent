package com.learn.buhle.criminalintent;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

/**
 * Created by BMwanza on 12/24/2017.
 */

public class CrimeFragment extends Fragment {
    private Crime mCrime;
    private EditText mTextField;
    private Button mDateButton;
    private CheckBox mCheckBox;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        mCrime = new Crime();

        //Notice no inflation
    }
    /*
    This is where we inflate the layout view and retrun it to the host
     */
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        //Inflate the Fragment View
        View view = inflater.inflate(R.layout.fragment_crime, container, false);

        mTextField = (EditText) view.findViewById(R.id.crime_title);
        mDateButton = (Button) view.findViewById(R.id.crime_date);
        mCheckBox = (CheckBox) view.findViewById(R.id.crime_solved);

        mTextField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                //Blank
            }

            @Override
            //Call a toString on the CharSequence the user has put in
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                mCrime.setTitle(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {
                //Intentionally blank
            }
        });

        mDateButton.setText(mCrime.getDateCommited().toString());
        mDateButton.setEnabled(false); //Can't be pressed


        mCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                mCrime.setSolved(b);
            }
        });



        return view;
    }
}
