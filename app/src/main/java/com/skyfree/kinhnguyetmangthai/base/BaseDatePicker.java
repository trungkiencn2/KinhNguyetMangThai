package com.skyfree.kinhnguyetmangthai.base;

import android.app.DatePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.widget.DatePicker;
import android.widget.Toast;

import com.skyfree.kinhnguyetmangthai.R;
import com.skyfree.kinhnguyetmangthai.custom_interface.MyDateSetListener;

import java.util.Calendar;

/**
 * Created by KienBeu on 3/9/2018.
 */

public class BaseDatePicker extends AppCompatActivity{
    protected Calendar mCurrentSelectedDate;
    protected void dialogForCalendar(final MyDateSetListener listener, long maxTime, String title, long minTime) {

        if (mCurrentSelectedDate == null) {
            mCurrentSelectedDate = Calendar.getInstance();
            mCurrentSelectedDate.setTimeInMillis(System.currentTimeMillis());
        }

        DatePickerDialog mDatePicker = new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() {
                    public void onDateSet(DatePicker datepicker, int year, int selectedmonth, int dayOfMonth) {
                        int month = selectedmonth + 1;

                        mCurrentSelectedDate.setTimeInMillis(System.currentTimeMillis()); //refresh to now
                        mCurrentSelectedDate.set(Calendar.YEAR, year);
                        mCurrentSelectedDate.set(Calendar.MONTH, selectedmonth);
                        mCurrentSelectedDate.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                        listener.onDateSet(mCurrentSelectedDate);

                    }
                }, mCurrentSelectedDate.get(Calendar.YEAR), mCurrentSelectedDate.get(Calendar.MONTH), mCurrentSelectedDate.get(Calendar.DAY_OF_MONTH));
        mDatePicker.getDatePicker().setMaxDate(maxTime);
        mDatePicker.getDatePicker().setMinDate(minTime);
        mDatePicker.setTitle(title);
        mDatePicker.show();
    }

}
