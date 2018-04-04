package com.skyfree.kinhnguyetmangthai.model;

/**
 * Created by KienBeu on 4/4/2018.
 */

public class Setting {
    private int mImg, mType;
    private String mName, mCountDay;

    public int getmImg() {
        return mImg;
    }

    public void setmImg(int mImg) {
        this.mImg = mImg;
    }

    public int getmType() {
        return mType;
    }

    public void setmType(int mType) {
        this.mType = mType;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public String getmCountDay() {
        return mCountDay;
    }

    public void setmCountDay(String mCountDay) {
        this.mCountDay = mCountDay;
    }

    public Setting() {

    }

    public Setting(int mImg, int mType, String mName, String mCountDay) {

        this.mImg = mImg;
        this.mType = mType;
        this.mName = mName;
        this.mCountDay = mCountDay;
    }
}
