package com.example.workoutmanager;

import android.os.Bundle;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.workoutmanager.models.Workout;

import java.util.LinkedHashMap;

public class SingleWorkoutActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout);
        String workouts = getIntent().getStringExtra("workout");
        System.out.println(workouts);
        //LinkedHashMap<String, Workout> workoutss =

        //tring[] workout = {"Tricep extensions;   sets: 3;   reps: 12;   weight: 8/10/12", "Bicep curls;   sets: 3;   reps: 15;   weight: 15/20/22.5", "Incline dumbell press;   sets: 3;   reps: 8;  weight: 30/34/36", "maar", "even", "werken"};

        // INLADEN ALLE TRIPS
        final ListView list = findViewById(R.id.listView);
       // final ArrayAdapter<String> itemsAdapter =
               // new ArrayAdapter<String>(this, R.layout.activity_single_workout, workout);
       // list.setAdapter(itemsAdapter);
    }
}
