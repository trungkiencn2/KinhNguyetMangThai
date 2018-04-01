package com.skyfree.kinhnguyetmangthai.model;

/**
 * Created by KienBeu on 3/16/2018.
 */

public class CalendarItem {
    private Integer mBg, mDes;
    private String mDate, mMonth, mYear;

    public Integer getmBg() {
        return mBg;
    }

    public void setmBg(Integer mBg) {
        this.mBg = mBg;
    }

    public Integer getmDes() {
        return mDes;
    }

    public void setmDes(Integer mDes) {
        this.mDes = mDes;
    }

    public String getmDate() {
        return mDate;
    }

    public void setmDate(String mDate) {
        this.mDate = mDate;
    }

    public String getmMonth() {
        return mMonth;
    }

    public void setmMonth(String mMonth) {
        this.mMonth = mMonth;
    }

    public String getmYear() {
        return mYear;
    }

    public void setmYear(String mYear) {
        this.mYear = mYear;
    }



    public CalendarItem() {
    }

    public CalendarItem(Integer mBg, Integer mDes, String mDate, String mMonth, String mYear) {
        this.mBg = mBg;
        this.mDes = mDes;
        this.mDate = mDate;
        this.mMonth = mMonth;
        this.mYear = mYear;
    }
}
