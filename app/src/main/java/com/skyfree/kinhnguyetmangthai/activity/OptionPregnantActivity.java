package com.skyfree.kinhnguyetmangthai.activity;

import android.icu.util.Calendar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.skyfree.kinhnguyetmangthai.R;
import com.skyfree.kinhnguyetmangthai.base.BaseDatePicker;
import com.skyfree.kinhnguyetmangthai.custom_interface.MyDateSetListener;
import com.skyfree.kinhnguyetmangthai.utils.Utils;

public class OptionPregnantActivity extends BaseDatePicker implements View.OnClickListener {

    private LinearLayout mLinearEstimate, mLinearEnable;
    private TextView mTvEstimate;

    private Calendar mCaEstimate = Calendar.getInstance();
    private Calendar mCaEndPregnant = Calendar.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_option_pregnant);
        initView();
        addEvent();
    }

    private void addEvent() {
        mCaEstimate.setTimeInMillis(Long.parseLong(Utils.readFromFile(Utils.FILE_NGAY_BAT_DAU_CHU_KY_KINH_NGUYET, this)) + 270 * Utils.mOneDay);
        mTvEstimate.setText(mCaEstimate.get(Calendar.DAY_OF_MONTH) + " - " + (mCaEstimate.get(Calendar.MONDAY) + 1) + " - " + mCaEstimate.get(Calendar.YEAR));
    }

    private void initView() {
        mLinearEstimate = (LinearLayout) findViewById(R.id.linear_estimate_the_due_date);
        mLinearEnable = (LinearLayout) findViewById(R.id.linear_enable_by_mistake);
        mTvEstimate = (TextView) findViewById(R.id.tv_estimate_the_due_date);

        mLinearEstimate.setOnClickListener(this);
        mLinearEnable.setOnClickListener(this);

    }

    private void alertShowDateEstimate(final TextView mTvDay) {
        dialogForCalendar(new MyDateSetListener() {
            @Override
            public void onDateSet(java.util.Calendar currentSelectedDate) {
                int dayOfMonth = currentSelectedDate.get(java.util.Calendar.DAY_OF_MONTH);
                int month = currentSelectedDate.get(java.util.Calendar.MONTH) + 1;
                int year = currentSelectedDate.get(java.util.Calendar.YEAR);
                mTvDay.setText(dayOfMonth + " - " + (month) + " - " + year);
                mCaEstimate.setTimeInMillis(currentSelectedDate.getTimeInMillis());
                Utils.writeToFile(mCaEstimate.getTimeInMillis()+"", Utils.FILE_DATE_ESTIMATE, getApplicationContext());
            }
        }, mCaEstimate.getTimeInMillis() + 20 * Utils.mOneDay, getString(R.string.estimate_the_due_date), mCaEstimate.getTimeInMillis());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.linear_estimate_the_due_date:
                alertShowDateEstimate(mTvEstimate);
                break;
//            case R.id.linear_childbirth_or_miscarriage:
//                alertShowChildbirthOrMiscarrige();
//                break;
            case R.id.linear_enable_by_mistake:
                alertShowEnableByMistake();
                break;
        }
    }

    private void alertShowEnableByMistake() {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_enable_by_mistake, null);
        dialogBuilder.setView(dialogView);

        Button mBtnCancel = (Button) dialogView.findViewById(R.id.btn_cancel_enable_by_mistake);
        Button mBtnTurnOff = (Button) dialogView.findViewById(R.id.btn_turn_off_enable_by_mistake);

        final AlertDialog alertStartDialog = dialogBuilder.create();
        alertStartDialog.show();

        mBtnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertStartDialog.cancel();
            }
        });

        mBtnTurnOff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Utils.writeToFile(Utils.FALSE, Utils.FILE_NEW_USER, getApplicationContext());
                alertStartDialog.cancel();
            }
        });
    }

//    private void alertShowChildbirthOrMiscarrige() {
//        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
//        LayoutInflater inflater = this.getLayoutInflater();
//        View dialogView = inflater.inflate(R.layout.dialog_childbirth_or_miscarriage, null);
//        dialogBuilder.setView(dialogView);
//
//        Button mBtnCancel = (Button) dialogView.findViewById(R.id.btn_cancel_dialog_childbirth_or_miscarriage);
//        Button mBtnTurnOff = (Button) dialogView.findViewById(R.id.btn_no_longer_dialog_childbirth_or_miscarriage);
//
//        final AlertDialog alertStartDialog = dialogBuilder.create();
//        alertStartDialog.show();
//
//        mBtnCancel.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                alertStartDialog.cancel();
//            }
//        });
//
//        mBtnTurnOff.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                showDialogNoLongerPregnant();
//                alertStartDialog.cancel();
//            }
//        });
//    }

//    private void showDialogNoLongerPregnant() {
//        dialogForCalendar(new MyDateSetListener() {
//            @Override
//            public void onDateSet(java.util.Calendar currentSelectedDate) {
//                int dayOfMonth = currentSelectedDate.get(java.util.Calendar.DAY_OF_MONTH);
//                int month = currentSelectedDate.get(java.util.Calendar.MONTH) + 1;
//                int year = currentSelectedDate.get(java.util.Calendar.YEAR);
//                mCaEndPregnant.setTimeInMillis(currentSelectedDate.getTimeInMillis());
//            }
//        }, mCaEndPregnant.getTimeInMillis() + 10 * Utils.mOneDay, getString(R.string.end_date_of_pregnancy), );
//    }
}
