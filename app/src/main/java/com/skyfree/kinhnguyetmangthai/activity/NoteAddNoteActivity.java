package com.skyfree.kinhnguyetmangthai.activity;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.skyfree.kinhnguyetmangthai.R;
import com.skyfree.kinhnguyetmangthai.utils.Utils;

public class NoteAddNoteActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView mImgBack, mImgDone;
    private EditText mEdtNote;

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

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.img_back_note_add_note:
                finish();
                break;
            case R.id.img_done_note_add_note:
                Intent returnIntent = new Intent();
                returnIntent.putExtra(Utils.BACK_NOTE, mEdtNote.getText().toString());
                setResult(Activity.RESULT_OK,returnIntent);
                finish();
                break;
        }
    }
}