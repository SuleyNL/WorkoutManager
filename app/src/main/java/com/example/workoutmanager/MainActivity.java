package com.example.workoutmanager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
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
        final Intent progressActivity = new Intent(this, ProgressActivity.class);


        progressButton = findViewById(R.id.progressButton);
        progressButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                progressButton.startAnimation(AnimationUtils.loadAnimation(context, R.anim.animatie));
                System.out.println("progress");
                startActivity(progressActivity);
            }
        });
        goalButton = findViewById(R.id.goalButton);
        goalButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                goalButton.startAnimation(AnimationUtils.loadAnimation(context, R.anim.animatie));
                System.out.println("goal");            }
        });
        weightButton = findViewById(R.id.weightButton);
        weightButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                weightButton.startAnimation(AnimationUtils.loadAnimation(context, R.anim.animatie));
                System.out.println("weight");
            }
        });

    }


}
