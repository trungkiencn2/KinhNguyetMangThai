package com.skyfree.kinhnguyetmangthai.activity;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

//import com.github.mikephil.charting.charts.BarChart;
//import com.github.mikephil.charting.data.BarData;
//import com.github.mikephil.charting.data.BarDataSet;
//import com.github.mikephil.charting.data.BarEntry;
import com.skyfree.kinhnguyetmangthai.R;

import java.util.ArrayList;
import java.util.List;

public class ChartActivity extends AppCompatActivity {

//    private BarChart barChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chart);
        initView();
//        addEvent();
    }

    private void initView() {
//        barChart = (BarChart) findViewById(R.id.chart);
    }

//    private void addEvent() {
//        ArrayList<BarEntry> entries = new ArrayList<>();
//        entries.add(new BarEntry(4f, 0));
//        entries.add(new BarEntry(8f, 1));
//        entries.add(new BarEntry(6f, 2));
//        entries.add(new BarEntry(12f, 3));
//        entries.add(new BarEntry(18f, 4));
//        entries.add(new BarEntry(9f, 5));
//        BarDataSet dataset = new BarDataSet(entries, "Kinh nguyet");
//        dataset.setBarSpacePercent(50f);
//
//        //Định nghĩa nhãn trục X
//        ArrayList<String> labels = new ArrayList<>();
//        labels.add("January");
//        labels.add("February");
//        labels.add("March");
//        labels.add("April");
//        labels.add("May");
//        labels.add("June");
//        labels.add("July");
//        labels.add("August");
//        labels.add("September");
//        labels.add("October");
//        labels.add("November");
//        labels.add("December");
//
//        BarData data = new BarData(labels, dataset);
//        barChart.setData(data);
//
//        barChart.setDescription("Bieu do thu thai va rung trung");
//
//    }
}
