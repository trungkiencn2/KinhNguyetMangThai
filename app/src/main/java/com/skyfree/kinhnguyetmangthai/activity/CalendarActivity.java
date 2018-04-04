package com.skyfree.kinhnguyetmangthai.activity;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.skyfree.kinhnguyetmangthai.R;
import com.skyfree.kinhnguyetmangthai.adapter.ListOptionAdapter;
import com.skyfree.kinhnguyetmangthai.custom_interface.ISetIdForCalendarActivity;
import com.skyfree.kinhnguyetmangthai.custom_interface.IUpdateTopTime;
import com.skyfree.kinhnguyetmangthai.fragment.FragmentCalendar;
import com.skyfree.kinhnguyetmangthai.adapter.PageMonthAdapter;
import com.skyfree.kinhnguyetmangthai.model.NoteObj;
import com.skyfree.kinhnguyetmangthai.utils.Utils;

import java.util.ArrayList;
import java.util.Calendar;

import io.realm.Realm;
import me.zhanghai.android.materialratingbar.MaterialRatingBar;

public class CalendarActivity extends AppCompatActivity implements IUpdateTopTime, ISetIdForCalendarActivity, View.OnClickListener {

    private TextView mTvDateCalendarActivity, mTvNote, mTvDrug, mTvSymptom, mTvMood, mTvWeight, mTvTemperature;
    private TextView mTvDateNote;
    private ImageView mImgHoiCham, mImgBackMonth, mImgNextMonth;
    private ListView mLvDrug, mLvSymptom, mLvMood;
    private LinearLayout mLinearInfo;
    private ImageView mImgBack;
    public static ViewPager mPager;
    private MaterialRatingBar mRatingBarLuongKinh;

    private PageMonthAdapter mFragmentAdapter;

    private Calendar mCaSetForPager = Calendar.getInstance();
    private Calendar mCurrentCa = Calendar.getInstance();
    private String mCurrentId = "";

    private Realm mRealm;
    private int mPutDay, mPutMonth, mPutYear;
    private ListOptionAdapter mAdapterForDrug;
    private ListOptionAdapter mAdapterForSymptom;
    private ListOptionAdapter mAdapterForMood;

    ArrayList<Integer> mListDrugImage;
    ArrayList<Integer> mListSymptomImage;
    ArrayList<Integer> mListMoodImage;

    ArrayList<String> mListDrugStr;
    ArrayList<String> mListSymptomStr;
    ArrayList<String> mListMoodStr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);
        mRealm = Realm.getDefaultInstance();
        initView();
        mCurrentId = mCurrentCa.get(Calendar.DAY_OF_MONTH) + "" + mCurrentCa.get(Calendar.MONTH) + "" + mCurrentCa.get(Calendar.YEAR);
    }

    @Override
    protected void onResume() {
        super.onResume();
        addEvent();
    }

    private void initView() {
        mImgBackMonth = (ImageView) findViewById(R.id.img_back_month);
        mImgNextMonth = (ImageView) findViewById(R.id.img_next_month);
        mImgHoiCham = (ImageView) findViewById(R.id.img_hoicham_calendar_activity);
        mTvDateCalendarActivity = (TextView) findViewById(R.id.tv_date_calendar_activity);
        mTvNote = (TextView) findViewById(R.id.tv_note_calendar_activity);
        mTvDrug = (TextView) findViewById(R.id.tv_drug_calendar_activity);
        mTvSymptom = (TextView) findViewById(R.id.tv_symptom_calendar_activity);
        mTvMood = (TextView) findViewById(R.id.tv_mood_calendar_activity);
        mTvWeight = (TextView) findViewById(R.id.tv_weight_calendar_activity);
        mTvTemperature = (TextView) findViewById(R.id.tv_temperature_calendar_activity);
        mTvDateNote = (TextView) findViewById(R.id.tv_date_note_calendar_activity);
        mLinearInfo = (LinearLayout) findViewById(R.id.linear_info_calendar_activity);
        mImgBack = (ImageView) findViewById(R.id.img_back_calendar_activity);
        mLvDrug = (ListView) findViewById(R.id.lv_drug_calendar_activity);
        mLvSymptom = (ListView) findViewById(R.id.lv_symptom_calendar_activity);
        mLvMood = (ListView) findViewById(R.id.lv_mood_calendar_activity);
        mRatingBarLuongKinh = (MaterialRatingBar) findViewById(R.id.ratingbar_luong_kinh_calendar_activity);

        mImgBackMonth.setOnClickListener(this);
        mImgNextMonth.setOnClickListener(this);
        mImgHoiCham.setOnClickListener(this);
        mImgBack.setOnClickListener(this);
        mLinearInfo.setOnClickListener(this);
        mCaSetForPager.add(Calendar.MONTH, -24);
        mFragmentAdapter = new PageMonthAdapter(getSupportFragmentManager());
        for (int i = 0; i <= 47; i++) {
            Fragment fragment = new FragmentCalendar(mCaSetForPager.get(Calendar.MONTH), mCaSetForPager.get(Calendar.YEAR), this, this);
            mCaSetForPager.add(Calendar.MONTH, 1);
            if (mCaSetForPager.get(Calendar.MONTH) > 11) {
                mCaSetForPager.set(Calendar.MONTH, 0);
                mCaSetForPager.add(Calendar.YEAR, 1);
            } else if (mCaSetForPager.get(Calendar.MONTH) < 0) {
                mCaSetForPager.set(Calendar.MONTH, 11);
                mCaSetForPager.add(Calendar.YEAR, -1);
            }
            mFragmentAdapter.addPage(fragment);
        }

        mPager = findViewById(R.id.view_pager_calendar);
        mPager.setAdapter(mFragmentAdapter);
        mPager.setCurrentItem(24);


    }

    private void addEvent() {

        mListDrugStr = new ArrayList<>();
        mListSymptomStr = new ArrayList<>();
        mListMoodStr = new ArrayList<>();

        mListDrugImage = new ArrayList<>();
        mListSymptomImage = new ArrayList<>();
        mListMoodImage = new ArrayList<>();

        NoteObj mCurrentNoteObj = Utils.getNoteObj(mRealm, mCurrentId);

        mAdapterForDrug = new ListOptionAdapter(this, mListDrugImage);
        mAdapterForSymptom = new ListOptionAdapter(this, mListSymptomImage);
        mAdapterForMood = new ListOptionAdapter(this, mListMoodImage);
        mLvDrug.setAdapter(mAdapterForDrug);
        mLvSymptom.setAdapter(mAdapterForSymptom);
        mLvMood.setAdapter(mAdapterForMood);

        String listDrug = "";
        String listSymptom = "";
        String listMood = "";

        String dateStr = mCurrentCa.get(Calendar.DAY_OF_MONTH) + " - " + (mCurrentCa.get(Calendar.MONTH) + 1) + " - " + mCurrentCa.get(Calendar.YEAR);
        mTvDateNote.setText(dateStr);

        if (mCurrentNoteObj != null) {

            mTvNote.setText(mCurrentNoteObj.getmNoteNote());

            for (int i = 0; i < mCurrentNoteObj.getmListDrug().size(); i++) {
                listDrug += mCurrentNoteObj.getmListDrug().get(i).getmDrug() + ", ";
                mListDrugStr.add(mCurrentNoteObj.getmListDrug().get(i).getmDrug());
            }
            for (int i = 0; i < mCurrentNoteObj.getmListSymptom().size(); i++) {
                listSymptom += mCurrentNoteObj.getmListSymptom().get(i).getmSymptom() + ", ";
                mListSymptomStr.add(mCurrentNoteObj.getmListSymptom().get(i).getmSymptom());
            }
            for (int i = 0; i < mCurrentNoteObj.getmListMood().size(); i++) {
                listMood += mCurrentNoteObj.getmListMood().get(i).getmMood() + ", ";
                mListMoodStr.add(mCurrentNoteObj.getmListMood().get(i).getmMood());
            }

            getListImageDrug(mListDrugStr);
            mAdapterForDrug.notifyDataSetChanged();
            getListImageSymptom(mListSymptomStr);
            mAdapterForSymptom.notifyDataSetChanged();
            getListImageMood(mListMoodStr);
            mAdapterForMood.notifyDataSetChanged();

            mTvDrug.setText(listDrug);
            mTvSymptom.setText(listSymptom);
            mTvMood.setText(listMood);
            mRatingBarLuongKinh.setProgress(mCurrentNoteObj.getmNoteLuongKinh());

            if (mCurrentNoteObj.getmNoteWeight() <= 0) {
                mTvWeight.setText("");
            } else {
                mTvWeight.setText(mCurrentNoteObj.getmNoteWeight() + " kg");
            }

            if (mCurrentNoteObj.getmNoteTemperature() <= 0) {
                mTvTemperature.setText("");
            } else {
                mTvTemperature.setText(mCurrentNoteObj.getmNoteTemperature() + " °C");
            }


        } else {
            mTvNote.setText("");
            mTvDrug.setText("");
            mTvTemperature.setText("");
            mTvWeight.setText("");
            mTvMood.setText("");
            mTvSymptom.setText("");
        }
    }

    private void getListImageDrug(ArrayList<String> listStr){
        for(int i = 0; i<listStr.size(); i++){
            if(listStr.get(i).equals(getString(R.string.contraceptives))){
                mListDrugImage.add(R.drawable.pill_brith_control);
            }else if(listStr.get(i).equals(getString(R.string.inject))){
                mListDrugImage.add(R.drawable.pill_injection);
            }else if(listStr.get(i).equals(getString(R.string.bandage))){
                mListDrugImage.add(R.drawable.pill_patch);
            }else if(listStr.get(i).equals(getString(R.string.vaginal_ring))){
                mListDrugImage.add(R.drawable.pill_vring);
            }
        }
    }

    private void getListImageSymptom(ArrayList<String> listStr){
        for(int i = 0; i<listStr.size(); i++){
            if(listStr.get(i).equals(getString(R.string.abdominal_cramps))){
                mListSymptomImage.add(R.drawable.icon_symp_abdominal_cramps);
            }else if(listStr.get(i).equals(getString(R.string.anxiety))){
                mListSymptomImage.add(R.drawable.icon_symp_anxiety);
            }else if(listStr.get(i).equals(getString(R.string.astriction))){
                mListSymptomImage.add(R.drawable.icon_symp_astriction);
            }else if(listStr.get(i).equals(getString(R.string.backaches))){
                mListSymptomImage.add(R.drawable.icon_symp_backaches);
            }else if(listStr.get(i).equals(getString(R.string.bloating))){
                mListSymptomImage.add(R.drawable.icon_symp_bloating);
            }else if(listStr.get(i).equals(getString(R.string.body_aches))){
                mListSymptomImage.add(R.drawable.icon_symp_body_aches);
            }else if(listStr.get(i).equals(getString(R.string.cervical_mucus))){
                mListSymptomImage.add(R.drawable.icon_symp_cervical_mucus);
            }else if(listStr.get(i).equals(getString(R.string.chills))){
                mListSymptomImage.add(R.drawable.icon_symp_chills);
            }else if(listStr.get(i).equals(getString(R.string.confused))){
                mListSymptomImage.add(R.drawable.icon_symp_confused);
            }else if(listStr.get(i).equals(getString(R.string.cramps))){
                mListSymptomImage.add(R.drawable.icon_symp_cramps);
            }else if(listStr.get(i).equals(getString(R.string.creamy))){
                mListSymptomImage.add(R.drawable.icon_symp_creamy);
            }else if(listStr.get(i).equals(getString(R.string.diarrhea))){
                mListSymptomImage.add(R.drawable.icon_symp_diarrhea);
            }else if(listStr.get(i).equals(getString(R.string.dizziness))){
                mListSymptomImage.add(R.drawable.icon_symp_dizziness);
            }else if(listStr.get(i).equals(getString(R.string.dry))){
                mListSymptomImage.add(R.drawable.icon_symp_dry);
            }else if(listStr.get(i).equals(getString(R.string.dyspepsia))){
                mListSymptomImage.add(R.drawable.icon_symp_dyspepsia);
            }else if(listStr.get(i).equals(getString(R.string.fatigue))){
                mListSymptomImage.add(R.drawable.icon_symp_fatigue);
            }else if(listStr.get(i).equals(getString(R.string.headaches))){
                mListSymptomImage.add(R.drawable.icon_symp_headaches);
            }else if(listStr.get(i).equals(getString(R.string.hectic_fever))){
                mListSymptomImage.add(R.drawable.icon_symp_hectic_fever);
            }else if(listStr.get(i).equals(getString(R.string.hungry))){
                mListSymptomImage.add(R.drawable.icon_symp_hungry);
            }else if(listStr.get(i).equals(getString(R.string.illness))){
                mListSymptomImage.add(R.drawable.icon_symp_illness);
            } else if(listStr.get(i).equals(getString(R.string.influenza))){
                mListSymptomImage.add(R.drawable.icon_symp_influenza);
            }else if(listStr.get(i).equals(getString(R.string.insomnia))){
                mListSymptomImage.add(R.drawable.icon_symp_insomnia);
            }else if(listStr.get(i).equals(getString(R.string.irritability))){
                mListSymptomImage.add(R.drawable.icon_symp_irritability);
            }else if(listStr.get(i).equals(getString(R.string.irritation))){
                mListSymptomImage.add(R.drawable.icon_symp_irritation);
            }else if(listStr.get(i).equals(getString(R.string.itch))){
                mListSymptomImage.add(R.drawable.icon_symp_itch);
            }else if(listStr.get(i).equals(getString(R.string.migraine))){
                mListSymptomImage.add(R.drawable.icon_symp_migraine);
            }else if(listStr.get(i).equals(getString(R.string.neckaches))){
                mListSymptomImage.add(R.drawable.icon_symp_neckaches);
            }else if(listStr.get(i).equals(getString(R.string.night_sweat))){
                mListSymptomImage.add(R.drawable.icon_symp_night_sweat);
            }else if(listStr.get(i).equals(getString(R.string.pelvic_pain))){
                mListSymptomImage.add(R.drawable.icon_symp_pelvic_pain);
            }else if(listStr.get(i).equals(getString(R.string.pms))){
                mListSymptomImage.add(R.drawable.icon_symp_pms);
            }else if(listStr.get(i).equals(getString(R.string.queasiness))){
                mListSymptomImage.add(R.drawable.icon_symp_queasiness);
            }else if(listStr.get(i).equals(getString(R.string.rash))){
                mListSymptomImage.add(R.drawable.icon_symp_rash);
            }else if(listStr.get(i).equals(getString(R.string.shoulder_ache))){
                mListSymptomImage.add(R.drawable.icon_symp_shoulder_ache);
            }else if(listStr.get(i).equals(getString(R.string.spotting_bleeding))){
                mListSymptomImage.add(R.drawable.icon_symp_spotting_bleeding);
            }else if(listStr.get(i).equals(getString(R.string.sticky))){
                mListSymptomImage.add(R.drawable.icon_symp_sticky);
            }else if(listStr.get(i).equals(getString(R.string.stress))){
                mListSymptomImage.add(R.drawable.icon_symp_stress);
            }else if(listStr.get(i).equals(getString(R.string.tension))){
                mListSymptomImage.add(R.drawable.icon_symp_tension);
            }else if(listStr.get(i).equals(getString(R.string.watery))){
                mListSymptomImage.add(R.drawable.icon_symp_watery);
            }else if(listStr.get(i).equals(getString(R.string.weight_gain))){
                mListSymptomImage.add(R.drawable.icon_symp_weight_gain);
            }else if(listStr.get(i).equals(getString(R.string.with_blood))){
                mListSymptomImage.add(R.drawable.icon_symp_with_blood);
            }
        }
    }

    private void getListImageMood(ArrayList<String> listStr){
        for(int i = 0; i<listStr.size(); i++){
            if(listStr.get(i).equals(getString(R.string.normal))){
                mListMoodImage.add(R.drawable.icon_mood_normal);
            }else if(listStr.get(i).equals(getString(R.string.sad))){
                mListMoodImage.add(R.drawable.icon_mood_grieved);
            }else if(listStr.get(i).equals(getString(R.string.sleepy))){
                mListMoodImage.add(R.drawable.icon_mood_sleepy);
            }else if(listStr.get(i).equals(getString(R.string.stress))){
                mListMoodImage.add(R.drawable.icon_mood_stressed);
            }else if(listStr.get(i).equals(getString(R.string.boring))){
                mListMoodImage.add(R.drawable.icon_mood_bored);
            }else if(listStr.get(i).equals(getString(R.string.dizzy))){
                mListMoodImage.add(R.drawable.icon_mood_tense);
            }else if(listStr.get(i).equals(getString(R.string.rough))){
                mListMoodImage.add(R.drawable.icon_mood_grumpy);
            }else if(listStr.get(i).equals(getString(R.string.allergy))){
                mListMoodImage.add(R.drawable.icon_mood_angelic);
            }else if(listStr.get(i).equals(getString(R.string.in_love))){
                mListMoodImage.add(R.drawable.icon_mood_in_love);
            }else if(listStr.get(i).equals(getString(R.string.jealous))){
                mListMoodImage.add(R.drawable.icon_mood_jealous);
            }else if(listStr.get(i).equals(getString(R.string.disappointment))){
                mListMoodImage.add(R.drawable.icon_mood_frustrated);
            }else if(listStr.get(i).equals(getString(R.string.monocotyledon))){
                mListMoodImage.add(R.drawable.icon_mood_evil);
            }else if(listStr.get(i).equals(getString(R.string.forgetful))){
                mListMoodImage.add(R.drawable.icon_mood_forgetful);
            }else if(listStr.get(i).equals(getString(R.string.miserable))){
                mListMoodImage.add(R.drawable.icon_mood_misery);
            }else if(listStr.get(i).equals(getString(R.string.panic))){
                mListMoodImage.add(R.drawable.icon_mood_panic);
            }else if(listStr.get(i).equals(getString(R.string.not_safe))){
                mListMoodImage.add(R.drawable.icon_mood_unsafe);
            }else if(listStr.get(i).equals(getString(R.string.exhausted))){
                mListMoodImage.add(R.drawable.icon_mood_exhausted);
            }else if(listStr.get(i).equals(getString(R.string.worry))){
                mListMoodImage.add(R.drawable.icon_mood_worry);
            }else if(listStr.get(i).equals(getString(R.string.hard_to_understand))){
                mListMoodImage.add(R.drawable.icon_mood_weird);
            }else if(listStr.get(i).equals(getString(R.string.heartless))){
                mListMoodImage.add(R.drawable.icon_mood_harsh);
            }else if(listStr.get(i).equals(getString(R.string.incredulous))){
                mListMoodImage.add(R.drawable.icon_mood_distrustful);
            }else if(listStr.get(i).equals(getString(R.string.naughty))){
                mListMoodImage.add(R.drawable.icon_mood_frisky);
            }else if(listStr.get(i).equals(getString(R.string.weak))){
                mListMoodImage.add(R.drawable.icon_mood_cranky);
            }else if(listStr.get(i).equals(getString(R.string.bad))){
                mListMoodImage.add(R.drawable.icon_mood_morose);
            }else if(listStr.get(i).equals(getString(R.string.hasty))){
                mListMoodImage.add(R.drawable.icon_mood_not_patient);
            }else if(listStr.get(i).equals(getString(R.string.satisfy))){
                mListMoodImage.add(R.drawable.icon_mood_satisfied);
            }else if(listStr.get(i).equals(getString(R.string.attenuate))){
                mListMoodImage.add(R.drawable.icon_mood_depressed);
            }else if(listStr.get(i).equals(getString(R.string.good))){
                mListMoodImage.add(R.drawable.icon_mood_pleased);
            }else if(listStr.get(i).equals(getString(R.string.proud))){
                mListMoodImage.add(R.drawable.icon_mood_proud);
            }else if(listStr.get(i).equals(getString(R.string.confident))){
                mListMoodImage.add(R.drawable.icon_mood_confident);
            }else if(listStr.get(i).equals(getString(R.string.angry))){
                mListMoodImage.add(R.drawable.icon_mood_angry);
            }else if(listStr.get(i).equals(getString(R.string.thin))){
                mListMoodImage.add(R.drawable.icon_mood_ill);
            }
        }
    }

    @Override
    public void updateTopTime(String timeStr) {
        mTvDateCalendarActivity.setText(timeStr);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mRealm.close();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.linear_info_calendar_activity:
                Intent it = new Intent(this, NoteActivity.class);
                it.putExtra(Utils.PUT_DAY, mCurrentCa.get(Calendar.DAY_OF_MONTH));
                it.putExtra(Utils.PUT_MONTH, mCurrentCa.get(Calendar.MONTH));
                it.putExtra(Utils.PUT_YEAR, mCurrentCa.get(Calendar.YEAR));
                startActivity(it);
                break;
            case R.id.img_back_calendar_activity:
                finish();
                break;
            case R.id.img_hoicham_calendar_activity:
                showAlertHoiCham();
                break;
            case R.id.img_back_month:
                if(mPager.getCurrentItem() == 0){
                    mPager.setCurrentItem(47);
                }else {
                    mPager.setCurrentItem(mPager.getCurrentItem() - 1);
                }
                break;
            case R.id.img_next_month:
                if(mPager.getCurrentItem() == 47){
                    mPager.setCurrentItem(0);
                }else {
                    mPager.setCurrentItem(mPager.getCurrentItem() + 1);
                }
                break;
        }
    }

    private void showAlertHoiCham() {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_hoi_cham, null);
        dialogBuilder.setView(dialogView);

        TextView mTvOk = (TextView) dialogView.findViewById(R.id.tv_ok_dialog_hoi_cham);

        final AlertDialog alertStartDialog = dialogBuilder.create();
        alertStartDialog.show();

        mTvOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertStartDialog.cancel();
            }
        });
    }

    @Override
    public void setDate(int day, int month, int year) {
        mCurrentCa.set(year, month, day);
        mCurrentId = day + "" + month + "" + year;
        addEvent();
    }

}
