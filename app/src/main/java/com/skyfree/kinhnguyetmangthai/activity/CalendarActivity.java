package com.skyfree.kinhnguyetmangthai.activity;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.skyfree.kinhnguyetmangthai.R;
import com.skyfree.kinhnguyetmangthai.custom_interface.IUpdateCalItem;
import com.skyfree.kinhnguyetmangthai.custom_interface.IUpdateTopTime;
import com.skyfree.kinhnguyetmangthai.fragment.FragmentCalendar;
import com.skyfree.kinhnguyetmangthai.fragment.PageThangAdapter;

import java.util.Calendar;

public class CalendarActivity extends AppCompatActivity implements IUpdateTopTime, IUpdateCalItem {

    public TextView mTvDateCalendarActivity;

    private PageThangAdapter mFragmentAdapter;
    private ViewPager mPager;
    private Calendar mCa = Calendar.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);
        initView();
    }

    public static Context getContext(){
        return getContext();
    }

    private void initView() {
        mTvDateCalendarActivity = (TextView) findViewById(R.id.tv_date_calendar_activity);

        mCa.add(Calendar.MONTH, -24);
        mFragmentAdapter = new PageThangAdapter(getSupportFragmentManager());
        for(int i = 0;i<= 47; i++){
            Fragment fragment = new FragmentCalendar(mCa.get(Calendar.MONTH), mCa.get(Calendar.YEAR), this, this);
            mCa.add(Calendar.MONTH, 1);
            if(mCa.get(Calendar.MONTH) > 11){
                mCa.set(Calendar.MONTH, 0);
                mCa.add(Calendar.YEAR, 1);
            }else if (mCa.get(Calendar.MONTH) < 0){
                mCa.set(Calendar.MONTH, 11);
                mCa.add(Calendar.YEAR, -1);
            }
            mFragmentAdapter.addPage(fragment);
        }

        mPager = findViewById(R.id.view_pager_calendar);
        mPager.setAdapter(mFragmentAdapter);
        mPager.setCurrentItem(24, false);

    }

    @Override
    public void updateTopTime(String timeStr) {
        mTvDateCalendarActivity.setText(timeStr);
    }

    @Override
    public void updateCalItem(int position) {

    }
}
