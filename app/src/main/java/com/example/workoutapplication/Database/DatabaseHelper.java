package com.example.workoutapplication.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.workoutapplication.Model.Workout;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String table = "exercise_table";
    private static final String COLUMN_ID = "exerciseID";
    private static final String COLUMN_NAME = "excercisename";
    private static final String COLUMN_REPS = "reps";
    private static final String COLUMN_SETS = "sets";
    private static final String COLUMN_WEIGHT = "weight";
    private static final String COLUMN_BODYPART = "bodypart";




    public DatabaseHelper(@Nullable Context context) {
        super(context, "workout.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableStatement =
                "CREATE TABLE " + table + " (" + COLUMN_ID + " INTEGER  NOT NULL, " +
                        COLUMN_NAME + " TEXT NOT NULL, " +
                COLUMN_REPS + " INTEGER NOT NULL, " +
                COLUMN_SETS + " INTEGER NOT NULL, " +
                COLUMN_WEIGHT + " INTEGER NOT NULL, " +
                COLUMN_BODYPART + " TEXT NOT NULL, " +
                "PRIMARY KEY("+ COLUMN_ID + " AUTOINCREMENT));";
            db.execSQL(createTableStatement);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
    public long addWorkout(Workout w){
    SQLiteDatabase db = this.getWritableDatabase();
    ContentValues cv = new ContentValues();
        cv.put(COLUMN_NAME,w.getExerciseName());
        cv.put(COLUMN_BODYPART,w.getBodypart());
        cv.put(COLUMN_REPS,w.getReps());
        cv.put(COLUMN_SETS, w.getSets());
        cv.put(COLUMN_WEIGHT,w.getWeight());
    long insert = db.insert(table, null, cv);
     return insert;
    }
}
