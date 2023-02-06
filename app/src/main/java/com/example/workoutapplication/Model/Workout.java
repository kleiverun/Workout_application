package com.example.workoutapplication.Model;

import java.util.Date;

public class Workout {
    private int id;
    private String exerciseName;
    private int reps ;
    private int sets ;
    private double weight ;
    private String bodypart ;
    private String date;

    public Workout(String exerciseName, int reps, int sets, double weight, String bodypart) {


        this.exerciseName = exerciseName;
        this.reps = reps;
        this.sets = sets;
        this.weight = weight;
        this.bodypart = bodypart;
    }
    public Workout(int id, String exerciseName, int reps, int sets, double weight, String bodypart,String date) {
        this.id=id;
        this.exerciseName = exerciseName;
        this.reps = reps;
        this.sets = sets;
        this.weight = weight;
        this.bodypart = bodypart;
        this.date = date;
    }


    @Override
    public String toString() {
        return  exerciseName + " :" +
                 reps+"reps |" +
                sets +"sets |"+
                weight +" kg |"+
                bodypart
                ;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getExerciseName() {
        return exerciseName;
    }

    public void setExerciseName(String exerciseName) {
        this.exerciseName = exerciseName;
    }

    public int getReps() {
        return reps;
    }

    public void setReps(int reps) {
        this.reps = reps;
    }

    public int getSets() {
        return sets;
    }

    public void setSets(int sets) {
        this.sets = sets;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public String getBodypart() {
        return bodypart;
    }

    public void setBodypart(String bodypart) {
        this.bodypart = bodypart;
    }
}
