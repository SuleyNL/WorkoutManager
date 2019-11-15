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

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public int getSets() {
        return sets;
    }

    public void setSets(int sets) {
        this.sets = sets;
    }

    public int getReps() {
        return reps;
    }

    public void setReps(int reps) {
        this.reps = reps;
    }

    public int getIntensity() {
        return intensity;
    }

    public void setIntensity(int intensity) {
        this.intensity = intensity;
    }
}
