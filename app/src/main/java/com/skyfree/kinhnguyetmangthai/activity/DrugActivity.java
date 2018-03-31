package com.skyfree.kinhnguyetmangthai.activity;

import android.annotation.SuppressLint;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.skyfree.kinhnguyetmangthai.R;
import com.skyfree.kinhnguyetmangthai.adapter.ListOtherDrugAdapter;
import com.skyfree.kinhnguyetmangthai.adapter.ListPillBirthControlAdapter;
import com.skyfree.kinhnguyetmangthai.model.NoteObj;
import com.skyfree.kinhnguyetmangthai.model.RealmDrug;
import com.skyfree.kinhnguyetmangthai.model.RealmMood;
import com.skyfree.kinhnguyetmangthai.model.RealmSymptom;
import com.skyfree.kinhnguyetmangthai.utils.Utils;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmList;

public class DrugActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView mImgBack, mImgDone;
    private ListView mLvBirthControlPills, mLvDrug;
    private LinearLayout mLinearBirthControlPills, mLinearDrug;

    private ListPillBirthControlAdapter mPillAdapter;
    private ListOtherDrugAdapter mOtherDrugAdapter;

    Realm realm;

    private ArrayList<String> mListAllDrugForResult;
    private ArrayList<String> mListDrugForResult;
    private ArrayList<String> mListOtherDrugForResult;
    private ArrayList<String> mArrDrugTam;
    private ArrayList<String> mArrOtherDrugTam;

    private NoteObj mNoteObj;
    private String mId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drug);
        initView();
        addEvent();
    }

    private void initView() {
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

        mLvBirthControlPills.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(DrugActivity.this, "1", Toast.LENGTH_SHORT).show();
                return false;
            }
        });

        mLvDrug.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(DrugActivity.this, "2", Toast.LENGTH_SHORT).show();
                return false;
            }
        });
    }

    @SuppressLint("LongLogTag")
    private void addEvent() {
        realm = Realm.getDefaultInstance();
        mListAllDrugForResult = new ArrayList<>();
        mArrDrugTam = new ArrayList<>();
        mArrOtherDrugTam = new ArrayList<>();

        mId = getIntent().getStringExtra(Utils.PUT_ID);
        mNoteObj = Utils.getNoteObj(realm, mId);
        if (mNoteObj != null) {
            for (int i = 0; i < mNoteObj.getmListDrug().size(); i++) {
                if(!Utils.checkStringExist(mListAllDrugForResult, mNoteObj.getmListDrug().get(i).getmDrug())){
                    mListAllDrugForResult.add(mNoteObj.getmListDrug().get(i).getmDrug());
                }
            }
        }

        for (int i = 0; i < mListAllDrugForResult.size(); i++) {

            if (mListAllDrugForResult.get(i).equals(getString(R.string.contraceptives))) {
                if (!Utils.checkStringExist(mArrDrugTam, getString(R.string.contraceptives))) {
                    mArrDrugTam.add(mListAllDrugForResult.get(i));
                }
            }
            if (mListAllDrugForResult.get(i).equals(getString(R.string.inject))) {
                if (!Utils.checkStringExist(mArrDrugTam, getString(R.string.inject))) {
                    mArrDrugTam.add(mListAllDrugForResult.get(i));
                }
            }
            if (mListAllDrugForResult.get(i).equals(getString(R.string.bandage))) {
                if (!Utils.checkStringExist(mArrDrugTam, getString(R.string.bandage))) {
                    mArrDrugTam.add(mListAllDrugForResult.get(i));
                }
            }
            if (mListAllDrugForResult.get(i).equals(getString(R.string.vaginal_ring))) {
                if (!Utils.checkStringExist(mArrDrugTam, getString(R.string.vaginal_ring))) {
                    mArrDrugTam.add(mListAllDrugForResult.get(i));
                }
            }
            if (!mListAllDrugForResult.get(i).equals(getString(R.string.contraceptives)) && !mListAllDrugForResult.get(i).equals(getString(R.string.inject)) && !mListAllDrugForResult.get(i).equals(getString(R.string.bandage)) && !mListAllDrugForResult.get(i).equals(getString(R.string.vaginal_ring))) {
                if (!Utils.checkStringExist(mArrDrugTam, mListAllDrugForResult.get(i))) {
                    mArrOtherDrugTam.add(mListAllDrugForResult.get(i));
                }
            }
        }

        mPillAdapter = new ListPillBirthControlAdapter(this, mArrDrugTam);
        mLvBirthControlPills.setAdapter(mPillAdapter);

        mOtherDrugAdapter = new ListOtherDrugAdapter(this, mArrOtherDrugTam);
        mLvDrug.setAdapter(mOtherDrugAdapter);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
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
                mListAllDrugForResult.clear();
                for(int i = 0; i<mArrDrugTam.size(); i++){
                    if(!Utils.checkStringExist(mListAllDrugForResult, mArrDrugTam.get(i))){
                        mListAllDrugForResult.add(mArrDrugTam.get(i));
                    }
                }
                for(int i = 0; i<mArrOtherDrugTam.size(); i++){
                    if(!Utils.checkStringExist(mListAllDrugForResult, mArrOtherDrugTam.get(i))){
                        mListAllDrugForResult.add(mArrOtherDrugTam.get(i));
                    }

                }

                RealmList<RealmDrug> mRealmListDrug = new RealmList<>();
                for(int i = 0; i<mListAllDrugForResult.size(); i++){
                    mRealmListDrug.add(new RealmDrug(mListAllDrugForResult.get(i)));
                }


                if(mNoteObj != null){
                    Utils.updateListDrug(realm, mId, mListAllDrugForResult);
                }else {
                    Utils.insertNoteObj(realm, new NoteObj(mId, 0, "", 0, 0, mRealmListDrug, new RealmList<RealmSymptom>(), new RealmList<RealmMood>()));
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

    private void alertBirthControlPills() {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_contraceptives, null);
        dialogBuilder.setView(dialogView);

        final CheckBox mCbContraceptives = (CheckBox) dialogView.findViewById(R.id.cb_pill_birth_control);
        final CheckBox mCbInjection = (CheckBox) dialogView.findViewById(R.id.cb_injection);
        final CheckBox mCbBandage = (CheckBox) dialogView.findViewById(R.id.cb_patch);
        final CheckBox mCbVaginalRing = (CheckBox) dialogView.findViewById(R.id.cb_vring);
        ImageView mImgDone = (ImageView) dialogView.findViewById(R.id.img_done_dialog_birth_control_pills);

        final AlertDialog alertStartDialog = dialogBuilder.create();
        alertStartDialog.show();

        for (int i = 0; i < mArrDrugTam.size(); i++) {
            if (mArrDrugTam.get(i).equals(getString(R.string.contraceptives))) {
                mCbContraceptives.setChecked(true);
            } else if (mArrDrugTam.get(i).equals(getString(R.string.inject))) {
                mCbInjection.setChecked(true);
            } else if (mArrDrugTam.get(i).equals(getString(R.string.bandage))) {
                mCbBandage.setChecked(true);
            } else if (mArrDrugTam.get(i).equals(getString(R.string.vaginal_ring))) {
                mCbVaginalRing.setChecked(true);
            }
        }

        mImgDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (mCbContraceptives.isChecked()) {
                    if (!Utils.checkStringExist(mArrDrugTam, getString(R.string.contraceptives))) {
                        mArrDrugTam.add(getString(R.string.contraceptives));
                    }
                } else {
                    if (Utils.checkStringExist(mArrDrugTam, getString(R.string.contraceptives))) {
                        mArrDrugTam.remove(getString(R.string.contraceptives));
                    }
                }
                if (mCbInjection.isChecked()) {
                    if (!Utils.checkStringExist(mArrDrugTam, getString(R.string.inject))) {
                        mArrDrugTam.add(getString(R.string.inject));
                    }
                } else {
                    if (Utils.checkStringExist(mArrDrugTam, getString(R.string.inject))) {
                        mArrDrugTam.remove(getString(R.string.inject));
                    }
                }

                if (mCbBandage.isChecked()) {
                    if (!Utils.checkStringExist(mArrDrugTam, getString(R.string.bandage))) {
                        mArrDrugTam.add(getString(R.string.bandage));
                    }

                } else {
                    if (Utils.checkStringExist(mArrDrugTam, getString(R.string.bandage))) {
                        mArrDrugTam.remove(getString(R.string.bandage));
                    }
                }
                if (mCbVaginalRing.isChecked()) {
                    if (!Utils.checkStringExist(mArrDrugTam, getString(R.string.vaginal_ring))) {
                        mArrDrugTam.add(getString(R.string.vaginal_ring));
                    }
                } else {
                    if (Utils.checkStringExist(mArrDrugTam, getString(R.string.vaginal_ring))) {
                        mArrDrugTam.remove(getString(R.string.vaginal_ring));
                    }
                }
                mPillAdapter.notifyDataSetChanged();
                alertStartDialog.cancel();
            }
        });
    }

    private void alertOtherDrug() {
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
                if (!mEdtEnter.getText().toString().equals("")) {
                    if (!Utils.checkStringExist(mArrOtherDrugTam, mEdtEnter.getText().toString())) {
                        mArrOtherDrugTam.add(mEdtEnter.getText().toString());
                    }
                }
                mOtherDrugAdapter.notifyDataSetChanged();
                alertStartDialog.cancel();
            }
        });
    }
}
