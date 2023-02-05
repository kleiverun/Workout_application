package com.example.workoutapplication;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.viewmodel.CreationExtras;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.workoutapplication.Database.DatabaseHelper;
import com.example.workoutapplication.ExceptionPack.EmptyInputException;
import com.example.workoutapplication.Model.Workout;

import java.sql.SQLException;
import java.util.EmptyStackException;

public class InputExercise extends AppCompatActivity {
    EditText edtName, edtWeight, edtSets,edtReps;
    Button btnAdd;
    RadioGroup rdoG;
    String  checkedRadio;
    Workout workout;
    DatabaseHelper dbHelper = new DatabaseHelper(this);
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_excercise);
        edtName = findViewById(R.id.edttxt_exercise);
        edtWeight = findViewById(R.id.edttxt_weight);
        edtReps = findViewById(R.id.edttxt_reps);
        edtSets = findViewById(R.id.edttxt_sets);
        rdoG = findViewById(R.id.rdoGroup);

        rdoG.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                RadioButton checkedRadioButton = (RadioButton) findViewById(checkedId);
                 checkedRadio = checkedRadioButton.getText().toString();
                Toast.makeText(getApplicationContext(), checkedRadio, Toast.LENGTH_SHORT).show();
            }
        });

        btnAdd = findViewById(R.id.btnAdd);

        btnAdd.setOnClickListener(view -> {
        try {
            if (isEmpty(edtName) || isEmpty(edtWeight) || isEmpty(edtReps) || isEmpty(edtSets) ){
                throw new EmptyInputException();
            }
            String name = String.valueOf(edtName.getText());
            double weight =Double.parseDouble(String.valueOf(edtWeight.getText()));
            int reps =Integer.parseInt(String.valueOf(edtReps.getText()));
            int sets =Integer.parseInt(String.valueOf(edtSets.getText()));
           //Instantiating the workout object
            workout = new Workout(name,reps,sets,weight, checkedRadio);

            //Add workout to the table
            if(dbHelper.addWorkout(workout)!=-1){
                Toast.makeText(this, "IT WORKS", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "IT DOES NOT WORK", Toast.LENGTH_SHORT).show();
                throw new SQLException();
            }
        } catch (SQLException e){
            Toast.makeText(InputExercise.this, Log.d("!!!", e.toString()), Toast.LENGTH_SHORT).show();
        }
        catch (EmptyInputException e){
            Toast.makeText(InputExercise.this, "Du må fylle inn alt",Toast.LENGTH_SHORT).show();
        }
        });


    }
    private boolean isEmpty(EditText etText) {
        if (etText.getText().toString().trim().length() > 0)
            return false;

        return true;
    }


}