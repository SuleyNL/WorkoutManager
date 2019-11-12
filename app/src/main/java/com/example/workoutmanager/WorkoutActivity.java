package com.example.workoutmanager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.workoutmanager.models.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

public class WorkoutActivity extends AppCompatActivity {
    public LinkedHashMap<String, Workout> workoutHashMap = new LinkedHashMap<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout);
        Workout maandag = new Workout("Maandag");
        Workout dinsdag = new Workout("Dinsdag");
        Workout woensdag = new Workout("Woensdag");
        Workout donderdag = new Workout("Donderdag");
        Workout vrijdag = new Workout("Vrijdag");
        Workout zaterdag = new Workout("Zaterdag");
        Workout zondag = new Workout("Zondag");
        workoutHashMap.put(maandag.getNaam(), maandag);
        workoutHashMap.put(dinsdag.getNaam(), dinsdag);
        workoutHashMap.put(woensdag.getNaam(), woensdag);
        workoutHashMap.put(donderdag.getNaam(), donderdag);
        workoutHashMap.put(vrijdag.getNaam(), vrijdag);
        workoutHashMap.put(zaterdag.getNaam(), zaterdag);
        workoutHashMap.put(zondag.getNaam(), zondag);
        final ArrayList<String> workoutArrayList = new ArrayList<>();
        for(String key :workoutHashMap.keySet()){
            System.out.println(key);
            workoutArrayList.add(key);
        }



        // INLADEN ALLE TRIPS
        final ListView list = findViewById(R.id.listView);
        final ArrayAdapter<String> itemsAdapter =
                new ArrayAdapter<String>(this, R.layout.text_view, workoutArrayList);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getApplicationContext(), SingleWorkoutActivity.class);
                intent.putExtra("workout", list.getItemAtPosition(i).toString());
                startActivity(intent);

            }
        });

        list.setAdapter(itemsAdapter);
        }
    public LinkedHashMap<String, Workout> getWorkoutHashMap() {
        return workoutHashMap;
    }

    public void setWorkoutHashMap(LinkedHashMap<String, Workout> workoutHashMap) {
        this.workoutHashMap = workoutHashMap;
    }
}
