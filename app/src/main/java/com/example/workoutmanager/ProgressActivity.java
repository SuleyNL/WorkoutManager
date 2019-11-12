package com.example.workoutmanager;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;

import java.util.ArrayList;

public class ProgressActivity extends AppCompatActivity {
    private RelativeLayout MainLayout;
    private PieChart volumeChart;
    private int[] yData = {5,10,15,31,42};
    private String[] xData = {"Quads","Hamstrings","Chest","Lats","Biceps"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progress);
        MainLayout = (RelativeLayout) findViewById(R.id.mainLayout);
        volumeChart = new PieChart(this);
        MainLayout.addView(volumeChart);
        volumeChart.setUsePercentValues(true);
        volumeChart.setDescription("Total Volume per muscle");

        volumeChart.setDrawHoleEnabled(true);
        volumeChart.setTransparentCircleRadius(10);

        volumeChart.setRotationAngle(0);
        volumeChart.setRotationEnabled(true);

       addVolumeChartData();

    }
    private void addVolumeChartData(){
        ArrayList<PieEntry> yValue = new ArrayList<>();
        for (int i =0; i<yData.length; i++){
            yValue.add(new PieEntry(yData[i], i));
        }
        ArrayList<String> xValue = new ArrayList<>();
        for (int i =0; i<xData.length; i++){
            xValue.add(xData[i]);
        }

        PieDataSet dataSet = new PieDataSet(yValue, "Volume");

    }
}
