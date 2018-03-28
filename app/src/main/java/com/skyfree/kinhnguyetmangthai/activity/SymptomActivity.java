package com.skyfree.kinhnguyetmangthai.activity;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.skyfree.kinhnguyetmangthai.R;
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

    private RealmList<RealmSymptom> mListSymptom;
    Realm realm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_symptom);
        initView();
        addEvent();
    }

    private void addEvent() {
        mListSymptom = new RealmList<>();
//        realm = Realm.getInstance(this);
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

        mCbAbdominal.setOnClickListener(this);
        mCbAnxiet.setOnClickListener(this);
        mCbAstriction.setOnClickListener(this);
        mCbBackaches.setOnClickListener(this);
        mCbBloating.setOnClickListener(this);
        mCbBodyAches.setOnClickListener(this);
        mCbCervical.setOnClickListener(this);
        mCbChills.setOnClickListener(this);
        mCbConfused.setOnClickListener(this);
        mCbCramps.setOnClickListener(this);
        mCbCream.setOnClickListener(this);
        mCbDiarrhea.setOnClickListener(this);
        mCbDizziness.setOnClickListener(this);
        mCbDry.setOnClickListener(this);
        mCbDyspepsia.setOnClickListener(this);
        mCbFatigue.setOnClickListener(this);
        mCbHeadaches.setOnClickListener(this);
        mCbHectic.setOnClickListener(this);
        mCbHungry.setOnClickListener(this);
        mCbIllness.setOnClickListener(this);
        mCbInfluenza.setOnClickListener(this);
        mCbInsomnia.setOnClickListener(this);
        mCbIrritability.setOnClickListener(this);
        mCbIrritation.setOnClickListener(this);
        mCbItch.setOnClickListener(this);
        mCbMigraine.setOnClickListener(this);
        mCbNeckaches.setOnClickListener(this);
        mCbNightSweat.setOnClickListener(this);
        mCbPelvicPain.setOnClickListener(this);
        mCbPMS.setOnClickListener(this);
        mCbQueasiness.setOnClickListener(this);
        mCbRash.setOnClickListener(this);
        mCbShoulderAche.setOnClickListener(this);
        mCbSpotting.setOnClickListener(this);
        mCbSticky.setOnClickListener(this);
        mCbStress.setOnClickListener(this);
        mCbTensi.setOnClickListener(this);
        mCbWatery.setOnClickListener(this);
        mCbWeightGain.setOnClickListener(this);
        mCbWithBlood.setOnClickListener(this);
        mImgBack.setOnClickListener(this);
        mImgDone.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.cb_symp_abdominal_cramps:
                if (mCbAbdominal.isChecked()) {
                    if (!Utils.checkSymptomExist(mListSymptom, mTvAbdominal.getText().toString())) {
                        mListSymptom.add(new RealmSymptom(mTvAbdominal.getText().toString()));
                    }
                } else {
                    if (Utils.checkSymptomExist(mListSymptom, mTvAbdominal.getText().toString())) {
                        mListSymptom.remove(new RealmSymptom(mTvAbdominal.getText().toString()));
                    }
                }
                break;
            case R.id.cb_symp_anxiety:
                if (mCbAnxiet.isChecked()) {
                    if (!Utils.checkSymptomExist(mListSymptom, mTvAnxiet.getText().toString())) {
                        mListSymptom.add(new RealmSymptom(mTvAnxiet.getText().toString()));
                    }
                } else {
                    if (Utils.checkSymptomExist(mListSymptom, mTvAnxiet.getText().toString())) {
                        mListSymptom.remove(new RealmSymptom(mTvAnxiet.getText().toString()));
                    }
                }
                break;
            case R.id.cb_symp_astriction:
                if (mCbAstriction.isChecked()) {
                    if (!Utils.checkSymptomExist(mListSymptom, mTvAstriction.getText().toString())) {
                        mListSymptom.add(new RealmSymptom(mTvAstriction.getText().toString()));
                    }
                } else {
                    if (Utils.checkSymptomExist(mListSymptom, mTvAstriction.getText().toString())) {
                        mListSymptom.remove(new RealmSymptom(mTvAstriction.getText().toString()));
                    }
                }
                break;
            case R.id.cb_symp_backaches:
                if (mCbBackaches.isChecked()) {
                    if (!Utils.checkSymptomExist(mListSymptom, mTvBackaches.getText().toString())) {
                        mListSymptom.add(new RealmSymptom(mTvBackaches.getText().toString()));
                    }
                } else {
                    if (Utils.checkSymptomExist(mListSymptom, mTvBackaches.getText().toString())) {
                        mListSymptom.remove(new RealmSymptom(mTvBackaches.getText().toString()));
                    }
                }
                break;
            case R.id.cb_symp_bloating:
                if (mCbBloating.isChecked()) {
                    if (!Utils.checkSymptomExist(mListSymptom, mTvBloating.getText().toString())) {
                        mListSymptom.add(new RealmSymptom(mTvBloating.getText().toString()));
                    }
                } else {
                    if (Utils.checkSymptomExist(mListSymptom, mTvBloating.getText().toString())) {
                        mListSymptom.remove(new RealmSymptom(mTvBloating.getText().toString()));
                    }
                }
                break;
            case R.id.cb_symp_body_aches:
                if (mCbBodyAches.isChecked()) {
                    if (!Utils.checkSymptomExist(mListSymptom, mCbBodyAches.getText().toString())) {
                        mListSymptom.add(new RealmSymptom(mCbBodyAches.getText().toString()));
                    }
                } else {
                    if (Utils.checkSymptomExist(mListSymptom, mCbBodyAches.getText().toString())) {
                        mListSymptom.remove(new RealmSymptom(mCbBodyAches.getText().toString()));
                    }
                }
                break;
            case R.id.cb_symp_cervical_mucus:
                if (mCbCervical.isChecked()) {
                    if (!Utils.checkSymptomExist(mListSymptom, mTvCervical.getText().toString())) {
                        mListSymptom.add(new RealmSymptom(mTvCervical.getText().toString()));
                    }
                } else {
                    if (Utils.checkSymptomExist(mListSymptom, mTvCervical.getText().toString())) {
                        mListSymptom.remove(new RealmSymptom(mTvCervical.getText().toString()));
                    }
                }
                break;
            case R.id.cb_symp_chills:
                if (mCbChills.isChecked()) {
                    if (!Utils.checkSymptomExist(mListSymptom, mTvChills.getText().toString())) {
                        mListSymptom.add(new RealmSymptom(mTvChills.getText().toString()));
                    }
                } else {
                    if (Utils.checkSymptomExist(mListSymptom, mTvChills.getText().toString())) {
                        mListSymptom.remove(new RealmSymptom(mTvChills.getText().toString()));
                    }
                }
                break;
            case R.id.cb_symp_confused:
                if (mListSymptom.size() > 0) {
                    if (mCbConfused.isChecked()) {
                        if (!Utils.checkSymptomExist(mListSymptom, mTvConfused.getText().toString())) {
                            mListSymptom.add(new RealmSymptom(mTvConfused.getText().toString()));
                        }
                    } else {
                        if (Utils.checkSymptomExist(mListSymptom, mTvConfused.getText().toString())) {
                            mListSymptom.remove(new RealmSymptom(mTvConfused.getText().toString()));
                        }
                    }
                }
                break;
            case R.id.cb_symp_cramps:
                if (mCbCramps.isChecked()) {
                    if (!Utils.checkSymptomExist(mListSymptom, mTvCramps.getText().toString())) {
                        mListSymptom.add(new RealmSymptom(mTvCramps.getText().toString()));
                    }
                } else {
                    if (Utils.checkSymptomExist(mListSymptom, mTvCramps.getText().toString())) {
                        mListSymptom.remove(new RealmSymptom(mTvCramps.getText().toString()));
                    }
                }
                break;
            case R.id.cb_symp_creamy:
                if (mCbCream.isChecked()) {
                    if (!Utils.checkSymptomExist(mListSymptom, mTvCream.getText().toString())) {
                        mListSymptom.add(new RealmSymptom(mTvCream.getText().toString()));
                    }
                } else {
                    if (Utils.checkSymptomExist(mListSymptom, mTvCream.getText().toString())) {
                        mListSymptom.remove(new RealmSymptom(mTvCream.getText().toString()));
                    }
                }
                break;
            case R.id.cb_symp_diarrhea:
                if (mCbDiarrhea.isChecked()) {
                    if (!Utils.checkSymptomExist(mListSymptom, mTvDiarrhea.getText().toString())) {
                        mListSymptom.add(new RealmSymptom(mTvDiarrhea.getText().toString()));
                    }
                } else {
                    if (Utils.checkSymptomExist(mListSymptom, mTvDiarrhea.getText().toString())) {
                        mListSymptom.remove(new RealmSymptom(mTvDiarrhea.getText().toString()));
                    }
                }
                break;
            case R.id.cb_symp_dizziness:
                if (mCbDizziness.isChecked()) {
                    if (!Utils.checkSymptomExist(mListSymptom, mTvDizziness.getText().toString())) {
                        mListSymptom.add(new RealmSymptom(mTvDizziness.getText().toString()));
                    }
                } else {
                    if (Utils.checkSymptomExist(mListSymptom, mTvDizziness.getText().toString())) {
                        mListSymptom.remove(new RealmSymptom(mTvDizziness.getText().toString()));
                    }
                }
                break;
            case R.id.cb_symp_dry:
                if (mCbDry.isChecked()) {
                    if (!Utils.checkSymptomExist(mListSymptom, mTvDry.getText().toString())) {
                        mListSymptom.add(new RealmSymptom(mTvDry.getText().toString()));
                    }
                } else {
                    if (Utils.checkSymptomExist(mListSymptom, mTvDry.getText().toString())) {
                        mListSymptom.remove(new RealmSymptom(mTvDry.getText().toString()));
                    }
                }
                break;
            case R.id.cb_symp_dyspepsia:
                if (mCbDyspepsia.isChecked()) {
                    if (!Utils.checkSymptomExist(mListSymptom, mTvDyspepsia.getText().toString())) {
                        mListSymptom.add(new RealmSymptom(mTvDyspepsia.getText().toString()));
                    }
                } else {
                    if (Utils.checkSymptomExist(mListSymptom, mTvDyspepsia.getText().toString())) {
                        mListSymptom.remove(new RealmSymptom(mTvDyspepsia.getText().toString()));
                    }
                }
                break;
            case R.id.cb_symp_fatigue:
                if (mCbFatigue.isChecked()) {
                    if (!Utils.checkSymptomExist(mListSymptom, mTvFatigue.getText().toString())) {
                        mListSymptom.add(new RealmSymptom(mTvFatigue.getText().toString()));
                    }
                } else {
                    if (Utils.checkSymptomExist(mListSymptom, mTvFatigue.getText().toString())) {
                        mListSymptom.remove(new RealmSymptom(mTvFatigue.getText().toString()));
                    }
                }
                break;
            case R.id.cb_symp_headaches:
                if (mCbHeadaches.isChecked()) {
                    if (!Utils.checkSymptomExist(mListSymptom, mTvHeadaches.getText().toString())) {
                        mListSymptom.add(new RealmSymptom(mTvHeadaches.getText().toString()));
                    }
                } else {
                    if (Utils.checkSymptomExist(mListSymptom, mTvHeadaches.getText().toString())) {
                        mListSymptom.remove(new RealmSymptom(mTvHeadaches.getText().toString()));
                    }
                }
                break;
            case R.id.cb_symp_hectic_fever:
                if (mCbHectic.isChecked()) {
                    if (!Utils.checkSymptomExist(mListSymptom, mTvHectic.getText().toString())) {
                        mListSymptom.add(new RealmSymptom(mTvHectic.getText().toString()));
                    }
                } else {
                    if (Utils.checkSymptomExist(mListSymptom, mTvHectic.getText().toString())) {
                        mListSymptom.remove(new RealmSymptom(mTvHectic.getText().toString()));
                    }
                }
                break;
            case R.id.cb_symp_hungry:
                if (mCbHungry.isChecked()) {
                    if (!Utils.checkSymptomExist(mListSymptom, mTvHungry.getText().toString())) {
                        mListSymptom.add(new RealmSymptom(mTvHungry.getText().toString()));
                    }
                } else {
                    if (Utils.checkSymptomExist(mListSymptom, mTvHungry.getText().toString())) {
                        mListSymptom.remove(new RealmSymptom(mTvHungry.getText().toString()));
                    }
                }
                break;
            case R.id.cb_symp_illness:
                if (mCbIllness.isChecked()) {
                    if (!Utils.checkSymptomExist(mListSymptom, mTvIllness.getText().toString())) {
                        mListSymptom.add(new RealmSymptom(mTvIllness.getText().toString()));
                    }
                } else {
                    if (Utils.checkSymptomExist(mListSymptom, mTvIllness.getText().toString())) {
                        mListSymptom.remove(new RealmSymptom(mTvIllness.getText().toString()));
                    }
                }
                break;
            case R.id.cb_symp_influenza:
                if (mCbInfluenza.isChecked()) {
                    if (!Utils.checkSymptomExist(mListSymptom, mTvInfluenza.getText().toString())) {
                        mListSymptom.add(new RealmSymptom(mTvInfluenza.getText().toString()));
                    }
                } else {
                    if (Utils.checkSymptomExist(mListSymptom, mTvInfluenza.getText().toString())) {
                        mListSymptom.remove(new RealmSymptom(mTvInfluenza.getText().toString()));
                    }
                }
                break;
            case R.id.cb_symp_insomnia:
                if (mCbInsomnia.isChecked()) {
                    if (!Utils.checkSymptomExist(mListSymptom, mTvInsomnia.getText().toString())) {
                        mListSymptom.add(new RealmSymptom(mTvInsomnia.getText().toString()));
                    }
                } else {
                    if (Utils.checkSymptomExist(mListSymptom, mTvInsomnia.getText().toString())) {
                        mListSymptom.remove(new RealmSymptom(mTvInsomnia.getText().toString()));
                    }
                }
                break;
            case R.id.cb_symp_irritability:
                if (mCbIrritability.isChecked()) {
                    if (!Utils.checkSymptomExist(mListSymptom, mTvIrritability.getText().toString())) {
                        mListSymptom.add(new RealmSymptom(mTvIrritability.getText().toString()));
                    }
                } else {
                    if (Utils.checkSymptomExist(mListSymptom, mTvIrritability.getText().toString())) {
                        mListSymptom.remove(new RealmSymptom(mTvIrritability.getText().toString()));
                    }
                }
                break;
            case R.id.cb_symp_irritation:
                if (mCbIrritation.isChecked()) {
                    if (!Utils.checkSymptomExist(mListSymptom, mTvIrritation.getText().toString())) {
                        mListSymptom.add(new RealmSymptom(mTvIrritation.getText().toString()));
                    }
                } else {
                    if (Utils.checkSymptomExist(mListSymptom, mTvIrritation.getText().toString())) {
                        mListSymptom.remove(new RealmSymptom(mTvIrritation.getText().toString()));
                    }
                }
                break;
            case R.id.cb_symp_itch:
                if (mCbItch.isChecked()) {
                    if (!Utils.checkSymptomExist(mListSymptom, mTvItch.getText().toString())) {
                        mListSymptom.add(new RealmSymptom(mTvItch.getText().toString()));
                    }
                } else {
                    if (Utils.checkSymptomExist(mListSymptom, mTvItch.getText().toString())) {
                        mListSymptom.remove(new RealmSymptom(mTvItch.getText().toString()));
                    }
                }
                break;
            case R.id.cb_symp_migraine:
                if (mCbMigraine.isChecked()) {
                    if (!Utils.checkSymptomExist(mListSymptom, mTvMigraine.getText().toString())) {
                        mListSymptom.add(new RealmSymptom(mTvMigraine.getText().toString()));
                    }
                } else {
                    if (Utils.checkSymptomExist(mListSymptom, mTvMigraine.getText().toString())) {
                        mListSymptom.remove(new RealmSymptom(mTvMigraine.getText().toString()));
                    }
                }
                break;
            case R.id.cb_symp_neckaches:
                if (mCbNeckaches.isChecked()) {
                    if (!Utils.checkSymptomExist(mListSymptom, mTvNeckaches.getText().toString())) {
                        mListSymptom.add(new RealmSymptom(mTvNeckaches.getText().toString()));
                    }
                } else {
                    if (Utils.checkSymptomExist(mListSymptom, mTvNeckaches.getText().toString())) {
                        mListSymptom.remove(new RealmSymptom(mTvNeckaches.getText().toString()));
                    }
                }
                break;
            case R.id.cb_symp_night_sweat:
                if (mCbNightSweat.isChecked()) {
                    if (!Utils.checkSymptomExist(mListSymptom, mTvNightSweat.getText().toString())) {
                        mListSymptom.add(new RealmSymptom(mTvNightSweat.getText().toString()));
                    }
                } else {
                    if (Utils.checkSymptomExist(mListSymptom, mTvNightSweat.getText().toString())) {
                        mListSymptom.remove(new RealmSymptom(mTvNightSweat.getText().toString()));
                    }
                }
                break;
            case R.id.cb_symp_pelvic_pain:
                if (mCbPelvicPain.isChecked()) {
                    if (!Utils.checkSymptomExist(mListSymptom, mTvPelvicPain.getText().toString())) {
                        mListSymptom.add(new RealmSymptom(mTvPelvicPain.getText().toString()));
                    }
                } else {
                    if (Utils.checkSymptomExist(mListSymptom, mTvPelvicPain.getText().toString())) {
                        mListSymptom.remove(new RealmSymptom(mTvPelvicPain.getText().toString()));
                    }
                }
                break;
            case R.id.cb_symp_pms:
                if (mCbPMS.isChecked()) {
                    if (!Utils.checkSymptomExist(mListSymptom, mTvPMS.getText().toString())) {
                        mListSymptom.add(new RealmSymptom(mTvPMS.getText().toString()));
                    }
                } else {
                    if (Utils.checkSymptomExist(mListSymptom, mTvPMS.getText().toString())) {
                        mListSymptom.remove(new RealmSymptom(mTvPMS.getText().toString()));
                    }
                }
                break;
            case R.id.cb_symp_queasiness:
                if (mCbQueasiness.isChecked()) {
                    if (!Utils.checkSymptomExist(mListSymptom, mTvQueasiness.getText().toString())) {
                        mListSymptom.add(new RealmSymptom(mTvQueasiness.getText().toString()));
                    }
                } else {
                    if (Utils.checkSymptomExist(mListSymptom, mTvQueasiness.getText().toString())) {
                        mListSymptom.remove(new RealmSymptom(mTvQueasiness.getText().toString()));
                    }
                }
                break;
            case R.id.cb_symp_rash:
                if (mCbRash.isChecked()) {
                    if (!Utils.checkSymptomExist(mListSymptom, mTvRash.getText().toString())) {
                        mListSymptom.add(new RealmSymptom(mTvRash.getText().toString()));
                    }
                } else {
                    if (Utils.checkSymptomExist(mListSymptom, mTvRash.getText().toString())) {
                        mListSymptom.remove(new RealmSymptom(mTvRash.getText().toString()));
                    }
                }
                break;
            case R.id.cb_symp_shoulder_ache:
                if (mCbShoulderAche.isChecked()) {
                    if (!Utils.checkSymptomExist(mListSymptom, mTvShoulderAche.getText().toString())) {
                        mListSymptom.add(new RealmSymptom(mTvShoulderAche.getText().toString()));
                    }
                } else {
                    if (Utils.checkSymptomExist(mListSymptom, mTvShoulderAche.getText().toString())) {
                        mListSymptom.remove(new RealmSymptom(mTvShoulderAche.getText().toString()));
                    }
                }
                break;
            case R.id.cb_symp_spotting_bleeding:
                if (mCbSpotting.isChecked()) {
                    if (!Utils.checkSymptomExist(mListSymptom, mTvSpotting.getText().toString())) {
                        mListSymptom.add(new RealmSymptom(mTvSpotting.getText().toString()));
                    }
                } else {
                    if (Utils.checkSymptomExist(mListSymptom, mTvSpotting.getText().toString())) {
                        mListSymptom.remove(new RealmSymptom(mTvSpotting.getText().toString()));
                    }
                }
                break;
            case R.id.cb_symp_sticky:
                if (mCbSticky.isChecked()) {
                    if (!Utils.checkSymptomExist(mListSymptom, mTvSticky.getText().toString())) {
                        mListSymptom.add(new RealmSymptom(mTvSticky.getText().toString()));
                    }
                } else {
                    if (Utils.checkSymptomExist(mListSymptom, mTvSticky.getText().toString())) {
                        mListSymptom.remove(new RealmSymptom(mTvSticky.getText().toString()));
                    }
                }
                break;
            case R.id.cb_symp_stress:
                if (mCbStress.isChecked()) {
                    if (!Utils.checkSymptomExist(mListSymptom, mTvStress.getText().toString())) {
                        mListSymptom.add(new RealmSymptom(mTvStress.getText().toString()));
                    }
                } else {
                    if (Utils.checkSymptomExist(mListSymptom, mTvStress.getText().toString())) {
                        mListSymptom.remove(new RealmSymptom(mTvStress.getText().toString()));
                    }
                }
                break;
            case R.id.cb_symp_tension:
                if (mCbTensi.isChecked()) {
                    if (!Utils.checkSymptomExist(mListSymptom, mTvTensi.getText().toString())) {
                        mListSymptom.add(new RealmSymptom(mTvTensi.getText().toString()));
                    }
                } else {
                    if (Utils.checkSymptomExist(mListSymptom, mTvTensi.getText().toString())) {
                        mListSymptom.remove(new RealmSymptom(mTvTensi.getText().toString()));
                    }
                }
                break;
            case R.id.cb_symp_watery:
                if (mCbWatery.isChecked()) {
                    if (!Utils.checkSymptomExist(mListSymptom, mTvWatery.getText().toString())) {
                        mListSymptom.add(new RealmSymptom(mTvWatery.getText().toString()));
                    }
                } else {
                    if (Utils.checkSymptomExist(mListSymptom, mTvWatery.getText().toString())) {
                        mListSymptom.remove(new RealmSymptom(mTvWatery.getText().toString()));
                    }
                }
                break;
            case R.id.cb_symp_weight_gain:
                if (mCbWeightGain.isChecked()) {
                    if (!Utils.checkSymptomExist(mListSymptom, mTvWeightGain.getText().toString())) {
                        mListSymptom.add(new RealmSymptom(mTvWeightGain.getText().toString()));
                    }
                } else {
                    if (Utils.checkSymptomExist(mListSymptom, mTvWeightGain.getText().toString())) {
                        mListSymptom.remove(new RealmSymptom(mTvWeightGain.getText().toString()));
                    }
                }
                break;
            case R.id.cb_symp_with_blood:
                if (mCbWithBlood.isChecked()) {
                    if (!Utils.checkSymptomExist(mListSymptom, mTvWithBlood.getText().toString())) {
                        mListSymptom.add(new RealmSymptom(mTvWithBlood.getText().toString()));
                    }
                } else {
                    if (Utils.checkSymptomExist(mListSymptom, mTvWithBlood.getText().toString())) {
                        mListSymptom.remove(new RealmSymptom(mTvWithBlood.getText().toString()));
                    }
                }
                break;
            case R.id.img_back_symptom_activity:
                finish();
                break;
            case R.id.img_done_symptom_activity:
                Intent intent = new Intent();

                ArrayList<String> mListSymptomArr = new ArrayList<>();
                for(int i = 0; i<mListSymptom.size(); i++){
                    mListSymptomArr.add(mListSymptom.get(i).getmSymptom());
                }

                intent.putStringArrayListExtra(Utils.BACK_SYMPTOM, mListSymptomArr);
                setResult(Activity.RESULT_OK, intent);
                finish();
                break;
        }
    }
}
