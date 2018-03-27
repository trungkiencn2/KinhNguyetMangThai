package com.skyfree.kinhnguyetmangthai.activity;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.skyfree.kinhnguyetmangthai.R;
import com.skyfree.kinhnguyetmangthai.database.DatabaseHelper;
import com.skyfree.kinhnguyetmangthai.model.NoteObj;
import com.skyfree.kinhnguyetmangthai.model.RealmDrug;
import com.skyfree.kinhnguyetmangthai.model.RealmMood;
import com.skyfree.kinhnguyetmangthai.model.RealmSymptom;
import com.skyfree.kinhnguyetmangthai.utils.Utils;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Calendar;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmList;
import io.realm.RealmMigration;
import io.realm.RealmResults;
import me.zhanghai.android.materialratingbar.MaterialRatingBar;

public class NoteActivity extends AppCompatActivity implements View.OnClickListener {

    private MaterialRatingBar mRatingBar;
    private LinearLayout mLinearNote, mLinearDrug, mLinearSymptom, mLinearMood, mLinearWeight, mLinearTemperature;
    private ImageView mImgBackDate, mImgNextDate, mImgBackAddNote, mImgDoneAddNote;
    private TextView mTvDate, mTvNote, mTvWeight, mTvTemperature;

    private Calendar mCa = Calendar.getInstance();
    private NoteObj mNote;
    private int mNoteLuongKinh;
    private String mNoteNote;

    private RealmList<RealmDrug> mNoteListDrug;
    private RealmList<RealmSymptom> mNoteListSymptoms;
    private RealmList<RealmMood> mNoteListMood;

    private ArrayList<String> mNoteListDrugArr;
    private ArrayList<String> mNoteListSymptomsArr;
    private ArrayList<String> mNoteListMoodArr;

    private float mNoteWeight;
    private float mNoteTemperature;

    private String UP_OR_DOWN = "DOWN";
    private String UP = "UP";
    private String DOWN = "DOWN";

    private Realm realm;

    private int mLoadLuongKinh;
    private String mLoadNote;
    private RealmList<RealmDrug> mLoadNoteListDrug;
    private RealmList<RealmSymptom> mLoadNoteListSymptoms;
    private RealmList<RealmMood> mLoadNoteListMood;
    private ArrayList<String> mLoadNoteListDrugArr;
    private ArrayList<String> mLoadNoteListSymptomsArr;
    private ArrayList<String> mLoadNoteListMoodArr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);
        initView();
        addEvent();
//        loadData(mCa.get(Calendar.DAY_OF_MONTH), mCa.get(Calendar.MONTH), mCa.get(Calendar.YEAR));
    }

    private void initView() {
        mRatingBar = (MaterialRatingBar) findViewById(R.id.rating_bar_add_note);
        mLinearNote = (LinearLayout) findViewById(R.id.linear_note_add_note);
        mLinearDrug = (LinearLayout) findViewById(R.id.linear_drug_add_note);
        mLinearSymptom = (LinearLayout) findViewById(R.id.linear_symptom_add_note);
        mLinearMood = (LinearLayout) findViewById(R.id.linear_mood_add_note);
        mLinearWeight = (LinearLayout) findViewById(R.id.linear_weight_add_note);
        mLinearTemperature = (LinearLayout) findViewById(R.id.linear_temperature_add_note);
        mImgBackDate = (ImageView) findViewById(R.id.img_back_date);
        mImgNextDate = (ImageView) findViewById(R.id.img_next_date);
        mImgBackAddNote = (ImageView) findViewById(R.id.img_back_add_note);
        mImgDoneAddNote = (ImageView) findViewById(R.id.img_done_add_note);
        mTvDate = (TextView) findViewById(R.id.tv_date_add_note);
        mTvNote = (TextView) findViewById(R.id.tv_note_note_activity);
        mTvWeight = (TextView) findViewById(R.id.tv_weight_note_activity);
        mTvTemperature = (TextView) findViewById(R.id.tv_temperature_note_activity);

        mLinearNote.setOnClickListener(this);
        mLinearDrug.setOnClickListener(this);
        mLinearSymptom.setOnClickListener(this);
        mLinearMood.setOnClickListener(this);
        mLinearWeight.setOnClickListener(this);
        mLinearTemperature.setOnClickListener(this);
        mImgBackDate.setOnClickListener(this);
        mImgNextDate.setOnClickListener(this);
        mImgBackAddNote.setOnClickListener(this);
        mImgDoneAddNote.setOnClickListener(this);

        mNote = new NoteObj();
    }

    private void addEvent() {
        realm = Realm.getInstance(this);
        mTvDate.setText(mCa.get(Calendar.DAY_OF_MONTH) + " - " + (mCa.get(Calendar.MONTH) + 1) + " - " + mCa.get(Calendar.YEAR));
        mNoteNote = "";
        mNoteListDrugArr = new ArrayList<>();
        mNoteListSymptomsArr = new ArrayList<>();
        mNoteListMoodArr = new ArrayList<>();
        mNoteListDrug = new RealmList<>();
        mNoteListSymptoms = new RealmList<>();
        mNoteListMood = new RealmList<>();

        mLoadNote = "";
        mLoadNoteListDrug = new RealmList<>();
        mLoadNoteListSymptoms = new RealmList<>();
        mLoadNoteListMood = new RealmList<>();
    }

    private void loadData(int day, int month, int year){
        realm = Realm.getInstance(this);
        if(Utils.checkNoteObjExistByDate(realm, mCa.get(Calendar.DAY_OF_MONTH) + "" + mCa.get(Calendar.MONTH) + "" + mCa.get(Calendar.YEAR))){
            NoteObj mCurrentNote = Utils.getNoteObj(realm, mCa.get(Calendar.DAY_OF_MONTH) + "" + mCa.get(Calendar.MONTH) + "" + mCa.get(Calendar.YEAR));
            mRatingBar.setProgress(mCurrentNote.getmNoteLuongKinh());
            mTvNote.setText(mCurrentNote.getmNoteNote());
            mTvWeight.setText(mCurrentNote.getmNoteWeight()+"");
            mTvTemperature.setText(mCurrentNote.getmNoteTemperature()+"");

            mLoadNote = mCurrentNote.getmNoteNote();
            mLoadNoteListDrug = mCurrentNote.getmListDrug();
            mLoadNoteListSymptoms = mCurrentNote.getmListSymptom();
            mLoadNoteListMood = mCurrentNote.getmListMood();
        }else {
            mRatingBar.setProgress(0);
            mTvNote.setText("");
            mTvWeight.setText("");
            mTvTemperature.setText("");
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(Utils.STATE.equals(Utils.BACK_TO_RESULT)){
            mTvNote.setText(mNoteNote);
            Utils.STATE = "";
        }else {
            loadData(mCa.get(Calendar.DAY_OF_MONTH), mCa.get(Calendar.MONTH), mCa.get(Calendar.YEAR));
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.linear_note_add_note:
                note();
                break;
            case R.id.linear_drug_add_note:
                drug();
                break;
            case R.id.linear_symptom_add_note:
                symptom();
                break;
            case R.id.linear_mood_add_note:
                mood();
                break;
            case R.id.linear_weight_add_note:
                weight();
                break;
            case R.id.linear_temperature_add_note:
                temperature();
                break;
            case R.id.img_back_date:
                mCa.add(Calendar.DAY_OF_MONTH, -1);
                addEvent();
                break;
            case R.id.img_next_date:
                mCa.add(Calendar.DAY_OF_MONTH, 1);
                addEvent();
                break;
            case R.id.img_back_add_note:
                finish();
                break;
            case R.id.img_done_add_note:
                mNoteLuongKinh = mRatingBar.getProgress();
                for (int i = 0; i < mNoteListDrugArr.size(); i++) {
                    mNoteListDrug.add(new RealmDrug(mNoteListDrugArr.get(i)));
                }
                for (int i = 0; i < mNoteListSymptomsArr.size(); i++) {
                    mNoteListSymptoms.add(new RealmSymptom(mNoteListSymptomsArr.get(i)));
                }
                for (int i = 0; i < mNoteListMoodArr.size(); i++) {
                    mNoteListMood.add(new RealmMood(mNoteListMoodArr.get(i)));
                }

                String idCheck = mCa.get(Calendar.DAY_OF_MONTH) + "" + mCa.get(Calendar.MONTH) + "" + mCa.get(Calendar.YEAR);

                NoteObj mNoteObjCheck = new NoteObj(idCheck , mNoteLuongKinh,
                        mNoteNote, mNoteWeight, mNoteTemperature, mNoteListDrug, mNoteListSymptoms, mNoteListMood);


                String aa = "";
                RealmResults<NoteObj> mListN = Utils.getAllNoteObj(realm);
                for(int i = 0; i<mListN.size(); i++){
                    String ii = mListN.get(i).getId();
                    aa += ii;
                }

                Log.d("aaa aa", aa);

                String idd = mNoteObjCheck.getId();


                if(Utils.checkNoteObjExist(realm, idd)){
                    Toast.makeText(this, "Data update!!!", Toast.LENGTH_SHORT).show();
                    String id = mNoteObjCheck.getId();
                    Utils.updateNoteObj(realm, id, mNoteObjCheck);
                    NoteObj mNoteObj = Utils.getNoteObj(realm, mNoteObjCheck.getId());
                }else {
                    Utils.insertNoteObj(realm, mNoteObjCheck);
                }
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == Utils.REQUEST_NOTE) {
            if (resultCode == Activity.RESULT_OK) {
                String result = data.getStringExtra(Utils.BACK_NOTE);
                mNoteNote = result;
                Log.d("aaa back note", mNoteNote);
            }
        } else if (requestCode == Utils.REQUEST_DRUG) {
            if (resultCode == Activity.RESULT_OK) {
                ArrayList<String> listDrug = data.getStringArrayListExtra(Utils.BACK_DRUG);
                mNoteListDrugArr.addAll(listDrug);
            }
        } else if (requestCode == Utils.REQUEST_SYMPTOM) {
            if (resultCode == Activity.RESULT_OK) {
                ArrayList<String> listSymptom = data.getStringArrayListExtra(Utils.BACK_SYMPTOM);
                mNoteListSymptomsArr.addAll(listSymptom);
            }
        } else if (requestCode == Utils.REQUEST_MOOD) {
            if (resultCode == Activity.RESULT_OK) {
                ArrayList<String> listMood = data.getStringArrayListExtra(Utils.BACK_MOOD);
                mNoteListMoodArr.addAll(listMood);
            }
        }
    }

    private void note() {
        Intent i = new Intent(this, NoteAddNoteActivity.class);
        i.putExtra(Utils.PUT_NOTE, mLoadNote);
        startActivityForResult(i, Utils.REQUEST_NOTE);
    }

    private void drug() {
        Intent it = new Intent(this, DrugActivity.class);
        for(int i = 0; i<mLoadNoteListDrug.size(); i++){
            mLoadNoteListDrugArr.add(mLoadNoteListDrug.get(i).getmDrug());
        }
        it.putStringArrayListExtra(Utils.PUT_LIST_DRUG, mLoadNoteListDrugArr);
        startActivityForResult(it, Utils.REQUEST_DRUG);
    }

    private void symptom() {
        Intent it = new Intent(this, SymptomActivity.class);
        for(int i = 0; i<mLoadNoteListSymptoms.size(); i++){
            mLoadNoteListSymptomsArr.add(mLoadNoteListDrug.get(i).getmDrug());
        }
        it.putStringArrayListExtra(Utils.PUT_LIST_SYMPTOM, mLoadNoteListSymptomsArr);
        startActivityForResult(it, Utils.REQUEST_SYMPTOM);
    }

    private void mood() {
        Intent it = new Intent(this, MoodActivity.class);
        for(int i = 0; i<mLoadNoteListMood.size(); i++){
            mLoadNoteListMoodArr.add(mLoadNoteListDrug.get(i).getmDrug());
        }
        it.putStringArrayListExtra(Utils.PUT_LIST_MOOD, mLoadNoteListMoodArr);
        startActivityForResult(it, Utils.REQUEST_MOOD);
    }

    private void weight() {
        alertWeight();
    }

    private void temperature() {
        alertTemperature();
    }

    private void alertWeight() {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_weight, null);
        dialogBuilder.setView(dialogView);

        ImageView mImgBack = (ImageView) dialogView.findViewById(R.id.img_back_dialog_weight);
        ImageView mImgDone = (ImageView) dialogView.findViewById(R.id.img_done_dialog_weight);
        final ImageView mImgUpOrDown = (ImageView) dialogView.findViewById(R.id.img_up_or_down_dialog_weight);
        ImageView mImgOne = (ImageView) dialogView.findViewById(R.id.img_one_weight);
        ImageView mImgTwo = (ImageView) dialogView.findViewById(R.id.img_two_weight);
        ImageView mImgThree = (ImageView) dialogView.findViewById(R.id.img_three_weight);
        ImageView mImgFour = (ImageView) dialogView.findViewById(R.id.img_four_weight);
        ImageView mImgFive = (ImageView) dialogView.findViewById(R.id.img_five_weight);
        ImageView mImgSix = (ImageView) dialogView.findViewById(R.id.img_six_weight);
        final EditText mEdtWeight = (EditText) dialogView.findViewById(R.id.edt_weight_dialog_weight);
        final EditText mEdtHeight = (EditText) dialogView.findViewById(R.id.edt_height_dialog_weight);
        TextView mTvPlusWeight = (TextView) dialogView.findViewById(R.id.tv_plus_weight_dialog_weight);
        TextView mTvPlusHeight = (TextView) dialogView.findViewById(R.id.tv_plus_height_dialog_weight);
        TextView mTvMinusWeight = (TextView) dialogView.findViewById(R.id.tv_minus_weight_dialog_weight);
        TextView mTvMinusHeight = (TextView) dialogView.findViewById(R.id.tv_minus_height_dialog_weight);
        final TextView mTvResult = (TextView) dialogView.findViewById(R.id.tv_result_dialog_weight);
        final TextView mTvPoint = (TextView) dialogView.findViewById(R.id.tv_point_dialog_weight);
        final LinearLayout mLinearResult = (LinearLayout) dialogView.findViewById(R.id.linear_result_dialog_weight);

        final AlertDialog alertStartDialog = dialogBuilder.create();
        alertStartDialog.show();

        mTvPlusWeight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!mEdtHeight.getText().toString().equals("") && !mEdtWeight.getText().toString().equals("")) {
                    mEdtWeight.setText(Float.parseFloat(mEdtWeight.getText().toString()) + 0.1 + "");
                    mTvPoint.setText((Float.parseFloat(mEdtWeight.getText().toString()) / Float.parseFloat(mEdtHeight.getText().toString()) * 100) + "");
                    mTvResult.setText(getResultWeight((Float.parseFloat(mTvPoint.getText().toString()))));
                }
            }
        });

        mTvPlusHeight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!mEdtHeight.getText().toString().equals("") && !mEdtWeight.getText().toString().equals("")) {
                    mEdtHeight.setText(Float.parseFloat(mEdtHeight.getText().toString()) + 0.1 + "");
                    mTvPoint.setText((Float.parseFloat(mEdtWeight.getText().toString()) / Float.parseFloat(mEdtHeight.getText().toString()) * 100) + "");
                    mTvResult.setText(getResultWeight((Float.parseFloat(mTvPoint.getText().toString()))));
                }
            }
        });

        mTvMinusWeight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!mEdtHeight.getText().toString().equals("") && !mEdtWeight.getText().toString().equals("")) {
                    mEdtWeight.setText(Float.parseFloat(mEdtWeight.getText().toString()) - 0.1 + "");
                    mTvPoint.setText((Float.parseFloat(mEdtWeight.getText().toString()) / Float.parseFloat(mEdtHeight.getText().toString()) * 100) + "");
                    mTvResult.setText(getResultWeight((Float.parseFloat(mTvPoint.getText().toString()))));
                }
            }
        });

        mTvMinusHeight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!mEdtHeight.getText().toString().equals("") && !mEdtWeight.getText().toString().equals("")) {
                    mEdtHeight.setText(Float.parseFloat(mEdtHeight.getText().toString()) - 0.1 + "");
                    mTvPoint.setText((Float.parseFloat(mEdtWeight.getText().toString()) / Float.parseFloat(mEdtHeight.getText().toString()) * 100) + "");
                    mTvResult.setText(getResultWeight((Float.parseFloat(mTvPoint.getText().toString()))));
                }
            }
        });

        mImgUpOrDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (UP_OR_DOWN.equals(DOWN)) {
                    UP_OR_DOWN = UP;
                    mImgUpOrDown.setImageResource(R.drawable.ic_up);
                    mLinearResult.setVisibility(View.VISIBLE);

                } else if (UP_OR_DOWN.equals(UP)) {
                    UP_OR_DOWN = DOWN;
                    mImgUpOrDown.setImageResource(R.drawable.ic_down);
                    mLinearResult.setVisibility(View.GONE);
                }
            }
        });

        mEdtWeight.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!mEdtHeight.getText().toString().equals("") && !mEdtWeight.getText().toString().equals("")) {
                    mTvPoint.setText((Float.parseFloat(mEdtWeight.getText().toString()) / Float.parseFloat(mEdtHeight.getText().toString()) * 100) + "");
                    mTvResult.setText(getResultWeight((Float.parseFloat(mTvPoint.getText().toString()))));
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        mEdtHeight.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!mEdtHeight.getText().toString().equals("") && !mEdtWeight.getText().toString().equals("")) {
                    mTvPoint.setText((Float.parseFloat(mEdtWeight.getText().toString()) / Float.parseFloat(mEdtHeight.getText().toString()) * 100) + "");
                    mTvResult.setText(getResultWeight((Float.parseFloat(mTvPoint.getText().toString()))));
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        mImgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertStartDialog.cancel();
            }
        });

        mImgDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mNoteWeight = Float.parseFloat(mEdtWeight.getText().toString());

                alertStartDialog.cancel();
            }
        });
    }

    private void alertTemperature() {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_temperature, null);
        dialogBuilder.setView(dialogView);

        final EditText mEdtTemperature = (EditText) dialogView.findViewById(R.id.edt_dialog_temperature);
        TextView mTvPlus = (TextView) dialogView.findViewById(R.id.tv_plus_dialog_temperature);
        TextView mTvMinus = (TextView) dialogView.findViewById(R.id.tv_minus_dialog_temperature);
        ImageView mImgBack = (ImageView) dialogView.findViewById(R.id.img_back_dialog_temperature);
        ImageView mImgDone = (ImageView) dialogView.findViewById(R.id.img_done_dialog_temperature);

        final AlertDialog alertStartDialog = dialogBuilder.create();
        alertStartDialog.show();

        mTvPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mEdtTemperature.setText(Float.parseFloat(mEdtTemperature.getText().toString()) + 0.1 + "");
            }
        });

        mTvMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mEdtTemperature.setText(Float.parseFloat(mEdtTemperature.getText().toString()) - 0.1 + "");
            }
        });

        mImgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                alertStartDialog.cancel();

            }
        });

        mImgDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Float.parseFloat(mEdtTemperature.getText().toString()) > 50 || Float.parseFloat(mEdtTemperature.getText().toString()) < 30) {
                    Toast.makeText(NoteActivity.this, getString(R.string.wrong_answer), Toast.LENGTH_SHORT).show();
                } else {
                    mNoteTemperature = Float.parseFloat(mEdtTemperature.getText().toString());
                    alertStartDialog.cancel();
                }
            }
        });

    }

    private String getResultWeight(Float result) {
        if (result < 15) {
            return getString(R.string.lack_of_extreme_weight);
        } else if (result < 16) {
            return getString(R.string.severe_weight_loss);
        } else if (result < 18.5) {
            return getString(R.string.underweight);
        } else if (result < 25) {
            return getString(R.string.full_weight);
        } else if (result < 30) {
            return getString(R.string.overweight);
        } else if (result < 35) {
            return getString(R.string.quite_fat);
        } else if (result < 40) {
            return getString(R.string.very_fat);
        } else {
            return getString(R.string.fat);
        }
    }
}
