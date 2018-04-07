package com.skyfree.kinhnguyetmangthai.model;

/**
 * Created by KienBeu on 4/5/2018.
 */

public class HoiCham {
    private int mImg;
    private String mName;

    public HoiCham() {
    }

    public int getmImg() {

        return mImg;
    }

    public void setmImg(int mImg) {
        this.mImg = mImg;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public HoiCham(int mImg, String mName) {

        this.mImg = mImg;
        this.mName = mName;
    }
}
