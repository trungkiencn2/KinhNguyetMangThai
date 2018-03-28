package com.skyfree.kinhnguyetmangthai.activity;

import android.app.Activity;
import android.content.Intent;
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
import com.skyfree.kinhnguyetmangthai.model.RealmDrug;
import com.skyfree.kinhnguyetmangthai.utils.Utils;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmList;

public class DrugActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView mImgBack, mImgDone;
    private ListView mLvBirthControlPills, mLvDrug;
    private LinearLayout mLinearBirthControlPills, mLinearDrug;

    private ListPillBirthControlAdapter mPillAdapter;
    private ListOtherDrugAdapter mOtherDrugAdapter;
    private RealmList<RealmDrug> mRealmListNow;
    private RealmList<RealmDrug> mRealmListAllDrug;
    private RealmList<RealmDrug> mRealmListDrug;
    private RealmList<RealmDrug> mRealmListOtherDrug;
    Realm realm;

    private ArrayList<String> mListAllDrugForResult;
    private ArrayList<String> mListDrugForResult;
    private ArrayList<String> mListOtherDrugForResult;

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
        realm = Realm.getDefaultInstance();
        mRealmListNow = new RealmList<>();
        mRealmListAllDrug = new RealmList<>();
        mRealmListDrug = new RealmList<>();
        mRealmListOtherDrug = new RealmList<>();
        mListAllDrugForResult = new ArrayList<>();

        for(int i = 0; i<mListAllDrugForResult.size(); i++){
            if(mListAllDrugForResult.get(i).equals(getString(R.string.contraceptives))){
                mRealmListDrug.add(new RealmDrug(mListAllDrugForResult.get(i)));
            }else if(mListAllDrugForResult.get(i).equals(getString(R.string.inject))){
                mRealmListDrug.add(new RealmDrug(mListAllDrugForResult.get(i)));
            }else if(mListAllDrugForResult.get(i).equals(getString(R.string.bandage))){
                mRealmListDrug.add(new RealmDrug(mListAllDrugForResult.get(i)));
            }else if(mListAllDrugForResult.get(i).equals(getString(R.string.vaginal_ring))){
                mRealmListDrug.add(new RealmDrug(mListAllDrugForResult.get(i)));
            }
        }

        mPillAdapter = new ListPillBirthControlAdapter(this, mRealmListDrug);
        mLvBirthControlPills.setAdapter(mPillAdapter);

        mOtherDrugAdapter = new ListOtherDrugAdapter(this, mRealmListOtherDrug);
        mLvDrug.setAdapter(mOtherDrugAdapter);
        
        mListAllDrugForResult = getIntent().getStringArrayListExtra(Utils.PUT_LIST_DRUG);
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
                mRealmListAllDrug.addAll(mRealmListDrug);
                mRealmListAllDrug.addAll(mRealmListOtherDrug);

                ArrayList<String> mListAllDrugForResult = new ArrayList<>();
                for (int i = 0; i<mRealmListAllDrug.size(); i++){
                    mListAllDrugForResult.add(mRealmListAllDrug.get(i).getmDrug());
                }

                Intent intent = new Intent();
                intent.putStringArrayListExtra(Utils.BACK_DRUG, mListAllDrugForResult);
                setResult(Activity.RESULT_OK, intent);
                finish();
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        realm.close();
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
                    if(!Utils.checkDrugExist(mRealmListNow, getString(R.string.contraceptives))){
                        mRealmListNow.add(new RealmDrug(getString(R.string.contraceptives)));
                    }
                }else {
                    if(Utils.checkDrugExist(mRealmListNow, getString(R.string.contraceptives))){
                        mRealmListNow.remove(new RealmDrug(getString(R.string.contraceptives)));
                    }
                }
            }
        });

        mCbInjection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mCbInjection.isChecked()){
                    if(!Utils.checkDrugExist(mRealmListNow, getString(R.string.inject))){
                        mRealmListNow.add(new RealmDrug(getString(R.string.inject)));
                    }
                }else {
                    if(Utils.checkDrugExist(mRealmListNow, getString(R.string.inject))){
                        mRealmListNow.remove(new RealmDrug(getString(R.string.inject)));
                    }
                }
            }
        });

        mCbPatch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mCbPatch.isChecked()){
                    if(!Utils.checkDrugExist(mRealmListNow, getString(R.string.bandage))){
                        mRealmListNow.add(new RealmDrug(getString(R.string.bandage)));
                    }
                }else {
                    if(Utils.checkDrugExist(mRealmListNow, getString(R.string.bandage))){
                        mRealmListNow.remove(new RealmDrug(getString(R.string.bandage)));
                    }
                }
            }
        });

        mCbVring.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mCbVring.isChecked()){
                    if(!Utils.checkDrugExist(mRealmListNow, getString(R.string.vaginal_ring))){
                        mRealmListNow.add(new RealmDrug(getString(R.string.vaginal_ring)));
                    }
                }else {
                    if (Utils.checkDrugExist(mRealmListNow, getString(R.string.vaginal_ring))){
                        mRealmListNow.remove(new RealmDrug(getString(R.string.vaginal_ring)));
                    }
                }
            }
        });

        mImgDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mRealmListNow.size()>0){
                    for (int i = 0; i<mRealmListNow.size(); i++){
                        if(!Utils.checkDrugExist(mRealmListDrug, mRealmListNow.get(i).getmDrug())){
                            mRealmListDrug.add(mRealmListNow.get(i));
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
                    if(!Utils.checkDrugExist(mRealmListOtherDrug, mEdtEnter.getText().toString())){
                        mRealmListOtherDrug.add(new RealmDrug(mEdtEnter.getText().toString()));
                    }
                }
                mOtherDrugAdapter.notifyDataSetChanged();
                alertStartDialog.cancel();
            }
        });
    }
}
