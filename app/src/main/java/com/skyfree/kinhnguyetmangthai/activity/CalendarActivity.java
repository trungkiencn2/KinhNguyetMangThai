package com.skyfree.kinhnguyetmangthai.activity;

import android.content.Intent;
import android.graphics.Color;
import android.icu.text.SimpleDateFormat;
import android.icu.util.Calendar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.applandeo.materialcalendarview.EventDay;
import com.github.sundeepk.compactcalendarview.CompactCalendarView;
import com.github.sundeepk.compactcalendarview.EventsContainer;
import com.github.sundeepk.compactcalendarview.domain.Event;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.CalendarMode;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.skyfree.kinhnguyetmangthai.R;
import com.skyfree.kinhnguyetmangthai.utils.Utils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class CalendarActivity extends AppCompatActivity {

    private CompactCalendarView mCompactView;
    private TextView mTvCurrentMonth;
    private SimpleDateFormat dateFormatMonth = new SimpleDateFormat("MMMM - yyyy", Locale.getDefault());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);
        initView();
        addEvent();
    }

    private void addEvent() {

        mCompactView.setUseThreeLetterAbbreviation(true);
        Event ev1 = new Event(Color.YELLOW, Long.parseLong(Utils.readFromFile(Utils.FILE_NGAY_BAT_DAU_CHU_KY_KINH_NGUYET, this)), "ahihi");
        Event ev2 = new Event(Color.WHITE, Long.parseLong(Utils.readFromFile(Utils.FILE_NGAY_BAT_DAU_CHU_KY_KINH_NGUYET, this))  + Utils.mOneDay, "ahaha");
        Event ev3 = new Event(Color.BLACK, Long.parseLong(Utils.readFromFile(Utils.FILE_NGAY_BAT_DAU_CHU_KY_KINH_NGUYET, this))  + Utils.mOneDay * 2, "ahaha");
        Event ev4 = new Event(Color.BLUE, Long.parseLong(Utils.readFromFile(Utils.FILE_NGAY_BAT_DAU_CHU_KY_KINH_NGUYET, this))  + Utils.mOneDay * 3, "ahaha");
        mCompactView.addEvent(ev1);
        mCompactView.addEvent(ev2);
        mCompactView.addEvent(ev3);
        mCompactView.addEvent(ev4);
        mCompactView.setListener(new CompactCalendarView.CompactCalendarViewListener() {
            @Override
            public void onDayClick(Date date) {

            }

            @Override
            public void onMonthScroll(Date date) {
                Toast.makeText(CalendarActivity.this, "" + date.getTime(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void initView(){
        mCompactView = (CompactCalendarView) findViewById(R.id.compactcalendar_view);
        mTvCurrentMonth = (TextView) findViewById(R.id.tv_month_calendar);
    }
}
