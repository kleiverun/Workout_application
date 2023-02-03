package com.example.workoutapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.workoutapplication.Database.DatabaseHelper;

public class Frontpage extends AppCompatActivity {
    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        databaseHelper = new DatabaseHelper(Frontpage.this);
        databaseHelper.getReadableDatabase();
    }

    public void changePage(View view) {
        Intent intent = new Intent(this, InputExercise.class);
        startActivity(intent);
    }

    public void toWorklist(View view) {
        Intent intent = new Intent(this, WorkoutList.class);
        startActivity(intent);
    }
}