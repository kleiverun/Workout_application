package com.example.workoutapplication.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import com.example.workoutapplication.InputExercise;
import com.example.workoutapplication.Model.Workout;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    @RequiresApi(api = Build.VERSION_CODES.O)
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
    public List<Workout> getAllWorkout(){
        List<Workout> returnList = new ArrayList<>();
        String queryString = "SELECT * FROM "+table;
        SQLiteDatabase db = this.getReadableDatabase() ;
        Cursor cursor = db.rawQuery(queryString,null);

        if(cursor.moveToFirst()){
            //Loop through the (result set) and create new customer objects. Put them first into the return list.
            do {
                int wId = cursor.getInt(0);
                String wName = cursor.getString(1);
                int wReps = cursor.getInt(2);
                int wSets = cursor.getInt(3);
                String wBodypart = cursor.getString(4);
                double wWeight= cursor.getDouble(5);
                String wDate = cursor.getString(6);
                Workout newWorkout = new Workout(wId,wName,wReps,wSets,wWeight,wBodypart,wDate);
                returnList.add(newWorkout);

            } while (cursor.moveToNext());
        }
        else {

        }
        cursor.close();
        db.close();
        return returnList;
    }
    //Dele opp på dato og sortere inn i lister delt på datto
    public HashMap<String, List<String>> getData() {
        SQLiteDatabase db = this.getReadableDatabase();
        HashMap<String, List<String>> expandableListDetail = new HashMap<String, List<String>>();
        ArrayList<String> arrayList1= new ArrayList<>();
        List<String> workouts = new ArrayList<>();
        String queryString = "SELECT * FROM "+table + " ORDER BY date";
        ArrayList <String> arrayList = new ArrayList<>();
        Cursor cursor = db.rawQuery(queryString,null);
        ArrayList<ArrayList<String>> listOLists = new ArrayList<ArrayList<String>>();


        try{
        while (cursor.moveToNext()){

            String wDate = cursor.getString(6);
            //Splits the date
            String[] d = wDate.split(" ");
            //Gets the year,month and date.
            arrayList.add(d[0]);


            int wId = cursor.getInt(0);
            String wName = cursor.getString(1);
            int wReps = cursor.getInt(2);
            int wSets = cursor.getInt(3);
            String wBodypart = cursor.getString(4);
            double wWeight= cursor.getDouble(5);

            Workout newWorkout = new Workout(wId,wName,wReps,wSets,wWeight,wBodypart,wDate);
            arrayList1.add(newWorkout.toString());
            //d[0];
            Log.d("!!!",newWorkout.toString());


        }



            expandableListDetail.put("dd",arrayList1);


        } finally {
            cursor.close();
            db.close();
        }
        /*if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            expandableListDetail.forEach((k,v)-> Log.d("!!!","Key " + k + "value" +v));
        }
        */
        return expandableListDetail;
    }
    public ArrayList<String> allDates(){
        SQLiteDatabase db = this.getReadableDatabase();

        ArrayList <String> dates = new ArrayList<>();

        String query = "SELECT "+COLUMN_DATE+" from "+table + " GROUP BY "+COLUMN_DATE;
        Cursor cursor = db.rawQuery(query,null);
        try {
            while (cursor.moveToNext()) {
                String wDate = cursor.getString(0);
                String[] d = wDate.split(" ");
                dates.add(d[0]);
            }
        } finally{
            cursor.close();
            db.close();
        }
        return dates;
    }

}
