package com.skyfree.kinhnguyetmangthai.activity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.skyfree.kinhnguyetmangthai.R;

public class MoodActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView mImgNormal, mImgSad, mImgSleepy, mImgStress, mImgBoring,
            mImgDizzy, mImgRough, mImgAllergy, mImgInLove, mImgJealous,
            mImgDisappoinment, mImgMonocotyledon, mImgForgetful,
            mImgMiserable, mImgPanic, mImgNotSafe, mImgExhausted,
            mImgWorry, mImgHadTo, mImgHeartless, mImgIncredulous,
            mImgNaughty, mImgWeak, mImgBad, mImgHasty, mImgSatisfy,
            mImgAttenuate, mImgGood, mImgProud, mImgConfident, mImgAngry, mImgThin;

    private ImageView mImgBack, mImgDone;

    private boolean mCheckNormal, mCheckSad, mCheckSleepy, mCheckStress, mCheckBoring,
            mCheckDizzy, mCheckRough, mCheckAllergy, mCheckInLove, mCheckJealous,
            mCheckDisappoinment, mCheckMonocotyledon, mCheckForgetful,
            mCheckMiserable, mCheckPanic, mCheckNotSafe, mCheckExhausted,
            mCheckWorry, mCheckHadTo, mCheckHeartless, mCheckIncredulous,
            mCheckNaughty, mCheckWeak, mCheckBad, mCheckHasty, mCheckSatisfy,
            mCheckAttenuate, mCheckGood, mCheckProud, mCheckConfident, mCheckAngry, mCheckThin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mood);
        initView();
    }

    private void initView(){
        mImgNormal = (ImageView) findViewById(R.id.img_mood_normal);
        mImgSad = (ImageView) findViewById(R.id.img_mood_sad);
        mImgSleepy = (ImageView) findViewById(R.id.img_mood_sleepy);
        mImgStress = (ImageView) findViewById(R.id.img_mood_strees);
        mImgBoring = (ImageView) findViewById(R.id.img_mood_boring);
        mImgDizzy = (ImageView) findViewById(R.id.img_mood_dizzy);
        mImgRough = (ImageView) findViewById(R.id.img_mood_rough);
        mImgAllergy = (ImageView) findViewById(R.id.img_mood_allergy);
        mImgInLove = (ImageView) findViewById(R.id.img_mood_inlove);
        mImgJealous = (ImageView) findViewById(R.id.img_mood_jealous);
        mImgDisappoinment = (ImageView) findViewById(R.id.img_mood_disappoinm);
        mImgMonocotyledon = (ImageView) findViewById(R.id.img_mood_monocotyle);
        mImgForgetful = (ImageView) findViewById(R.id.img_mood_forgetful);
        mImgMiserable = (ImageView) findViewById(R.id.img_mood_miserable);
        mImgPanic = (ImageView) findViewById(R.id.img_mood_panic);
        mImgNotSafe = (ImageView) findViewById(R.id.img_mood_notsafe);
        mImgExhausted = (ImageView) findViewById(R.id.img_mood_exhausted);
        mImgWorry = (ImageView) findViewById(R.id.img_mood_worry);
        mImgHadTo = (ImageView) findViewById(R.id.img_mood_hadtounderstand);
        mImgHeartless = (ImageView) findViewById(R.id.img_mood_heartless);
        mImgIncredulous = (ImageView) findViewById(R.id.img_mood_incredulous);
        mImgNaughty = (ImageView) findViewById(R.id.img_mood_naughty);
        mImgWeak = (ImageView) findViewById(R.id.img_mood_weak);
        mImgBad = (ImageView) findViewById(R.id.img_mood_bad);
        mImgHasty = (ImageView) findViewById(R.id.img_mood_hasty);
        mImgSatisfy = (ImageView) findViewById(R.id.img_mood_satisfy);
        mImgAttenuate = (ImageView) findViewById(R.id.img_mood_attenuate);
        mImgGood = (ImageView) findViewById(R.id.img_mood_good);
        mImgProud = (ImageView) findViewById(R.id.img_mood_proud);
        mImgConfident = (ImageView) findViewById(R.id.img_mood_confident);
        mImgAngry = (ImageView) findViewById(R.id.img_mood_angry);
        mImgThin = (ImageView) findViewById(R.id.img_mood_thin);
        mImgBack = (ImageView) findViewById(R.id.img_back_mood_add_note);
        mImgDone = (ImageView) findViewById(R.id.img_done_mood_add_note);

        mImgNormal.setOnClickListener(this);
        mImgSad.setOnClickListener(this);
        mImgSleepy.setOnClickListener(this);
        mImgStress.setOnClickListener(this);
        mImgBoring.setOnClickListener(this);
        mImgDizzy.setOnClickListener(this);
        mImgRough.setOnClickListener(this);
        mImgAllergy.setOnClickListener(this);
        mImgInLove.setOnClickListener(this);
        mImgJealous.setOnClickListener(this);
        mImgDisappoinment.setOnClickListener(this);
        mImgMonocotyledon.setOnClickListener(this);
        mImgForgetful.setOnClickListener(this);
        mImgMiserable.setOnClickListener(this);
        mImgPanic.setOnClickListener(this);
        mImgNotSafe.setOnClickListener(this);
        mImgExhausted.setOnClickListener(this);
        mImgWorry.setOnClickListener(this);
        mImgHadTo.setOnClickListener(this);
        mImgHeartless.setOnClickListener(this);
        mImgIncredulous.setOnClickListener(this);
        mImgNaughty.setOnClickListener(this);
        mImgWeak.setOnClickListener(this);
        mImgBad.setOnClickListener(this);
        mImgHasty.setOnClickListener(this);
        mImgSatisfy.setOnClickListener(this);
        mImgAttenuate.setOnClickListener(this);
        mImgGood.setOnClickListener(this);
        mImgProud.setOnClickListener(this);
        mImgConfident.setOnClickListener(this);
        mImgAngry.setOnClickListener(this);
        mImgThin.setOnClickListener(this);
        mImgBack.setOnClickListener(this);
        mImgDone.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.img_mood_normal:
                mCheckNormal = !mCheckNormal;
                if(mCheckNormal){
                    mImgNormal.setBackgroundResource(R.drawable.pin_round);
                }else {
                    mImgNormal.setBackgroundResource(0);
                }
                break;
            case R.id.img_mood_sad:
                mCheckSad = !mCheckSad;
                if(mCheckSad){
                    mImgSad.setBackgroundResource(R.drawable.pin_round);
                }else {
                    mImgSad.setBackgroundResource(0);
                }
                break;
            case R.id.img_mood_sleepy:
                mCheckSleepy = !mCheckSleepy;
                if(mCheckSleepy){
                    mImgSleepy.setBackgroundResource(R.drawable.pin_round);
                }else {
                    mImgSleepy.setBackgroundResource(0);
                }
                break;
            case R.id.img_mood_strees:
                mCheckStress = !mCheckStress;
                if(mCheckStress){
                    mImgStress.setBackgroundResource(R.drawable.pin_round);
                }else {
                    mImgStress.setBackgroundResource(0);
                }
                break;
            case R.id.img_mood_boring:
                mCheckBoring = !mCheckBoring;
                if(mCheckBoring){
                    mImgBoring.setBackgroundResource(R.drawable.pin_round);
                }else {
                    mImgBoring.setBackgroundResource(0);
                }
                break;
            case R.id.img_mood_dizzy:
                mCheckDizzy = !mCheckDizzy;
                if(mCheckDizzy){
                    mImgDizzy.setBackgroundResource(R.drawable.pin_round);
                }else {
                    mImgDizzy.setBackgroundResource(0);
                }
                break;
            case R.id.img_mood_rough:
                mCheckRough = !mCheckRough;
                if(mCheckRough){
                    mImgRough.setBackgroundResource(R.drawable.pin_round);
                }else {
                    mImgRough.setBackgroundResource(0);
                }
                break;
            case R.id.img_mood_allergy:
                mCheckAllergy = !mCheckAllergy;
                if(mCheckAllergy){
                    mImgAllergy.setBackgroundResource(R.drawable.pin_round);
                }else {
                    mImgAllergy.setBackgroundResource(0);
                }
                break;
            case R.id.img_mood_inlove:
                mCheckInLove = !mCheckInLove;
                if(mCheckInLove){
                    mImgInLove.setBackgroundResource(R.drawable.pin_round);
                }else {
                    mImgInLove.setBackgroundResource(0);
                }
                break;
            case R.id.img_mood_jealous:
                mCheckJealous = !mCheckJealous;
                if(mCheckJealous){
                    mImgJealous.setBackgroundResource(R.drawable.pin_round);
                }else {
                    mImgJealous.setBackgroundResource(0);
                }
                break;
            case R.id.img_mood_disappoinm:
                mCheckDisappoinment = !mCheckDisappoinment;
                if(mCheckDisappoinment){
                    mImgDisappoinment.setBackgroundResource(R.drawable.pin_round);
                }else {
                    mImgDisappoinment.setBackgroundResource(0);
                }
                break;
            case R.id.img_mood_monocotyle:
                mCheckMonocotyledon = !mCheckMonocotyledon;
                if(mCheckMonocotyledon){
                    mImgMonocotyledon.setBackgroundResource(R.drawable.pin_round);
                }else {
                    mImgMonocotyledon.setBackgroundResource(0);
                }
                break;
            case R.id.img_mood_forgetful:
                mCheckForgetful = !mCheckForgetful;
                if(mCheckForgetful){
                    mImgForgetful.setBackgroundResource(R.drawable.pin_round);
                }else {
                    mImgForgetful.setBackgroundResource(0);
                }
                break;
            case R.id.img_mood_miserable:
                mCheckMiserable = !mCheckMiserable;
                if(mCheckMiserable){
                    mImgMiserable.setBackgroundResource(R.drawable.pin_round);
                }else {
                    mImgMiserable.setBackgroundResource(0);
                }
                break;
            case R.id.img_mood_panic:
                mCheckPanic = !mCheckPanic;
                if(mCheckPanic){
                    mImgPanic.setBackgroundResource(R.drawable.pin_round);
                }else {
                    mImgPanic.setBackgroundResource(0);
                }
                break;
            case R.id.img_mood_notsafe:
                mCheckNotSafe = !mCheckNotSafe;
                if(mCheckNotSafe){
                    mImgNotSafe.setBackgroundResource(R.drawable.pin_round);
                }else {
                    mImgNotSafe.setBackgroundResource(0);
                }
                break;
            case R.id.img_mood_exhausted:
                mCheckExhausted = !mCheckExhausted;
                if(mCheckExhausted){
                    mImgExhausted.setBackgroundResource(R.drawable.pin_round);
                }else {
                    mImgExhausted.setBackgroundResource(0);
                }
                break;
            case R.id.img_mood_worry:
                mCheckWorry = !mCheckWorry;
                if(mCheckWorry){
                    mImgWorry.setBackgroundResource(R.drawable.pin_round);
                }else {
                    mImgWorry.setBackgroundResource(0);
                }
                break;
            case R.id.img_mood_hadtounderstand:
                mCheckHadTo = !mCheckHadTo;
                if(mCheckHadTo){
                    mImgHadTo.setBackgroundResource(R.drawable.pin_round);
                }else {
                    mImgHadTo.setBackgroundResource(0);
                }
                break;
            case R.id.img_mood_heartless:
                mCheckHeartless = !mCheckHeartless;
                if(mCheckHeartless){
                    mImgHeartless.setBackgroundResource(R.drawable.pin_round);
                }else {
                    mImgHeartless.setBackgroundResource(0);
                }
                break;
            case R.id.img_mood_incredulous:
                mCheckIncredulous = !mCheckIncredulous;
                if(mCheckIncredulous){
                    mImgIncredulous.setBackgroundResource(R.drawable.pin_round);
                }else {
                    mImgIncredulous.setBackgroundResource(0);
                }
                break;
            case R.id.img_mood_naughty:
                mCheckNaughty = !mCheckNaughty;
                if(mCheckNaughty){
                    mImgNaughty.setBackgroundResource(R.drawable.pin_round);
                }else {
                    mImgNaughty.setBackgroundResource(0);
                }
                break;
            case R.id.img_mood_weak:
                mCheckWeak = !mCheckWeak;
                if(mCheckWeak){
                    mImgWeak.setBackgroundResource(R.drawable.pin_round);
                }else {
                    mImgWeak.setBackgroundResource(0);
                }
                break;
            case R.id.img_mood_bad:
                mCheckBad = !mCheckBad;
                if(mCheckBad){
                    mImgBad.setBackgroundResource(R.drawable.pin_round);
                }else {
                    mImgBad.setBackgroundResource(0);
                }
                break;
            case R.id.img_mood_hasty:
                mCheckHasty = !mCheckHasty;
                if(mCheckHasty){
                    mImgHasty.setBackgroundResource(R.drawable.pin_round);
                }else {
                    mImgHasty.setBackgroundResource(0);
                }
                break;
            case R.id.img_mood_satisfy:
                mCheckSatisfy = !mCheckSatisfy;
                if(mCheckSatisfy){
                    mImgSatisfy.setBackgroundResource(R.drawable.pin_round);
                }else {
                    mImgSatisfy.setBackgroundResource(0);
                }
                break;
            case R.id.img_mood_attenuate:
                mCheckAttenuate = !mCheckAttenuate;
                if(mCheckAttenuate){
                    mImgAttenuate.setBackgroundResource(R.drawable.pin_round);
                }else {
                    mImgAttenuate.setBackgroundResource(0);
                }
                break;
            case R.id.img_mood_good:
                mCheckGood = !mCheckGood;
                if(mCheckGood){
                    mImgGood.setBackgroundResource(R.drawable.pin_round);
                }else {
                    mImgGood.setBackgroundResource(0);
                }
                break;
            case R.id.img_mood_proud:
                mCheckProud = !mCheckProud;
                if(mCheckProud){
                    mImgProud.setBackgroundResource(R.drawable.pin_round);
                }else {
                    mImgProud.setBackgroundResource(0);
                }
                break;
            case R.id.img_mood_confident:
                mCheckConfident = !mCheckConfident;
                if(mCheckConfident){
                    mImgConfident.setBackgroundResource(R.drawable.pin_round);
                }else {
                    mImgConfident.setBackgroundResource(0);
                }
                break;
            case R.id.img_mood_angry:
                mCheckAngry = !mCheckAngry;
                if(mCheckAngry){
                    mImgAngry.setBackgroundResource(R.drawable.pin_round);
                }else {
                    mImgAngry.setBackgroundResource(0);
                }
                break;
            case R.id.img_mood_thin:
                mCheckThin = !mCheckThin;
                if(mCheckThin){
                    mImgThin.setBackgroundResource(R.drawable.pin_round);
                }else {
                    mImgThin.setBackgroundResource(0);
                }
                break;
            case R.id.img_back_mood_add_note:
                finish();
                break;
            case R.id.img_done_mood_add_note:
                break;
        }
    }
}
