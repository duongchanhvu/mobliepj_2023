package com.example.quanlychitieu;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;
import java.util.List;


import java.util.ArrayList;

public class Report_chart extends AppCompatActivity {
    //Initialize variable
    barChart = findViewById(R.id.bar_chart);
    ArrayList<BarEntry> barEntries = new ArrayList<>();



// Retrieve data from Firebase
for (int i = 1; i < 10; i++) {
        //convert to float
        float value = (float) (i*10.0);
        //Initialize bar chart
        BarEntry barEntry = new BarEntry(i,value);
        PieEntry pieEntry = new PieEntry(i,value);
        //Add values in array list
        barEntries.add(barEntry);

    }
    //Initialize bar dataset
    BarDataSet barDataSet = new BarDataSet(barEntries,"Tien gi do");
    //set colors
        barDataSet.setColors(ColorTemplate.COLORFUL_COLORS);
    //Hide draw value
        barDataSet.setDrawValues(false);
    //Set bar data
        barChart.setData(new BarData(barDataSet));
    //set Animation
        barChart.animateY(5000);
    //set description test and color
        barChart.getDescription().setText("Tien gi do chart");
        barChart.getDescription().setTextColor(Color.BLUE);

    //Initialize pie dataset

}




