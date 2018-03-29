package com.skyfree.kinhnguyetmangthai.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.skyfree.kinhnguyetmangthai.R;
import com.skyfree.kinhnguyetmangthai.custom_interface.IUpdateCalItem;
import com.skyfree.kinhnguyetmangthai.custom_interface.IUpdateInfoForCalendarActivity;
import com.skyfree.kinhnguyetmangthai.custom_interface.IUpdateTopTime;
import com.skyfree.kinhnguyetmangthai.fragment.FragmentCalendar;
import com.skyfree.kinhnguyetmangthai.fragment.PageThangAdapter;
import com.skyfree.kinhnguyetmangthai.model.NoteObj;
import com.skyfree.kinhnguyetmangthai.utils.Utils;

import java.util.Calendar;

import io.realm.Realm;

public class CalendarActivity extends AppCompatActivity implements IUpdateTopTime, IUpdateCalItem, IUpdateInfoForCalendarActivity, View.OnClickListener {

    private TextView mTvDateCalendarActivity, mTvNote, mTvDrug, mTvSymptom, mTvMood, mTvWeight, mTvTemperature;
    private TextView mTvFinalNote, mTvFinalDrug, mTvFinalSymptom, mTvFinalMood, mTvDateNote;
    private LinearLayout mLinearInfo;

    private PageThangAdapter mFragmentAdapter;
    private ViewPager mPager;
    private Calendar mCaSetForPager = Calendar.getInstance();
    private Calendar mCurrentCa = Calendar.getInstance();
    private Calendar mCaSend = Calendar.getInstance();
    private String mCurrentId = "";

    private Realm mRealm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);
        mRealm = Realm.getDefaultInstance();
        initView();
        addEvent();
    }

    public static Context getContext() {
        return getContext();
    }

    private void initView() {
        mTvDateCalendarActivity = (TextView) findViewById(R.id.tv_date_calendar_activity);
        mTvNote = (TextView) findViewById(R.id.tv_note_calendar_activity);
        mTvDrug = (TextView) findViewById(R.id.tv_drug_calendar_activity);
        mTvSymptom = (TextView) findViewById(R.id.tv_symptom_calendar_activity);
        mTvMood = (TextView) findViewById(R.id.tv_mood_calendar_activity);
        mTvWeight = (TextView) findViewById(R.id.tv_weight_calendar_activity);
        mTvTemperature = (TextView) findViewById(R.id.tv_temperature_calendar_activity);
        mTvFinalNote = (TextView) findViewById(R.id.tv_final_note);
        mTvFinalDrug = (TextView) findViewById(R.id.tv_final_drug);
        mTvFinalSymptom = (TextView) findViewById(R.id.tv_final_symptom);
        mTvFinalMood = (TextView) findViewById(R.id.tv_final_mood);
        mTvDateNote = (TextView) findViewById(R.id.tv_date_note_calendar_activity);
        mLinearInfo = (LinearLayout) findViewById(R.id.linear_info_calendar_activity);
        mLinearInfo.setOnClickListener(this);

        mCaSetForPager.add(Calendar.MONTH, -24);
        mFragmentAdapter = new PageThangAdapter(getSupportFragmentManager());
        for (int i = 0; i <= 47; i++) {
            Fragment fragment = new FragmentCalendar(mCaSetForPager.get(Calendar.MONTH), mCaSetForPager.get(Calendar.YEAR), this, this, this);
            mCaSetForPager.add(Calendar.MONTH, 1);
            if (mCaSetForPager.get(Calendar.MONTH) > 11) {
                mCaSetForPager.set(Calendar.MONTH, 0);
                mCaSetForPager.add(Calendar.YEAR, 1);
            } else if (mCaSetForPager.get(Calendar.MONTH) < 0) {
                mCaSetForPager.set(Calendar.MONTH, 11);
                mCaSetForPager.add(Calendar.YEAR, -1);
            }
            mFragmentAdapter.addPage(fragment);
        }

        mPager = findViewById(R.id.view_pager_calendar);
        mPager.setAdapter(mFragmentAdapter);
        mPager.setCurrentItem(24, false);

    }

    private void addEvent(){
        mCurrentId = mCurrentCa.get(Calendar.DAY_OF_MONTH) + "" + mCurrentCa.get(Calendar.MONTH) + "" + mCurrentCa.get(Calendar.YEAR);
        NoteObj mCurrentNoteObj = Utils.getNoteObj(mRealm, mCurrentId);
        if(mCurrentNoteObj != null){
            mTvDateNote.setText(mCurrentCa.get(Calendar.DAY_OF_MONTH) + " - " + (mCurrentCa.get(Calendar.MONTH) + 1) + " - " + mCurrentCa.get(Calendar.YEAR));
            mTvNote.setText(mCurrentNoteObj.getmNoteNote());
            String listDrug = "";

            for (int i = 0; i < mCurrentNoteObj.getmListDrug().size(); i++) {
                listDrug += mCurrentNoteObj.getmListDrug().get(i).getmDrug() + ", ";
            }

            String listSymptom = "";

            for (int i = 0; i < mCurrentNoteObj.getmListSymptom().size(); i++) {
                listSymptom += mCurrentNoteObj.getmListSymptom().get(i).getmSymptom() + ", ";
            }

            String listMood = "";

            for (int i = 0; i < mCurrentNoteObj.getmListMood().size(); i++) {
                listMood += mCurrentNoteObj.getmListMood().get(i).getmMood() + ", ";
            }

            if(mCurrentNoteObj.getmNoteNote().equals("")){
                mTvFinalNote.setText("");
            }
            if(listDrug.equals("")){
                mTvFinalDrug.setText("");
            }
            if(listSymptom.equals("")){
                mTvFinalSymptom.setText("");
            }
            if(listMood.equals("")){
                mTvFinalMood.setText("");
            }

            mTvDrug.setText(listDrug);
            mTvSymptom.setText(listSymptom);
            mTvMood.setText(listMood);

            if(mCurrentNoteObj.getmNoteWeight() <= 0){
                mTvWeight.setText("");
            }else {
                mTvWeight.setText(mCurrentNoteObj.getmNoteWeight() + " kg");
            }

            if(mCurrentNoteObj.getmNoteTemperature() <= 0){
                mTvTemperature.setText("");
            }else {
                mTvTemperature.setText(mCurrentNoteObj.getmNoteTemperature() + " °C");
            }
        }
    }

    @Override
    public void updateTopTime(String timeStr) {
        mTvDateCalendarActivity.setText(timeStr);
    }

    @Override
    public void updateCalItem(int position) {

    }

    private int mPutDay, mPutMonth, mPutYear;

    @Override
    public void updateInfo(NoteObj mNoteObj, int day, int month, int year) {
        mPutDay = day;
        mPutMonth = month;
        mPutYear = year;
        if (mNoteObj != null) {
            if (mNoteObj.getmNoteNote() != "") {
                mTvFinalNote.setText(getString(R.string.note));
            } else {
                mTvFinalNote.setText("");
            }
            if (mNoteObj.getmListDrug().size() > 0) {
                mTvFinalDrug.setText(getString(R.string.drug));
            } else {
                mTvFinalDrug.setText("");
            }
            if (mNoteObj.getmListSymptom().size() > 0) {
                mTvFinalSymptom.setText(getString(R.string.symptom));
            }else {
                mTvFinalSymptom.setText("");
            }
            if (mNoteObj.getmListMood().size() > 0) {
                mTvFinalMood.setText(getString(R.string.mood));
            }else {
                mTvFinalMood.setText("");
            }

            mTvNote.setText(mNoteObj.getmNoteNote());

            String listDrug = "";

            for (int i = 0; i < mNoteObj.getmListDrug().size(); i++) {
                listDrug += mNoteObj.getmListDrug().get(i).getmDrug() + ", ";
            }

            String listSymptom = "";

            for (int i = 0; i < mNoteObj.getmListSymptom().size(); i++) {
                listSymptom += mNoteObj.getmListSymptom().get(i).getmSymptom() + ", ";
            }

            String listMood = "";

            for (int i = 0; i < mNoteObj.getmListMood().size(); i++) {
                listMood += mNoteObj.getmListMood().get(i).getmMood() + ", ";
            }

            mTvDrug.setText(listDrug);
            mTvSymptom.setText(listSymptom);
            mTvMood.setText(listMood);

            if(mNoteObj.getmNoteWeight() <= 0){
                mTvWeight.setText("");
            }else {
                mTvWeight.setText(mNoteObj.getmNoteWeight() + " kg");
            }

            if(mNoteObj.getmNoteTemperature() <= 0){
                mTvTemperature.setText("");
            }else {
                mTvTemperature.setText(mNoteObj.getmNoteTemperature() + " °C");
            }
        } else {
            mTvNote.setText("");
            mTvDrug.setText("");
            mTvTemperature.setText("");
            mTvWeight.setText("");
            mTvMood.setText("");
            mTvSymptom.setText("");

            mTvFinalNote.setText("");
            mTvFinalDrug.setText("");
            mTvFinalSymptom.setText("");
            mTvFinalMood.setText("");
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mRealm.close();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.linear_info_calendar_activity:
                Intent it = new Intent(this, NoteActivity.class);
                it.putExtra(Utils.PUT_DAY, mPutDay);
                it.putExtra(Utils.PUT_MONTH, mPutMonth);
                it.putExtra(Utils.PUT_YEAR, mPutYear);
                startActivity(it);
                break;
        }
    }
}
