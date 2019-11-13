package com.example.workoutmanager.models;

public class Exercise {
    private String Name;
    private int sets;
    private int reps;
    private int intensity;

    public Exercise(String Name, int sets, int reps, int intensity){
         this.Name=Name;
         this.sets=sets;
         this.reps=reps;
         this.intensity=intensity;
    }
    public Exercise(){

    }
}
