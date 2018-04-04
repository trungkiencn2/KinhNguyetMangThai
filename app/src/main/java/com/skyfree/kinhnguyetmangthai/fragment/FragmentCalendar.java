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
                if (position >= numberOfDataNull) {
//                    Log.d("aaa", (position - numberOfDataNull + 1) + " - " + (mMonth + 1) + " - " + mYear);
//                    View viewItem = mRcv.getLayoutManager().findViewByPosition(position);
//                    viewItem.setBackgroundResource(R.drawable.bg_cal_today);
                    if (mCheck > 0) {
                        CalendarItem mCaItemTam = mListCaItem.get(mCheck);
                        mCaItemTam.setmBg(R.drawable.bg_date);
                        mListCaItem.set(mCheck, mCaItemTam);
                        mAdapter.notifyItemChanged(mCheck);
                    }

                    CalendarItem mCaItem = mListCaItem.get(position);
                    mCaItem.setmBg(R.drawable.bg_cal_selector);
                    mListCaItem.set(position, mCaItem);
                    mAdapter.notifyItemChanged(position);
                    mCheck = position;

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

    private Integer getPositionNgayBatDauChuKy(int numberNull, int day, int month, int year) {

        ArrayList<Calendar> mListC = getListCalKinhNguyet();

//        for(int i= 0; i<mListC.size(); i++){
//            if (day == mListC.get(i).get(Calendar.DAY_OF_MONTH) && month == mListC.get(i).get(Calendar.MONTH) && year == mListC.get(i).get(Calendar.YEAR)) {
//                return (numberNull + day - 1);
//            } else {
//                return -1;
//            }
//        }
//        return -1;

        if (day == mCaTam.get(Calendar.DAY_OF_MONTH) && day == mCaTam.get(Calendar.MONTH) && year == mCaTam.get(Calendar.YEAR)) {
            return (numberNull + day - 1);
        }else if(day == mCaAdd.get(Calendar.DAY_OF_MONTH) && day == mCaAdd.get(Calendar.MONTH) && year == mCaAdd.get(Calendar.YEAR)){
            return (numberNull + day - 1);
        }else {
            return -1;
        }



    }

    ArrayList<Calendar> mListCalendar = new ArrayList<>();

    Calendar mCaTam = Calendar.getInstance();
    Calendar mCaTam2 = Calendar.getInstance();
    Calendar mCaTam3 = Calendar.getInstance();
    Calendar mCaTam4 = Calendar.getInstance();

    private void getListCal(){
        mCaTam.setTimeInMillis(Long.parseLong(Utils.readFromFile(Utils.FILE_NGAY_BAT_DAU_CHU_KY_KINH_NGUYET, getContext())));
        mCaTam2.setTimeInMillis(mCaTam.getTimeInMillis());
        mCaTam3.setTimeInMillis(mCaTam.getTimeInMillis());
        mCaTam4.setTimeInMillis(mCaTam.getTimeInMillis());

        mCaTam2.add(Calendar.DAY_OF_MONTH, 1);
        mCaTam3.add(Calendar.DAY_OF_MONTH, 2);
        mCaTam4.add(Calendar.DAY_OF_MONTH, 3);
    }

    Calendar mCaAdd = Calendar.getInstance();

    private ArrayList<Calendar> getListCalKinhNguyet() {
        ArrayList<Calendar> mListCal = new ArrayList<>();
        mCaTam.setTimeInMillis(Long.parseLong(Utils.readFromFile(Utils.FILE_NGAY_BAT_DAU_CHU_KY_KINH_NGUYET, getContext())));
        mCaAdd.setTimeInMillis(mCaTam.getTimeInMillis() + Utils.mOneDay);
        mListCal.add(mCaTam);
        mListCal.add(mCaAdd);

//        Calendar mCaAdd = Calendar.getInstance();
//        Calendar mCaAddHanhKinh = Calendar.getInstance();
//        mCaAdd.setTimeInMillis(mCaTam.getTimeInMillis());
//
//        int chuKyHanhKinh = Integer.parseInt(Utils.readFromFile(Utils.FILE_CHU_KY_HANH_KINH, getContext()));
//        int chuKyKinhNguyet = Integer.parseInt(Utils.readFromFile(Utils.FILE_CHU_KY_KINH_NGUYET, getContext()));
//
//        for(int i = 1; i<=24; i++){
//            mCaAdd.add(Calendar.DAY_OF_MONTH, chuKyKinhNguyet * i);
//            for(int j = 1; j<=chuKyHanhKinh; j++){
//                mCaAddHanhKinh.setTimeInMillis(mCaAdd.getTimeInMillis() + j * Utils.mOneDay);
//                int mDay = mCaAddHanhKinh.get(Calendar.DAY_OF_MONTH);
//                mListCal.add(mCaAddHanhKinh);
//            }
//            mListCal.add(mCaAdd);
//
//        }

        return mListCal;
    }

}