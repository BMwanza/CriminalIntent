package com.learn.buhle.criminalintent.database;

/**
 * Created by BMwanza on 12/31/2017.
 */

public class CrimeDbSchema {
    /*
    DEFINES THE SCHEMA OF THE DATABASE
     */

    /*
    Why do we do it like this?
    This way we can refer to a column in our Data base in a safe way
    eg ---> CrimeTable.Cols.TITLE
    Essentially, we are defining our Schema
     */
    public static final class CrimeTable
    {
        public static final String NAME = "crimes";

        public static final class Cols
        {
            public static final String UUID = "uuid";
            public static final String TITLE = "title";
            public static final String DATE = "date";
            public static final String SOLVED = "solved";
        }
    }

}
