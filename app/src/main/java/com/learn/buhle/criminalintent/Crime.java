package com.learn.buhle.criminalintent;

import java.util.Date;
import java.util.UUID;

/**
 * Created by BMwanza on 12/24/2017.
 */

public class Crime {


    private UUID mId;
    private String mTitle;
    private Date mDateCommited;
    private boolean isSolved;

    public Crime()
    {
        this(UUID.randomUUID());
    }

    public Crime(UUID id)
    {
        mId = id;
        mDateCommited = new Date();
    }

    public UUID getId() {
        return mId;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public Date getDateCommited() {
        return mDateCommited;
    }

    public void setDateCommited(Date dateCommited) {
        mDateCommited = dateCommited;
    }

    public boolean isSolved() {
        return isSolved;
    }

    public void setSolved(boolean solved) {
        isSolved = solved;
    }
}
