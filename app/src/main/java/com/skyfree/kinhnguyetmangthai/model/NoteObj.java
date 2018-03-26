package com.skyfree.kinhnguyetmangthai.model;

import java.util.ArrayList;
import java.util.Objects;

import io.realm.RealmList;
import io.realm.RealmObject;

/**
 * Created by KienBeu on 3/21/2018.
 */

public class NoteObj extends RealmObject{

    private int mNoteDate, mNoteMonth, mNoteYear, mNoteLuongKinh;
    private String mNoteNote;
    private float mNoteWeight, mNoteTemperature;

    private RealmList<RealmDrug> mListDrug;
    private RealmList<RealmSymptom> mListSymptom;
    private RealmList<RealmMood> mListMood;

    public int getmNoteDate() {
        return mNoteDate;
    }

    public void setmNoteDate(int mNoteDate) {
        this.mNoteDate = mNoteDate;
    }

    public int getmNoteMonth() {
        return mNoteMonth;
    }

    public void setmNoteMonth(int mNoteMonth) {
        this.mNoteMonth = mNoteMonth;
    }

    public int getmNoteYear() {
        return mNoteYear;
    }

    public void setmNoteYear(int mNoteYear) {
        this.mNoteYear = mNoteYear;
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

    public NoteObj() {

    }

    public NoteObj(int mNoteDate, int mNoteMonth, int mNoteYear, int mNoteLuongKinh, String mNoteNote, float mNoteWeight, float mNoteTemperature, RealmList<RealmDrug> mListDrug, RealmList<RealmSymptom> mListSymptom, RealmList<RealmMood> mListMood) {

        this.mNoteDate = mNoteDate;
        this.mNoteMonth = mNoteMonth;
        this.mNoteYear = mNoteYear;
        this.mNoteLuongKinh = mNoteLuongKinh;
        this.mNoteNote = mNoteNote;
        this.mNoteWeight = mNoteWeight;
        this.mNoteTemperature = mNoteTemperature;
        this.mListDrug = mListDrug;
        this.mListSymptom = mListSymptom;
        this.mListMood = mListMood;
    }
}
