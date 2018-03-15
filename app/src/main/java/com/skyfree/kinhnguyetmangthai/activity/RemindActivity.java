package com.skyfree.kinhnguyetmangthai.activity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.icu.util.Calendar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.skyfree.kinhnguyetmangthai.R;
import com.skyfree.kinhnguyetmangthai.receiver.AlarmNotifyReceiver;
import com.skyfree.kinhnguyetmangthai.utils.Utils;

import java.io.File;

public class RemindActivity extends AppCompatActivity implements View.OnClickListener {

    private Calendar mCaNow = Calendar.getInstance();

    private Switch mSwReportCycle, mSwReportPregnancy, mSwReportOvulation;
    private TextView mTvReportCycle, mTvReportEasyToConceive, mTvReportOvulation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remind);
        initView();
        addEvent();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(Utils.readFromFile(Utils.FILE_REPORT_CYCLE, this).equals(Utils.TRUE)){
            mSwReportCycle.setChecked(true);
        }
        if (Utils.readFromFile(Utils.FILE_REPORT_EASY_TO_CONCEIVE, this).equals(Utils.TRUE)){
            mSwReportPregnancy.setChecked(true);
        }
        if(Utils.readFromFile(Utils.FILE_REPORT_SO_NGAY_GIAI_DOAN_HOANG_THE, this).equals(Utils.TRUE)){
            mSwReportOvulation.setChecked(true);
        }
    }

    private void initView() {
        mSwReportCycle = (Switch) findViewById(R.id.sw_report_cycle_remind);
        mSwReportPregnancy = (Switch) findViewById(R.id.sw_report_pregnancy_remind);
        mSwReportOvulation = (Switch) findViewById(R.id.sw_report_ovulation_remind);
        mTvReportCycle = (TextView) findViewById(R.id.tv_report_cycle_remind);
        mTvReportEasyToConceive = (TextView) findViewById(R.id.tv_report_pregnancy_remind);
        mTvReportOvulation = (TextView) findViewById(R.id.tv_report_ovulation_remind);

        mSwReportCycle.setOnClickListener(this);
        mSwReportPregnancy.setOnClickListener(this);
        mSwReportOvulation.setOnClickListener(this);

    }

    private void addEvent() {

        long a = Long.parseLong(Utils.readFromFile(Utils.FILE_NGAY_BAT_DAU_CHU_KY_KINH_NGUYET, this));
        long b = Long.parseLong(Utils.readFromFile(Utils.FILE_CHU_KY_KINH_NGUYET, this));
        long c = Long.parseLong(Utils.readFromFile(Utils.FILE_REPORT_SO_NGAY_GIAI_DOAN_HOANG_THE, this));

        mTvReportCycle.setText(getString(R.string.next_cycle) + " - "
                + (a + b * Utils.mOneDay - mCaNow.getTimeInMillis()) / Utils.mOneDay
                + " " + getString(R.string.days_left));

        mTvReportEasyToConceive.setText(getString(R.string.easy_to_conceive) + " - "
                + (a + Utils.getDayLeftToDateEasyToConceive((int) b) * Utils.mOneDay - mCaNow.getTimeInMillis()) / Utils.mOneDay
                + " " + getString(R.string.days_left));

        mTvReportOvulation.setText(getString(R.string.next_ovulation) + " - "
                + (b - c)
                + " " + getString(R.string.days_left));

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.sw_report_cycle_remind:
                if (mSwReportCycle.isChecked()) {
                    Utils.writeToFile(Utils.TRUE, Utils.FILE_REPORT_CYCLE, this);
                } else {
                    Utils.writeToFile(Utils.FALSE, Utils.FILE_REPORT_CYCLE, this);
                }
                startAlarm();
                break;
            case R.id.sw_report_pregnancy_remind:
                if (mSwReportPregnancy.isChecked()) {
                    Utils.writeToFile(Utils.TRUE, Utils.FILE_REPORT_EASY_TO_CONCEIVE, this);
                } else {
                    Utils.writeToFile(Utils.FALSE, Utils.FILE_REPORT_EASY_TO_CONCEIVE, this);
                }
                startAlarm();
                break;
            case R.id.sw_report_ovulation_remind:
                if (mSwReportOvulation.isChecked()) {
                    Utils.writeToFile(Utils.TRUE, Utils.FILE_REPORT_SO_NGAY_GIAI_DOAN_HOANG_THE, this);
                } else {
                    Utils.writeToFile(Utils.FALSE, Utils.FILE_REPORT_SO_NGAY_GIAI_DOAN_HOANG_THE, this);
                }
                startAlarm();
                break;
        }
    }



    private void startAlarm() {
        AlarmManager manager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent myIntent;
        PendingIntent pendingIntent;
        myIntent = new Intent(RemindActivity.this, AlarmNotifyReceiver.class);
        pendingIntent = PendingIntent.getBroadcast(this, 0, myIntent, PendingIntent.FLAG_CANCEL_CURRENT);
        manager.setRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + 90000, Utils.mOneDay, pendingIntent);

        if(!mSwReportCycle.isChecked() && !mSwReportPregnancy.isChecked() && !mSwReportOvulation.isChecked()){
            manager.cancel(pendingIntent);
        }
    }
}
