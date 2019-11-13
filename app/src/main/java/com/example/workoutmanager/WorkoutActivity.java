package com.example.workoutmanager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.workoutmanager.models.*;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.database.*;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.gson.Gson;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class WorkoutActivity extends AppCompatActivity {
    public LinkedHashMap<String, Workout> workoutHashMap = new LinkedHashMap<>();
    public int TotalWorkouts = 4;
    public String currentWorkout;
    FirebaseFirestore db = FirebaseFirestore.getInstance();


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


//        DocumentReference docRef = db.collection("Users").document("Pxq8xzmSBjhNH7wGFl20").collection("Workouts").document("Workouts").collection("Dinsdag").document("Info");
//        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
//            @Override
//            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
//                if (task.isSuccessful()) {
//                    DocumentSnapshot document = task.getResult();
//                    if (document.exists()) {
//                        Log.d("thicc", "DocumentSnapshot data: " + document.getData());
//                    } else {
//                        Log.d("thicc", "No such document");
//                    }
//                } else {
//                    Log.d("tag", "get failed with ", task.getException());
//                }
//            }
//        });
        final ArrayList<String> workoutArrayList = new ArrayList<>();

//        int i = 0;
//        Log.d("thicc", "workout start?" + getTotalWorkouts());
//        while(i<TotalWorkouts){
//            Log.d("thicc", "workout start:");
//            Log.d("thicc", "workout:" + getWorkout(i));
//            getTotalWorkouts();
//            i++;
//        }

        for(String key :workoutHashMap.keySet()){
            System.out.println(key);
            workoutArrayList.add(key);
        }



        // INLADEN ALLE WORKOUTS
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

        Log.d("thicc", "getWorkout(\"Maandag\") " +getWorkout("Maandag"));
//        Log.d("thicc", "getWorkout(1)" +getWorkout(1));
//        Log.d("thicc", "getWorkout(getWorkout(1))" +getWorkout(getWorkout(1)));
        addWorkout(new Workout("vrijdag"));
        addWorkout(new Workout("woensdag"));
        addWorkout(new Workout("donderdag"));
        addWorkout(new Workout("maandag"));


    }

    public int getTotalWorkouts(){
        DocumentReference docRef = db.collection("Users").document("Pxq8xzmSBjhNH7wGFl20").collection("Workouts").document("Workouts");
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        TotalWorkouts = Integer.parseInt(document.getData().get("TotalWorkouts").toString());
                    } else {
                        Log.d("nothicc", "No such document");
                    }
                } else {
                    Log.d("nothicc", "get failed with ", task.getException());
                }
            }
        });
        return TotalWorkouts;
    }

    public String getWorkout(int WorkoutId){
        final String WorkoutID = String.valueOf(WorkoutId);
        DocumentReference docRef = db.collection("Users").document("Pxq8xzmSBjhNH7wGFl20").collection("Workouts").document("Workouts");
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        currentWorkout = document.getData().get(WorkoutID).toString();
                    } else {
                        Log.d("nothicc", "No such document");
                    }
                } else {
                    Log.d("nothicc", "get failed with ", task.getException());
                }
            }
        });
        return currentWorkout;
    }

    public String getWorkout(String WorkoutName){
        final String Name = String.valueOf(WorkoutName);
        DocumentReference docRef = db.collection("Users").document("Pxq8xzmSBjhNH7wGFl20").collection("Workouts").document("Workouts").collection(Name).document("Info");
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        Log.d("thicc", "Yes such document " + Name +" "+document.getData().get("Bench").toString());
                        currentWorkout = document.getData().get("Bench").toString();
                    } else {
                        Log.d("nothicc", "No such document" + Name);
                    }
                } else {
                    Log.d("nothicc", "get failed with ", task.getException());
                }
            }
        });
        return currentWorkout;
    }

    public void addWorkout(Workout workout){
        Gson g = new Gson();
        String jasonStr = g.toJson(workout);
        DocumentReference docRef = db.collection("Users").document("Pxq8xzmSBjhNH7wGFl20").collection("Workouts").document("Workouts");
        HashMap input = new HashMap<String, Object>();
        input.put(workout.getNaam(), jasonStr);
        docRef.set(workout);
     }

}
