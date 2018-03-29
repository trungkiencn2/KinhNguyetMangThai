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
import com.skyfree.kinhnguyetmangthai.custom_interface.IUpdateCalItem;
import com.skyfree.kinhnguyetmangthai.custom_interface.IUpdateInfoForCalendarActivity;
import com.skyfree.kinhnguyetmangthai.custom_interface.IUpdateTopTime;
import com.skyfree.kinhnguyetmangthai.model.CalendarItem;
import com.skyfree.kinhnguyetmangthai.utils.Utils;

import java.util.ArrayList;
import java.util.Calendar;

import io.realm.Realm;

/**
 * Created by KienBeu on 3/16/2018.
 */

public class FragmentCalendar extends Fragment{
    private int mMonth, mYear;
    private Calendar mCaNow = Calendar.getInstance();
    private IUpdateTopTime mUpdateTopTime;
    private IUpdateCalItem mUpdateCalItem;
    private IUpdateInfoForCalendarActivity mUpdateInfo;
    private Realm realm;

    public int getmMonth() {
        return mMonth;
    }

    public void setmMonth(int mMonth) {
        this.mMonth = mMonth;
    }

    public int getmYear() {
        return mYear;
    }

    public void setmYear(int mYear) {
        this.mYear = mYear;
    }

    public FragmentCalendar() {
    }

    public FragmentCalendar(int month, int year, IUpdateTopTime mUpdate, IUpdateCalItem mUpdateItem, IUpdateInfoForCalendarActivity updateInfoForCalendarActivity) {
        this.mMonth = month;
        this.mYear = year;
        this.mUpdateTopTime = mUpdate;
        this.mUpdateCalItem = mUpdateItem;
        this.mUpdateInfo = updateInfoForCalendarActivity;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    ArrayList<CalendarItem> mListCaItem = new ArrayList<>();

    private ArrayList<CalendarItem> getListCaItem(){
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
        RecycleViewCalendarAdapter mAdapter = new RecycleViewCalendarAdapter(mListCaItem, getContext());
        mRcv.setLayoutManager(new GridLayoutManager(getContext(), 7));
        mRcv.setHasFixedSize(true);
        mRcv.setAdapter(mAdapter);

        mRcv.addOnItemTouchListener(new RecyclerItemClickListener(getContext(), mRcv, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
//                Log.d("aaa", (mMonth + 1) + " - " + mYear);
                if(position >= numberOfDataNull){
//                    Log.d("aaa", (position - numberOfDataNull + 1) + " - " + (mMonth + 1) + " - " + mYear);
//                    View viewItem = mRcv.getLayoutManager().findViewByPosition(position);
//                    viewItem.setBackgroundResource(R.drawable.bg_cal_today);
                    mUpdateInfo.updateInfo(Utils.getNoteObj(realm, (position - numberOfDataNull + 1) + "" + mMonth + "" + mYear), position - numberOfDataNull + 1, mMonth, mYear);
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

        if (menuVisible){
            mUpdateTopTime.updateTopTime((mMonth + 1) + " - " + mYear);
//            mDb = new DatabaseHelper(getContext());
//            Log.d("aaa", mDb.getListAcount().size() + " frag");
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        realm.close();
    }
}