package com.skyfree.kinhnguyetmangthai.activity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.skyfree.kinhnguyetmangthai.R;
import com.skyfree.kinhnguyetmangthai.adapter.ListSettingAdapter;
import com.skyfree.kinhnguyetmangthai.model.Setting;
import com.skyfree.kinhnguyetmangthai.utils.Utils;

import java.util.ArrayList;

import io.realm.Realm;

public class SettingActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener {

    Realm realm;

    private ImageView mImgBack;
    private ListView mLvSetting;
    private ListSettingAdapter mAdapter;
    private ArrayList<Setting> mListSetting;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        realm = Realm.getDefaultInstance();
        Utils.writeToFile(14 + "", Utils.FILE_REPORT_SO_NGAY_GIAI_DOAN_HOANG_THE, this);
        realm = Realm.getDefaultInstance();
        initView();
        addEvent();
    }

    private void initView() {
        mImgBack = (ImageView) findViewById(R.id.img_back_setting_activity);
        mImgBack.setOnClickListener(this);
        mLvSetting = (ListView) findViewById(R.id.lv_setting);
        mLvSetting.setOnItemClickListener(this);
    }

    private void addEvent(){
        mListSetting = new ArrayList<>();
        mListSetting.add(new Setting(0, 0, getString(R.string.menstruation), ""));
        mListSetting.add(new Setting(R.drawable.icon_setting_reminders, 1, getString(R.string.length_periods), Utils.readFromFile(Utils.FILE_CHU_KY_HANH_KINH, this)));
        mListSetting.add(new Setting(R.drawable.icon_setting_period, 1, getString(R.string.length_cycle), Utils.readFromFile(Utils.FILE_CHU_KY_KINH_NGUYET, this)));
        mListSetting.add(new Setting( R.drawable.icon_setting_ovulation, 1, getString(R.string.ovulation), ""));
        mListSetting.add(new Setting( R.drawable.icon_setting_pregnancy, 1, getString(R.string.maternity), ""));

        mListSetting.add(new Setting(0, 0, getString(R.string.remind), ""));
        mListSetting.add(new Setting( R.drawable.icon_setting_pill, 1, getString(R.string.remind), ""));

        mListSetting.add(new Setting(0, 0, getString(R.string.data_and_account), ""));
        mListSetting.add(new Setting( R.drawable.icon_setting_password,1, getString(R.string.password), ""));

        mListSetting.add(new Setting(0, 0, getString(R.string.support_us), ""));
        mListSetting.add(new Setting( R.drawable.icon_setting_forum,1, getString(R.string.send_message_to_us), ""));
        mListSetting.add(new Setting( R.drawable.icon_setting_rate,1, getString(R.string.rate_us), ""));
        mListSetting.add(new Setting( R.drawable.icon_setting_share,1, getString(R.string.share), ""));
        mListSetting.add(new Setting( R.drawable.icon_setting_reset,1, getString(R.string.delete_all_data), ""));

        mAdapter = new ListSettingAdapter(this, 5, mListSetting);
        mLvSetting.setAdapter(mAdapter);

    }

    @Override
    protected void onResume() {
        super.onResume();
        if(Utils.readFromFile(Utils.FILE_NEW_USER, this).equals(Utils.DANG_MANG_THAI)){
            mListSetting.get(1).setmCountDay("");
            mListSetting.get(2).setmCountDay("");
            mAdapter.notifyDataSetChanged();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        realm.close();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.img_back_setting_activity:
                finish();
                break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (position){
            case 1:
                menstrualLeng();
                break;
            case 2:
                cycleLength();
                break;
            case 3:
                ovulation();
                break;
            case 4:
                maternity();
                break;
            case 6:
                remind();
                break;
            case 8:
                password();
                break;
            case 10:
                messageUs();
                break;
            case 11:
                rate();
                break;
            case 12:
                share();
                break;
            case 13:
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
                Utils.deleteAllNoteObj(realm);

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
                        mListSetting.get(2).setmCountDay((Integer.parseInt(mEdtCount.getText().toString()) +""));
                        mAdapter.notifyDataSetChanged();
                        Utils.writeToFile(mListSetting.get(2).getmCountDay(), Utils.FILE_CHU_KY_KINH_NGUYET, getApplicationContext());
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
            @Override
            public void onClick(View v) {
                if(!mEdtCount.getText().toString().equals("")){
                    if(Integer.parseInt(mEdtCount.getText().toString()) < 3 || Integer.parseInt(mEdtCount.getText().toString()) >15){
                        Toast.makeText(SettingActivity.this, getString(R.string.you_should_typing_menstrual), Toast.LENGTH_SHORT).show();
                    }else {
                        mListSetting.get(1).setmCountDay((Integer.parseInt(mEdtCount.getText().toString()) +""));
                        mAdapter.notifyDataSetChanged();
                        Utils.writeToFile(mListSetting.get(1).getmCountDay(), Utils.FILE_CHU_KY_HANH_KINH, getApplicationContext());
                        alertStartDialog.cancel();
                    }
                }else {
                    Toast.makeText(SettingActivity.this, getString(R.string.you_should_typing_menstrual), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
