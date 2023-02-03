package com.example.workoutapplication.Model;

public class Workout {
    
    private String exerciseName;
    private int reps ;
    private int sets ;
    private double weight ;
    private String bodypart ;

    public Workout(String exerciseName, int reps, int sets, double weight, String bodypart) {
        this.exerciseName = exerciseName;
        this.reps = reps;
        this.sets = sets;
        this.weight = weight;
        this.bodypart = bodypart;
    }


    @Override
    public String toString() {
        return "Workout{" +
                "exerciseName='" + exerciseName + '\'' +
                ", reps=" + reps +
                ", sets=" + sets +
                ", weight=" + weight +
                ", bodypart='" + bodypart + '\'' +
                '}';
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
