package com.skyfree.kinhnguyetmangthai.activity;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;

import com.skyfree.kinhnguyetmangthai.R;
import com.skyfree.kinhnguyetmangthai.base.BaseDatePicker;
import com.skyfree.kinhnguyetmangthai.utils.Utils;

public class MaternityActivity extends AppCompatActivity {

    private Switch mSwMaternity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maternity);
        initView();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(Utils.readFromFile(Utils.FILE_NEW_USER, this).equals(Utils.DANG_MANG_THAI)){
            startActivity(new Intent(this, OptionPregnantActivity.class));
            finish();
        }
    }

    private void showAlert() {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_maternity, null);
        dialogBuilder.setView(dialogView);

        Button mBtnCancel = (Button) dialogView.findViewById(R.id.btn_cancel_dialog_maternity);
        Button mBtnOk = (Button) dialogView.findViewById(R.id.btn_ok_dialog_maternity);

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
                alertStartDialog.cancel();
                Utils.writeToFile(Long.parseLong(Utils.readFromFile(Utils.FILE_NGAY_BAT_DAU_CHU_KY_KINH_NGUYET, getApplicationContext())) + 270 * Utils.mOneDay+"", Utils.FILE_DATE_ESTIMATE, getApplicationContext());
                startActivity(new Intent(getApplicationContext(), OptionPregnantActivity.class));
                finish();
            }
        });
    }

    private void initView() {
        mSwMaternity = (Switch) findViewById(R.id.sw_maternity);
        mSwMaternity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mSwMaternity.isChecked()){
                    Utils.writeToFile(Utils.DANG_MANG_THAI, Utils.FILE_NEW_USER, getApplicationContext());
                    showAlert();
                }
            }
        });
    }
}
