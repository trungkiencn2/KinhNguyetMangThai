package com.skyfree.kinhnguyetmangthai.model;

/**
 * Created by KienBeu on 3/16/2018.
 */

public class CalendarItem {
    private Integer mDes;
    private String mDay;

    public Integer getmDes() {
        return mDes;
    }

    public void setmDes(Integer mDes) {
        this.mDes = mDes;
    }

    public String getmDay() {
        return mDay;
    }

    public void setmDay(String mDay) {
        this.mDay = mDay;
    }

    public CalendarItem() {
    }

    public CalendarItem(Integer mDes, String mDay) {
        this.mDes = mDes;
        this.mDay = mDay;
    }
}
