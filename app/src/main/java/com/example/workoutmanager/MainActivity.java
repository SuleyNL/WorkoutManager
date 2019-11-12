package com.example.workoutmanager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {
    private ImageButton progressButton;
    private ImageButton goalButton;
    private ImageButton weightButton;
    Context context = this;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressButton = findViewById(R.id.progressButton);
        progressButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                progressButton.startAnimation(AnimationUtils.loadAnimation(context, R.anim.animatie));
                System.out.println("progress");
                navigateToProgress();
            }
        });
        goalButton = findViewById(R.id.goalButton);
        goalButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                goalButton.startAnimation(AnimationUtils.loadAnimation(context, R.anim.animatie));
                System.out.println("goal");
                navigateToGoal();
            }
        });
        weightButton = findViewById(R.id.weightButton);
        weightButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                weightButton.startAnimation(AnimationUtils.loadAnimation(context, R.anim.animatie));
                System.out.println("weight");
                navigateToWorkout();
            }
        });

    }

    public void navigateToGoal(){
        Intent intent = new Intent(getApplicationContext(), GoalActivity.class);
        startActivity(intent);
    }

    public void navigateToProgress(){
        Intent intent = new Intent(getApplicationContext(), ProgressActivity.class);
        startActivity(intent);
    }

    public void navigateToWorkout(){
        Intent intent = new Intent(getApplicationContext(), WorkoutActivity.class);
        startActivity(intent);
    }




}
