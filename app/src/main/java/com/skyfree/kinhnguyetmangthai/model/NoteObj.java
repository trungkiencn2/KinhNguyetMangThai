package com.skyfree.kinhnguyetmangthai.model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Objects;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by KienBeu on 3/21/2018.
 */

public class NoteObj extends RealmObject{
    @PrimaryKey
    private String id;
    private int mNoteLuongKinh;
    private String mNoteNote;
    private float mNoteWeight, mNoteTemperature;

    private RealmList<RealmDrug> mListDrug;
    private RealmList<RealmSymptom> mListSymptom;
    private RealmList<RealmMood> mListMood;

    public NoteObj() {
    }

    public NoteObj(String id, int mNoteLuongKinh, String mNoteNote, float mNoteWeight, float mNoteTemperature, RealmList<RealmDrug> mListDrug, RealmList<RealmSymptom> mListSymptom, RealmList<RealmMood> mListMood) {

        this.id = id;
        this.mNoteLuongKinh = mNoteLuongKinh;
        this.mNoteNote = mNoteNote;
        this.mNoteWeight = mNoteWeight;
        this.mNoteTemperature = mNoteTemperature;
        this.mListDrug = mListDrug;
        this.mListSymptom = mListSymptom;
        this.mListMood = mListMood;
    }

    public String getId() {

        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getmNoteLuongKinh() {
        return mNoteLuongKinh;
    }

    public void setmNoteLuongKinh(int mNoteLuongKinh) {
        this.mNoteLuongKinh = mNoteLuongKinh;
    }

    public String getmNoteNote() {
        return mNoteNote;
    }

    public void setmNoteNote(String mNoteNote) {
        this.mNoteNote = mNoteNote;
    }

    public float getmNoteWeight() {
        return mNoteWeight;
    }

    public void setmNoteWeight(float mNoteWeight) {
        this.mNoteWeight = mNoteWeight;
    }

    public float getmNoteTemperature() {
        return mNoteTemperature;
    }

    public void setmNoteTemperature(float mNoteTemperature) {
        this.mNoteTemperature = mNoteTemperature;
    }

    public RealmList<RealmDrug> getmListDrug() {
        return mListDrug;
    }

    public void setmListDrug(RealmList<RealmDrug> mListDrug) {
        this.mListDrug = mListDrug;
    }

    public RealmList<RealmSymptom> getmListSymptom() {
        return mListSymptom;
    }

    public void setmListSymptom(RealmList<RealmSymptom> mListSymptom) {
        this.mListSymptom = mListSymptom;
    }

    public RealmList<RealmMood> getmListMood() {
        return mListMood;
    }

    public void setmListMood(RealmList<RealmMood> mListMood) {
        this.mListMood = mListMood;
    }
}
