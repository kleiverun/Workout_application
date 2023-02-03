package com.example.workoutapplication.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.workoutapplication.Model.Workout;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String table = "exercise_table";
    public static final String COLUMN_ID = "exerciseID";
    public static final String COLUMN_NAME = "excercisename";
    public static final String COLUMN_REPS = "reps";
    public static final String COLUMN_SETS = "sets";
    public static final String COLUMN_WEIGHT = "weight";
    public static final String COLUMN_DATE = "date";
    public static final String COLUMN_BODYPART = "bodypart";




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
                        COLUMN_BODYPART + " TEXT NOT NULL, " +
                        COLUMN_WEIGHT + " REAL NOT NULL, " +
                        COLUMN_DATE + " TEXT NOT NULL, " +
                "PRIMARY KEY("+ COLUMN_ID + " AUTOINCREMENT));";
            db.execSQL(createTableStatement);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
      // String sqlUpgrade = "DROP TABLE IF EXISTS " + table;


        // sqLiteDatabase.execSQL(sqlUpgrade);
    }
    public long addWorkout(Workout w){
    SQLiteDatabase db = this.getWritableDatabase();
    ContentValues cv = new ContentValues();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");
        LocalDateTime now = LocalDateTime.now();
        cv.put(COLUMN_NAME, w.getExerciseName());
        cv.put(COLUMN_REPS, w.getReps());
        cv.put(COLUMN_SETS,w.getSets());
        cv.put(COLUMN_WEIGHT,w.getWeight());
        cv.put(COLUMN_BODYPART, w.getBodypart());
        cv.put(COLUMN_DATE, dtf.format(now));
        return db.insert(table, null, cv);
    }
    public void getWorkout(){
        List<Workout> returnList = new ArrayList<>();

    }
}
