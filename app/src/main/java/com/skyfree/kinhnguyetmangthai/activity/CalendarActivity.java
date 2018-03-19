package com.skyfree.kinhnguyetmangthai.activity;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.skyfree.kinhnguyetmangthai.R;
import com.skyfree.kinhnguyetmangthai.adapter.FragmentAdapter;

public class CalendarActivity extends AppCompatActivity {

    public static TextView mTvDateCalendarActivity;

    private FragmentAdapter mFragmentAdapter;
    private ViewPager mPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);
        initView();
    }

    private void initView() {
        mTvDateCalendarActivity = (TextView) findViewById(R.id.tv_date_calendar_activity);
        mFragmentAdapter = new FragmentAdapter(getSupportFragmentManager());
        mPager = findViewById(R.id.view_pager_calendar);
        mPager.setAdapter(mFragmentAdapter);
//        mPager.setCurrentItem(5, false);
    }

    private void addEvent(){

    }

}
