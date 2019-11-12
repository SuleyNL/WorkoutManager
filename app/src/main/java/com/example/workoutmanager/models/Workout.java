package com.example.workoutmanager.models;

import java.util.ArrayList;

public class Workout {
    private String naam;
    private ArrayList<Exercise> exercises;

    public Workout(String naam){
        this.naam = naam;
    }


    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public ArrayList<Exercise> getExercises() {
        return exercises;
    }

    public void setExercises(ArrayList<Exercise> exercises) {
        this.exercises = exercises;
    }
}
