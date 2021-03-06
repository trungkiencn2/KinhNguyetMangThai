package com.skyfree.kinhnguyetmangthai.activity;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.skyfree.kinhnguyetmangthai.R;
import com.skyfree.kinhnguyetmangthai.model.NoteObj;
import com.skyfree.kinhnguyetmangthai.model.RealmDrug;
import com.skyfree.kinhnguyetmangthai.model.RealmMood;
import com.skyfree.kinhnguyetmangthai.model.RealmSymptom;
import com.skyfree.kinhnguyetmangthai.utils.Utils;

import io.realm.Realm;
import io.realm.RealmList;

public class NoteAddNoteActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView mImgBack, mImgDone;
    private EditText mEdtNote;

    private NoteObj mNoteObj;
    private Realm realm;
    private String mId;
    private long mTimeMili;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_add_note);
        initView();
        addEvent();
    }

    private void initView(){
        mImgBack = (ImageView) findViewById(R.id.img_back_note_add_note);
        mImgDone = (ImageView) findViewById(R.id.img_done_note_add_note);
        mEdtNote = (EditText) findViewById(R.id.mEdt_note_add_note);

        mImgBack.setOnClickListener(this);
        mImgDone.setOnClickListener(this);
    }

    private void addEvent(){
        realm = Realm.getDefaultInstance();
        mId = getIntent().getStringExtra(Utils.PUT_ID);
        mTimeMili = getIntent().getLongExtra(Utils.PUT_TIME_MILI, 0);
        mNoteObj = Utils.getNoteObj(realm, mId);

        if(mNoteObj != null){
            mEdtNote.setText(mNoteObj.getmNoteNote());
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.img_back_note_add_note:
                finish();
                break;
            case R.id.img_done_note_add_note:
                Utils.STATE = Utils.BACK_TO_RESULT;
                Intent returnIntent = new Intent();
                returnIntent.putExtra(Utils.BACK_NOTE, mEdtNote.getText().toString());
                setResult(Activity.RESULT_OK,returnIntent);
                if(mNoteObj!= null){
                    Utils.updateNoteOfNote(realm, mId, mEdtNote.getText().toString());
                }else {
                    Utils.insertNoteObj(realm, new NoteObj(mId,mTimeMili, 0, mEdtNote.getText().toString(), 0, 0, new RealmList<RealmDrug>(), new RealmList<RealmSymptom>(), new RealmList<RealmMood>()));
                }
                finish();
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        realm.close();
    }
}