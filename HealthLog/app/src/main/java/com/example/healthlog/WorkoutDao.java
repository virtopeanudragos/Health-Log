package com.example.healthlog;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Data Access Object (DAO) for handling workout data in the SQLite database.
 */
public class WorkoutDao {

    private final SQLiteDatabase db;

    /**
     * Constructor for WorkoutDao.
     * Initializes the database helper and gets the writable database.
     * @param context The context used to open or create the database.
     */
    public WorkoutDao(Context context) {
        DatabaseHelper dbHelper = new DatabaseHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    /**
     * Inserts a new WorkoutRoutine into the database.
     * @param workout The WorkoutRoutine object to insert.
     * @return The row ID of the newly inserted row, or -1 if an error occurred.
     */
    public long insertWorkout(WorkoutRoutine workout) {
        ContentValues values = new ContentValues();
        values.put("title", workout.getTitle());
        values.put("rating", workout.getRating());
        values.put("duration", workout.getDuration());
        values.put("type", workout.getType());

        return db.insert("workoutROUTINES", null, values);
    }



    public List<WorkoutRoutine> getCustomWorkouts() {
        return getWorkoutsByType("custom");
    }

    public List<WorkoutRoutine> getExploreWorkouts() {
        return getWorkoutsByType("explore");
    }



    private List<WorkoutRoutine> getWorkoutsByType(String workoutType) {
        List<WorkoutRoutine> workouts = new ArrayList<>();
        Cursor cursor = null;
        try {
            String selection = "type = ?";
            String[] selectionArgs = { workoutType };
            cursor = db.query("workoutRoutines", null, selection, selectionArgs, null, null, null);

            int titleIndex = cursor.getColumnIndex("title");
            int ratingIndex = cursor.getColumnIndex("rating");
            int durationIndex = cursor.getColumnIndex("duration");
            int typeIndex = cursor.getColumnIndex("type");

            if (cursor.moveToFirst()) {
                do {
                    workouts.add(new WorkoutRoutine(
                            cursor.getString(titleIndex),
                            cursor.getString(ratingIndex),
                            cursor.getString(durationIndex),
                            cursor.getString(typeIndex)
                    ));
                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
        return workouts;
    }


    // Future implementation: Add methods for updating and deleting workouts as needed.
}
