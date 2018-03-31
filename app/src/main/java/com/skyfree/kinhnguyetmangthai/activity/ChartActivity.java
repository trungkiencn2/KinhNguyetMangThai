package com.skyfree.kinhnguyetmangthai.activity;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.numetriclabz.numandroidcharts.ChartData;
import com.numetriclabz.numandroidcharts.MultiLineChart;
import com.skyfree.kinhnguyetmangthai.R;
import com.skyfree.kinhnguyetmangthai.model.NoteObj;
import com.skyfree.kinhnguyetmangthai.utils.Utils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;

public class ChartActivity extends AppCompatActivity {

    private MultiLineChart mChart;

    Realm realm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chart);
        realm = Realm.getDefaultInstance();
        initView();
        addEvent();

    }

    private void addEvent() {

        RealmResults<NoteObj> mListNote = Utils.getAllNoteObj(realm);
        ArrayList<Float> mListWeight = new ArrayList<>();
        ArrayList<Float> mListTemperature = new ArrayList<>();
        for (int i = 0; i < mListNote.size(); i++) {
            if (mListNote.get(i).getmNoteWeight() != 0) {
                mListWeight.add(mListNote.get(i).getmNoteWeight());
            }
            if (mListNote.get(i).getmNoteTemperature() != 0) {
                mListTemperature.add(mListNote.get(i).getmNoteTemperature());
            }
        }

        List<ChartData> value1 = new ArrayList<>();
        value1.add(new ChartData(4f, 0.5f)); //values.add(new ChartData(y,x));
        value1.add(new ChartData(9f, 1f));
        value1.add(new ChartData(18f, 2f));
        value1.add(new ChartData(2f, 4f));
        value1.add(new ChartData(12f, 5f));
        value1.add(new ChartData(9f, 7f));
        List<ChartData> value2 = new ArrayList<>();
        value2.add(new ChartData(5f, 1f)); //values.add(new ChartData(y,x));
        value2.add(new ChartData(6f, 2f));
        value2.add(new ChartData(15f, 3f));
        value2.add(new ChartData(2f, 5f));
        value2.add(new ChartData(3f, 8f));
        List<ChartData> value3 = new ArrayList<>();
        value3.add(new ChartData(value1));
        value3.add(new ChartData(value2));
        mChart.setData(value3);
//        mChart.
    }

    private void initView() {
        mChart = (MultiLineChart) findViewById(R.id.chart);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        realm.close();
    }
}
