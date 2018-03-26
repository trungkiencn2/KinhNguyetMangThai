package com.skyfree.kinhnguyetmangthai.model;

import io.realm.RealmObject;

/**
 * Created by KienBeu on 3/26/2018.
 */

public class RealmDrug extends RealmObject{
    private String mDrug;

    public String getmDrug() {
        return mDrug;
    }

    public void setmDrug(String mDrug) {
        this.mDrug = mDrug;
    }

    public RealmDrug() {

    }

    public RealmDrug(String mDrug) {

        this.mDrug = mDrug;
    }
}
