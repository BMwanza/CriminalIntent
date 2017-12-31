package com.learn.buhle.criminalintent;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.DatePicker;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by BMwanza on 12/31/2017.
 */

public class DatePickerFragment extends DialogFragment {


    private DatePicker mDatePicker; //?
    private static final String ARG_DATE = "crime_date";
    public static  final String DATE_EXTRA = "date_as_extra";

    public Dialog onCreateDialog(Bundle savedInstanceState)
    {
        int year, month, day;
        View dialog = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_date, null);



        Date date = (Date) getArguments().getSerializable(ARG_DATE);
        Calendar calendar = Calendar.getInstance(); //Use a calendar object to get the integers for DMY from Date
        calendar.setTime(date); //Link them
        //Extract the Data from the Calendar
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);

        mDatePicker = (DatePicker) dialog.findViewById(R.id.dialog_date_picker);
        mDatePicker.init(day, month, year, null);
        /*
        The Alert Dialog Builder Class provides a interface(set of methods to make a dialog)
         */
        return new AlertDialog.Builder(getActivity()) //Pass the context to the builder
                .setView(dialog)
                .setTitle(R.string.date_picker_title) //Set the Title
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Date date = new GregorianCalendar(mDatePicker.getYear(), mDatePicker.getMonth(), mDatePicker.getDayOfMonth()).getTime();
                        sendResult(Activity.RESULT_OK, date);
                    }
                }).create(); // The button you want and create it :)
    }

    public static DatePickerFragment newInstance(Date date)
    {
        Bundle args = new Bundle();
        args.putSerializable(ARG_DATE, date);

        DatePickerFragment frag = new DatePickerFragment();
        frag.setArguments(args);
        return frag;

    }

    private void sendResult(int resultCode, Date date)
    {
        if(getTargetFragment() == null)
        {
            return;
        }

        Intent intent = new Intent();
        intent.putExtra(DATE_EXTRA, date);
        //Calling the Target Fragment's on Activity Result
        getTargetFragment().onActivityResult(getTargetRequestCode(), resultCode, intent);
    }
}
