package com.example.android.healthyhabitstracker.Data;

import android.provider.BaseColumns;

/**
 * Created by Utilizador on 03/08/2017.
 */

public final class HabitsContract {

    // To prevent someone from accidentally instantiating the contract class,
    // give it an empty constructor.
    private HabitsContract() {}

    /**
     * Inner class that defines constant values for the habits database table.
     * Each entry in the table represents a single habit.
     */
    public static final class HabitsEntry implements BaseColumns {

        /** Name of database table for Habits */
        public final static String TABLE_NAME = "habits";

        /**
         * Unique ID number for the habit (only for use in the database table).
         *
         * Type: INTEGER
         */
        public final static String _ID = BaseColumns._ID;

        /**
         * The habit.
         *
         * Type: TEXT
         */
        public final static String COLUMN_HABIT_NAME ="habit";

        /**
         * Number of times the habit has been repeated.
         * Type: INTEGER
         */
        public final static String COLUMN_HABIT_REPETITIONS = "repetitions";

    }

}