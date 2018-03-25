package com.skyfree.kinhnguyetmangthai.activity;

import android.media.Image;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.skyfree.kinhnguyetmangthai.R;
import com.skyfree.kinhnguyetmangthai.adapter.ListOtherDrugAdapter;
import com.skyfree.kinhnguyetmangthai.adapter.ListPillBirthControlAdapter;

import java.util.ArrayList;

public class DrugActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView mImgBack, mImgDone;
    private ListView mLvBirthControlPills, mLvDrug;
    private LinearLayout mLinearBirthControlPills, mLinearDrug;

    private ListPillBirthControlAdapter mPillAdapter;
    private ListOtherDrugAdapter mOtherDrugAdapter;
    private ArrayList<String> mListPill;
    private ArrayList<String> mListNow;
    private ArrayList<String> mListOtherDrug;
    private ArrayList<String> mListAllDrug;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drug);

        initView();
        addEvent();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    private void initView(){
        mImgBack = (ImageView) findViewById(R.id.img_back_drug);
        mImgDone = (ImageView) findViewById(R.id.img_done_drug);
        mLvBirthControlPills = (ListView) findViewById(R.id.lv_birth_control_pills_drug);
        mLvDrug = (ListView) findViewById(R.id.lv_other_drug);
        mLinearBirthControlPills = (LinearLayout) findViewById(R.id.linear_birth_control_pills);
        mLinearDrug = (LinearLayout) findViewById(R.id.linear_other_drug);

        mLinearBirthControlPills.setOnClickListener(this);
        mLinearDrug.setOnClickListener(this);
        mImgBack.setOnClickListener(this);
        mImgDone.setOnClickListener(this);
    }

    private void addEvent(){
        mListPill = new ArrayList<>();
        mListNow = new ArrayList<>();
        mListOtherDrug = new ArrayList<>();
        mListAllDrug = new ArrayList<>();

        mPillAdapter = new ListPillBirthControlAdapter(this, mListPill);
        mLvBirthControlPills.setAdapter(mPillAdapter);

        mOtherDrugAdapter = new ListOtherDrugAdapter(this, mListOtherDrug);
        mLvDrug.setAdapter(mOtherDrugAdapter);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.linear_birth_control_pills:
                alertBirthControlPills();
                break;
            case R.id.linear_other_drug:
                alertOtherDrug();
                break;
            case R.id.img_back_drug:
                finish();
                break;
            case R.id.img_done_drug:
                mListAllDrug.addAll(mListOtherDrug);
                mListAllDrug.addAll(mListPill);
                break;
        }
    }

    private void alertBirthControlPills(){
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_birth_control_pills, null);
        dialogBuilder.setView(dialogView);

        final CheckBox mCbPillBirthControl = (CheckBox) dialogView.findViewById(R.id.cb_pill_birth_control);
        final CheckBox mCbInjection = (CheckBox) dialogView.findViewById(R.id.cb_injection);
        final CheckBox mCbPatch = (CheckBox) dialogView.findViewById(R.id.cb_patch);
        final CheckBox mCbVring = (CheckBox) dialogView.findViewById(R.id.cb_vring);
        ImageView mImgDone = (ImageView) dialogView.findViewById(R.id.img_done_dialog_birth_control_pills);

        final AlertDialog alertStartDialog = dialogBuilder.create();
        alertStartDialog.show();

        mCbPillBirthControl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mCbPillBirthControl.isChecked()){
                    if(!checkDataExist(mListNow, getString(R.string.contraceptives))){
                        mListNow.add(getString(R.string.contraceptives));
                    }
                }else {
                    if(checkDataExist(mListNow, getString(R.string.contraceptives))){
                        mListNow.remove(getString(R.string.contraceptives));
                    }
                }
            }
        });

        mCbInjection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mCbInjection.isChecked()){
                    if(!checkDataExist(mListNow, getString(R.string.inject))){
                        mListNow.add(getString(R.string.inject));
                    }
                }else {
                    if(checkDataExist(mListNow, getString(R.string.inject))){
                        mListNow.remove(getString(R.string.inject));
                    }
                }
            }
        });

        mCbPatch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mCbPatch.isChecked()){
                    if(!checkDataExist(mListNow, getString(R.string.bandage))){
                        mListNow.add(getString(R.string.bandage));
                    }
                }else {
                    if(checkDataExist(mListNow, getString(R.string.bandage))){
                        mListNow.remove(getString(R.string.bandage));
                    }
                }
            }
        });

        mCbVring.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mCbVring.isChecked()){
                    if(!checkDataExist(mListNow, getString(R.string.vaginal_ring))){
                        mListNow.add(getString(R.string.vaginal_ring));
                    }
                }else {
                    if (checkDataExist(mListNow, getString(R.string.vaginal_ring))){
                        mListNow.remove(getString(R.string.vaginal_ring));
                    }
                }
            }
        });

        mImgDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mListNow.size()>0){
                    for (int i = 0; i<mListNow.size(); i++){
                        if(!checkDataExist(mListPill, mListNow.get(i))){
                            mListPill.add(mListNow.get(i));
                        }
                    }
                }
                mPillAdapter.notifyDataSetChanged();
                alertStartDialog.cancel();
            }
        });
    }

    private void alertOtherDrug(){
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_other_drug, null);
        dialogBuilder.setView(dialogView);

        final EditText mEdtEnter = (EditText) dialogView.findViewById(R.id.edt_dialog_other_drug);
        TextView mTvCancel = (TextView) dialogView.findViewById(R.id.tv_cancel_dialog_other_drug);
        TextView mTvOk = (TextView) dialogView.findViewById(R.id.tv_ok_dialog_other_drug);

        final AlertDialog alertStartDialog = dialogBuilder.create();
        alertStartDialog.show();

        mTvCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertStartDialog.cancel();
            }
        });

        mTvOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!mEdtEnter.getText().toString().equals("")){
                    if(!checkDataExist(mListOtherDrug, mEdtEnter.getText().toString())){
                        mListOtherDrug.add(mEdtEnter.getText().toString());
                    }
                }
                mOtherDrugAdapter.notifyDataSetChanged();
                alertStartDialog.cancel();
            }
        });
    }

    private boolean checkDataExist(ArrayList mList, String str){
        if(mList.size()>0){
            for (int i = 0; i<mList.size(); i++){
                if (mList.get(i).equals(str)){
                    return true;
                }
            }
        }
        return false;
    }
}
