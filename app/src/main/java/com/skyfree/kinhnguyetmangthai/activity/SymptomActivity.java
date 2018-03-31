package com.skyfree.kinhnguyetmangthai.activity;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.skyfree.kinhnguyetmangthai.R;
import com.skyfree.kinhnguyetmangthai.model.NoteObj;
import com.skyfree.kinhnguyetmangthai.model.RealmDrug;
import com.skyfree.kinhnguyetmangthai.model.RealmMood;
import com.skyfree.kinhnguyetmangthai.model.RealmSymptom;
import com.skyfree.kinhnguyetmangthai.utils.Utils;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmList;

public class SymptomActivity extends AppCompatActivity implements View.OnClickListener {

    private CheckBox mCbAbdominal, mCbAnxiet, mCbAstriction, mCbBackaches, mCbBloating,
            mCbBodyAches, mCbCervical, mCbChills, mCbConfused, mCbCramps, mCbCream,
            mCbDiarrhea, mCbDizziness, mCbDry, mCbDyspepsia, mCbFatigue, mCbHeadaches,
            mCbHectic, mCbHungry, mCbIllness, mCbInfluenza, mCbInsomnia, mCbIrritability,
            mCbIrritation, mCbItch, mCbMigraine, mCbNeckaches, mCbNightSweat, mCbPelvicPain,
            mCbPMS, mCbQueasiness, mCbRash, mCbShoulderAche, mCbSpotting, mCbSticky,
            mCbStress, mCbTensi, mCbWatery, mCbWeightGain, mCbWithBlood;

    private TextView mTvAbdominal, mTvAnxiet, mTvAstriction, mTvBackaches, mTvBloating,
            mTvBodyAches, mTvCervical, mTvChills, mTvConfused, mTvCramps, mTvCream,
            mTvDiarrhea, mTvDizziness, mTvDry, mTvDyspepsia, mTvFatigue, mTvHeadaches,
            mTvHectic, mTvHungry, mTvIllness, mTvInfluenza, mTvInsomnia, mTvIrritability,
            mTvIrritation, mTvItch, mTvMigraine, mTvNeckaches, mTvNightSweat, mTvPelvicPain,
            mTvPMS, mTvQueasiness, mTvRash, mTvShoulderAche, mTvSpotting, mTvSticky,
            mTvStress, mTvTensi, mTvWatery, mTvWeightGain, mTvWithBlood;

    private ImageView mImgBack, mImgDone;
    
    Realm realm;

    private NoteObj mNoteObj;
    private String mId;
    
    private ArrayList<String> mListAllSymptomForResult;
    private ArrayList<String> mArrSymptomTam;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_symptom);
        initView();
        addEvent();
    }

    private void addEvent() {
        realm = Realm.getDefaultInstance();
        mListAllSymptomForResult = new ArrayList<>();
        mArrSymptomTam = new ArrayList<>();

        mId = getIntent().getStringExtra(Utils.PUT_ID);
        mNoteObj = Utils.getNoteObj(realm, mId);
        if(mNoteObj != null){
            for(int i = 0; i<mNoteObj.getmListSymptom().size(); i++){
                if(!Utils.checkStringExist(mListAllSymptomForResult, mNoteObj.getmListSymptom().get(i).getmSymptom())){
                    mListAllSymptomForResult.add(mNoteObj.getmListSymptom().get(i).getmSymptom());
                }
            }
        }

        for(int i = 0; i<mListAllSymptomForResult.size(); i++){
            if(mListAllSymptomForResult.get(i).equals(getString(R.string.abdominal_cramps))) {
                mCbAbdominal.setChecked(true);
            }
            if (mListAllSymptomForResult.get(i).equals(getString(R.string.anxiety))){
                mCbAnxiet.setChecked(true);
            }
            if(mListAllSymptomForResult.get(i).equals(getString(R.string.astriction))){
                mCbAstriction.setChecked(true);
            }
            if(mListAllSymptomForResult.get(i).equals(getString(R.string.backaches))){
                mCbBackaches.setChecked(true);
            }
            if(mListAllSymptomForResult.get(i).equals(getString(R.string.bloating))){
                mCbBloating.setChecked(true);
            }
            if(mListAllSymptomForResult.get(i).equals(getString(R.string.body_aches))){
                mCbBodyAches.setChecked(true);
            }
            if(mListAllSymptomForResult.get(i).equals(getString(R.string.cervical_mucus))){
                mCbCervical.setChecked(true);
            }
            if(mListAllSymptomForResult.get(i).equals(getString(R.string.chills))){
                mCbChills.setChecked(true);
            }
            if(mListAllSymptomForResult.get(i).equals(getString(R.string.confused))){
                mCbConfused.setChecked(true);
            }
            if(mListAllSymptomForResult.get(i).equals(getString(R.string.cramps))){
                mCbCramps.setChecked(true);
            }
            if(mListAllSymptomForResult.get(i).equals(getString(R.string.creamy))) {
                mCbCream.setChecked(true);
            }
            if (mListAllSymptomForResult.get(i).equals(getString(R.string.diarrhea))){
                mCbDiarrhea.setChecked(true);
            }
            if(mListAllSymptomForResult.get(i).equals(getString(R.string.dizziness))){
                mCbDizziness.setChecked(true);
            }
            if(mListAllSymptomForResult.get(i).equals(getString(R.string.dry))){
                mCbDry.setChecked(true);
            }
            if(mListAllSymptomForResult.get(i).equals(getString(R.string.dyspepsia))){
                mCbDyspepsia.setChecked(true);
            }
            if(mListAllSymptomForResult.get(i).equals(getString(R.string.fatigue))){
                mCbFatigue.setChecked(true);
            }
            if(mListAllSymptomForResult.get(i).equals(getString(R.string.headaches))){
                mCbHeadaches.setChecked(true);
            }
            if(mListAllSymptomForResult.get(i).equals(getString(R.string.hectic_fever))){
                mCbHectic.setChecked(true);
            }
            if(mListAllSymptomForResult.get(i).equals(getString(R.string.hungry))){
                mCbHungry.setChecked(true);
            }
            if(mListAllSymptomForResult.get(i).equals(getString(R.string.illness))) {
                mCbIllness.setChecked(true);
            }
            if (mListAllSymptomForResult.get(i).equals(getString(R.string.influenza))){
                mCbInfluenza.setChecked(true);
            }
            if(mListAllSymptomForResult.get(i).equals(getString(R.string.insomnia))){
                mCbInsomnia.setChecked(true);
            }
            if(mListAllSymptomForResult.get(i).equals(getString(R.string.irritability))){
                mCbIrritability.setChecked(true);
            }
            if(mListAllSymptomForResult.get(i).equals(getString(R.string.irritation))){
                mCbIrritation.setChecked(true);
            }
            if(mListAllSymptomForResult.get(i).equals(getString(R.string.itch))){
                mCbItch.setChecked(true);
            }
            if(mListAllSymptomForResult.get(i).equals(getString(R.string.migraine))){
                mCbMigraine.setChecked(true);
            }
            if(mListAllSymptomForResult.get(i).equals(getString(R.string.neckaches))){
                mCbNeckaches.setChecked(true);
            }
            if(mListAllSymptomForResult.get(i).equals(getString(R.string.night_sweat))){
                mCbNightSweat.setChecked(true);
            }
            if(mListAllSymptomForResult.get(i).equals(getString(R.string.pelvic_pain))){
                mCbPelvicPain.setChecked(true);
            }
            if(mListAllSymptomForResult.get(i).equals(getString(R.string.pms))) {
                mCbPMS.setChecked(true);
            }
            if (mListAllSymptomForResult.get(i).equals(getString(R.string.queasiness))){
                mCbQueasiness.setChecked(true);
            }
            if(mListAllSymptomForResult.get(i).equals(getString(R.string.rash))){
                mCbRash.setChecked(true);
            }
            if(mListAllSymptomForResult.get(i).equals(getString(R.string.shoulder_ache))){
                mCbShoulderAche.setChecked(true);
            }
            if(mListAllSymptomForResult.get(i).equals(getString(R.string.spotting_bleeding))){
                mCbSpotting.setChecked(true);
            }
            if(mListAllSymptomForResult.get(i).equals(getString(R.string.sticky))){
                mCbSticky.setChecked(true);
            }
            if(mListAllSymptomForResult.get(i).equals(getString(R.string.stress))){
                mCbStress.setChecked(true);
            }
            if(mListAllSymptomForResult.get(i).equals(getString(R.string.tension))){
                mCbTensi.setChecked(true);
            }
            if(mListAllSymptomForResult.get(i).equals(getString(R.string.watery))){
                mCbWatery.setChecked(true);
            }
            if(mListAllSymptomForResult.get(i).equals(getString(R.string.weight_gain))){
                mCbWeightGain.setChecked(true);
            }
            if(mListAllSymptomForResult.get(i).equals(getString(R.string.with_blood))){
                mCbWithBlood.setChecked(true);
            }
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        realm.close();
    }

    private void initView() {
        mCbAbdominal = (CheckBox) findViewById(R.id.cb_symp_abdominal_cramps);
        mCbAnxiet = (CheckBox) findViewById(R.id.cb_symp_anxiety);
        mCbAstriction = (CheckBox) findViewById(R.id.cb_symp_astriction);
        mCbBackaches = (CheckBox) findViewById(R.id.cb_symp_backaches);
        mCbBloating = (CheckBox) findViewById(R.id.cb_symp_bloating);
        mCbBodyAches = (CheckBox) findViewById(R.id.cb_symp_body_aches);
        mCbCervical = (CheckBox) findViewById(R.id.cb_symp_cervical_mucus);
        mCbChills = (CheckBox) findViewById(R.id.cb_symp_chills);
        mCbConfused = (CheckBox) findViewById(R.id.cb_symp_confused);
        mCbCramps = (CheckBox) findViewById(R.id.cb_symp_cramps);
        mCbCream = (CheckBox) findViewById(R.id.cb_symp_creamy);
        mCbDiarrhea = (CheckBox) findViewById(R.id.cb_symp_diarrhea);
        mCbDizziness = (CheckBox) findViewById(R.id.cb_symp_dizziness);
        mCbDry = (CheckBox) findViewById(R.id.cb_symp_dry);
        mCbDyspepsia = (CheckBox) findViewById(R.id.cb_symp_dyspepsia);
        mCbFatigue = (CheckBox) findViewById(R.id.cb_symp_fatigue);
        mCbHeadaches = (CheckBox) findViewById(R.id.cb_symp_headaches);
        mCbHectic = (CheckBox) findViewById(R.id.cb_symp_hectic_fever);
        mCbHungry = (CheckBox) findViewById(R.id.cb_symp_hungry);
        mCbIllness = (CheckBox) findViewById(R.id.cb_symp_illness);
        mCbInfluenza = (CheckBox) findViewById(R.id.cb_symp_influenza);
        mCbInsomnia = (CheckBox) findViewById(R.id.cb_symp_insomnia);
        mCbIrritability = (CheckBox) findViewById(R.id.cb_symp_irritability);
        mCbIrritation = (CheckBox) findViewById(R.id.cb_symp_irritation);
        mCbItch = (CheckBox) findViewById(R.id.cb_symp_itch);
        mCbMigraine = (CheckBox) findViewById(R.id.cb_symp_migraine);
        mCbNeckaches = (CheckBox) findViewById(R.id.cb_symp_neckaches);
        mCbNightSweat = (CheckBox) findViewById(R.id.cb_symp_night_sweat);
        mCbPelvicPain = (CheckBox) findViewById(R.id.cb_symp_pelvic_pain);
        mCbPMS = (CheckBox) findViewById(R.id.cb_symp_pms);
        mCbQueasiness = (CheckBox) findViewById(R.id.cb_symp_queasiness);
        mCbRash = (CheckBox) findViewById(R.id.cb_symp_rash);
        mCbShoulderAche = (CheckBox) findViewById(R.id.cb_symp_shoulder_ache);
        mCbSpotting = (CheckBox) findViewById(R.id.cb_symp_spotting_bleeding);
        mCbSticky = (CheckBox) findViewById(R.id.cb_symp_sticky);
        mCbStress = (CheckBox) findViewById(R.id.cb_symp_stress);
        mCbTensi = (CheckBox) findViewById(R.id.cb_symp_tension);
        mCbWatery = (CheckBox) findViewById(R.id.cb_symp_watery);
        mCbWeightGain = (CheckBox) findViewById(R.id.cb_symp_weight_gain);
        mCbWithBlood = (CheckBox) findViewById(R.id.cb_symp_with_blood);
        mImgBack = (ImageView) findViewById(R.id.img_back_symptom_activity);
        mImgDone = (ImageView) findViewById(R.id.img_done_symptom_activity);
        mTvAbdominal = (TextView) findViewById(R.id.tv_symp_abdominal_cramps);
        mTvAnxiet = (TextView) findViewById(R.id.tv_symp_anxiety);
        mTvAstriction = (TextView) findViewById(R.id.tv_symp_astriction);
        mTvBackaches = (TextView) findViewById(R.id.tv_symp_backaches);
        mTvBloating = (TextView) findViewById(R.id.tv_symp_bloating);
        mTvBodyAches = (TextView) findViewById(R.id.tv_symp_body_aches);
        mTvCervical = (TextView) findViewById(R.id.tv_symp_cervical_mucus);
        mTvChills = (TextView) findViewById(R.id.tv_symp_chills);
        mTvConfused = (TextView) findViewById(R.id.tv_symp_confused);
        mTvCramps = (TextView) findViewById(R.id.tv_symp_cramps);
        mTvCream = (TextView) findViewById(R.id.tv_symp_creamy);
        mTvDiarrhea = (TextView) findViewById(R.id.tv_symp_diarrhea);
        mTvDizziness = (TextView) findViewById(R.id.tv_symp_dizziness);
        mTvDry = (TextView) findViewById(R.id.tv_symp_dry);
        mTvDyspepsia = (TextView) findViewById(R.id.tv_symp_dyspepsia);
        mTvFatigue = (TextView) findViewById(R.id.tv_symp_fatique);
        mTvHeadaches = (TextView) findViewById(R.id.tv_symp_headaches);
        mTvHectic = (TextView) findViewById(R.id.tv_symp_hectic_fever);
        mTvHungry = (TextView) findViewById(R.id.tv_symp_hungry);
        mTvIllness = (TextView) findViewById(R.id.tv_symp_illness);
        mTvInfluenza = (TextView) findViewById(R.id.tv_symp_influenza);
        mTvInsomnia = (TextView) findViewById(R.id.tv_symp_insomnia);
        mTvIrritability = (TextView) findViewById(R.id.tv_symp_irritability);
        mTvIrritation = (TextView) findViewById(R.id.tv_symp_irritation);
        mTvItch = (TextView) findViewById(R.id.tv_symp_itch);
        mTvMigraine = (TextView) findViewById(R.id.tv_symp_migraine);
        mTvNeckaches = (TextView) findViewById(R.id.tv_symp_neckaches);
        mTvNightSweat = (TextView) findViewById(R.id.tv_symp_night_sweat);
        mTvPelvicPain = (TextView) findViewById(R.id.tv_symp_pelvic_pain);
        mTvPMS = (TextView) findViewById(R.id.tv_symp_pms);
        mTvQueasiness = (TextView) findViewById(R.id.tv_symp_queasiness);
        mTvRash = (TextView) findViewById(R.id.tv_symp_rash);
        mTvShoulderAche = (TextView) findViewById(R.id.tv_symp_shoulder_ache);
        mTvSpotting = (TextView) findViewById(R.id.tv_symp_spotting_bleeding);
        mTvSticky = (TextView) findViewById(R.id.tv_symp_sticky);
        mTvStress = (TextView) findViewById(R.id.tv_symp_stress);
        mTvTensi = (TextView) findViewById(R.id.tv_symp_tension);
        mTvWatery = (TextView) findViewById(R.id.tv_symp_watery);
        mTvWeightGain = (TextView) findViewById(R.id.tv_symp_weight_gain);
        mTvWithBlood = (TextView) findViewById(R.id.tv_symp_with_blood);

        mImgBack.setOnClickListener(this);
        mImgDone.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.img_back_symptom_activity:
                finish();
                break;
            case R.id.img_done_symptom_activity:

                clickDone();

                RealmList<RealmSymptom> mRealmList = new RealmList<>();
                for(int i = 0; i<mListAllSymptomForResult.size(); i++){
                    mRealmList.add(new RealmSymptom(mListAllSymptomForResult.get(i)));
                }

                if(mNoteObj != null){
                    Utils.updateListSymptom(realm, mId, mListAllSymptomForResult);
                }else {
                    Utils.insertNoteObj(realm, new NoteObj(mId, 0, "", 0, 0, new RealmList<RealmDrug>(), mRealmList, new RealmList<RealmMood>()));
                }
                finish();
                break;
        }
    }

    private void clickDone(){
        mListAllSymptomForResult.clear();
        if (mCbAbdominal.isChecked()) {
            if (!Utils.checkStringExist(mListAllSymptomForResult, mTvAbdominal.getText().toString())) {
                mListAllSymptomForResult.add(mTvAbdominal.getText().toString());
            }
        } else {
            if (Utils.checkStringExist(mListAllSymptomForResult, mTvAbdominal.getText().toString())) {
                mListAllSymptomForResult.remove(mTvAbdominal.getText().toString());
            }
        }

        if (mCbAnxiet.isChecked()) {
            if (!Utils.checkStringExist(mListAllSymptomForResult, mTvAnxiet.getText().toString())) {
                mListAllSymptomForResult.add(mTvAnxiet.getText().toString());
            }
        } else {
            if (Utils.checkStringExist(mListAllSymptomForResult, mTvAnxiet.getText().toString())) {
                mListAllSymptomForResult.remove(mTvAnxiet.getText().toString());
            }
        }

        if (mCbAstriction.isChecked()) {
            if (!Utils.checkStringExist(mListAllSymptomForResult, mTvAstriction.getText().toString())) {
                mListAllSymptomForResult.add(mTvAstriction.getText().toString());
            }
        } else {
            if (Utils.checkStringExist(mListAllSymptomForResult, mTvAstriction.getText().toString())) {
                mListAllSymptomForResult.remove(mTvAstriction.getText().toString());
            }
        }

        if (mCbBackaches.isChecked()) {
            if (!Utils.checkStringExist(mListAllSymptomForResult, mTvBackaches.getText().toString())) {
                mListAllSymptomForResult.add(mTvBackaches.getText().toString());
            }
        } else {
            if (Utils.checkStringExist(mListAllSymptomForResult, mTvBackaches.getText().toString())) {
                mListAllSymptomForResult.remove(mTvBackaches.getText().toString());
            }
        }

        if (mCbBloating.isChecked()) {
            if (!Utils.checkStringExist(mListAllSymptomForResult, mTvBloating.getText().toString())) {
                mListAllSymptomForResult.add(mTvBloating.getText().toString());
            }
        } else {
            if (Utils.checkStringExist(mListAllSymptomForResult, mTvBloating.getText().toString())) {
                mListAllSymptomForResult.remove(mTvBloating.getText().toString());
            }
        }

        if (mCbBodyAches.isChecked()) {
            if (!Utils.checkStringExist(mListAllSymptomForResult, mTvBodyAches.getText().toString())) {
                mListAllSymptomForResult.add(mTvBodyAches.getText().toString());
            }
        } else {
            if (Utils.checkStringExist(mListAllSymptomForResult, mTvBodyAches.getText().toString())) {
                mListAllSymptomForResult.remove(mTvBodyAches.getText().toString());
            }
        }

        if (mCbCervical.isChecked()) {
            if (!Utils.checkStringExist(mListAllSymptomForResult, mTvCervical.getText().toString())) {
                mListAllSymptomForResult.add(mTvCervical.getText().toString());
            }
        } else {
            if (Utils.checkStringExist(mListAllSymptomForResult, mTvCervical.getText().toString())) {
                mListAllSymptomForResult.remove(mTvCervical.getText().toString());
            }
        }

        if (mCbChills.isChecked()) {
            if (!Utils.checkStringExist(mListAllSymptomForResult, mTvChills.getText().toString())) {
                mListAllSymptomForResult.add(mTvChills.getText().toString());
            }
        } else {
            if (Utils.checkStringExist(mListAllSymptomForResult, mTvChills.getText().toString())) {
                mListAllSymptomForResult.remove(mTvChills.getText().toString());
            }
        }

        if (mCbConfused.isChecked()) {
            if (!Utils.checkStringExist(mListAllSymptomForResult, mTvConfused.getText().toString())) {
                mListAllSymptomForResult.add(mTvConfused.getText().toString());
            }
        } else {
            if (Utils.checkStringExist(mListAllSymptomForResult, mTvConfused.getText().toString())) {
                mListAllSymptomForResult.remove(mTvConfused.getText().toString());
            }
        }

        if (mCbCramps.isChecked()) {
            if (!Utils.checkStringExist(mListAllSymptomForResult, mTvCramps.getText().toString())) {
                mListAllSymptomForResult.add(mTvCramps.getText().toString());
            }
        } else {
            if (Utils.checkStringExist(mListAllSymptomForResult, mTvCramps.getText().toString())) {
                mListAllSymptomForResult.remove(mTvCramps.getText().toString());
            }
        }

        if (mCbCream.isChecked()) {
            if (!Utils.checkStringExist(mListAllSymptomForResult, mTvCream.getText().toString())) {
                mListAllSymptomForResult.add(mTvCream.getText().toString());
            }
        } else {
            if (Utils.checkStringExist(mListAllSymptomForResult, mTvCream.getText().toString())) {
                mListAllSymptomForResult.remove(mTvCream.getText().toString());
            }
        }

        if (mCbDiarrhea.isChecked()) {
            if (!Utils.checkStringExist(mListAllSymptomForResult, mTvDiarrhea.getText().toString())) {
                mListAllSymptomForResult.add(mTvDiarrhea.getText().toString());
            }
        } else {
            if (Utils.checkStringExist(mListAllSymptomForResult, mTvDiarrhea.getText().toString())) {
                mListAllSymptomForResult.remove(new RealmSymptom(mTvDiarrhea.getText().toString()));
            }
        }

        if (mCbDizziness.isChecked()) {
            if (!Utils.checkStringExist(mListAllSymptomForResult, mTvDizziness.getText().toString())) {
                mListAllSymptomForResult.add(mTvDizziness.getText().toString());
            }
        } else {
            if (Utils.checkStringExist(mListAllSymptomForResult, mTvDizziness.getText().toString())) {
                mListAllSymptomForResult.remove(new RealmSymptom(mTvDizziness.getText().toString()));
            }
        }

        if (mCbDry.isChecked()) {
            if (!Utils.checkStringExist(mListAllSymptomForResult, mTvDry.getText().toString())) {
                mListAllSymptomForResult.add(mTvDry.getText().toString());
            }
        } else {
            if (Utils.checkStringExist(mListAllSymptomForResult, mTvDry.getText().toString())) {
                mListAllSymptomForResult.remove(mTvDry.getText().toString());
            }
        }

        if (mCbDyspepsia.isChecked()) {
            if (!Utils.checkStringExist(mListAllSymptomForResult, mTvDyspepsia.getText().toString())) {
                mListAllSymptomForResult.add(mTvDyspepsia.getText().toString());
            }
        } else {
            if (Utils.checkStringExist(mListAllSymptomForResult, mTvDyspepsia.getText().toString())) {
                mListAllSymptomForResult.remove(mTvDyspepsia.getText().toString());
            }
        }

        if (mCbFatigue.isChecked()) {
            if (!Utils.checkStringExist(mListAllSymptomForResult, mTvFatigue.getText().toString())) {
                mListAllSymptomForResult.add(mTvFatigue.getText().toString());
            }
        } else {
            if (Utils.checkStringExist(mListAllSymptomForResult, mTvFatigue.getText().toString())) {
                mListAllSymptomForResult.remove(mTvFatigue.getText().toString());
            }
        }

        if (mCbHeadaches.isChecked()) {
            if (!Utils.checkStringExist(mListAllSymptomForResult, mTvHeadaches.getText().toString())) {
                mListAllSymptomForResult.add(mTvHeadaches.getText().toString());
            }
        } else {
            if (Utils.checkStringExist(mListAllSymptomForResult, mTvHeadaches.getText().toString())) {
                mListAllSymptomForResult.remove(mTvHeadaches.getText().toString());
            }
        }

        if (mCbHectic.isChecked()) {
            if (!Utils.checkStringExist(mListAllSymptomForResult, mTvHectic.getText().toString())) {
                mListAllSymptomForResult.add(mTvHectic.getText().toString());
            }
        } else {
            if (Utils.checkStringExist(mListAllSymptomForResult, mTvHectic.getText().toString())) {
                mListAllSymptomForResult.remove(mTvHectic.getText().toString());
            }
        }

        if (mCbHungry.isChecked()) {
            if (!Utils.checkStringExist(mListAllSymptomForResult, mTvHungry.getText().toString())) {
                mListAllSymptomForResult.add(mTvHungry.getText().toString());
            }
        } else {
            if (Utils.checkStringExist(mListAllSymptomForResult, mTvHungry.getText().toString())) {
                mListAllSymptomForResult.remove(mTvHungry.getText().toString());
            }
        }

        if (mCbIllness.isChecked()) {
            if (!Utils.checkStringExist(mListAllSymptomForResult, mTvIllness.getText().toString())) {
                mListAllSymptomForResult.add(mTvIllness.getText().toString());
            }
        } else {
            if (Utils.checkStringExist(mListAllSymptomForResult, mTvIllness.getText().toString())) {
                mListAllSymptomForResult.remove(mTvIllness.getText().toString());
            }
        }

        if (mCbInfluenza.isChecked()) {
            if (!Utils.checkStringExist(mListAllSymptomForResult, mTvInfluenza.getText().toString())) {
                mListAllSymptomForResult.add(mTvInfluenza.getText().toString());
            }
        } else {
            if (Utils.checkStringExist(mListAllSymptomForResult, mTvInfluenza.getText().toString())) {
                mListAllSymptomForResult.remove(mTvInfluenza.getText().toString());
            }
        }
        if (mCbInsomnia.isChecked()) {
            if (!Utils.checkStringExist(mListAllSymptomForResult, mTvInsomnia.getText().toString())) {
                mListAllSymptomForResult.add(mTvInsomnia.getText().toString());
            }
        } else {
            if (Utils.checkStringExist(mListAllSymptomForResult, mTvInsomnia.getText().toString())) {
                mListAllSymptomForResult.remove(mTvInsomnia.getText().toString());
            }
        }

        if (mCbIrritability.isChecked()) {
            if (!Utils.checkStringExist(mListAllSymptomForResult, mTvIrritability.getText().toString())) {
                mListAllSymptomForResult.add(mTvIrritability.getText().toString());
            }
        } else {
            if (Utils.checkStringExist(mListAllSymptomForResult, mTvIrritability.getText().toString())) {
                mListAllSymptomForResult.remove(new RealmSymptom(mTvIrritability.getText().toString()));
            }
        }

        if (mCbIrritation.isChecked()) {
            if (!Utils.checkStringExist(mListAllSymptomForResult, mTvIrritation.getText().toString())) {
                mListAllSymptomForResult.add(mTvIrritation.getText().toString());
            }
        } else {
            if (Utils.checkStringExist(mListAllSymptomForResult, mTvIrritation.getText().toString())) {
                mListAllSymptomForResult.remove(mTvIrritation.getText().toString());
            }
        }

        if (mCbItch.isChecked()) {
            if (!Utils.checkStringExist(mListAllSymptomForResult, mTvItch.getText().toString())) {
                mListAllSymptomForResult.add(mTvItch.getText().toString());
            }
        } else {
            if (Utils.checkStringExist(mListAllSymptomForResult, mTvItch.getText().toString())) {
                mListAllSymptomForResult.remove(mTvItch.getText().toString());
            }
        }

        if (mCbMigraine.isChecked()) {
            if (!Utils.checkStringExist(mListAllSymptomForResult, mTvMigraine.getText().toString())) {
                mListAllSymptomForResult.add(mTvMigraine.getText().toString());
            }
        } else {
            if (Utils.checkStringExist(mListAllSymptomForResult, mTvMigraine.getText().toString())) {
                mListAllSymptomForResult.remove(mTvMigraine.getText().toString());
            }
        }

        if (mCbNeckaches.isChecked()) {
            if (!Utils.checkStringExist(mListAllSymptomForResult, mTvNeckaches.getText().toString())) {
                mListAllSymptomForResult.add(mTvNeckaches.getText().toString());
            }
        } else {
            if (Utils.checkStringExist(mListAllSymptomForResult, mTvNeckaches.getText().toString())) {
                mListAllSymptomForResult.remove(mTvNeckaches.getText().toString());
            }
        }

        if (mCbNightSweat.isChecked()) {
            if (!Utils.checkStringExist(mListAllSymptomForResult, mTvNightSweat.getText().toString())) {
                mListAllSymptomForResult.add(mTvNightSweat.getText().toString());
            }
        } else {
            if (Utils.checkStringExist(mListAllSymptomForResult, mTvNightSweat.getText().toString())) {
                mListAllSymptomForResult.remove(mTvNightSweat.getText().toString());
            }
        }

        if (mCbPelvicPain.isChecked()) {
            if (!Utils.checkStringExist(mListAllSymptomForResult, mTvPelvicPain.getText().toString())) {
                mListAllSymptomForResult.add(mTvPelvicPain.getText().toString());
            }
        } else {
            if (Utils.checkStringExist(mListAllSymptomForResult, mTvPelvicPain.getText().toString())) {
                mListAllSymptomForResult.remove(mTvPelvicPain.getText().toString());
            }
        }

        if (mCbPMS.isChecked()) {
            if (!Utils.checkStringExist(mListAllSymptomForResult, mTvPMS.getText().toString())) {
                mListAllSymptomForResult.add(mTvPMS.getText().toString());
            }
        } else {
            if (Utils.checkStringExist(mListAllSymptomForResult, mTvPMS.getText().toString())) {
                mListAllSymptomForResult.remove(mTvPMS.getText().toString());
            }
        }

        if (mCbQueasiness.isChecked()) {
            if (!Utils.checkStringExist(mListAllSymptomForResult, mTvQueasiness.getText().toString())) {
                mListAllSymptomForResult.add(mTvQueasiness.getText().toString());
            }
        } else {
            if (Utils.checkStringExist(mListAllSymptomForResult, mTvQueasiness.getText().toString())) {
                mListAllSymptomForResult.remove(mTvQueasiness.getText().toString());
            }
        }
        if (mCbRash.isChecked()) {
            if (!Utils.checkStringExist(mListAllSymptomForResult, mTvRash.getText().toString())) {
                mListAllSymptomForResult.add(mTvRash.getText().toString());
            }
        } else {
            if (Utils.checkStringExist(mListAllSymptomForResult, mTvRash.getText().toString())) {
                mListAllSymptomForResult.remove(mTvRash.getText().toString());
            }
        }

        if (mCbShoulderAche.isChecked()) {
            if (!Utils.checkStringExist(mListAllSymptomForResult, mTvShoulderAche.getText().toString())) {
                mListAllSymptomForResult.add(mTvShoulderAche.getText().toString());
            }
        } else {
            if (Utils.checkStringExist(mListAllSymptomForResult, mTvShoulderAche.getText().toString())) {
                mListAllSymptomForResult.remove(new RealmSymptom(mTvShoulderAche.getText().toString()));
            }
        }

        if (mCbSpotting.isChecked()) {
            if (!Utils.checkStringExist(mListAllSymptomForResult, mTvSpotting.getText().toString())) {
                mListAllSymptomForResult.add(mTvSpotting.getText().toString());
            }
        } else {
            if (Utils.checkStringExist(mListAllSymptomForResult, mTvSpotting.getText().toString())) {
                mListAllSymptomForResult.remove(mTvSpotting.getText().toString());
            }
        }

        if (mCbSticky.isChecked()) {
            if (!Utils.checkStringExist(mListAllSymptomForResult, mTvSticky.getText().toString())) {
                mListAllSymptomForResult.add(mTvSticky.getText().toString());
            }
        } else {
            if (Utils.checkStringExist(mListAllSymptomForResult, mTvSticky.getText().toString())) {
                mListAllSymptomForResult.remove(mTvSticky.getText().toString());
            }
        }

        if (mCbStress.isChecked()) {
            if (!Utils.checkStringExist(mListAllSymptomForResult, mTvStress.getText().toString())) {
                mListAllSymptomForResult.add(mTvStress.getText().toString());
            }
        } else {
            if (Utils.checkStringExist(mListAllSymptomForResult, mTvStress.getText().toString())) {
                mListAllSymptomForResult.remove(mTvStress.getText().toString());
            }
        }

        if (mCbTensi.isChecked()) {
            if (!Utils.checkStringExist(mListAllSymptomForResult, mTvTensi.getText().toString())) {
                mListAllSymptomForResult.add(mTvTensi.getText().toString());
            }
        } else {
            if (Utils.checkStringExist(mListAllSymptomForResult, mTvTensi.getText().toString())) {
                mListAllSymptomForResult.remove(mTvTensi.getText().toString());
            }
        }

        if (mCbWatery.isChecked()) {
            if (!Utils.checkStringExist(mListAllSymptomForResult, mTvWatery.getText().toString())) {
                mListAllSymptomForResult.add(mTvWatery.getText().toString());
            }
        } else {
            if (Utils.checkStringExist(mListAllSymptomForResult, mTvWatery.getText().toString())) {
                mListAllSymptomForResult.remove(mTvWatery.getText().toString());
            }
        }

        if (mCbWeightGain.isChecked()) {
            if (!Utils.checkStringExist(mListAllSymptomForResult, mTvWeightGain.getText().toString())) {
                mListAllSymptomForResult.add(mTvWeightGain.getText().toString());
            }
        } else {
            if (Utils.checkStringExist(mListAllSymptomForResult, mTvWeightGain.getText().toString())) {
                mListAllSymptomForResult.remove(mTvWeightGain.getText().toString());
            }
        }

        if (mCbWithBlood.isChecked()) {
            if (!Utils.checkStringExist(mListAllSymptomForResult, mTvWithBlood.getText().toString())) {
                mListAllSymptomForResult.add(mTvWithBlood.getText().toString());
            }
        } else {
            if (Utils.checkStringExist(mListAllSymptomForResult, mTvWithBlood.getText().toString())) {
                mListAllSymptomForResult.remove(mTvWithBlood.getText().toString());
            }
        }
    }
}
