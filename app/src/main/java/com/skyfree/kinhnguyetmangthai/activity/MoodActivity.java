package com.skyfree.kinhnguyetmangthai.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.skyfree.kinhnguyetmangthai.R;
import com.skyfree.kinhnguyetmangthai.model.NoteObj;
import com.skyfree.kinhnguyetmangthai.model.RealmDrug;
import com.skyfree.kinhnguyetmangthai.model.RealmMood;
import com.skyfree.kinhnguyetmangthai.model.RealmSymptom;
import com.skyfree.kinhnguyetmangthai.utils.Utils;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmList;

public class MoodActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView mImgNormal, mImgSad, mImgSleepy, mImgStress, mImgBoring,
            mImgDizzy, mImgRough, mImgAllergy, mImgInLove, mImgJealous,
            mImgDisappoinment, mImgMonocotyledon, mImgForgetful,
            mImgMiserable, mImgPanic, mImgNotSafe, mImgExhausted,
            mImgWorry, mImgHadTo, mImgHeartless, mImgIncredulous,
            mImgNaughty, mImgWeak, mImgBad, mImgHasty, mImgSatisfy,
            mImgAttenuate, mImgGood, mImgProud, mImgConfident, mImgAngry, mImgThin;

    private TextView mTvNormal, mTvSad, mTvSleepy, mTvStress, mTvBoring,
            mTvDizzy, mTvRough, mTvAllergy, mTvInLove, mTvJealous,
            mTvDisappoinment, mTvMonocotyledon, mTvForgetful,
            mTvMiserable, mTvPanic, mTvNotSafe, mTvExhausted,
            mTvWorry, mTvHadTo, mTvHeartless, mTvIncredulous,
            mTvNaughty, mTvWeak, mTvBad, mTvHasty, mTvSatisfy,
            mTvAttenuate, mTvGood, mTvProud, mTvConfident, mTvAngry, mTvThin;

    private ImageView mImgBack, mImgDone;

    private boolean mCheckNormal, mCheckSad, mCheckSleepy, mCheckStress, mCheckBoring,
            mCheckDizzy, mCheckRough, mCheckAllergy, mCheckInLove, mCheckJealous,
            mCheckDisappoinment, mCheckMonocotyledon, mCheckForgetful,
            mCheckMiserable, mCheckPanic, mCheckNotSafe, mCheckExhausted,
            mCheckWorry, mCheckHadTo, mCheckHeartless, mCheckIncredulous,
            mCheckNaughty, mCheckWeak, mCheckBad, mCheckHasty, mCheckSatisfy,
            mCheckAttenuate, mCheckGood, mCheckProud, mCheckConfident, mCheckAngry, mCheckThin;

    Realm realm;
    private NoteObj mNoteObj;
    private String mId;
    private ArrayList<String> mListAllMoodForResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mood);
        initView();
        addEvent();
    }

    private void addEvent() {
        realm = Realm.getDefaultInstance();
        mListAllMoodForResult = new ArrayList<>();
        mId = getIntent().getStringExtra(Utils.PUT_ID);
        mNoteObj = Utils.getNoteObj(realm, mId);
        if (mNoteObj != null) {
            for (int i = 0; i < mNoteObj.getmListMood().size(); i++) {
                if (!Utils.checkStringExist(mListAllMoodForResult, mNoteObj.getmListMood().get(i).getmMood())) {
                    mListAllMoodForResult.add(mNoteObj.getmListMood().get(i).getmMood());
                }
            }
        }

        for (int i = 0; i < mListAllMoodForResult.size(); i++) {
            if (mListAllMoodForResult.get(i).equals(getString(R.string.normal))) {
                mImgNormal.setBackgroundResource(R.drawable.pin_round);
            }
            if (mListAllMoodForResult.get(i).equals(getString(R.string.sad))) {
                mImgSad.setBackgroundResource(R.drawable.pin_round);
            }
            if (mListAllMoodForResult.get(i).equals(getString(R.string.sleepy))) {
                mImgSleepy.setBackgroundResource(R.drawable.pin_round);
            }
            if (mListAllMoodForResult.get(i).equals(getString(R.string.stress))) {
                mImgStress.setBackgroundResource(R.drawable.pin_round);
            }
            if (mListAllMoodForResult.get(i).equals(getString(R.string.boring))) {
                mImgBoring.setBackgroundResource(R.drawable.pin_round);
            }
            if (mListAllMoodForResult.get(i).equals(getString(R.string.dizzy))) {
                mImgDizzy.setBackgroundResource(R.drawable.pin_round);
            }
            if (mListAllMoodForResult.get(i).equals(getString(R.string.rough))) {
                mImgRough.setBackgroundResource(R.drawable.pin_round);
            }
            if (mListAllMoodForResult.get(i).equals(getString(R.string.allergy))) {
                mImgAllergy.setBackgroundResource(R.drawable.pin_round);
            }
            if (mListAllMoodForResult.get(i).equals(getString(R.string.in_love))) {
                mImgInLove.setBackgroundResource(R.drawable.pin_round);
            }
            if (mListAllMoodForResult.get(i).equals(getString(R.string.jealous))) {
                mImgJealous.setBackgroundResource(R.drawable.pin_round);
            }
            if (mListAllMoodForResult.get(i).equals(getString(R.string.disappointment))) {
                mImgDisappoinment.setBackgroundResource(R.drawable.pin_round);
            }
            if (mListAllMoodForResult.get(i).equals(getString(R.string.monocotyledon))) {
                mImgMonocotyledon.setBackgroundResource(R.drawable.pin_round);
            }
            if (mListAllMoodForResult.get(i).equals(getString(R.string.forgetful))) {
                mImgForgetful.setBackgroundResource(R.drawable.pin_round);
            }
            if (mListAllMoodForResult.get(i).equals(getString(R.string.miserable))) {
                mImgMiserable.setBackgroundResource(R.drawable.pin_round);
            }
            if (mListAllMoodForResult.get(i).equals(getString(R.string.panic))) {
                mImgPanic.setBackgroundResource(R.drawable.pin_round);
            }
            if (mListAllMoodForResult.get(i).equals(getString(R.string.not_safe))) {
                mImgNotSafe.setBackgroundResource(R.drawable.pin_round);
            }
            if (mListAllMoodForResult.get(i).equals(getString(R.string.exhausted))) {
                mImgExhausted.setBackgroundResource(R.drawable.pin_round);
            }
            if (mListAllMoodForResult.get(i).equals(getString(R.string.worry))) {
                mImgWorry.setBackgroundResource(R.drawable.pin_round);
            }
            if (mListAllMoodForResult.get(i).equals(getString(R.string.hard_to_understand))) {
                mImgHadTo.setBackgroundResource(R.drawable.pin_round);
            }
            if (mListAllMoodForResult.get(i).equals(getString(R.string.heartless))) {
                mImgHeartless.setBackgroundResource(R.drawable.pin_round);
            }
            if (mListAllMoodForResult.get(i).equals(getString(R.string.incredulous))) {
                mImgIncredulous.setBackgroundResource(R.drawable.pin_round);
            }
            if (mListAllMoodForResult.get(i).equals(getString(R.string.naughty))) {
                mImgNaughty.setBackgroundResource(R.drawable.pin_round);
            }
            if (mListAllMoodForResult.get(i).equals(getString(R.string.weak))) {
                mImgWeak.setBackgroundResource(R.drawable.pin_round);
            }
            if (mListAllMoodForResult.get(i).equals(getString(R.string.bad))) {
                mImgBad.setBackgroundResource(R.drawable.pin_round);
            }
            if (mListAllMoodForResult.get(i).equals(getString(R.string.hasty))) {
                mImgHasty.setBackgroundResource(R.drawable.pin_round);
            }
            if (mListAllMoodForResult.get(i).equals(getString(R.string.satisfy))) {
                mImgSatisfy.setBackgroundResource(R.drawable.pin_round);
            }
            if (mListAllMoodForResult.get(i).equals(getString(R.string.attenuate))) {
                mImgAttenuate.setBackgroundResource(R.drawable.pin_round);
            }
            if (mListAllMoodForResult.get(i).equals(getString(R.string.good))) {
                mImgGood.setBackgroundResource(R.drawable.pin_round);
            }
            if (mListAllMoodForResult.get(i).equals(getString(R.string.proud))) {
                mImgProud.setBackgroundResource(R.drawable.pin_round);
            }
            if (mListAllMoodForResult.get(i).equals(getString(R.string.confident))) {
                mImgConfident.setBackgroundResource(R.drawable.pin_round);
            }
            if (mListAllMoodForResult.get(i).equals(getString(R.string.angry))) {
                mImgAngry.setBackgroundResource(R.drawable.pin_round);
            }
            if (mListAllMoodForResult.get(i).equals(getString(R.string.thin))) {
                mImgThin.setBackgroundResource(R.drawable.pin_round);
            }
        }
    }

    private void initView() {
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

        mTvNormal = (TextView) findViewById(R.id.tv_mood_normal);
        mTvSad = (TextView) findViewById(R.id.tv_mood_sad);
        mTvSleepy = (TextView) findViewById(R.id.tv_mood_sleepy);
        mTvStress = (TextView) findViewById(R.id.tv_mood_strees);
        mTvBoring = (TextView) findViewById(R.id.tv_mood_boring);
        mTvDizzy = (TextView) findViewById(R.id.tv_mood_dizzy);
        mTvRough = (TextView) findViewById(R.id.tv_mood_rough);
        mTvAllergy = (TextView) findViewById(R.id.tv_mood_allergy);
        mTvInLove = (TextView) findViewById(R.id.tv_mood_inlove);
        mTvJealous = (TextView) findViewById(R.id.tv_mood_jealous);
        mTvDisappoinment = (TextView) findViewById(R.id.tv_mood_disappoinm);
        mTvMonocotyledon = (TextView) findViewById(R.id.tv_mood_monocotyle);
        mTvForgetful = (TextView) findViewById(R.id.tv_mood_forgetful);
        mTvMiserable = (TextView) findViewById(R.id.tv_mood_miserable);
        mTvPanic = (TextView) findViewById(R.id.tv_mood_panic);
        mTvNotSafe = (TextView) findViewById(R.id.tv_mood_notsafe);
        mTvExhausted = (TextView) findViewById(R.id.tv_mood_exhausted);
        mTvWorry = (TextView) findViewById(R.id.tv_mood_worry);
        mTvHadTo = (TextView) findViewById(R.id.tv_mood_hadto);
        mTvHeartless = (TextView) findViewById(R.id.tv_mood_heartless);
        mTvIncredulous = (TextView) findViewById(R.id.tv_mood_incredulous);
        mTvNaughty = (TextView) findViewById(R.id.tv_mood_naughty);
        mTvWeak = (TextView) findViewById(R.id.tv_mood_weak);
        mTvBad = (TextView) findViewById(R.id.tv_mood_bad);
        mTvHasty = (TextView) findViewById(R.id.tv_mood_hasty);
        mTvSatisfy = (TextView) findViewById(R.id.tv_mood_satisfy);
        mTvAttenuate = (TextView) findViewById(R.id.tv_mood_attenuate);
        mTvGood = (TextView) findViewById(R.id.tv_mood_good);
        mTvProud = (TextView) findViewById(R.id.tv_mood_proud);
        mTvConfident = (TextView) findViewById(R.id.tv_mood_confident);
        mTvAngry = (TextView) findViewById(R.id.tv_mood_angry);
        mTvThin = (TextView) findViewById(R.id.tv_mood_thin);
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
        switch (v.getId())

        {
            case R.id.img_mood_normal:
                mCheckNormal = !mCheckNormal;
                if (mCheckNormal) {
                    mImgNormal.setBackgroundResource(R.drawable.pin_round);
                    if (!Utils.checkStringExist(mListAllMoodForResult, getString(R.string.normal))) {
                        mListAllMoodForResult.add(getString(R.string.normal));
                    }
                } else {
                    mImgNormal.setBackgroundResource(0);
                    if (Utils.checkStringExist(mListAllMoodForResult, getString(R.string.normal))) {
                        mListAllMoodForResult.remove(getString(R.string.normal));
                    }
                }

                break;
            case R.id.img_mood_sad:
                mCheckSad = !mCheckSad;
                if (mCheckSad) {
                    mImgSad.setBackgroundResource(R.drawable.pin_round);
                    if (!Utils.checkStringExist(mListAllMoodForResult, getString(R.string.sad))) {
                        mListAllMoodForResult.add(getString(R.string.sad));
                    }
                } else {
                    mImgSad.setBackgroundResource(0);
                    if (Utils.checkStringExist(mListAllMoodForResult, getString(R.string.sad))) {
                        mListAllMoodForResult.remove(getString(R.string.sad));
                    }
                }
                break;
            case R.id.img_mood_sleepy:
                mCheckSleepy = !mCheckSleepy;
                if (mCheckSleepy) {
                    mImgSleepy.setBackgroundResource(R.drawable.pin_round);
                    if (!Utils.checkStringExist(mListAllMoodForResult, getString(R.string.sleepy))) {
                        mListAllMoodForResult.add(getString(R.string.sleepy));
                    }
                } else {
                    mImgSleepy.setBackgroundResource(0);
                    if (Utils.checkStringExist(mListAllMoodForResult, getString(R.string.sleepy))) {
                        mListAllMoodForResult.remove(getString(R.string.sleepy));
                    }
                }
                break;
            case R.id.img_mood_strees:
                mCheckStress = !mCheckStress;
                if (mCheckStress) {
                    mImgStress.setBackgroundResource(R.drawable.pin_round);
                    if (!Utils.checkStringExist(mListAllMoodForResult, getString(R.string.stress))) {
                        mListAllMoodForResult.add(getString(R.string.stress));
                    }
                } else {
                    mImgStress.setBackgroundResource(0);
                    if (Utils.checkStringExist(mListAllMoodForResult, getString(R.string.stress))) {
                        mListAllMoodForResult.remove(getString(R.string.stress));
                    }
                }
                break;
            case R.id.img_mood_boring:
                mCheckBoring = !mCheckBoring;
                if (mCheckBoring) {
                    mImgBoring.setBackgroundResource(R.drawable.pin_round);
                    if (!Utils.checkStringExist(mListAllMoodForResult, getString(R.string.boring))) {
                        mListAllMoodForResult.add(getString(R.string.boring));
                    }
                } else {
                    mImgBoring.setBackgroundResource(0);
                    if (Utils.checkStringExist(mListAllMoodForResult, getString(R.string.boring))) {
                        mListAllMoodForResult.remove(getString(R.string.boring));
                    }
                }
                break;
            case R.id.img_mood_dizzy:
                mCheckDizzy = !mCheckDizzy;
                if (mCheckDizzy) {
                    mImgDizzy.setBackgroundResource(R.drawable.pin_round);
                    if (!Utils.checkStringExist(mListAllMoodForResult, getString(R.string.dizzy))) {
                        mListAllMoodForResult.add(getString(R.string.dizzy));
                    }
                } else {
                    mImgDizzy.setBackgroundResource(0);
                    if (Utils.checkStringExist(mListAllMoodForResult, getString(R.string.dizzy))) {
                        mListAllMoodForResult.remove(getString(R.string.dizzy));
                    }
                }
                break;
            case R.id.img_mood_rough:
                mCheckRough = !mCheckRough;
                if (mCheckRough) {
                    mImgRough.setBackgroundResource(R.drawable.pin_round);
                    if (!Utils.checkStringExist(mListAllMoodForResult, getString(R.string.rough))) {
                        mListAllMoodForResult.add(getString(R.string.rough));
                    }
                } else {
                    mImgRough.setBackgroundResource(0);
                    if (Utils.checkStringExist(mListAllMoodForResult, getString(R.string.rough))) {
                        mListAllMoodForResult.remove(getString(R.string.rough));
                    }
                }
                break;
            case R.id.img_mood_allergy:
                mCheckAllergy = !mCheckAllergy;
                if (mCheckAllergy) {
                    mImgAllergy.setBackgroundResource(R.drawable.pin_round);
                    if (!Utils.checkStringExist(mListAllMoodForResult, getString(R.string.allergy))) {
                        mListAllMoodForResult.add(getString(R.string.allergy));
                    }
                } else {
                    mImgAllergy.setBackgroundResource(0);
                    if (Utils.checkStringExist(mListAllMoodForResult, getString(R.string.allergy))) {
                        mListAllMoodForResult.remove(getString(R.string.allergy));
                    }
                }
                break;
            case R.id.img_mood_inlove:
                mCheckInLove = !mCheckInLove;
                if (mCheckInLove) {
                    mImgInLove.setBackgroundResource(R.drawable.pin_round);
                    if (!Utils.checkStringExist(mListAllMoodForResult, getString(R.string.in_love))) {
                        mListAllMoodForResult.add(getString(R.string.in_love));
                    }
                } else {
                    mImgInLove.setBackgroundResource(0);
                    if (Utils.checkStringExist(mListAllMoodForResult, getString(R.string.in_love))) {
                        mListAllMoodForResult.remove(getString(R.string.in_love));
                    }
                }
                break;
            case R.id.img_mood_jealous:
                mCheckJealous = !mCheckJealous;
                if (mCheckJealous) {
                    mImgJealous.setBackgroundResource(R.drawable.pin_round);
                    if (!Utils.checkStringExist(mListAllMoodForResult, getString(R.string.jealous))) {
                        mListAllMoodForResult.add(getString(R.string.jealous));
                    }
                } else {
                    mImgJealous.setBackgroundResource(0);
                    if (Utils.checkStringExist(mListAllMoodForResult, getString(R.string.jealous))) {
                        mListAllMoodForResult.remove(getString(R.string.jealous));
                    }
                }
                break;
            case R.id.img_mood_disappoinm:
                mCheckDisappoinment = !mCheckDisappoinment;
                if (mCheckDisappoinment) {
                    mImgDisappoinment.setBackgroundResource(R.drawable.pin_round);
                    if (!Utils.checkStringExist(mListAllMoodForResult, getString(R.string.disappointment))) {
                        mListAllMoodForResult.add(getString(R.string.disappointment));
                    }
                } else {
                    mImgDisappoinment.setBackgroundResource(0);
                    if (Utils.checkStringExist(mListAllMoodForResult, getString(R.string.disappointment))) {
                        mListAllMoodForResult.remove(getString(R.string.disappointment));
                    }
                }
                break;
            case R.id.img_mood_monocotyle:
                mCheckMonocotyledon = !mCheckMonocotyledon;
                if (mCheckMonocotyledon) {
                    mImgMonocotyledon.setBackgroundResource(R.drawable.pin_round);
                    if (!Utils.checkStringExist(mListAllMoodForResult, getString(R.string.monocotyledon))) {
                        mListAllMoodForResult.add(getString(R.string.monocotyledon));
                    }
                } else {
                    mImgMonocotyledon.setBackgroundResource(0);
                    if (Utils.checkStringExist(mListAllMoodForResult, getString(R.string.monocotyledon))) {
                        mListAllMoodForResult.remove(getString(R.string.monocotyledon));
                    }
                }
                break;
            case R.id.img_mood_forgetful:
                mCheckForgetful = !mCheckForgetful;
                if (mCheckForgetful) {
                    mImgForgetful.setBackgroundResource(R.drawable.pin_round);
                    if (!Utils.checkStringExist(mListAllMoodForResult, getString(R.string.forgetful))) {
                        mListAllMoodForResult.add(getString(R.string.forgetful));
                    }
                } else {
                    mImgForgetful.setBackgroundResource(0);
                    if (!Utils.checkStringExist(mListAllMoodForResult, getString(R.string.forgetful))) {
                        mListAllMoodForResult.remove(getString(R.string.forgetful));
                    }
                }
                break;
            case R.id.img_mood_miserable:
                mCheckMiserable = !mCheckMiserable;
                if (mCheckMiserable) {
                    mImgMiserable.setBackgroundResource(R.drawable.pin_round);
                    if (!Utils.checkStringExist(mListAllMoodForResult, getString(R.string.miserable))) {
                        mListAllMoodForResult.add(getString(R.string.miserable));
                    }
                } else {
                    mImgMiserable.setBackgroundResource(0);
                    if (Utils.checkStringExist(mListAllMoodForResult, getString(R.string.miserable))) {
                        mListAllMoodForResult.remove(getString(R.string.miserable));
                    }
                }
                break;
            case R.id.img_mood_panic:
                mCheckPanic = !mCheckPanic;
                if (mCheckPanic) {
                    mImgPanic.setBackgroundResource(R.drawable.pin_round);
                    if (!Utils.checkStringExist(mListAllMoodForResult, getString(R.string.panic))) {
                        mListAllMoodForResult.add(getString(R.string.panic));
                    }
                } else {
                    mImgPanic.setBackgroundResource(0);
                    if (Utils.checkStringExist(mListAllMoodForResult, getString(R.string.panic))) {
                        mListAllMoodForResult.remove(getString(R.string.panic));
                    }
                }
                break;
            case R.id.img_mood_notsafe:
                mCheckNotSafe = !mCheckNotSafe;
                if (mCheckNotSafe) {
                    mImgNotSafe.setBackgroundResource(R.drawable.pin_round);
                    if (!Utils.checkStringExist(mListAllMoodForResult, getString(R.string.not_safe))) {
                        mListAllMoodForResult.add(getString(R.string.not_safe));
                    }
                } else {
                    mImgNotSafe.setBackgroundResource(0);
                    if (Utils.checkStringExist(mListAllMoodForResult, getString(R.string.not_safe))) {
                        mListAllMoodForResult.remove(getString(R.string.not_safe));
                    }
                }
                break;
            case R.id.img_mood_exhausted:
                mCheckExhausted = !mCheckExhausted;
                if (mCheckExhausted) {
                    mImgExhausted.setBackgroundResource(R.drawable.pin_round);
                    if (!Utils.checkStringExist(mListAllMoodForResult, getString(R.string.exhausted))) {
                        mListAllMoodForResult.add(getString(R.string.exhausted));
                    }
                } else {
                    mImgExhausted.setBackgroundResource(0);
                    if (Utils.checkStringExist(mListAllMoodForResult, getString(R.string.exhausted))) {
                        mListAllMoodForResult.remove(getString(R.string.exhausted));
                    }
                }
                break;
            case R.id.img_mood_worry:
                mCheckWorry = !mCheckWorry;
                if (mCheckWorry) {
                    mImgWorry.setBackgroundResource(R.drawable.pin_round);
                    if (!Utils.checkStringExist(mListAllMoodForResult, getString(R.string.worry))) {
                        mListAllMoodForResult.add(getString(R.string.worry));
                    }
                } else {
                    mImgWorry.setBackgroundResource(0);
                    if (Utils.checkStringExist(mListAllMoodForResult, getString(R.string.worry))) {
                        mListAllMoodForResult.remove(getString(R.string.worry));
                    }
                }
                break;
            case R.id.img_mood_hadtounderstand:
                mCheckHadTo = !mCheckHadTo;
                if (mCheckHadTo) {
                    mImgHadTo.setBackgroundResource(R.drawable.pin_round);
                    if (!Utils.checkStringExist(mListAllMoodForResult, getString(R.string.hard_to_understand))) {
                        mListAllMoodForResult.add(getString(R.string.hard_to_understand));
                    }
                } else {
                    mImgHadTo.setBackgroundResource(0);
                    if (Utils.checkStringExist(mListAllMoodForResult, getString(R.string.hard_to_understand))) {
                        mListAllMoodForResult.remove(getString(R.string.hard_to_understand));
                    }
                }
                break;
            case R.id.img_mood_heartless:
                mCheckHeartless = !mCheckHeartless;
                if (mCheckHeartless) {
                    mImgHeartless.setBackgroundResource(R.drawable.pin_round);
                    if (!Utils.checkStringExist(mListAllMoodForResult, getString(R.string.heartless))) {
                        mListAllMoodForResult.add(getString(R.string.heartless));
                    }
                } else {
                    mImgHeartless.setBackgroundResource(0);
                    if (Utils.checkStringExist(mListAllMoodForResult, getString(R.string.heartless))) {
                        mListAllMoodForResult.remove(getString(R.string.heartless));
                    }
                }
                break;
            case R.id.img_mood_incredulous:
                mCheckIncredulous = !mCheckIncredulous;
                if (mCheckIncredulous) {
                    mImgIncredulous.setBackgroundResource(R.drawable.pin_round);
                    if (!Utils.checkStringExist(mListAllMoodForResult, getString(R.string.incredulous))) {
                        mListAllMoodForResult.add(getString(R.string.incredulous));
                    }
                } else {
                    mImgIncredulous.setBackgroundResource(0);
                    if (Utils.checkStringExist(mListAllMoodForResult, getString(R.string.incredulous))) {
                        mListAllMoodForResult.remove(getString(R.string.incredulous));
                    }
                }
                break;
            case R.id.img_mood_naughty:
                mCheckNaughty = !mCheckNaughty;
                if (mCheckNaughty) {
                    mImgNaughty.setBackgroundResource(R.drawable.pin_round);
                    if (!Utils.checkStringExist(mListAllMoodForResult, getString(R.string.naughty))) {
                        mListAllMoodForResult.add(getString(R.string.naughty));
                    }
                } else {
                    mImgNaughty.setBackgroundResource(0);
                    if (Utils.checkStringExist(mListAllMoodForResult, getString(R.string.naughty))) {
                        mListAllMoodForResult.remove(getString(R.string.naughty));
                    }
                }
                break;
            case R.id.img_mood_weak:
                mCheckWeak = !mCheckWeak;
                if (mCheckWeak) {
                    mImgWeak.setBackgroundResource(R.drawable.pin_round);
                    if (!Utils.checkStringExist(mListAllMoodForResult, getString(R.string.weak))) {
                        mListAllMoodForResult.add(getString(R.string.weak));
                    }
                } else {
                    mImgWeak.setBackgroundResource(0);
                    if (Utils.checkStringExist(mListAllMoodForResult, getString(R.string.weak))) {
                        mListAllMoodForResult.remove(getString(R.string.weak));
                    }
                }
                break;
            case R.id.img_mood_bad:
                mCheckBad = !mCheckBad;
                if (mCheckBad) {
                    mImgBad.setBackgroundResource(R.drawable.pin_round);
                    if (!Utils.checkStringExist(mListAllMoodForResult, getString(R.string.bad))) {
                        mListAllMoodForResult.add(getString(R.string.bad));
                    }
                } else {
                    mImgBad.setBackgroundResource(0);
                    if (Utils.checkStringExist(mListAllMoodForResult, getString(R.string.bad))) {
                        mListAllMoodForResult.remove(getString(R.string.bad));
                    }
                }
                break;
            case R.id.img_mood_hasty:
                mCheckHasty = !mCheckHasty;
                if (mCheckHasty) {
                    mImgHasty.setBackgroundResource(R.drawable.pin_round);
                    if (!Utils.checkStringExist(mListAllMoodForResult, getString(R.string.hasty))) {
                        mListAllMoodForResult.add(getString(R.string.hasty));
                    }
                } else {
                    mImgHasty.setBackgroundResource(0);
                    if (Utils.checkStringExist(mListAllMoodForResult, getString(R.string.hasty))) {
                        mListAllMoodForResult.remove(getString(R.string.hasty));
                    }
                }
                break;
            case R.id.img_mood_satisfy:
                mCheckSatisfy = !mCheckSatisfy;
                if (mCheckSatisfy) {
                    mImgSatisfy.setBackgroundResource(R.drawable.pin_round);
                    if (!Utils.checkStringExist(mListAllMoodForResult, getString(R.string.satisfy))) {
                        mListAllMoodForResult.add(getString(R.string.satisfy));
                    }
                } else {
                    mImgSatisfy.setBackgroundResource(0);
                    if (Utils.checkStringExist(mListAllMoodForResult, getString(R.string.satisfy))) {
                        mListAllMoodForResult.remove(getString(R.string.satisfy));
                    }
                }
                break;
            case R.id.img_mood_attenuate:
                mCheckAttenuate = !mCheckAttenuate;
                if (mCheckAttenuate) {
                    mImgAttenuate.setBackgroundResource(R.drawable.pin_round);
                    if (!Utils.checkStringExist(mListAllMoodForResult, getString(R.string.attenuate))) {
                        mListAllMoodForResult.add(getString(R.string.attenuate));
                    }
                } else {
                    mImgAttenuate.setBackgroundResource(0);
                    if (Utils.checkStringExist(mListAllMoodForResult, getString(R.string.attenuate))) {
                        mListAllMoodForResult.remove(getString(R.string.attenuate));
                    }
                }
                break;
            case R.id.img_mood_good:
                mCheckGood = !mCheckGood;
                if (mCheckGood) {
                    mImgGood.setBackgroundResource(R.drawable.pin_round);
                    if (!Utils.checkStringExist(mListAllMoodForResult, getString(R.string.good))) {
                        mListAllMoodForResult.add(getString(R.string.good));
                    }
                } else {
                    mImgGood.setBackgroundResource(0);
                    if (Utils.checkStringExist(mListAllMoodForResult, getString(R.string.good))) {
                        mListAllMoodForResult.remove(getString(R.string.good));
                    }
                }
                break;
            case R.id.img_mood_proud:
                mCheckProud = !mCheckProud;
                if (mCheckProud) {
                    mImgProud.setBackgroundResource(R.drawable.pin_round);
                    if (!Utils.checkStringExist(mListAllMoodForResult, getString(R.string.proud))) {
                        mListAllMoodForResult.add(getString(R.string.proud));
                    }
                } else {
                    mImgProud.setBackgroundResource(0);
                    if (Utils.checkStringExist(mListAllMoodForResult, getString(R.string.proud))) {
                        mListAllMoodForResult.remove(getString(R.string.proud));
                    }
                }
                break;
            case R.id.img_mood_confident:
                mCheckConfident = !mCheckConfident;
                if (mCheckConfident) {
                    mImgConfident.setBackgroundResource(R.drawable.pin_round);
                    if (!Utils.checkStringExist(mListAllMoodForResult, getString(R.string.confident))) {
                        mListAllMoodForResult.add(getString(R.string.confident));
                    }
                } else {
                    mImgConfident.setBackgroundResource(0);
                    if (Utils.checkStringExist(mListAllMoodForResult, getString(R.string.confident))) {
                        mListAllMoodForResult.remove(getString(R.string.confident));
                    }
                }
                break;
            case R.id.img_mood_angry:
                mCheckAngry = !mCheckAngry;
                if (mCheckAngry) {
                    mImgAngry.setBackgroundResource(R.drawable.pin_round);
                    if (!Utils.checkStringExist(mListAllMoodForResult, getString(R.string.angry))) {
                        mListAllMoodForResult.add(getString(R.string.angry));
                    }
                } else {
                    mImgAngry.setBackgroundResource(0);
                    if (Utils.checkStringExist(mListAllMoodForResult, getString(R.string.angry))) {
                        mListAllMoodForResult.remove(getString(R.string.angry));
                    }
                }
                break;
            case R.id.img_mood_thin:
                mCheckThin = !mCheckThin;
                if (mCheckThin) {
                    mImgThin.setBackgroundResource(R.drawable.pin_round);
                    if (!Utils.checkStringExist(mListAllMoodForResult, getString(R.string.thin))) {
                        mListAllMoodForResult.add(getString(R.string.thin));
                    }
                } else {
                    mImgAngry.setBackgroundResource(0);
                    if (Utils.checkStringExist(mListAllMoodForResult, getString(R.string.thin))) {
                        mListAllMoodForResult.remove(getString(R.string.thin));
                    }
                }
                break;
            case R.id.img_back_mood_add_note:
                finish();
                break;
            case R.id.img_done_mood_add_note:
//                Intent intent = new Intent();
//                intent.putStringArrayListExtra(Utils.BACK_MOOD, mListAllMoodForResult);
//                setResult(Activity.RESULT_OK, intent);

                RealmList<RealmMood> mRealmList = new RealmList<>();
                for(int i = 0; i<mListAllMoodForResult.size(); i++){
                    mRealmList.add(new RealmMood(mListAllMoodForResult.get(i)));
                }

                if(mNoteObj != null){
                    Utils.updateListMood(realm, mId, mListAllMoodForResult);
                }else {
                    Utils.insertNoteObj(realm, new NoteObj(mId, 0, "", 0, 0, new RealmList<RealmDrug>(), new RealmList<RealmSymptom>(), mRealmList));
                }
                finish();
                break;
        }
    }
}
