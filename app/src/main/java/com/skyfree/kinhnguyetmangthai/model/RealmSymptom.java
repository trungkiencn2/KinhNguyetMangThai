package com.skyfree.kinhnguyetmangthai.model;

import io.realm.RealmObject;

/**
 * Created by KienBeu on 3/26/2018.
 */

public class RealmSymptom extends RealmObject {
    private String mSymptom;

    public String getmSymptom() {
        return mSymptom;
    }

    public void setmSymptom(String mSymptom) {
        this.mSymptom = mSymptom;
    }

    public RealmSymptom() {

    }

    public RealmSymptom(String mSymptom) {

        this.mSymptom = mSymptom;
    }
}
