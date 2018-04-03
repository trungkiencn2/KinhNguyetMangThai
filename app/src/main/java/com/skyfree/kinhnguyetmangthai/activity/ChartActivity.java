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

import com.skyfree.kinhnguyetmangthai.R;
import com.skyfree.kinhnguyetmangthai.base.BaseDatePicker;
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
import lecho.lib.hellocharts.formatter.AxisValueFormatter;
import lecho.lib.hellocharts.model.Axis;
import lecho.lib.hellocharts.model.AxisValue;
import lecho.lib.hellocharts.model.Line;
import lecho.lib.hellocharts.model.LineChartData;
import lecho.lib.hellocharts.model.PointValue;
import lecho.lib.hellocharts.model.Viewport;
import lecho.lib.hellocharts.view.LineChartView;

public class ChartActivity extends AppCompatActivity implements View.OnClickListener {

    Realm realm;
    private LineChartView mChart;
    private ImageView mImgBack, mImgAdd;
    private Spinner mSpinner;

    private final String WEIGHT = "WEIGHT";
    private final String TEMPERATURE = "TEMPERATURE";
    private String CHOOSE = WEIGHT;

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
        if (mListNote.size() >= 2) {
            mListNote = mListNote.sort("mTimeMili");
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




            List<PointValue> values = new ArrayList<PointValue>();
            PointValue tempPointValue;

            Line line = new Line(values)
                    .setColor(Color.RED)
                    .setCubic(false)
                    .setHasPoints(true).setHasLabels(true);
            List<Line> lines = new ArrayList<Line>();
            lines.add(line);




            if(CHOOSE.equals(WEIGHT)){
                for (int i = 0; i < mListWeight.size(); i++) {
                    tempPointValue = new PointValue(i, mListWeight.get(i));
                    tempPointValue.setLabel(mListWeight.get(i) + " kg");
                    values.add(tempPointValue);
                }

                LineChartData data = new LineChartData();
                data.setLines(lines);

                List<AxisValue> axisValuesForY = new ArrayList<>();
                AxisValue tempAxisValue;
                for (int i = 0; i <= 120; i += 15){
                    tempAxisValue = new AxisValue(i);
                    tempAxisValue.setLabel(i+"");
                    axisValuesForY.add(tempAxisValue);
                }

                Axis yAxis = new Axis(axisValuesForY);
                yAxis.setHasLines(true);
                yAxis.setTextColor(Color.BLACK);
                yAxis.setName(getString(R.string.weight));
                data.setAxisYLeft(yAxis);

                mChart.setDrawingCacheBackgroundColor(Color.BLACK);
                mChart.setLineChartData(data);
            }else if(CHOOSE.equals(TEMPERATURE)){
                for(int i = 0; i<mListTemperature.size(); i++){
                    tempPointValue = new PointValue(i, mListTemperature.get(i));
                    tempPointValue.setLabel(mListTemperature.get(i) + " °C");
                    values.add(tempPointValue);
                }

                LineChartData data = new LineChartData();
                data.setLines(lines);

                List<AxisValue> axisValuesForY = new ArrayList<>();
                AxisValue tempAxisValue;
                for(int i = 0; i<= 40; i+= 5){
                    tempAxisValue = new AxisValue(i);
                    tempAxisValue.setLabel(i + "");
                    axisValuesForY.add(tempAxisValue);
                }

                Axis yAxis = new Axis(axisValuesForY);
                yAxis.setHasLines(true);
                yAxis.setTextColor(Color.BLACK);
                yAxis.setName(getString(R.string.temperature));
                data.setAxisYLeft(yAxis);

                mChart.setDrawingCacheBackgroundColor(Color.BLACK);
                mChart.setLineChartData(data);
            }

        }
    }

    private void initView(){
        mChart = (LineChartView) findViewById(R.id.chart);
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
                Toast.makeText(ChartActivity.this, "nothing", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private Calendar mCa = Calendar.getInstance();
    private Calendar mCaNow = Calendar.getInstance();

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
                Intent refresh = new Intent(getApplicationContext(), ChartActivity.class);
                finish();
                startActivity(refresh);
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
                if (mEdtTemperature.getText().toString().equals("")) {
                    if (Utils.checkNoteObjExistById(realm, mCa.get(Calendar.DAY_OF_MONTH) + "" + mCa.get(Calendar.MONTH) + "" + mCa.get(Calendar.YEAR))) {
                        Utils.updateTemperature(realm, mCa.get(Calendar.DAY_OF_MONTH) + "" + mCa.get(Calendar.MONTH) + "" + mCa.get(Calendar.YEAR), 0);
                    } else {
                        Utils.insertNoteObj(realm, new NoteObj(mCa.get(Calendar.DAY_OF_MONTH) + "" + mCa.get(Calendar.MONTH) + "" + mCa.get(Calendar.YEAR),
                                mCa.getTimeInMillis(), 0, "", 0, 0, new RealmList<RealmDrug>(),
                                new RealmList<RealmSymptom>(), new RealmList<RealmMood>()));
                    }
                } else {
                    if (Utils.checkNoteObjExistById(realm, mCa.get(Calendar.DAY_OF_MONTH) + "" + mCa.get(Calendar.MONTH) + "" + mCa.get(Calendar.YEAR))) {
                        Utils.updateTemperature(realm, mCa.get(Calendar.DAY_OF_MONTH) + "" + mCa.get(Calendar.MONTH) + "" + mCa.get(Calendar.YEAR), Float.parseFloat(mEdtTemperature.getText().toString()));
                    } else {
                        Utils.insertNoteObj(realm, new NoteObj(mCa.get(Calendar.DAY_OF_MONTH) + "" + mCa.get(Calendar.MONTH) + "" + mCa.get(Calendar.YEAR),
                                mCa.getTimeInMillis(), 0, "", 0, Float.parseFloat(mEdtTemperature.getText().toString()), new RealmList<RealmDrug>(),
                                new RealmList<RealmSymptom>(), new RealmList<RealmMood>()));
                    }
                }
                Intent refresh = new Intent(getApplicationContext(), ChartActivity.class);
                finish();
                startActivity(refresh);
                alertStartDialog.cancel();
            }
        });
    }
}
