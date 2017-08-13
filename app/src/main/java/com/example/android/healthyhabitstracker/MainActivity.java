package com.example.android.healthyhabitstracker;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.android.healthyhabitstracker.Data.HabitsDbHelper;
import com.example.android.healthyhabitstracker.Data.HabitsContract.HabitsEntry;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        HabitsDbHelper mDbHelper = new HabitsDbHelper(this);

        mDbHelper.insertHabit("Drinking water", 2);

        mDbHelper.insertHabit("Small walk", 1);

        Cursor cursor = mDbHelper.readDatabase();

        // Check if the read method is working
        try {

            int nameColumnIndex = cursor.getColumnIndex(HabitsEntry.COLUMN_HABIT_NAME);
            int repetitionsColumnIndex = cursor.getColumnIndex(HabitsEntry.COLUMN_HABIT_REPETITIONS);

            while (cursor.moveToNext()) {
                String currentName = cursor.getString(nameColumnIndex);
                int currentRepetitions = cursor.getInt(repetitionsColumnIndex);
                Log.v("Habit " + currentName, "has been repeated " + currentRepetitions + " times.");
            }
        } finally {
            cursor.close();
        }
    }
}
