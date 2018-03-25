package com.skyfree.kinhnguyetmangthai.activity;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import com.skyfree.kinhnguyetmangthai.R;
import com.skyfree.kinhnguyetmangthai.database.DatabaseHelper;
import com.skyfree.kinhnguyetmangthai.model.Note;

import java.util.Calendar;

public class NoteActivity extends AppCompatActivity implements View.OnClickListener {

    private RatingBar mRatingBar;
    private LinearLayout mLinearNote, mLinearDrug, mLinearSymptom, mLinearMood, mLinearWeight, mLinearTemperature;
    private ImageView mImgBackDate, mImgNextDate, mImgBackAddNote, mImgDoneAddNote;
    private TextView mTvDate;

    private Calendar mCa = Calendar.getInstance();
    private DatabaseHelper mDb;
    private Note mNote;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);
        initView();
        addEvent();
    }

    private void initView(){
        mRatingBar = (RatingBar) findViewById(R.id.rating_bar_add_note);
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

        mDb = new DatabaseHelper(this);
        mNote = new Note();
    }

    private void addEvent(){
        mTvDate.setText(mCa.get(Calendar.DAY_OF_MONTH) + " - " + (mCa.get(Calendar.MONTH) + 1) + " - " + mCa.get(Calendar.YEAR));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
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

                break;
        }
    }

    private void note(){
        startActivity(new Intent(this, NoteAddNoteActivity.class));
    }

    private void drug(){
        startActivity(new Intent(this, DrugActivity.class));
    }

    private void symptom(){
        startActivity(new Intent(this, SymptomActivity.class));
    }

    private void mood(){
        startActivity(new Intent(this, MoodActivity.class));
    }

    private void weight(){
        alertWeight();
    }

    private void temperature() {
        alertTemperature();
    }

    private void alertWeight(){
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_weight, null);
        dialogBuilder.setView(dialogView);

        ImageView mImgBack = (ImageView) dialogView.findViewById(R.id.img_back_dialog_weight);
        ImageView mImgDone = (ImageView) dialogView.findViewById(R.id.img_done_dialog_weight);
        EditText mEdtWeight = (EditText) dialogView.findViewById(R.id.edt_weight_dialog_weight);
        EditText mEdtHeight = (EditText) dialogView.findViewById(R.id.edt_height_dialog_weight);
        TextView mTvPlusWeight = (TextView) dialogView.findViewById(R.id.tv_plus_weight_dialog_weight);
        TextView mTvPlusHeight = (TextView) dialogView.findViewById(R.id.tv_plus_height_dialog_weight);
        TextView mTvMinusWeight = (TextView) dialogView.findViewById(R.id.tv_minus_weight_dialog_weight);
        TextView mTvMinusHeight = (TextView) dialogView.findViewById(R.id.tv_minus_height_dialog_weight);

        final AlertDialog alertStartDialog = dialogBuilder.create();
        alertStartDialog.show();
    }

    private void alertTemperature(){

    }
}
