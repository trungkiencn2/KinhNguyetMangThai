package com.skyfree.kinhnguyetmangthai.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.icu.util.ChineseCalendar;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.skyfree.kinhnguyetmangthai.R;
import com.skyfree.kinhnguyetmangthai.base.BaseDatePicker;
import com.skyfree.kinhnguyetmangthai.custom_interface.IMyDateSetListener;
import com.skyfree.kinhnguyetmangthai.utils.Utils;
import com.skyfree.kinhnguyetmangthai.utils.VietCalendar;

import java.util.ArrayList;
import java.util.Calendar;

import io.realm.Realm;

public class MainActivity extends BaseDatePicker implements View.OnClickListener {

    private Calendar mCa = Calendar.getInstance();
    private Calendar mCaNow = Calendar.getInstance();
    private Calendar mCaNextCycle = Calendar.getInstance();
    private Calendar mCaEasyToConceive = Calendar.getInstance();
    private Calendar mCaDayleft = Calendar.getInstance();
    private Calendar mCaMinTimeForDatePicker = Calendar.getInstance();
    private ImageView mImgPet, mImgBgMessage;
    private LinearLayout mLinearSetting, mLinearDiary, mLinearCalendar, mLinearChart, mLinearNote;
    private TextView mTvDaysLeft, mTvNextCycle, mTvEasyToConceive, mTvEndCycle;

    private Realm realm;

    private Handler mHandler = new Handler();
    private Runnable mRotate = new Runnable() {
        @Override
        public void run() {
            mImgPet.setRotationY((float) (mImgPet.getRotationY() + 0.5));
            mTvEndCycle.setRotationY((float) (mTvEndCycle.getRotationY() + 0.5));
            mImgBgMessage.setRotationY((float) (mImgBgMessage.getRotationY() + 0.5));
            mHandler.postDelayed(mRotate, 10);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mCaMinTimeForDatePicker.set(2017, 2, 1);
        realm = Realm.getDefaultInstance();
        initView();
        addEvent();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Realm.init(this);
        if(Utils.readFromFile(Utils.FILE_NEW_USER, this).equals(Utils.TRUE)){
            mTvDaysLeft.setText("");
            mTvNextCycle.setText("");
            mTvEasyToConceive.setText("");
            startDialog();
        }else if(Utils.readFromFile(Utils.FILE_NEW_USER, this).equals(Utils.FALSE)){
            long mTimeMenLength = Long.parseLong(Utils.readFromFile(Utils.FILE_CHU_KY_HANH_KINH, this));
            long mTimeCycleLength = Long.parseLong(Utils.readFromFile(Utils.FILE_CHU_KY_KINH_NGUYET, this));
            long mTimeDateStart = Long.parseLong(Utils.readFromFile(Utils.FILE_NGAY_BAT_DAU_CHU_KY_KINH_NGUYET, this));

            long mTimeNextCycle = mTimeDateStart + mTimeCycleLength * Utils.mOneDay;
            long mTimeEasyToConceive = mTimeDateStart + Utils.mOneDay * Utils.getDayLeftToDateEasyToConceive(Integer.parseInt(Utils.readFromFile(Utils.FILE_CHU_KY_KINH_NGUYET, this)));

            Calendar mCaNextCycle = Calendar.getInstance();
            Calendar mCaEasyToConceive = Calendar.getInstance();
            mCaNextCycle.setTimeInMillis(mTimeNextCycle);
            mCaEasyToConceive.setTimeInMillis(mTimeEasyToConceive);

            mTvDaysLeft.setText((mCaNextCycle.getTimeInMillis() - mCaNow.getTimeInMillis())/Utils.mOneDay + " " + getString(R.string.days_left));
            mTvNextCycle.setText(mCaNextCycle.get(Calendar.DAY_OF_MONTH) + " thg " + (mCaNextCycle.get(Calendar.MONTH)+1) + " " + getString(R.string.next_cycle));
            mTvEasyToConceive.setText(mCaEasyToConceive.get(Calendar.DAY_OF_MONTH) + " thg " + (mCaEasyToConceive.get(Calendar.MONTH)+1) + " " + getString(R.string.easy_to_conceive));
        }else if(Utils.readFromFile(Utils.FILE_NEW_USER, this).equals(Utils.DANG_MANG_THAI)){
            mTvNextCycle.setText("");
            mTvEasyToConceive.setText("");
            String str = (Long.parseLong(Utils.readFromFile(Utils.FILE_DATE_ESTIMATE, this)) - mCaNow.getTimeInMillis()) / Utils.mOneDay + " " + getString(R.string.days_to_give_birth);
            mTvDaysLeft.setText(str);
        } else {
            startDialog();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        realm.close();
    }

    private void initView(){
        mImgBgMessage = (ImageView) findViewById(R.id.img_background_message_main_activity);
        mImgPet = (ImageView) findViewById(R.id.img_pet_main_activity) ;
        mTvDaysLeft = (TextView) findViewById(R.id.days_left_main);
        mTvNextCycle = (TextView) findViewById(R.id.tv_next_cycle_main);
        mTvEasyToConceive = (TextView) findViewById(R.id.tv_easy_conceive_main);
        mTvEndCycle = (TextView) findViewById(R.id.tv_end_cycle_main_activity);
        mLinearSetting = (LinearLayout) findViewById(R.id.linear_setting);
        mLinearDiary = (LinearLayout) findViewById(R.id.linear_diary);
        mLinearCalendar = (LinearLayout) findViewById(R.id.linear_calendar);
        mLinearChart = (LinearLayout) findViewById(R.id.linear_chart);
        mLinearNote = (LinearLayout) findViewById(R.id.linear_note);

        mLinearSetting.setOnClickListener(this);
        mLinearDiary.setOnClickListener(this);
        mLinearCalendar.setOnClickListener(this);
        mLinearChart.setOnClickListener(this);
        mLinearNote.setOnClickListener(this);
        mTvEndCycle.setOnClickListener(this);
    }

    private void addEvent(){
        mHandler.postDelayed(mRotate, 10);
    }

    private void startDialog(){

        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_start, null);
        dialogBuilder.setView(dialogView);

        final EditText mEdtTop = (EditText) dialogView.findViewById(R.id.edt_top_dialog_start);
        final EditText mEdtBottom = (EditText) dialogView.findViewById(R.id.edt_bottom_dialog_start);
        final TextView mTvTouchHere = (TextView) dialogView.findViewById(R.id.tv_touch_here_dialog_start);
        TextView mTvDone = (TextView) dialogView.findViewById(R.id.tv_done_dialog_start);

        final AlertDialog alertStartDialog = dialogBuilder.create();
        alertStartDialog.show();

        mTvTouchHere.setText(mCaNow.get(Calendar.DAY_OF_MONTH) + "-" + (mCaNow.get(Calendar.MONTH)+1) + "-" + mCaNow.get(Calendar.YEAR));

        mTvTouchHere.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogDateStartCycle(mTvTouchHere);
            }
        });

        mTvDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if( Integer.parseInt(mEdtTop.getText().toString()) < 3 || Integer.parseInt(mEdtTop.getText().toString()) > 15 || Integer.parseInt(mEdtBottom.getText().toString()) < 23 || Integer.parseInt(mEdtBottom.getText().toString()) > 35){
                    Toast.makeText(MainActivity.this, getString(R.string.try_again), Toast.LENGTH_SHORT).show();
                }else {
                    Utils.writeToFile(mEdtTop.getText().toString(), Utils.FILE_CHU_KY_HANH_KINH, getApplicationContext());
                    Utils.writeToFile(mEdtBottom.getText().toString(), Utils.FILE_CHU_KY_KINH_NGUYET, getApplicationContext());
                    Utils.writeToFile(String.valueOf(mCa.getTimeInMillis()), Utils.FILE_NGAY_BAT_DAU_CHU_KY_KINH_NGUYET, getApplicationContext());

                    int lengOfCycle = Integer.parseInt(mEdtTop.getText().toString());
                    int lengOfMenstrual = Integer.parseInt(mEdtBottom.getText().toString());
                    long timePre = mCa.getTimeInMillis();

                    mCaNextCycle.setTimeInMillis(timePre + lengOfMenstrual * Utils.mOneDay);
                    mCaEasyToConceive.setTimeInMillis(timePre + Utils.getDayLeftToDateEasyToConceive(Integer.parseInt(mEdtBottom.getText().toString())) * Utils.mOneDay);
                    mCaDayleft.setTimeInMillis(mCaNextCycle.getTimeInMillis() - mCaNow.getTimeInMillis());

                    mTvDaysLeft.setText((mCaNextCycle.getTimeInMillis() - mCaNow.getTimeInMillis()) / Utils.mOneDay + " " + getString(R.string.days_left));
                    mTvNextCycle.setText(mCaNextCycle.get(Calendar.DAY_OF_MONTH) + " thg " + ( mCaNextCycle.get(Calendar.MONTH) + 1) + " " + getString(R.string.next_cycle));
                    mTvEasyToConceive.setText(mCaEasyToConceive.get(Calendar.DAY_OF_MONTH) + " thg " + ( mCaEasyToConceive.get(Calendar.MONTH) + 1) + " " + getString(R.string.easy_to_conceive));

                    Utils.writeToFile(Utils.FALSE, Utils.FILE_NEW_USER, getApplicationContext());
                    alertStartDialog.cancel();
                }
            }
        });
    }

    private void dialogDateStartCycle(final TextView mTvDay) {
        dialogForCalendar(new IMyDateSetListener() {
            @Override
            public void onDateSet(Calendar currentSelectedDate) {
                int dayOfMonth = currentSelectedDate.get(Calendar.DAY_OF_MONTH);
                int month = currentSelectedDate.get(Calendar.MONTH) + 1;
                int year = currentSelectedDate.get(Calendar.YEAR);
                mTvDay.setText(dayOfMonth + "-" + (month) + "-" + year);
                mCa.setTimeInMillis(currentSelectedDate.getTimeInMillis());
            }
        }, mCaNow.getTimeInMillis(), getString(R.string.select_date), mCaMinTimeForDatePicker.getTimeInMillis() - 3600000);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.linear_setting:
                Intent it = new Intent(this, SettingActivity.class);
                startActivity(it);
                break;
            case R.id.linear_diary:
                startActivity(new Intent(this, DiaryActivity.class));
                break;
            case R.id.linear_calendar:
                startActivity(new Intent(this, CalendarActivity.class));
                break;
            case R.id.linear_chart:
                startActivity(new Intent(this, ChartActivity.class));
                break;
            case R.id.linear_note:
                startActivity(new Intent(this, NoteActivity.class));
                break;
            case R.id.tv_end_cycle_main_activity:

                AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
                LayoutInflater inflater = this.getLayoutInflater();
                View dialogView = inflater.inflate(R.layout.dialog_delete_all_data, null);
                dialogBuilder.setView(dialogView);

                Button mBtnCancel = (Button) dialogView.findViewById(R.id.btn_cancel_dialog_delete);
                Button mBtnOk = (Button) dialogView.findViewById(R.id.btn_ok_dialog_delete);

                final AlertDialog alertStartDialog = dialogBuilder.create();
                alertStartDialog.show();

                mBtnCancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        alertStartDialog.cancel();
                    }
                });

                mBtnOk.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Utils.writeToFile(Utils.TRUE, Utils.FILE_NEW_USER, getApplicationContext());
                        getApplicationContext().deleteFile(Utils.FILE_CHU_KY_KINH_NGUYET);
                        getApplicationContext().deleteFile(Utils.FILE_CHU_KY_HANH_KINH);
                        getApplicationContext().deleteFile(Utils.FILE_NGAY_BAT_DAU_CHU_KY_KINH_NGUYET);
                        getApplicationContext().deleteFile(Utils.FILE_OVULATION);
                        getApplicationContext().deleteFile(Utils.FILE_DATE_ESTIMATE);
                        getApplicationContext().deleteFile(Utils.FILE_REPORT_CYCLE);
                        getApplicationContext().deleteFile(Utils.FILE_REPORT_EASY_TO_CONCEIVE);
                        getApplicationContext().deleteFile(Utils.FILE_REPORT_SO_NGAY_GIAI_DOAN_HOANG_THE);
                        Utils.deleteAllNoteObj(realm);
                        Intent refresh = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(refresh);
                        alertStartDialog.cancel();
                        Toast.makeText(MainActivity.this, getString(R.string.you_just_delete_all_data), Toast.LENGTH_SHORT).show();
                    }
                });

                break;
        }
    }
}
