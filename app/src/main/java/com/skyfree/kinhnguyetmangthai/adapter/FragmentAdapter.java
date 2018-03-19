package com.skyfree.kinhnguyetmangthai.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;

import com.skyfree.kinhnguyetmangthai.custom_interface.UpdateListCaItem;
import com.skyfree.kinhnguyetmangthai.fragment.FragmentCalendar;
import com.skyfree.kinhnguyetmangthai.model.CalendarItem;
import com.skyfree.kinhnguyetmangthai.utils.Utils;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by KienBeu on 3/16/2018.
 */

public class FragmentAdapter extends FragmentStatePagerAdapter {
    private static final int NUM_ITEMS = 12;
    private ArrayList<Integer> page_indexes;
    private int mMonth, mYear;

    private ArrayList<CalendarItem> mListItem;
    private Context mContext;

    private UpdateListCaItem mUpdateListCaItem;
    private Calendar mCa = Calendar.getInstance();

    public FragmentAdapter(FragmentManager fm) {
        super(fm);

        page_indexes = new ArrayList<>();
        for (int i = 0; i < NUM_ITEMS; i++) {
            page_indexes.add(i);
        }
    }

    @Override
    public int getCount() {
        return page_indexes.size();
    }

    @Override
    public Fragment getItem(int position) {
        Integer index = page_indexes.get(position);

        switch (position) {
            case 0:
                if (mCa.get(Calendar.MONTH) - 5 < 1){
                    mMonth = 12;
                    mYear = mCa.get(Calendar.YEAR) - 1;
                }else if(mCa.get(Calendar.MONTH) > 11){
                    mMonth = 1;
                    mYear = mCa.get(Calendar.YEAR) + 1;
                }else {
                    mMonth = mCa.get(Calendar.MONTH) - 5;
                    mYear = mCa.get(Calendar.YEAR);
                }
                break;
            case 1:
                if (mCa.get(Calendar.MONTH)-4 < 1){
                    mMonth = 12;
                    mYear = mCa.get(Calendar.YEAR) - 1;
                }else if(mCa.get(Calendar.MONTH) > 11){
                    mMonth = 1;
                    mYear = mCa.get(Calendar.YEAR) + 1;
                }else {
                    mMonth = mCa.get(Calendar.MONTH) - 4;
                    mYear = mCa.get(Calendar.YEAR);
                }
                break;
            case 2:
                if (mCa.get(Calendar.MONTH)-3 < 1){
                    mMonth = 12;
                    mYear = mCa.get(Calendar.YEAR) - 1;
                }else if(mCa.get(Calendar.MONTH) > 11){
                    mMonth = 1;
                    mYear = mCa.get(Calendar.YEAR) + 1;
                }else {
                    mMonth = mCa.get(Calendar.MONTH) - 3;
                    mYear = mCa.get(Calendar.YEAR);
                }
                break;
            case 3:
                if (mCa.get(Calendar.MONTH)-2 < 1){
                    mMonth = 12;
                    mYear = mCa.get(Calendar.YEAR) - 1;
                }else if(mCa.get(Calendar.MONTH) > 11){
                    mMonth = 1;
                    mYear = mCa.get(Calendar.YEAR) + 1;
                }else {
                    mMonth = mCa.get(Calendar.MONTH) - 2;
                    mYear = mCa.get(Calendar.YEAR);
                }
                break;
            case 4:
                if (mCa.get(Calendar.MONTH)-1 < 1){
                    mMonth = 12;
                    mYear = mCa.get(Calendar.YEAR) - 1;
                }else if(mCa.get(Calendar.MONTH) > 11){
                    mMonth = 1;
                    mYear = mCa.get(Calendar.YEAR) + 1;
                }else {
                    mMonth = mCa.get(Calendar.MONTH) - 1;
                    mYear = mCa.get(Calendar.YEAR);
                }
                break;
            case 5:
                if (mCa.get(Calendar.MONTH) < 1){
                    mMonth = 12;
                    mYear = mCa.get(Calendar.YEAR) - 1;
                }else if(mCa.get(Calendar.MONTH) > 11){
                    mMonth = 1;
                    mYear = mCa.get(Calendar.YEAR) + 1;
                }else {
                    mMonth = mCa.get(Calendar.MONTH);
                    mYear = mCa.get(Calendar.YEAR);
                }
                break;
            case 6:
                if (mCa.get(Calendar.MONTH) < 1){
                    mMonth = 12;
                    mYear = mCa.get(Calendar.YEAR) - 1;
                }else if(mCa.get(Calendar.MONTH)+1 > 11){
                    mMonth = 1;
                    mYear = mCa.get(Calendar.YEAR) + 1;
                }else {
                    mMonth = mCa.get(Calendar.MONTH) + 1;
                    mYear = mCa.get(Calendar.YEAR);
                }
                break;
            case 7:
                if (mCa.get(Calendar.MONTH) < 1){
                    mMonth = 12;
                    mYear = mCa.get(Calendar.YEAR) - 1;
                }else if(mCa.get(Calendar.MONTH)+2 > 11){
                    mMonth = 1;
                    mYear = mCa.get(Calendar.YEAR) + 1;
                }else {
                    mMonth = mCa.get(Calendar.MONTH) + 2;
                    mYear = mCa.get(Calendar.YEAR);
                }
                break;
            case 8:
                if (mCa.get(Calendar.MONTH) < 1){
                    mMonth = 12;
                    mYear = mCa.get(Calendar.YEAR) - 1;
                }else if(mCa.get(Calendar.MONTH)+3 > 11){
                    mMonth = 1;
                    mYear = mCa.get(Calendar.YEAR) + 1;
                }else {
                    mMonth = mCa.get(Calendar.MONTH) + 3;
                    mYear = mCa.get(Calendar.YEAR);
                }
                break;
            case 9:
                if (mCa.get(Calendar.MONTH) < 1){
                    mMonth = 12;
                    mYear = mCa.get(Calendar.YEAR) - 1;
                }else if(mCa.get(Calendar.MONTH)+4 > 11){
                    mMonth = 1;
                    mYear = mCa.get(Calendar.YEAR) + 1;
                }else {
                    mMonth = mCa.get(Calendar.MONTH) + 4;
                    mYear = mCa.get(Calendar.YEAR);
                }
                break;
            case 10:
                if (mCa.get(Calendar.MONTH) < 1){
                    mMonth = 12;
                    mYear = mCa.get(Calendar.YEAR) - 1;
                }else if(mCa.get(Calendar.MONTH)+5 > 11){
                    mMonth = 1;
                    mYear = mCa.get(Calendar.YEAR) + 1;
                }else {
                    mMonth = mCa.get(Calendar.MONTH) + 5;
                    mYear = mCa.get(Calendar.YEAR);
                }
                break;
            case 11:
                if (mCa.get(Calendar.MONTH) < 1){
                    mMonth = 12;
                    mYear = mCa.get(Calendar.YEAR) - 1;
                }else if(mCa.get(Calendar.MONTH)+6 > 11){
                    mMonth = 1;
                    mYear = mCa.get(Calendar.YEAR) + 1;
                }else {
                    mMonth = mCa.get(Calendar.MONTH) + 6;
                    mYear = mCa.get(Calendar.YEAR);
                }
                break;

        }

        return FragmentCalendar.newInstance(index, mMonth, mYear);
    }

    void deletePage(int position) {
        if (canDelete()) {
            page_indexes.remove(position);
            notifyDataSetChanged();
        }
    }

    boolean canDelete() {
        return page_indexes.size() > 0;
    }

    // This is called when notifyDataSetChanged() is called
    @Override
    public int getItemPosition(Object object) {
        // refresh all fragments when data set changed
        return PagerAdapter.POSITION_NONE;
    }
}
