package com.example.workoutapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.workoutapplication.Database.DatabaseHelper;
import com.example.workoutapplication.Model.Workout;

public class InputExercise extends AppCompatActivity {
    EditText edtName, edtWeight, edtSets,edtReps;
    Button btnAdd;
    RadioGroup rdoG;
    String  checkedRadio;
    DatabaseHelper dbHelper;
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

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Workout workout;
            try {
                String name = String.valueOf(edtName.getText());
                double weight =Double.parseDouble(String.valueOf(edtWeight.getText()));
                int reps =Integer.parseInt(String.valueOf(edtReps.getText()));
                int sets =Integer.parseInt(String.valueOf(edtSets.getText()));
                 workout = new Workout(name,reps,sets,weight,checkedRadio);
            } catch (Exception e){
                Toast.makeText(InputExercise.this, "Exercise could not be made", Toast.LENGTH_SHORT).show();
                workout = new Workout("",-1,-1,-1,"error");
            }
                if (dbHelper.addWorkout(workout)==-1){
                    Toast.makeText(InputExercise.this, "SUCCESS", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(InputExercise.this, "NOT SUCCESS", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }
}