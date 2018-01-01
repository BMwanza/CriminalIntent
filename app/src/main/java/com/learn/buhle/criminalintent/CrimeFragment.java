package com.learn.buhle.criminalintent;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

import java.util.Date;
import java.util.UUID;

/**
 * Created by BMwanza on 12/24/2017.
 */

public class CrimeFragment extends Fragment {
    private Crime mCrime;
    private EditText mTextField;
    private Button mDateButton;
    private CheckBox mCheckBox;

    private static final String ARG_CRIME_ID = "crime_id";
    private static final String ARG_DATE_PICKER =" data_picker_tag";
    private static final int DATE_PICKER_REQUEST_CODE = 0;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        //Get the UUID of the crime that was stored in the Intent
        UUID crimeID = (UUID) getArguments().getSerializable(ARG_CRIME_ID);
        mCrime = CrimeLab.get(getActivity()).getCrime(crimeID);


        //Notice no inflation
    }

    @Override
    public void onPause()
    {
        super.onPause();
        CrimeLab.get(getActivity()).updateCrime(mCrime);

    }


    public static CrimeFragment newInstance(UUID crimeID)
    {
        Bundle args = new Bundle();
        args.putSerializable(ARG_CRIME_ID, crimeID);

        CrimeFragment crimeFrag = new CrimeFragment();
        crimeFrag.setArguments(args); //Attatch the Arguments to the Fragment
        return crimeFrag;
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

        mTextField.setText(mCrime.getTitle());

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

        updateDate();
//        mDateButton.setEnabled(true);
        mDateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager manager = getFragmentManager();
                DatePickerFragment dialog = DatePickerFragment.newInstance(mCrime.getDateCommited());
                dialog.setTargetFragment(CrimeFragment.this, DATE_PICKER_REQUEST_CODE); //Datepicker is setting THIS Fragment as the Target
                dialog.show(manager, ARG_DATE_PICKER);
            }
        });

        mCheckBox.setChecked(mCrime.isSolved());
        mCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                mCrime.setSolved(b);
            }
        });

        return view;
    }

    /*
    Handels the Data that we have recieved from an child Fragment
     */
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if (resultCode != Activity.RESULT_OK)
        {
            return;
        }

        if(requestCode == DATE_PICKER_REQUEST_CODE)
        {
            Date date = (Date) data.getSerializableExtra(DatePickerFragment.DATE_EXTRA);
            mCrime.setDateCommited(date);
            updateDate();
        }
    }

    private void updateDate() {
        mDateButton.setText(mCrime.getDateCommited().toString());
    }

}
