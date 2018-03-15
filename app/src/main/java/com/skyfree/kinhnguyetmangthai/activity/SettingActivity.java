package com.skyfree.kinhnguyetmangthai.activity;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.skyfree.kinhnguyetmangthai.R;
import com.skyfree.kinhnguyetmangthai.utils.Utils;

public class SettingActivity extends AppCompatActivity implements View.OnClickListener {

    private LinearLayout mLinearMenstrualLength, mLinearCycleLength, mLinearOvulation,
            mLinearMaternity, mLinearRemind,  mLinearPassword, mLinearSendMessage,
            mLinearRate, mLinearShare, mLinearDelete;

    private TextView mTvMenstrualLength, mTvCycleLength;

    private SharedPreferences.Editor mEditor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        Utils.writeToFile(14 + "", Utils.FILE_REPORT_SO_NGAY_GIAI_DOAN_HOANG_THE, this);
        initView();
        addEvent();
    }

    private void addEvent() {
        mTvMenstrualLength.setText(Utils.readFromFile(Utils.FILE_CHU_KY_HANH_KINH, this));
        mTvCycleLength.setText(Utils.readFromFile(Utils.FILE_CHU_KY_KINH_NGUYET, this));
    }

    private void initView() {
        mLinearMenstrualLength = (LinearLayout) findViewById(R.id.st_menstrual_length);
        mLinearCycleLength = (LinearLayout) findViewById(R.id.st_ovulation);
        mLinearOvulation = (LinearLayout) findViewById(R.id.st_ovulation);
        mLinearMaternity = (LinearLayout) findViewById(R.id.st_maternity);
        mLinearRemind = (LinearLayout) findViewById(R.id.st_remind);
        mLinearPassword = (LinearLayout) findViewById(R.id.st_password);
//        mLinearFollow = (LinearLayout) findViewById(R.id.st_follow);
//        mLinearDataForDoctor = (LinearLayout) findViewById(R.id.st_for_doctor);
        mLinearSendMessage = (LinearLayout) findViewById(R.id.st_message_us);
        mLinearRate = (LinearLayout) findViewById(R.id.st_rate);
        mLinearShare = (LinearLayout) findViewById(R.id.st_share);
        mLinearDelete = (LinearLayout) findViewById(R.id.st_delete);
        mTvMenstrualLength = (TextView) findViewById(R.id.tv_menstrual_length_st);
        mTvCycleLength = (TextView) findViewById(R.id.tv_cycle_length_st);

        mLinearMenstrualLength.setOnClickListener(this);
        mLinearCycleLength.setOnClickListener(this);
        mLinearOvulation.setOnClickListener(this);
        mLinearMaternity.setOnClickListener(this);
        mLinearRemind.setOnClickListener(this);
        mLinearPassword.setOnClickListener(this);
//        mLinearFollow.setOnClickListener(this);
//        mLinearDataForDoctor.setOnClickListener(this);
        mLinearSendMessage.setOnClickListener(this);
        mLinearRate.setOnClickListener(this);
        mLinearShare.setOnClickListener(this);
        mLinearDelete.setOnClickListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(Utils.readFromFile(Utils.FILE_NEW_USER, this).equals(Utils.DANG_MANG_THAI)){
            mTvCycleLength.setText("");
            mTvMenstrualLength.setText("");
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.st_menstrual_length:
                menstrualLeng();
                break;
            case R.id.st_cycle_length:
                cycleLength();
                break;
            case R.id.st_ovulation:
                ovulation();
                break;
            case R.id.st_maternity:
                maternity();
                break;
            case R.id.st_remind:
                remind();
                break;
            case R.id.st_password:
                password();
                break;
//            case R.id.st_follow:
//                follow();
//                break;
//            case R.id.st_for_doctor:
//                forDoctor();
//                break;
            case R.id.st_message_us:
                messageUs();
                break;
            case R.id.st_rate:
                rate();
                break;
            case R.id.st_share:
                share();
                break;
            case R.id.st_delete:
                delete();
                break;

        }
    }

    private void delete() {

        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_delete_all_data, null);
        dialogBuilder.setView(dialogView);
        
        Button mBtnCancel = (Button) dialogView.findViewById(R.id.btn_cancel_dialog_delete);
        Button mBtnOk = (Button) dialogView.findViewById(R.id.btn_ok_dialog_delete);

        final AlertDialog alertStartDialog = dialogBuilder.create();
        alertStartDialog.show();
        
        mBtnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertStartDialog.cancel();
            }
        });
        
        mBtnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Utils.writeToFile(Utils.TRUE, Utils.FILE_NEW_USER, getApplicationContext());
                getApplicationContext().deleteFile(Utils.FILE_CHU_KY_KINH_NGUYET);
                getApplicationContext().deleteFile(Utils.FILE_CHU_KY_HANH_KINH);
                getApplicationContext().deleteFile(Utils.FILE_NGAY_BAT_DAU_CHU_KY_KINH_NGUYET);
                getApplicationContext().deleteFile(Utils.FILE_OVULATION);
                getApplicationContext().deleteFile(Utils.FILE_DATE_ESTIMATE);
                getApplicationContext().deleteFile(Utils.FILE_REPORT_CYCLE);
                getApplicationContext().deleteFile(Utils.FILE_REPORT_EASY_TO_CONCEIVE);
                getApplicationContext().deleteFile(Utils.FILE_REPORT_SO_NGAY_GIAI_DOAN_HOANG_THE);

                Toast.makeText(SettingActivity.this, getString(R.string.you_just_delete_all_data), Toast.LENGTH_SHORT).show();
                alertStartDialog.cancel();

                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                finish();
            }
        });
        
    }

    private void share() {
        Utils.sendPassToMail(this, "https://play.google.com/store/apps/details?id=" + getPackageName() + "\n" + getString(R.string.share_mail));
    }

    private void rate() {
        try {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + getPackageName())));
        } catch (ActivityNotFoundException e) {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + getPackageName())));
        }
    }

    private void messageUs() {
        Utils.sendByGmail(this, getString(R.string.message_us), "trungkiencn2@gmail.com");
    }

    private void password() {
        startActivity(new Intent(this, PasswordActivity.class));
    }

    private void remind() {
        startActivity(new Intent(this, RemindActivity.class));
    }

    private void maternity() {
        startActivity(new Intent(this, MaternityActivity.class));
    }

    private void ovulation() {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_ovulation, null);
        dialogBuilder.setView(dialogView);

        final EditText mEdtCount = (EditText) dialogView.findViewById(R.id.edt_count_dialog_ovulation);
        TextView mTvPlus = (TextView) dialogView.findViewById(R.id.tv_plus_dialog_ovulation);
        TextView mTvMinus = (TextView) dialogView.findViewById(R.id.tv_minus_dialog_ovulation);
        Button mBtnCancel = (Button) dialogView.findViewById(R.id.btn_cancel_dialog_ovulation);
        Button mBtnOk = (Button) dialogView.findViewById(R.id.btn_ok_dialog_ovulation);

        if(mEdtCount.getText().toString().equals("")){
            mEdtCount.setText(14+"");
        }else {
            mEdtCount.setText(Utils.readFromFile(Utils.FILE_REPORT_SO_NGAY_GIAI_DOAN_HOANG_THE, this));
        }

        final AlertDialog alertStartDialog = dialogBuilder.create();
        alertStartDialog.show();

        mTvPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mEdtCount.setText(Integer.parseInt(mEdtCount.getText().toString()) + 1 +"");
            }
        });

        mTvMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mEdtCount.setText(Integer.parseInt(mEdtCount.getText().toString()) - 1 +"");
            }
        });

        mBtnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertStartDialog.cancel();
            }
        });

        mBtnOk.setOnClickListener(new View.OnClickListener() {
            SharedPreferences prefs = getPreferences(MODE_PRIVATE);
            @Override
            public void onClick(View v) {
                if(!mEdtCount.getText().toString().equals("")){
                    if(Integer.parseInt(mEdtCount.getText().toString()) < 13 || Integer.parseInt(mEdtCount.getText().toString()) >18){
                        Toast.makeText(SettingActivity.this, getString(R.string.we_base_the_luteal_phase), Toast.LENGTH_SHORT).show();
                    }else {
                        deleteFile(Utils.FILE_REPORT_SO_NGAY_GIAI_DOAN_HOANG_THE);
                        Utils.writeToFile(mEdtCount.getText().toString(), Utils.FILE_REPORT_SO_NGAY_GIAI_DOAN_HOANG_THE, getApplicationContext());
                        alertStartDialog.cancel();
                    }
                }else {
                    Toast.makeText(SettingActivity.this, getString(R.string.we_base_the_luteal_phase), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void cycleLength() {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_ovulation, null);
        dialogBuilder.setView(dialogView);

        final EditText mEdtCount = (EditText) dialogView.findViewById(R.id.edt_count_dialog_ovulation);
        TextView mTvPlus = (TextView) dialogView.findViewById(R.id.tv_plus_dialog_ovulation);
        TextView mTvMinus = (TextView) dialogView.findViewById(R.id.tv_minus_dialog_ovulation);
        Button mBtnCancel = (Button) dialogView.findViewById(R.id.btn_cancel_dialog_ovulation);
        Button mBtnOk = (Button) dialogView.findViewById(R.id.btn_ok_dialog_ovulation);

        mEdtCount.setText(Utils.readFromFile(Utils.FILE_CHU_KY_KINH_NGUYET, this));

        final AlertDialog alertStartDialog = dialogBuilder.create();
        alertStartDialog.show();

        mTvPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mEdtCount.setText(Integer.parseInt(mEdtCount.getText().toString()) + 1 +"");
            }
        });

        mTvMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mEdtCount.setText(Integer.parseInt(mEdtCount.getText().toString()) - 1 +"");
            }
        });

        mBtnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertStartDialog.cancel();
            }
        });

        mBtnOk.setOnClickListener(new View.OnClickListener() {
            SharedPreferences prefs = getPreferences(MODE_PRIVATE);
            @Override
            public void onClick(View v) {
                if(!mEdtCount.getText().toString().equals("")){
                    if(Integer.parseInt(mEdtCount.getText().toString()) < 23 || Integer.parseInt(mEdtCount.getText().toString()) >35){
                        Toast.makeText(SettingActivity.this, getString(R.string.you_should_typing_cycle), Toast.LENGTH_SHORT).show();
                    }else {
                        mTvCycleLength.setText(Integer.parseInt(mEdtCount.getText().toString()) +"");
                        Utils.writeToFile(mTvCycleLength.getText().toString(), Utils.FILE_CHU_KY_KINH_NGUYET, getApplicationContext());
                        alertStartDialog.cancel();
                    }
                }else {
                    Toast.makeText(SettingActivity.this, getString(R.string.you_should_typing_cycle), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void menstrualLeng() {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_menstrual_length, null);
        dialogBuilder.setView(dialogView);

        final EditText mEdtCount = (EditText) dialogView.findViewById(R.id.edt_count_dialog_menstrual_length);
        TextView mTvPlus = (TextView) dialogView.findViewById(R.id.tv_plus_dialog_menstrual_length);
        TextView mTvMinus = (TextView) dialogView.findViewById(R.id.tv_minus_dialog_menstrual_length);
        Button mBtnCancel = (Button) dialogView.findViewById(R.id.btn_cancel_dialog_menstrual_length);
        Button mBtnOk = (Button) dialogView.findViewById(R.id.btn_ok_dialog_menstrual_length);

        mEdtCount.setText(Utils.readFromFile(Utils.FILE_CHU_KY_HANH_KINH, this));

        final AlertDialog alertStartDialog = dialogBuilder.create();
        alertStartDialog.show();

        mTvPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mEdtCount.setText(Integer.parseInt(mEdtCount.getText().toString()) + 1 +"");
            }
        });

        mTvMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mEdtCount.setText(Integer.parseInt(mEdtCount.getText().toString()) - 1 +"");
            }
        });

        mBtnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertStartDialog.cancel();
            }
        });

        mBtnOk.setOnClickListener(new View.OnClickListener() {
            SharedPreferences prefs = getPreferences(MODE_PRIVATE);
            @Override
            public void onClick(View v) {
                if(!mEdtCount.getText().toString().equals("")){
                    if(Integer.parseInt(mEdtCount.getText().toString()) < 4 || Integer.parseInt(mEdtCount.getText().toString()) >7){
                        Toast.makeText(SettingActivity.this, getString(R.string.you_should_typing_menstrual), Toast.LENGTH_SHORT).show();
                    }else {
                        mTvMenstrualLength.setText(Integer.parseInt(mEdtCount.getText().toString()) +"");
                        Utils.writeToFile(mTvMenstrualLength.getText().toString(), Utils.FILE_CHU_KY_HANH_KINH, getApplicationContext());
                        alertStartDialog.cancel();
                    }
                }else {
                    Toast.makeText(SettingActivity.this, getString(R.string.you_should_typing_menstrual), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
