package com.learn.buhle.criminalintent;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by BMwanza on 12/29/2017.
 */

public class CrimeListFragment extends Fragment {
    private RecyclerView mCrimeRecycleView;
    private CrimeAdapter mCrimeAdapter;
    private int mChangedPos; //The specific position changed

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        //Again we inflate the layout file in the OnCreate View, this is the layout of the fragment
        View view = inflater.inflate(R.layout.fragment_crime_list, container, false);

        mCrimeRecycleView = (RecyclerView) view.findViewById(R.id.crime_recycler_view);
        mCrimeRecycleView.setLayoutManager(new LinearLayoutManager(getActivity()));

        updateUI(mChangedPos);
        return view;
    }

    /*
    Override the onResume function such that it will update the List in the event of data being changed.
     */
    @Override
    public void onResume()
    {
        super.onResume();
        updateUI(mChangedPos);
    }



    /*
    Links the Recycle View and the adapter
     */
    private void updateUI(int changed)
    {
        CrimeLab crimeLab = CrimeLab.get(getActivity());
        ArrayList<Crime> crimes = crimeLab.getCrimes();

        if(mCrimeAdapter == null)
        {
            mCrimeAdapter = new CrimeAdapter(crimes);
            mCrimeRecycleView.setAdapter(mCrimeAdapter);
        }
        else //Tell the adapter that a change has been made
        {
            mCrimeAdapter.notifyItemChanged(changed);
        }
    }

    //Private ViewHolder Class
    /*
    Holds a references to a single View, a TextView
    For simplicity we just assume that the itemView is a textView
     */
    private class CrimeHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        public CheckBox mSolvedCheckBox;
        public TextView mTitleTextView;
        public TextView mCrimeDateTextView;
        private Crime mCrime;

        public CrimeHolder(View itemView)
        {
            super(itemView);
            itemView.setOnClickListener(this);
            mSolvedCheckBox = (CheckBox) itemView.findViewById(R.id.list_item_crime_solved_checkbox);
            mTitleTextView = (TextView) itemView.findViewById(R.id.list_item_title_textview);
            mCrimeDateTextView = (TextView) itemView.findViewById(R.id.list_item_crime_date_textview);

        }

        @Override
        public void onClick(View v)
        {
            mChangedPos = getAdapterPosition(); //What was the position changed
            Intent intent = CrimeActivity.newIntent(getActivity(), mCrime.getId());
            startActivity(intent);
        }

        //update The Text view
        public void bindCrime(Crime crime)
        {
            mCrime = crime;
            mSolvedCheckBox.setChecked(crime.isSolved());
            mTitleTextView.setText(crime.getTitle());
            mCrimeDateTextView.setText(crime.getDateCommited().toString());
        }
    }

    /*
    Creating the adapter For the Recycler View
     */

    private class CrimeAdapter extends RecyclerView.Adapter<CrimeHolder>
    {
        private ArrayList<Crime> mCrimes;
        public CrimeAdapter(ArrayList<Crime> crimes)
        {
            mCrimes = crimes;
        }

        @Override
        /*
        Called by the Recycler
        Create a view and wrap it into a ViewHolder (Crime Holder)
         */
        public CrimeHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(getActivity());
            View view = inflater.inflate(R.layout.list_item_crime, parent, false); //The View of each ListItem
            return new CrimeHolder(view);

        }

        @Override
        /*
        Bind the view Holder's View to the data of our model using the position
         */
        public void onBindViewHolder(CrimeHolder holder, int position) {
            Crime crime = mCrimes.get(position); //Get the data of the model(By getting the model at whatever position
            holder.bindCrime(crime); //Update the view with the data of the Crime at ith position
        }

        @Override
        public int getItemCount() {
            return mCrimes.size();
        }
    }

}
