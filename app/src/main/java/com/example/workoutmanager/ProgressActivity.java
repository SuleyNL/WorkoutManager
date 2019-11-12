package com.example.workoutmanager;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;

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
        createVolumeChart();
        addVolumeChartData();


    }

    private void createVolumeChart(){
        volumeChart = new PieChart(this);
        MainLayout.addView(volumeChart);

        volumeChart.setDescription(null);
        volumeChart.setMinimumWidth(1000);
        volumeChart.setMinimumHeight(1000);
        volumeChart.setExtraOffsets(15, 10, 5, 5);

        volumeChart.setUsePercentValues(true);

        volumeChart.setCenterText("Volume");
        volumeChart.setCenterTextSize(20);
        volumeChart.setEntryLabelTextSize(16);
        volumeChart.setEntryLabelColor(Color.BLACK);

        volumeChart.setBackgroundColor(Color.parseColor("#ECFCFF"));
        volumeChart.setDrawHoleEnabled(true);
        volumeChart.setHoleColor(Color.parseColor("#ECFCFF"));
        volumeChart.setTransparentCircleRadius(100000);
        volumeChart.setHoleRadius(40);

        volumeChart.setRotationAngle(0);
        volumeChart.setRotationEnabled(true);

        volumeChart.getLegend().setEnabled(false);
    }
    private void addVolumeChartData(){
        ArrayList<PieEntry> yValue = new ArrayList<>();
        for (int i =0; i<yData.length; i++){
            yValue.add(new PieEntry(yData[i], xData[i]));
        }
//        ArrayList<String> xValue = new ArrayList<>();
//        for (int i =0; i<xData.length; i++){
//            xValue.add(xData[i]);
//        }

        PieDataSet dataSet = new PieDataSet(yValue, "");
        dataSet.setSliceSpace(3);
        dataSet.setSelectionShift(5);

        ArrayList<Integer> colors = new ArrayList<>();

        for(int o: ColorTemplate.VORDIPLOM_COLORS)
            colors.add(o);
        for(int o: ColorTemplate.JOYFUL_COLORS)
            colors.add(o);
        for(int o: ColorTemplate.COLORFUL_COLORS)
            colors.add(o);
        for(int o: ColorTemplate.LIBERTY_COLORS)
            colors.add(o);
        for(int o: ColorTemplate.PASTEL_COLORS)
            colors.add(o);
        colors.add(ColorTemplate.getHoloBlue());
        dataSet.setColors(colors);

        PieData data = new PieData();
        data.setDataSet(dataSet);
        data.setValueTextSize(15);
        volumeChart.setData(data);

        // undo all highlights
        volumeChart.highlightValues(null);

        volumeChart.invalidate();
    }
}
