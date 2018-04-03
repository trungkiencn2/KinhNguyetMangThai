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
    private int xxx = -1;

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

    ArrayList<CalendarItem> mListCaItem = new ArrayList<>();

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

        String dayWeek = Utils.getThuMayLaMung1(mMonth, mYear);

        final int numberOfDataNull = Utils.getNumberDataNull(dayWeek);
        int maxDayInMonth = Utils.getSoNgayTrong1Thang(mMonth, mYear);
        mListCaItem = Utils.createListCaItem(numberOfDataNull, maxDayInMonth, mMonth, mYear);

        final View v = inflater.inflate(R.layout.fragment_month_calendar, container, false);
        RecyclerView mRcv = v.findViewById(R.id.rcv_fragment_month_calendar);
        final RecycleViewCalendarAdapter mAdapter = new RecycleViewCalendarAdapter(mListCaItem, getContext());
        mRcv.setLayoutManager(new GridLayoutManager(getContext(), 7));
        mRcv.setHasFixedSize(true);
        mRcv.setAdapter(mAdapter);

        for (int i = numberOfDataNull; i < mListCaItem.size(); i++) {

            int pos = getPositionCurrentDay(numberOfDataNull, Integer.parseInt(mListCaItem.get(i).getmDate()),
                    Integer.parseInt(mListCaItem.get(i).getmMonth()), Integer.parseInt(mListCaItem.get(i).getmYear()));

            if (pos != -1) {
                CalendarItem mCaItem = mListCaItem.get(pos);
                mCaItem.setmBg(R.drawable.bg_cal_today);
                mListCaItem.set(pos, mCaItem);
                mAdapter.notifyItemChanged(pos);
            }


            int posNgayBatDauChuKy = getPositionNgayBatDauChuKy(numberOfDataNull, Integer.parseInt(mListCaItem.get(i).getmDate()),
                    Integer.parseInt(mListCaItem.get(i).getmMonth()), Integer.parseInt(mListCaItem.get(i).getmYear()));

            if (posNgayBatDauChuKy != -1) {
                CalendarItem mCaItem = mListCaItem.get(posNgayBatDauChuKy);
                mCaItem.setmBg(R.color.colorAccent);
                mListCaItem.set(posNgayBatDauChuKy, mCaItem);
                mAdapter.notifyItemChanged(posNgayBatDauChuKy);
            }
        }

        mRcv.addOnItemTouchListener(new RecyclerItemClickListener(getContext(), mRcv, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
//                Log.d("aaa", (mMonth + 1) + " - " + mYear);
                if (position >= numberOfDataNull) {
//                    Log.d("aaa", (position - numberOfDataNull + 1) + " - " + (mMonth + 1) + " - " + mYear);
//                    View viewItem = mRcv.getLayoutManager().findViewByPosition(position);
//                    viewItem.setBackgroundResource(R.drawable.bg_cal_today);
                    if(xxx > 0){
                        CalendarItem mCaItemTam = mListCaItem.get(xxx);
                        mCaItemTam.setmBg(R.drawable.bg_date);
                        mListCaItem.set(xxx, mCaItemTam);
                        mAdapter.notifyItemChanged(xxx);
                    }

                    CalendarItem mCaItem = mListCaItem.get(position);
                    mCaItem.setmBg(R.drawable.bg_cal_selector);
                    mListCaItem.set(position, mCaItem);
                    mAdapter.notifyItemChanged(position);
                    xxx = position;

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

    int mDem = 1;
    Calendar mCaTam = Calendar.getInstance();

    private Integer getPositionNgayBatDauChuKy(int numberNull, int day, int month, int year) {

        mCaTam.setTimeInMillis(Long.parseLong(Utils.readFromFile(Utils.FILE_NGAY_BAT_DAU_CHU_KY_KINH_NGUYET, getContext())));
        if (day == mCaTam.get(Calendar.DAY_OF_MONTH) && month == mCaTam.get(Calendar.MONTH) && year == mCaTam.get(Calendar.YEAR)) {
            if (mDem < 5) {
                mCaTam.add(Calendar.DAY_OF_MONTH, mDem);
                mDem++;
                return (numberNull + day - 1);
            } else {
                return -1;
            }
        } else {
            return -1;
        }

    }

}