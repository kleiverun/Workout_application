package com.example.workoutapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.workoutapplication.Database.DatabaseHelper;
import com.example.workoutapplication.Model.Workout;

import java.util.ArrayList;
import java.util.List;

public class WorkoutList extends AppCompatActivity {
    ArrayAdapter arrayAdapter;
    DatabaseHelper databaseHelper;
    ListView workoutLv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout_list);
        workoutLv = findViewById(R.id.workoutLv);
        databaseHelper = new DatabaseHelper(this);
       showWorkoutsOnListView();
        List<Workout> list;
       list = databaseHelper.getAllWorkout();

        for (Workout w:
             list) {
            arrayAdapter.add(w.toString());
        }
    }

    public void showWorkoutsOnListView(){
        arrayAdapter = new ArrayAdapter<Workout>(WorkoutList.this, android.R.layout.simple_list_item_1);
        workoutLv.setAdapter(arrayAdapter);
    }

}