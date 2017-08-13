package com.example.android.healthyhabitstracker.Data;

/**
 * Created by Utilizador on 03/08/2017.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.android.healthyhabitstracker.Data.HabitsContract.HabitsEntry;



/**
 * Database helper for HealthyHabits app. Manages database creation and version management.
 */
public class HabitsDbHelper extends SQLiteOpenHelper {

    /** Name of the database file */
    private static final String DATABASE_NAME = "habits.db";

    /**
     * Database version. If you change the database schema, you must increment the database version.
     */
    private static final int DATABASE_VERSION = 1;

    /**
     * Constructs a new instance of HabitsDbHelper.
     *
     * @param context of the app
     */
    public HabitsDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    /**
     * This is called when the database is created for the first time.
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        // Create a String that contains the SQL statement to create the habits table
        String SQL_CREATE_HABITS_TABLE =  "CREATE TABLE " + HabitsEntry.TABLE_NAME + " ("
                + HabitsEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + HabitsEntry.COLUMN_HABIT_NAME + " TEXT NOT NULL, "
                + HabitsEntry.COLUMN_HABIT_REPETITIONS + " INTEGER NOT NULL DEFAULT 0);";

        // Execute the SQL statement
        db.execSQL(SQL_CREATE_HABITS_TABLE);
    }

    /**
     * Helper method to insert habit data into the database.
     */
    public void insertHabit(String habit, int repetitions) {

        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(HabitsEntry.COLUMN_HABIT_NAME, habit);
        values.put(HabitsEntry.COLUMN_HABIT_REPETITIONS, repetitions);

        long newRowId = db.insert(HabitsEntry.TABLE_NAME, null, values);
        Log.v("Habit: " + habit, "inserted");

    }

    public Cursor readDatabase() {

        SQLiteDatabase db = getReadableDatabase();

        // Define a projection that specifies which columns from the database
        // will actually be used after this query.
        String[] projection = {
                HabitsEntry._ID,
                HabitsEntry.COLUMN_HABIT_NAME,
                HabitsEntry.COLUMN_HABIT_REPETITIONS };

        // Perform a query on the habits table
        Cursor cursor = db.query(
                HabitsEntry.TABLE_NAME,   // The table to query
                projection,            // The columns to return
                null,                  // The columns for the WHERE clause
                null,                  // The values for the WHERE clause
                null,                  // Don't group the rows
                null,                  // Don't filter by row groups
                null);                   // The sort order

        return cursor;
    }

    /**
     * This is called when the database needs to be upgraded.
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
}