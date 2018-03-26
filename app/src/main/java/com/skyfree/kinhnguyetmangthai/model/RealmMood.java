package com.skyfree.kinhnguyetmangthai.model;

import io.realm.RealmObject;

/**
 * Created by KienBeu on 3/26/2018.
 */

public class RealmMood extends RealmObject{
    private String mMood;

    public String getmMood() {
        return mMood;
    }

    public void setmMood(String mMood) {
        this.mMood = mMood;
    }

    public RealmMood() {

    }

    public RealmMood(String mMood) {

        this.mMood = mMood;
    }
}
