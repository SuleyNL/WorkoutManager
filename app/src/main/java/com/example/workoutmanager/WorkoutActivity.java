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
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.NoSuchElementException;

public class WorkoutActivity extends AppCompatActivity {
    public LinkedHashMap<String, Workout> workoutHashMap = new LinkedHashMap<>();
    public int TotalWorkouts = 4;
    public String currentWorkout;
    public HashMap<String, Object> databaseHashMap = new HashMap<>();

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

        for (String key : workoutHashMap.keySet()) {
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
        Log.d("thicc", "TE]ESTESTSETSETESTSET");
        getAllWorkouts();
        Log.d("thicc", databaseHashMap.toString());
        Log.d("thicc", "TESTESTESTESTSETESTESTESTSETSETESTSET");

    }

    public int getTotalWorkouts() {
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

    public String getWorkout(int WorkoutId) {
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

    public String getWorkout(String WorkoutName) {
        final String Name = String.valueOf(WorkoutName);
        DocumentReference docRef = db.collection("Users").document("Pxq8xzmSBjhNH7wGFl20").collection("Workouts").document("Workouts").collection(Name).document("Info");
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        Log.d("thicc", "Yes such document " + Name + " " + document.getData().get("Bench").toString());
                        currentWorkout = document.getData().get("Bench").toString();
                    } else {
                        Log.d("nothicc", "error " + Name);
                    }
                } else {
                    Log.d("nothicc", "get failed with ", task.getException());
                }
            }
        });
        return currentWorkout;
    }

    public HashMap<String, Object> getAllWorkouts() {
        final DocumentReference docRef = db.collection("Users").document("Pxq8xzmSBjhNH7wGFl20").collection("Workouts").document("Workouts");
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        Map<String, Object> map = document.getData();
                        HashMap<String, Object> tempMap = new HashMap<>();
                        for (Map.Entry<String, Object> entry : map.entrySet()) {
                            //Log.d("thicc", "element : " + entry.getKey() + "/" + entry.getValue());
                            //Log.d("thicc", "document.getdata() : " + document.getData().toString());

                            Gson gson = new Gson();
                            Workout workout1 = gson.fromJson(entry.getValue().toString(), Workout.class);

                            Gson g = new Gson();
                            String jasonStr = g.toJson(workout1);

                            tempMap.put(workout1.getNaam(), jasonStr);
                            //Log.d("thicc", "hashmap : " + tempMap);

                            //System.out.println(entry.getKey() + "/" + entry.getValue());
                        }
                        setDatabaseHashMap(tempMap);

                    } else {
                        Log.d("nothicc", "No such document");
                    }
                } else {
                    Log.d("nothicc", "get failed with ", task.getException());
                }
            }
        });
        //Log.d("thicc", "de databaseHashMap ervoor is : " + databaseHashMap);

        return null;
    }




    public void addWorkout(Workout workout) {

        Gson g = new Gson();
        String jasonStr = g.toJson(workout);
        DocumentReference docRef = db.collection("Users").document("Pxq8xzmSBjhNH7wGFl20").collection("Workouts").document("Workouts");
        databaseHashMap.put(workout.getNaam(), jasonStr);
        docRef.set(databaseHashMap);
        Log.d("thicc", "de databaseHashMap erna is : " + databaseHashMap);


//        HashMap<String, Exercise> workoutHashMap = new HashMap<>();
//        if (!workout.getExercises().isEmpty()) {
//            for (Exercise item : workout.getExercises()) {
//                workoutHashMap.put(item.getName(), item);
//            }
//        }
//        docRef.set(workoutHashMap);


     }


    public void setDatabaseHashMap(HashMap<String, Object> databaseHashMap) {
        this.databaseHashMap = databaseHashMap;
        Log.d("thicc", databaseHashMap.toString());
        Workout water = new Workout("GEKKESULEY");
        Exercise hangboardSmoll = new Exercise("moonboard", 3, 5,10 );
        ArrayList<Exercise> q = new ArrayList<Exercise>();
        q.add(hangboardSmoll);
        water.setExercises(q);
        addWorkout(water);
    }
}
