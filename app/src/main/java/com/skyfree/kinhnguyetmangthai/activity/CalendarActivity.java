package com.skyfree.kinhnguyetmangthai.activity;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.skyfree.kinhnguyetmangthai.R;
import com.skyfree.kinhnguyetmangthai.fragment.FragmentCalendar;
import com.skyfree.kinhnguyetmangthai.fragment.PageThangAdapter;

import java.util.Calendar;

public class CalendarActivity extends AppCompatActivity {

    public TextView mTvDateCalendarActivity;

    private PageThangAdapter mFragmentAdapter;
    private ViewPager mPager;
    private Calendar mCaNow = Calendar.getInstance();
    private Calendar mCaNow2 = Calendar.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);
        initView();
    }

    private void initView() {
        mTvDateCalendarActivity = (TextView) findViewById(R.id.tv_date_calendar_activity);
        mFragmentAdapter = new PageThangAdapter(getSupportFragmentManager());
        for(int i = 0;i<= 11; i++){
            Fragment fragment = new FragmentCalendar(i + 2, 2018);
            mFragmentAdapter.addPage(fragment);
        }

        mPager = findViewById(R.id.view_pager_calendar);
        mPager.setAdapter(mFragmentAdapter);
        mPager.setCurrentItem(0, false);

    }

    private void addEvent(){

    }

}
