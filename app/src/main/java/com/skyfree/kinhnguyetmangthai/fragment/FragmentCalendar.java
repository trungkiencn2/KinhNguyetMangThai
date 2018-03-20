package com.skyfree.kinhnguyetmangthai.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.skyfree.kinhnguyetmangthai.R;
import com.skyfree.kinhnguyetmangthai.adapter.RecycleViewCalendarAdapter;
import com.skyfree.kinhnguyetmangthai.custom_interface.UpdateListCaItem;
import com.skyfree.kinhnguyetmangthai.model.CalendarItem;
import com.skyfree.kinhnguyetmangthai.utils.Utils;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by KienBeu on 3/16/2018.
 */

public class FragmentCalendar extends Fragment{
    int mNum;
    private int mMonth, mYear;
    private Calendar mCa = Calendar.getInstance();
    private ArrayList<CalendarItem> mListItem = new ArrayList<>();

    public FragmentCalendar(int month, int year) {
        this.mMonth = month;
        this.mYear = year;
    }

//    private static int position;
//    public static FragmentCalendar newInstance(int num, int month, int year) {
//        FragmentCalendar f = new FragmentCalendar();
//        return f;
//    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        String dayWeek = Utils.getThuMayLaMung1(mMonth, mYear);

        int numberOfDataNull = Utils.getNumberDataNull(dayWeek);
        int maxDayInMonth = Utils.getSoNgayTrong1Thang(mMonth, mYear);

        View v = inflater.inflate(R.layout.fragment_month_calendar, container, false);
        RecyclerView mRcv = v.findViewById(R.id.rcv_fragment_month_calendar);

        RecycleViewCalendarAdapter mAdapter = new RecycleViewCalendarAdapter(Utils.createListCaItem(numberOfDataNull, maxDayInMonth), getContext());
        mRcv.setLayoutManager(new GridLayoutManager(getContext(), 7));
        mRcv.setHasFixedSize(true);
        mRcv.setAdapter(mAdapter);

        return v;
    }
}