package com.skyfree.kinhnguyetmangthai.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
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

    private Calendar mCaNgayBatDauChuKy = Calendar.getInstance();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        realm = Realm.getDefaultInstance();

        String dayWeek = Utils.getThuMayLaMung1(mMonth, mYear);

        final int numberOfDataNull = Utils.getNumberDataNull(dayWeek);
        int maxDayInMonth = Utils.getSoNgayTrong1Thang(mMonth, mYear);
        mListCaItem = Utils.createListCaItem(numberOfDataNull, maxDayInMonth, mMonth, mYear);


        final View v = inflater.inflate(R.layout.fragment_month_calendar, container, false);
        RecyclerView mRcv = v.findViewById(R.id.rcv_fragment_month_calendar);
        RecycleViewCalendarAdapter mAdapter = new RecycleViewCalendarAdapter(mListCaItem, getContext());
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
                mCaTam.add(Calendar.DAY_OF_MONTH, 1);
                CalendarItem mCaItem = mListCaItem.get(posNgayBatDauChuKy);
                mCaItem.setmBg(R.color.colorAccent);
                mListCaItem.set(posNgayBatDauChuKy, mCaItem);
                mAdapter.notifyItemChanged(posNgayBatDauChuKy);
                continue;
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
        mListCaItem = Utils.createListCaItem(numberOfDataNull, maxDayInMonth, mMonth, mYear);

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
//        mCaNgayBatDauChuKy.setTimeInMillis(mCaTam.getTimeInMillis());
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

//        ArrayList<Integer> mList = new ArrayList<>();
//        Calendar mCaTam = Calendar.getInstance();
//        Calendar mCaTam2 = Calendar.getInstance();
//        Calendar mCaTam3 = Calendar.getInstance();
//        Calendar mCaTam4 = Calendar.getInstance();
//        mCaTam.setTimeInMillis(Long.parseLong(Utils.readFromFile(Utils.FILE_NGAY_BAT_DAU_CHU_KY_KINH_NGUYET, getContext())));
//        mCaTam2.setTimeInMillis(Long.parseLong(Utils.readFromFile(Utils.FILE_NGAY_BAT_DAU_CHU_KY_KINH_NGUYET, getContext())));
//        mCaTam3.setTimeInMillis(Long.parseLong(Utils.readFromFile(Utils.FILE_NGAY_BAT_DAU_CHU_KY_KINH_NGUYET, getContext())));
//        mCaTam4.setTimeInMillis(Long.parseLong(Utils.readFromFile(Utils.FILE_NGAY_BAT_DAU_CHU_KY_KINH_NGUYET, getContext())));
//        mCaTam2.add(Calendar.DAY_OF_MONTH, 1);
//        mCaTam3.add(Calendar.DAY_OF_MONTH, 2);
//        mCaTam4.add(Calendar.DAY_OF_MONTH, 3);
//        int mDayStart = mCaTam.get(Calendar.DAY_OF_MONTH);
//        int mMonthStart = mCaTam.get(Calendar.MONTH);
//        int mYearStart = mCaTam.get(Calendar.YEAR);
//        int mDayStart2 = mCaTam2.get(Calendar.DAY_OF_MONTH);
//        int mMonthStart2 = mCaTam2.get(Calendar.MONTH);
//        int mYearStart2 = mCaTam2.get(Calendar.YEAR);
//        int mDayStart3 = mCaTam3.get(Calendar.DAY_OF_MONTH);
//        int mMonthStart3 = mCaTam3.get(Calendar.MONTH);
//        int mYearStart3 = mCaTam3.get(Calendar.YEAR);
//        int mDayStart4 = mCaTam4.get(Calendar.DAY_OF_MONTH);
//        int mMonthStart4 = mCaTam4.get(Calendar.MONTH);
//        int mYearStart4 = mCaTam4.get(Calendar.YEAR);
//
//        if(day == mDayStart && month == mMonthStart && year == mYearStart){
//            mList.add(numberNull + day - 1);
//        }else if(day == mDayStart2 && month == mMonthStart2 && year == mYearStart2){
//            mList.add(numberNull + day - 1);
//        }else if(day == mDayStart3 && month == mMonthStart3 && year == mYearStart3){
//            mList.add(numberNull + day - 1);
//        }else if(day == mDayStart && month == mMonthStart && year == mYearStart){
//            mList.add(numberNull + day - 1);
//        }
//
//        return mList;
    }

}