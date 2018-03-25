package com.skyfree.kinhnguyetmangthai.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.skyfree.kinhnguyetmangthai.R;
import com.skyfree.kinhnguyetmangthai.utils.Utils;

import java.util.ArrayList;

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

    private ArrayList<String> mListSymptom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_symptom);
        initView();
        addEvent();
    }

    private void addEvent() {
        mListSymptom = new ArrayList<>();
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
                    if (mListSymptom.size() > 0) {
                        if (!Utils.checkDataExist(mListSymptom, mTvAbdominal.getText().toString())) {
                            mListSymptom.add(mTvAbdominal.getText().toString());
                        }
                    }
                } else {
                    if (mListSymptom.size() > 0) {
                        if (Utils.checkDataExist(mListSymptom, mTvAbdominal.getText().toString())) {
                            mListSymptom.remove(mTvAbdominal.getText().toString());
                        }
                    }
                }
                break;
            case R.id.cb_symp_anxiety:
                if (mListSymptom.size() > 0) {
                    if (mCbAnxiet.isChecked()) {
                        if (!Utils.checkDataExist(mListSymptom, mTvAnxiet.getText().toString())) {
                            mListSymptom.add(mTvAnxiet.getText().toString());
                        }
                    } else {
                        if (Utils.checkDataExist(mListSymptom, mTvAnxiet.getText().toString())) {
                            mListSymptom.remove(mTvAnxiet.getText().toString());
                        }
                    }
                }
                break;
            case R.id.cb_symp_astriction:
                if (mListSymptom.size() > 0) {
                    if (mCbAstriction.isChecked()) {
                        if (!Utils.checkDataExist(mListSymptom, mTvAstriction.getText().toString())) {
                            mListSymptom.add(mTvAstriction.getText().toString());
                        }
                    } else {
                        if (Utils.checkDataExist(mListSymptom, mTvAstriction.getText().toString())) {
                            mListSymptom.remove(mTvAstriction.getText().toString());
                        }
                    }
                }
                break;
            case R.id.cb_symp_backaches:
                if (mListSymptom.size() > 0) {
                    if (mCbBackaches.isChecked()) {
                        if (!Utils.checkDataExist(mListSymptom, mTvBackaches.getText().toString())) {
                            mListSymptom.add(mTvBackaches.getText().toString());
                        }
                    } else {
                        if (Utils.checkDataExist(mListSymptom, mTvBackaches.getText().toString())) {
                            mListSymptom.remove(mTvBackaches.getText().toString());
                        }
                    }
                }
                break;
            case R.id.cb_symp_bloating:
                if (mListSymptom.size() > 0) {
                    if (mCbBloating.isChecked()) {
                        if (!Utils.checkDataExist(mListSymptom, mTvBloating.getText().toString())) {
                            mListSymptom.add(mTvBloating.getText().toString());
                        }
                    } else {
                        if (Utils.checkDataExist(mListSymptom, mTvBloating.getText().toString())) {
                            mListSymptom.remove(mTvBloating.getText().toString());
                        }
                    }
                }
                break;
            case R.id.cb_symp_body_aches:
                if (mListSymptom.size() > 0) {
                    if (mCbBodyAches.isChecked()) {
                        if (!Utils.checkDataExist(mListSymptom, mCbBodyAches.getText().toString())) {
                            mListSymptom.add(mCbBodyAches.getText().toString());
                        }
                    } else {
                        if (Utils.checkDataExist(mListSymptom, mCbBodyAches.getText().toString())) {
                            mListSymptom.remove(mCbBodyAches.getText().toString());
                        }
                    }
                }
                break;
            case R.id.cb_symp_cervical_mucus:
                if (mListSymptom.size() > 0) {
                    if (mCbCervical.isChecked()) {
                        if (!Utils.checkDataExist(mListSymptom, mTvCervical.getText().toString())) {
                            mListSymptom.add(mTvCervical.getText().toString());
                        }
                    } else {
                        if (Utils.checkDataExist(mListSymptom, mTvCervical.getText().toString())) {
                            mListSymptom.remove(mTvCervical.getText().toString());
                        }
                    }
                }
                break;
            case R.id.cb_symp_chills:
                if (mListSymptom.size() > 0) {
                    if (mCbChills.isChecked()) {
                        if (!Utils.checkDataExist(mListSymptom, mTvChills.getText().toString())) {
                            mListSymptom.add(mTvChills.getText().toString());
                        }
                    } else {
                        if (Utils.checkDataExist(mListSymptom, mTvChills.getText().toString())) {
                            mListSymptom.remove(mTvChills.getText().toString());
                        }
                    }
                }
                break;
            case R.id.cb_symp_confused:
                if (mListSymptom.size() > 0) {
                    if (mCbConfused.isChecked()) {
                        if (!Utils.checkDataExist(mListSymptom, mTvConfused.getText().toString())) {
                            mListSymptom.add(mTvConfused.getText().toString());
                        }
                    } else {
                        if (Utils.checkDataExist(mListSymptom, mTvConfused.getText().toString())) {
                            mListSymptom.remove(mTvConfused.getText().toString());
                        }
                    }
                }
                break;
            case R.id.cb_symp_cramps:
                if (mListSymptom.size() > 0) {
                    if (mCbCramps.isChecked()) {
                        if (!Utils.checkDataExist(mListSymptom, mTvCramps.getText().toString())) {
                            mListSymptom.add(mTvCramps.getText().toString());
                        }
                    } else {
                        if (Utils.checkDataExist(mListSymptom, mTvCramps.getText().toString())) {
                            mListSymptom.remove(mTvCramps.getText().toString());
                        }
                    }
                }
                break;
            case R.id.cb_symp_creamy:
                if (mListSymptom.size() > 0) {
                    if (mCbCream.isChecked()) {
                        if (!Utils.checkDataExist(mListSymptom, mTvCream.getText().toString())) {
                            mListSymptom.add(mTvCream.getText().toString());
                        }
                    } else {
                        if (Utils.checkDataExist(mListSymptom, mTvCream.getText().toString())) {
                            mListSymptom.remove(mTvCream.getText().toString());
                        }
                    }
                }
                break;
            case R.id.cb_symp_diarrhea:
                if (mListSymptom.size() > 0) {
                    if (mCbDiarrhea.isChecked()) {
                        if (!Utils.checkDataExist(mListSymptom, mTvDiarrhea.getText().toString())) {
                            mListSymptom.add(mTvDiarrhea.getText().toString());
                        }
                    } else {
                        if (Utils.checkDataExist(mListSymptom, mTvDiarrhea.getText().toString())) {
                            mListSymptom.remove(mTvDiarrhea.getText().toString());
                        }
                    }
                }
                break;
            case R.id.cb_symp_dizziness:
                if (mListSymptom.size() > 0) {
                    if (mCbDizziness.isChecked()) {
                        if (!Utils.checkDataExist(mListSymptom, mTvDizziness.getText().toString())) {
                            mListSymptom.add(mTvDizziness.getText().toString());
                        }
                    } else {
                        if (Utils.checkDataExist(mListSymptom, mTvDizziness.getText().toString())) {
                            mListSymptom.remove(mTvDizziness.getText().toString());
                        }
                    }
                }
                break;
            case R.id.cb_symp_dry:
                if (mListSymptom.size() > 0) {
                    if (mCbDry.isChecked()) {
                        if (!Utils.checkDataExist(mListSymptom, mTvDry.getText().toString())) {
                            mListSymptom.add(mTvDry.getText().toString());
                        }
                    } else {
                        if (Utils.checkDataExist(mListSymptom, mTvDry.getText().toString())) {
                            mListSymptom.remove(mTvDry.getText().toString());
                        }
                    }
                }
                break;
            case R.id.cb_symp_dyspepsia:
                if (mListSymptom.size() > 0) {
                    if (mCbDyspepsia.isChecked()) {
                        if (!Utils.checkDataExist(mListSymptom, mTvDyspepsia.getText().toString())) {
                            mListSymptom.add(mTvDyspepsia.getText().toString());
                        }
                    } else {
                        if (Utils.checkDataExist(mListSymptom, mTvDyspepsia.getText().toString())) {
                            mListSymptom.remove(mTvDyspepsia.getText().toString());
                        }
                    }
                }
                break;
            case R.id.cb_symp_fatigue:
                if (mListSymptom.size() > 0) {
                    if (mCbFatigue.isChecked()) {
                        if (!Utils.checkDataExist(mListSymptom, mTvFatigue.getText().toString())) {
                            mListSymptom.add(mTvFatigue.getText().toString());
                        }
                    } else {
                        if (Utils.checkDataExist(mListSymptom, mTvFatigue.getText().toString())) {
                            mListSymptom.remove(mTvFatigue.getText().toString());
                        }
                    }
                }
                break;
            case R.id.cb_symp_headaches:
                if (mListSymptom.size() > 0) {
                    if (mCbHeadaches.isChecked()) {
                        if (!Utils.checkDataExist(mListSymptom, mTvHeadaches.getText().toString())) {
                            mListSymptom.add(mTvHeadaches.getText().toString());
                        }
                    } else {
                        if (Utils.checkDataExist(mListSymptom, mTvHeadaches.getText().toString())) {
                            mListSymptom.remove(mTvHeadaches.getText().toString());
                        }
                    }
                }
                break;
            case R.id.cb_symp_hectic_fever:
                if (mListSymptom.size() > 0) {
                    if (mCbHectic.isChecked()) {
                        if (!Utils.checkDataExist(mListSymptom, mTvHectic.getText().toString())) {
                            mListSymptom.add(mTvHectic.getText().toString());
                        }
                    } else {
                        if (Utils.checkDataExist(mListSymptom, mTvHectic.getText().toString())) {
                            mListSymptom.remove(mTvHectic.getText().toString());
                        }
                    }
                }
                break;
            case R.id.cb_symp_hungry:
                if (mListSymptom.size() > 0) {
                    if (mCbHungry.isChecked()) {
                        if (!Utils.checkDataExist(mListSymptom, mTvHungry.getText().toString())) {
                            mListSymptom.add(mTvHungry.getText().toString());
                        }
                    } else {
                        if (Utils.checkDataExist(mListSymptom, mTvHungry.getText().toString())) {
                            mListSymptom.remove(mTvHungry.getText().toString());
                        }
                    }
                }
                break;
            case R.id.cb_symp_illness:
                if (mListSymptom.size() > 0) {
                    if (mCbIllness.isChecked()) {
                        if (!Utils.checkDataExist(mListSymptom, mTvIllness.getText().toString())) {
                            mListSymptom.add(mTvIllness.getText().toString());
                        }
                    } else {
                        if (Utils.checkDataExist(mListSymptom, mTvIllness.getText().toString())) {
                            mListSymptom.remove(mTvIllness.getText().toString());
                        }
                    }
                }
                break;
            case R.id.cb_symp_influenza:
                if (mListSymptom.size() > 0) {
                    if (mCbInfluenza.isChecked()) {
                        if (!Utils.checkDataExist(mListSymptom, mTvInfluenza.getText().toString())) {
                            mListSymptom.add(mTvInfluenza.getText().toString());
                        }
                    } else {
                        if (Utils.checkDataExist(mListSymptom, mTvInfluenza.getText().toString())) {
                            mListSymptom.remove(mTvInfluenza.getText().toString());
                        }
                    }
                }
                break;
            case R.id.cb_symp_insomnia:
                if (mListSymptom.size() > 0) {
                    if (mCbInsomnia.isChecked()) {
                        if (!Utils.checkDataExist(mListSymptom, mTvInsomnia.getText().toString())) {
                            mListSymptom.add(mTvInsomnia.getText().toString());
                        }
                    } else {
                        if (Utils.checkDataExist(mListSymptom, mTvInsomnia.getText().toString())) {
                            mListSymptom.remove(mTvInsomnia.getText().toString());
                        }
                    }
                }
                break;
            case R.id.cb_symp_irritability:
                if (mListSymptom.size() > 0) {
                    if (mCbIrritability.isChecked()) {
                        if (!Utils.checkDataExist(mListSymptom, mTvIrritability.getText().toString())) {
                            mListSymptom.add(mTvIrritability.getText().toString());
                        }
                    } else {
                        if (Utils.checkDataExist(mListSymptom, mTvIrritability.getText().toString())) {
                            mListSymptom.remove(mTvIrritability.getText().toString());
                        }
                    }
                }
                break;
            case R.id.cb_symp_irritation:
                if (mListSymptom.size() > 0) {
                    if (mCbIrritation.isChecked()) {
                        if (!Utils.checkDataExist(mListSymptom, mTvIrritation.getText().toString())) {
                            mListSymptom.add(mTvIrritation.getText().toString());
                        }
                    } else {
                        if (Utils.checkDataExist(mListSymptom, mTvIrritation.getText().toString())) {
                            mListSymptom.remove(mTvIrritation.getText().toString());
                        }
                    }
                }
                break;
            case R.id.cb_symp_itch:
                if (mListSymptom.size() > 0) {
                    if (mCbItch.isChecked()) {
                        if (!Utils.checkDataExist(mListSymptom, mTvItch.getText().toString())) {
                            mListSymptom.add(mTvItch.getText().toString());
                        }
                    } else {
                        if (Utils.checkDataExist(mListSymptom, mTvItch.getText().toString())) {
                            mListSymptom.remove(mTvItch.getText().toString());
                        }
                    }
                }
                break;
            case R.id.cb_symp_migraine:
                if (mListSymptom.size() > 0) {
                    if (mCbMigraine.isChecked()) {
                        if (!Utils.checkDataExist(mListSymptom, mTvMigraine.getText().toString())) {
                            mListSymptom.add(mTvMigraine.getText().toString());
                        }
                    } else {
                        if (Utils.checkDataExist(mListSymptom, mTvMigraine.getText().toString())) {
                            mListSymptom.remove(mTvMigraine.getText().toString());
                        }
                    }
                }
                break;
            case R.id.cb_symp_neckaches:
                if (mListSymptom.size() > 0) {
                    if (mCbNeckaches.isChecked()) {
                        if (!Utils.checkDataExist(mListSymptom, mTvNeckaches.getText().toString())) {
                            mListSymptom.add(mTvNeckaches.getText().toString());
                        }
                    } else {
                        if (Utils.checkDataExist(mListSymptom, mTvNeckaches.getText().toString())) {
                            mListSymptom.remove(mTvNeckaches.getText().toString());
                        }
                    }
                }
                break;
            case R.id.cb_symp_night_sweat:
                if (mListSymptom.size() > 0) {
                    if (mCbNightSweat.isChecked()) {
                        if (!Utils.checkDataExist(mListSymptom, mTvNightSweat.getText().toString())) {
                            mListSymptom.add(mTvNightSweat.getText().toString());
                        }
                    } else {
                        if (Utils.checkDataExist(mListSymptom, mTvNightSweat.getText().toString())) {
                            mListSymptom.remove(mTvNightSweat.getText().toString());
                        }
                    }
                }
                break;
            case R.id.cb_symp_pelvic_pain:
                if (mListSymptom.size() > 0) {
                    if (mCbPelvicPain.isChecked()) {
                        if (!Utils.checkDataExist(mListSymptom, mTvPelvicPain.getText().toString())) {
                            mListSymptom.add(mTvPelvicPain.getText().toString());
                        }
                    } else {
                        if (Utils.checkDataExist(mListSymptom, mTvPelvicPain.getText().toString())) {
                            mListSymptom.remove(mTvPelvicPain.getText().toString());
                        }
                    }
                }
                break;
            case R.id.cb_symp_pms:
                if (mListSymptom.size() > 0) {
                    if (mCbPMS.isChecked()) {
                        if (!Utils.checkDataExist(mListSymptom, mTvPMS.getText().toString())) {
                            mListSymptom.add(mTvPMS.getText().toString());
                        }
                    } else {
                        if (Utils.checkDataExist(mListSymptom, mTvPMS.getText().toString())) {
                            mListSymptom.remove(mTvPMS.getText().toString());
                        }
                    }
                }
                break;
            case R.id.cb_symp_queasiness:
                if (mListSymptom.size() > 0) {
                    if (mCbQueasiness.isChecked()) {
                        if (!Utils.checkDataExist(mListSymptom, mTvQueasiness.getText().toString())) {
                            mListSymptom.add(mTvQueasiness.getText().toString());
                        }
                    } else {
                        if (Utils.checkDataExist(mListSymptom, mTvQueasiness.getText().toString())) {
                            mListSymptom.remove(mTvQueasiness.getText().toString());
                        }
                    }
                }
                break;
            case R.id.cb_symp_rash:
                if (mListSymptom.size() > 0) {
                    if (mCbRash.isChecked()) {
                        if (!Utils.checkDataExist(mListSymptom, mTvRash.getText().toString())) {
                            mListSymptom.add(mTvRash.getText().toString());
                        }
                    } else {
                        if (Utils.checkDataExist(mListSymptom, mTvRash.getText().toString())) {
                            mListSymptom.remove(mTvRash.getText().toString());
                        }
                    }
                }
                break;
            case R.id.cb_symp_shoulder_ache:
                if (mListSymptom.size() > 0) {
                    if (mCbShoulderAche.isChecked()) {
                        if (!Utils.checkDataExist(mListSymptom, mTvShoulderAche.getText().toString())) {
                            mListSymptom.add(mTvShoulderAche.getText().toString());
                        }
                    } else {
                        if (Utils.checkDataExist(mListSymptom, mTvShoulderAche.getText().toString())) {
                            mListSymptom.remove(mTvShoulderAche.getText().toString());
                        }
                    }
                }
                break;
            case R.id.cb_symp_spotting_bleeding:
                if (mListSymptom.size() > 0) {
                    if (mCbSpotting.isChecked()) {
                        if (!Utils.checkDataExist(mListSymptom, mTvSpotting.getText().toString())) {
                            mListSymptom.add(mTvSpotting.getText().toString());
                        }
                    } else {
                        if (Utils.checkDataExist(mListSymptom, mTvSpotting.getText().toString())) {
                            mListSymptom.remove(mTvSpotting.getText().toString());
                        }
                    }
                }
                break;
            case R.id.cb_symp_sticky:
                if (mListSymptom.size() > 0) {
                    if (mCbSticky.isChecked()) {
                        if (!Utils.checkDataExist(mListSymptom, mTvSticky.getText().toString())) {
                            mListSymptom.add(mTvSticky.getText().toString());
                        }
                    } else {
                        if (Utils.checkDataExist(mListSymptom, mTvSticky.getText().toString())) {
                            mListSymptom.remove(mTvSticky.getText().toString());
                        }
                    }
                }
                break;
            case R.id.cb_symp_stress:
                if (mListSymptom.size() > 0) {
                    if (mCbStress.isChecked()) {
                        if (!Utils.checkDataExist(mListSymptom, mTvStress.getText().toString())) {
                            mListSymptom.add(mTvStress.getText().toString());
                        }
                    } else {
                        if (Utils.checkDataExist(mListSymptom, mTvStress.getText().toString())) {
                            mListSymptom.remove(mTvStress.getText().toString());
                        }
                    }
                }
                break;
            case R.id.cb_symp_tension:
                if (mListSymptom.size() > 0) {
                    if (mCbTensi.isChecked()) {
                        if (!Utils.checkDataExist(mListSymptom, mTvTensi.getText().toString())) {
                            mListSymptom.add(mTvTensi.getText().toString());
                        }
                    } else {
                        if (Utils.checkDataExist(mListSymptom, mTvTensi.getText().toString())) {
                            mListSymptom.remove(mTvTensi.getText().toString());
                        }
                    }
                }
                break;
            case R.id.cb_symp_watery:
                if (mListSymptom.size() > 0) {
                    if (mCbWatery.isChecked()) {
                        if (!Utils.checkDataExist(mListSymptom, mTvWatery.getText().toString())) {
                            mListSymptom.add(mTvWatery.getText().toString());
                        }
                    } else {
                        if (Utils.checkDataExist(mListSymptom, mTvWatery.getText().toString())) {
                            mListSymptom.remove(mTvWatery.getText().toString());
                        }
                    }
                }
                break;
            case R.id.cb_symp_weight_gain:
                if (mListSymptom.size() > 0) {
                    if (mCbWeightGain.isChecked()) {
                        if (!Utils.checkDataExist(mListSymptom, mTvWeightGain.getText().toString())) {
                            mListSymptom.add(mTvWeightGain.getText().toString());
                        }
                    } else {
                        if (Utils.checkDataExist(mListSymptom, mTvWeightGain.getText().toString())) {
                            mListSymptom.remove(mTvWeightGain.getText().toString());
                        }
                    }
                }
                break;
            case R.id.cb_symp_with_blood:
                if (mListSymptom.size()>0){
                    if (mCbWithBlood.isChecked()) {
                        if (!Utils.checkDataExist(mListSymptom, mTvWithBlood.getText().toString())) {
                            mListSymptom.add(mTvWithBlood.getText().toString());
                        }
                    } else {
                        if (Utils.checkDataExist(mListSymptom, mTvWithBlood.getText().toString())) {
                            mListSymptom.remove(mTvWithBlood.getText().toString());
                        }
                    }
                }
                break;
            case R.id.img_back_symptom_activity:
                finish();
                break;
            case R.id.img_done_symptom_activity:
                break;
        }
    }
}
