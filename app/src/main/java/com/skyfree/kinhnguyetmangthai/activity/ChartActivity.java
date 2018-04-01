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
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.numetriclabz.numandroidcharts.ChartData;
import com.numetriclabz.numandroidcharts.MultiLineChart;
import com.skyfree.kinhnguyetmangthai.R;
import com.skyfree.kinhnguyetmangthai.base.BaseDatePicker;
import com.skyfree.kinhnguyetmangthai.custom_interface.IMyDateSetListener;
import com.skyfree.kinhnguyetmangthai.model.NoteObj;
import com.skyfree.kinhnguyetmangthai.model.RealmDrug;
import com.skyfree.kinhnguyetmangthai.model.RealmMood;
import com.skyfree.kinhnguyetmangthai.model.RealmSymptom;
import com.skyfree.kinhnguyetmangthai.utils.Utils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmResults;

public class ChartActivity extends AppCompatActivity implements View.OnClickListener {

    private MultiLineChart mChart;
    private ImageView mImgBack, mImgAdd;

    private Realm realm;
    private String mId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chart);
        realm = Realm.getDefaultInstance();
        initView();


    }

    @Override
    protected void onResume() {
        super.onResume();
        addEvent();
    }

    private float mXStart = 1f;
    private float mXStartTem = 1f;

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

            List<ChartData> mValueWeight = new ArrayList<>();
            for (int i = 0; i < mListWeight.size(); i++) {
                mValueWeight.add(new ChartData(mListWeight.get(i), mXStart));
                mXStart += 1f;
            }

            List<ChartData> mValueTemperature = new ArrayList<>();
            for (int i = 0; i < mListTemperature.size(); i++) {
                mValueTemperature.add(new ChartData(mListTemperature.get(i), mXStartTem));
                mXStartTem += 1f;
            }

            List<String> legends = new ArrayList<>();
            List<ChartData> value3 = new ArrayList<>();
            if (mValueWeight.size() >= 2) {
                value3.add(new ChartData(mValueWeight));
                legends.add(getString(R.string.weight));
            }
            if (mValueTemperature.size() >= 2) {
                value3.add(new ChartData(mValueTemperature));
                legends.add(getString(R.string.temperature));
            }
            mChart.setData(value3);
            mChart.setLegends(legends);
        }

    }

    private void initView() {
        mChart = (MultiLineChart) findViewById(R.id.chart);
        mImgBack = (ImageView) findViewById(R.id.img_back_chart);
        mImgAdd = (ImageView) findViewById(R.id.img_add_chart);
        mImgBack.setOnClickListener(this);
        mImgAdd.setOnClickListener(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        realm.close();
    }

    private Calendar mCaNow = Calendar.getInstance();
    private Calendar mCa = Calendar.getInstance();

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
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
                pic.setTitle("Chọn ngày hoàn thành");
                pic.show();

//                DatePickerDialog mDatePicker = new DatePickerDialog(this,
//                        new DatePickerDialog.OnDateSetListener() {
//                            public void onDateSet(DatePicker datepicker, int year, int selectedmonth, int dayOfMonth) {
//                                int month = selectedmonth + 1;
//                                mCa.setTimeInMillis(System.currentTimeMillis()); //refresh to now
//                                mCa.set(Calendar.YEAR, year);
//                                mCa.set(Calendar.MONTH, selectedmonth);
//                                mCa.set(Calendar.DAY_OF_MONTH, dayOfMonth);
//                            }
//                        }, mCaNow.get(Calendar.YEAR), mCaNow.get(Calendar.MONTH), mCaNow.get(Calendar.DAY_OF_MONTH));
//                mDatePicker.getDatePicker().setMaxDate(mCaNow.getTimeInMillis() + 2*365*Utils.mOneDay);
//                mDatePicker.getDatePicker().setMinDate(mCaNow.getTimeInMillis() - 2*365*Utils.mOneDay);
//                mDatePicker.setTitle(getString(R.string.select_date));
//                mDatePicker.show();
//
//                mDatePicker.setButton(DialogInterface.BUTTON1, "Btn 1", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        showAlertWeightOrTemperature();
//                    }
//                });

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
