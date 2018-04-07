package com.skyfree.kinhnguyetmangthai.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.skyfree.kinhnguyetmangthai.R;
import com.skyfree.kinhnguyetmangthai.adapter.RecycleViewCalendarAdapter;
import com.skyfree.kinhnguyetmangthai.custom_interface.ISetIdForCalendarActivity;
import com.skyfree.kinhnguyetmangthai.custom_interface.IUpdateTopTime;
import com.skyfree.kinhnguyetmangthai.listener.RecyclerItemClickListener;
import com.skyfree.kinhnguyetmangthai.model.CalendarItem;
import com.skyfree.kinhnguyetmangthai.utils.Utils;

import java.util.ArrayList;
import java.util.Calendar;

import io.realm.Realm;

/**
 * Created by KienBeu on 3/16/2018.
 */

public class FragmentCalendar extends Fragment {
    private int mMonth, mYear;
    private Calendar mCaNow = Calendar.getInstance();
    private IUpdateTopTime mUpdateTopTime;
    private Realm realm;
    private ISetIdForCalendarActivity mSetDate;
    private int mCheck = -1;

    public FragmentCalendar() {
    }

    public FragmentCalendar(int month, int year, IUpdateTopTime mUpdate, ISetIdForCalendarActivity mSetUI) {
        this.mMonth = month;
        this.mYear = year;
        this.mUpdateTopTime = mUpdate;
        this.mSetDate = mSetUI;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    ArrayList<CalendarItem> mListCaItem;

    private ArrayList<CalendarItem> getListCaItem() {
        String dayWeek = Utils.getThuMayLaMung1(mMonth, mYear);
        final int numberOfDataNull = Utils.getNumberDataNull(dayWeek);
        int maxDayInMonth = Utils.getSoNgayTrong1Thang(mMonth, mYear);
        mListCaItem = Utils.createListCaItem(numberOfDataNull, maxDayInMonth, mMonth, mYear);
        return mListCaItem;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        realm = Realm.getDefaultInstance();
        mListCaItem = new ArrayList<>();

        final int numberOfDataNull = Utils.getNumberDataNull(Utils.getThuMayLaMung1(mMonth, mYear));
        int maxDayInMonth = Utils.getSoNgayTrong1Thang(mMonth, mYear);
        mListCaItem = Utils.createListCaItem(numberOfDataNull, maxDayInMonth, mMonth, mYear);

        final View v = inflater.inflate(R.layout.fragment_month_calendar, container, false);
        RecyclerView mRcv = v.findViewById(R.id.rcv_fragment_month_calendar);
        final RecycleViewCalendarAdapter mAdapter = new RecycleViewCalendarAdapter(mListCaItem, getContext());
        mRcv.setLayoutManager(new GridLayoutManager(getContext(), 7));
        mRcv.setHasFixedSize(true);
        mRcv.setAdapter(mAdapter);

        mRcv.addOnItemTouchListener(new RecyclerItemClickListener(getContext(), mRcv, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                if (position >= numberOfDataNull) {
                    mSetDate.setDate(position - numberOfDataNull + 1, mMonth, mYear);
                }
            }

            @Override
            public void onLongItemClick(View view, int position) {

            }
        }));

        return v;
    }

    @Override
    public void setMenuVisibility(boolean menuVisible) {
        super.setMenuVisibility(menuVisible);

        String dayWeek = Utils.getThuMayLaMung1(mMonth, mYear);
        final int numberOfDataNull = Utils.getNumberDataNull(dayWeek);
        int maxDayInMonth = Utils.getSoNgayTrong1Thang(mMonth, mYear);

        if (menuVisible) {
            mUpdateTopTime.updateTopTime((mMonth + 1) + " - " + mYear);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        realm.close();
    }

    private int getPositionCurrentDay(int numberNull, int day, int month, int year) {
        if (day == Calendar.getInstance().get(Calendar.DAY_OF_MONTH) && month == Calendar.getInstance().get(Calendar.MONTH) && year == Calendar.getInstance().get(Calendar.YEAR)) {
            return (numberNull + day - 1);
        } else {
            return -1;
        }
    }

}