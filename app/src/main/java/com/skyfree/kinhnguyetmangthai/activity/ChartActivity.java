package com.skyfree.kinhnguyetmangthai.activity;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.LimitLine;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.listener.ChartTouchListener;
import com.github.mikephil.charting.listener.OnChartGestureListener;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.skyfree.kinhnguyetmangthai.R;
import com.skyfree.kinhnguyetmangthai.custom_interface.IMyDateSetListener;
import com.skyfree.kinhnguyetmangthai.model.NoteObj;
import com.skyfree.kinhnguyetmangthai.model.RealmDrug;
import com.skyfree.kinhnguyetmangthai.model.RealmMood;
import com.skyfree.kinhnguyetmangthai.model.RealmSymptom;
import com.skyfree.kinhnguyetmangthai.utils.Utils;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmResults;

public class ChartActivity extends AppCompatActivity implements View.OnClickListener {

    Realm realm;
    private LineChart mChart;
    private ImageView mImgBack, mImgAdd;
    private Spinner mSpinner;

    private final String WEIGHT = "WEIGHT";
    private final String TEMPERATURE = "TEMPERATURE";
    private String CHOOSE = WEIGHT;

    private Calendar mCa = Calendar.getInstance();
    private Calendar mCaNow = Calendar.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chart);
        initView();
        addEvent();
    }

    private void initView(){
        mChart = (LineChart) findViewById(R.id.linechart);
        mImgBack = (ImageView) findViewById(R.id.img_back_chart);
        mImgAdd = (ImageView) findViewById(R.id.img_add_chart);
        mSpinner = (Spinner) findViewById(R.id.spinner_wei_or_temp);
        mImgBack.setOnClickListener(this);
        mImgAdd.setOnClickListener(this);

        List<String> list = new ArrayList<>();
        list.add(getString(R.string.weight));
        list.add(getString(R.string.temperature));

        ArrayAdapter<String> adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item,list);
        adapter.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
        mSpinner.setAdapter(adapter);
        mSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(i == 0){
                    CHOOSE = WEIGHT;
                    addEvent();
                }else if(i == 1){
                    CHOOSE = TEMPERATURE;
                    addEvent();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void addEvent(){
        realm = Realm.getDefaultInstance();
        RealmResults<NoteObj> mListNote = Utils.getAllNoteObj(realm);
        mListNote = mListNote.sort("mTimeMili");

        ArrayList<Entry> entries = new ArrayList<>();
        ArrayList<String> labels = new ArrayList<String>();

        if(CHOOSE.equals(WEIGHT)){
            entries = new ArrayList<>();
            for(int i = 0; i<mListNote.size(); i++){
                if(mListNote.get(i).getmNoteWeight() != 0){
                    entries.add(new Entry(mListNote.get(i).getmNoteWeight(), i));
                    labels.add(i+"");
                }
            }

            LineDataSet dataset = new LineDataSet(entries, getString(R.string.weight));
            dataset.setColors(ColorTemplate.VORDIPLOM_COLORS);
            LineData data = new LineData(labels, dataset);
            data.setHighlightEnabled(true);
            data.setValueTextColor(Color.RED);
            data.setValueTextSize(12f);
            mChart.setData(data);
            mChart.removeAllViews();
            mChart.invalidate();
            mChart.setDescription(getString(R.string.weight_chart));
            mChart.setBorderColor(Color.YELLOW);
            mChart.setNoDataText(getString(R.string.no_date_exist));
            mChart.setAutoScaleMinMaxEnabled(true);
            mChart.animateXY(500, 500);

            LimitLine upper_limit = new LimitLine(120f, getString(R.string.upper_limit));
            upper_limit.setLineWidth(4f);
            upper_limit.enableDashedLine(10f, 10f, 0f);
            upper_limit.setLabelPosition(LimitLine.LimitLabelPosition.RIGHT_TOP);
            upper_limit.setTextSize(10f);

            LimitLine lower_limit = new LimitLine(30f, getString(R.string.lower_limit));
            lower_limit.setLineWidth(4f);
            lower_limit.enableDashedLine(10f, 10f, 0f);
            lower_limit.setLabelPosition(LimitLine.LimitLabelPosition.RIGHT_BOTTOM);
            lower_limit.setTextSize(10f);

            YAxis leftAxis = mChart.getAxisLeft();
            leftAxis.removeAllLimitLines();
            leftAxis.addLimitLine(upper_limit);
            leftAxis.addLimitLine(lower_limit);
            leftAxis.setAxisMaxValue(125f);
            leftAxis.setAxisMinValue(0f);
            leftAxis.enableGridDashedLine(10f, 10f, 0f);
            leftAxis.setDrawZeroLine(false);
            leftAxis.setDrawLimitLinesBehindData(true);

            mChart.getAxisRight().setEnabled(false);

        }

        if(CHOOSE.equals(TEMPERATURE)){
            entries = new ArrayList<>();
            for(int i = 0; i<mListNote.size(); i++){
                if(mListNote.get(i).getmNoteTemperature() != 0){
                    entries.add(new Entry(mListNote.get(i).getmNoteTemperature(), i));
                    labels.add(i+"");
                }
            }
            for(int i= 0;i< entries.size();i++){
                entries.get(i).setXIndex(i);
            }


            LineDataSet dataset = new LineDataSet(entries, getString(R.string.temperature));
            dataset.setColors(ColorTemplate.VORDIPLOM_COLORS);
            LineData data = new LineData(labels, dataset);
            data.setHighlightEnabled(true);
            data.setValueTextColor(Color.RED);
            data.setValueTextSize(12f);
            mChart.setData(data);
            mChart.removeAllViews();
            mChart.invalidate();
            mChart.setDescription(getString(R.string.weight_chart));
            mChart.setBorderColor(Color.YELLOW);
            mChart.setNoDataText(getString(R.string.no_date_exist));
            mChart.setAutoScaleMinMaxEnabled(true);
            mChart.animateXY(500, 500);

            LimitLine upper_limit = new LimitLine(45f, getString(R.string.upper_limit));
            upper_limit.setLineWidth(4f);
            upper_limit.enableDashedLine(10f, 10f, 0f);
            upper_limit.setLabelPosition(LimitLine.LimitLabelPosition.RIGHT_TOP);
            upper_limit.setTextSize(10f);

            LimitLine lower_limit = new LimitLine(30f, getString(R.string.lower_limit));
            lower_limit.setLineWidth(4f);
            lower_limit.enableDashedLine(10f, 10f, 0f);
            lower_limit.setLabelPosition(LimitLine.LimitLabelPosition.RIGHT_BOTTOM);
            lower_limit.setTextSize(10f);

            YAxis leftAxis = mChart.getAxisLeft();
            leftAxis.removeAllLimitLines();
            leftAxis.addLimitLine(upper_limit);
            leftAxis.addLimitLine(lower_limit);
            leftAxis.setAxisMaxValue(50f);
            leftAxis.setAxisMinValue(25f);
            leftAxis.enableGridDashedLine(10f, 10f, 0f);
            leftAxis.setDrawZeroLine(false);
            leftAxis.setDrawLimitLinesBehindData(true);

            mChart.getAxisRight().setEnabled(false);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.img_back_chart:
                finish();
                break;
            case R.id.img_add_chart:
                DatePickerDialog.OnDateSetListener callback = new DatePickerDialog.OnDateSetListener() {
                    //Sự kiện khi click vào nút Done trên Dialog
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                        mCa.set(year, month, day);
                        showAlertWeightOrTemperature();
                    }
                };

                DatePickerDialog pic=new DatePickerDialog(
                        ChartActivity.this,
                        callback, mCaNow.get(Calendar.YEAR), mCaNow.get(Calendar.MONTH), mCaNow.get(Calendar.DAY_OF_MONTH));
                pic.setTitle(getString(R.string.select_date));
                pic.show();
                break;
        }
    }

    private void showAlertWeightOrTemperature() {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_weight_temperature, null);
        dialogBuilder.setView(dialogView);

        TextView mTvWeight = (TextView) dialogView.findViewById(R.id.tv_weight_dialog_choose);
        TextView mTvTemperature = (TextView) dialogView.findViewById(R.id.tv_temperature_dialog_choose);

        final AlertDialog alertStartDialog = dialogBuilder.create();
        alertStartDialog.show();

        mTvWeight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chooseWeight();
                alertStartDialog.cancel();
            }
        });

        mTvTemperature.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chooseTemperature();
                alertStartDialog.cancel();

            }
        });
    }

    private void chooseWeight() {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_choose_weight, null);
        dialogBuilder.setView(dialogView);

        final EditText mEdtWeight = (EditText) dialogView.findViewById(R.id.edt_dialog_choose_weight);
        TextView mTvOk = (TextView) dialogView.findViewById(R.id.tv_ok_dialog_choose_weight);
        TextView mTvCancel = (TextView) dialogView.findViewById(R.id.tv_cancel_dialog_choose_weight);

        final AlertDialog alertStartDialog = dialogBuilder.create();
        alertStartDialog.show();

        mTvCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertStartDialog.cancel();
            }
        });

        mTvOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mEdtWeight.getText().toString().equals("")) {
                    if (Utils.checkNoteObjExistById(realm, mCa.get(Calendar.DAY_OF_MONTH) + "" + mCa.get(Calendar.MONTH) + "" + mCa.get(Calendar.YEAR))) {
                        Utils.updateWeight(realm, mCa.get(Calendar.DAY_OF_MONTH) + "" + mCa.get(Calendar.MONTH) + "" + mCa.get(Calendar.YEAR), 0);
                    } else {
                        Utils.insertNoteObj(realm, new NoteObj(mCa.get(Calendar.DAY_OF_MONTH) + "" + mCa.get(Calendar.MONTH) + "" + mCa.get(Calendar.YEAR),
                                mCa.getTimeInMillis(), 0, "", 0, 0, new RealmList<RealmDrug>(),
                                new RealmList<RealmSymptom>(), new RealmList<RealmMood>()));
                    }
                } else {
                    if (Utils.checkNoteObjExistById(realm, mCa.get(Calendar.DAY_OF_MONTH) + "" + mCa.get(Calendar.MONTH) + "" + mCa.get(Calendar.YEAR))) {
                        Utils.updateWeight(realm, mCa.get(Calendar.DAY_OF_MONTH) + "" + mCa.get(Calendar.MONTH) + "" + mCa.get(Calendar.YEAR), Float.parseFloat(mEdtWeight.getText().toString()));
                    } else {
                        Utils.insertNoteObj(realm, new NoteObj(mCa.get(Calendar.DAY_OF_MONTH) + "" + mCa.get(Calendar.MONTH) + "" + mCa.get(Calendar.YEAR),
                                mCa.getTimeInMillis(), 0, "", Float.parseFloat(mEdtWeight.getText().toString()), 0, new RealmList<RealmDrug>(),
                                new RealmList<RealmSymptom>(), new RealmList<RealmMood>()));
                    }
                }
                addEvent();
                alertStartDialog.cancel();
            }
        });
    }

    private void chooseTemperature() {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_choose_temperature, null);
        dialogBuilder.setView(dialogView);

        final EditText mEdtTemperature = (EditText) dialogView.findViewById(R.id.edt_dialog_choose_temperature);
        TextView mTvOk = (TextView) dialogView.findViewById(R.id.tv_ok_dialog_choose_temperature);
        TextView mTvCancel = (TextView) dialogView.findViewById(R.id.tv_cancel_dialog_choose_temperature);

        final AlertDialog alertStartDialog = dialogBuilder.create();
        alertStartDialog.show();

        mTvCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertStartDialog.cancel();
            }
        });

        mTvOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!mEdtTemperature.getText().toString().equals("")) {
                    if (Float.parseFloat(mEdtTemperature.getText().toString()) > 50 || Float.parseFloat(mEdtTemperature.getText().toString()) < 30) {
                        Toast.makeText(ChartActivity.this, getString(R.string.wrong_answer), Toast.LENGTH_SHORT).show();
                    }else {
                        if (Utils.checkNoteObjExistById(realm, mCa.get(Calendar.DAY_OF_MONTH) + "" + mCa.get(Calendar.MONTH) + "" + mCa.get(Calendar.YEAR))) {
                            Utils.updateTemperature(realm, mCa.get(Calendar.DAY_OF_MONTH) + "" + mCa.get(Calendar.MONTH) + "" + mCa.get(Calendar.YEAR), Float.parseFloat(mEdtTemperature.getText().toString()));
                        } else {
                            Utils.insertNoteObj(realm, new NoteObj(mCa.get(Calendar.DAY_OF_MONTH) + "" + mCa.get(Calendar.MONTH) + "" + mCa.get(Calendar.YEAR),
                                    mCa.getTimeInMillis(), 0, "", 0, Float.parseFloat(mEdtTemperature.getText().toString()), new RealmList<RealmDrug>(),
                                    new RealmList<RealmSymptom>(), new RealmList<RealmMood>()));
                        }
                    }

                }
                addEvent();
                alertStartDialog.cancel();
            }
        });
    }
}
