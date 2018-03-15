package com.skyfree.kinhnguyetmangthai.utils;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.widget.Toast;

import com.skyfree.kinhnguyetmangthai.R;
import com.skyfree.kinhnguyetmangthai.activity.PasswordActivity;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 * Created by KienBeu on 3/9/2018.
 */

public class Utils {
    public static final long mOneDay = 86400000L;
    public static final String LENGTH_OF_CYCLE = "LENGTH_OF_CYCLE";
    public static final String LENGTH_OF_MENSTRUAL_CYCLE = "LENGTH_OF_MENSTRUAL_CYCLE";
    public static final String DATE_ORDER = "DATE_ORDER";
    public static final String FILE_CHU_KY_HANH_KINH = "FILE_CHU_KY_HANH_KINH";
    public static final String FILE_CHU_KY_KINH_NGUYET = "FILE_CHU_KY_KINH_NGUYET";
    public static final String FILE_NGAY_BAT_DAU_CHU_KY_KINH_NGUYET = "FILE_NGAY_BAT_DAU_CHU_KY_KINH_NGUYET";
    public static final String FILE_REPORT_SO_NGAY_GIAI_DOAN_HOANG_THE = "FILE_REPORT_SO_NGAY_GIAI_DOAN_HOANG_THE";
    public static final String FILE_OVULATION = "FILE_OVULATION";
    public static final String FILE_NEW_USER = "FILE_NEW_USER";
    public static final String FILE_REPORT_CYCLE = "FILE_REPORT_CYCLE";
    public static final String FILE_REPORT_EASY_TO_CONCEIVE = "FILE_REPORT_EASY_TO_CONCEIVE";
    public static final String FILE_DATE_ESTIMATE = "FILE_DATE_ESTIMATE";
    public static final String TRUE = "TRUE";
    public static final String FALSE = "FALSE";
    public static final String DANG_MANG_THAI= "DANG_MANG_THAI";

    public static void writeToFile(String data, String fileName, Context context) {
        try {
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(context.openFileOutput(fileName, Context.MODE_PRIVATE));
            outputStreamWriter.write(data);
            outputStreamWriter.close();
        } catch (IOException e) {
            Log.e("Exception", "File write failed: " + e.toString());
        }
    }

    public static String readFromFile(String fileName, Context context) {

        String ret = "";

        try {
            InputStream inputStream = context.openFileInput(fileName);

            if (inputStream != null) {
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String receiveString = "";
                StringBuilder stringBuilder = new StringBuilder();

                while ((receiveString = bufferedReader.readLine()) != null) {
                    stringBuilder.append(receiveString);
                }

                inputStream.close();
                ret = stringBuilder.toString();
            }
        } catch (FileNotFoundException e) {
            Log.e("login activity", "File not found: " + e.toString());
        } catch (IOException e) {
            Log.e("login activity", "Can not read file: " + e.toString());
        }

        return ret;
    }

    public static void sendPassToMail(Context mContext, String info){
        Intent i = new Intent(Intent.ACTION_SEND);
        i.setType("message/rfc822");
        i.putExtra(Intent.EXTRA_EMAIL  , new String[]{"someone@gmail.com"});
        i.putExtra(Intent.EXTRA_SUBJECT, mContext.getString(R.string.subject_of_email));
        i.putExtra(Intent.EXTRA_TEXT   , info);
        try {
            mContext.startActivity(Intent.createChooser(i, mContext.getString(R.string.send)));
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(mContext, "There are no email clients installed.", Toast.LENGTH_SHORT).show();
        }
    }

    public static void sendByGmail(Context mContext, String info, String mGmail){
        Intent gmail = new Intent(Intent.ACTION_SEND);
        gmail.setPackage("com.google.android.gm");
        gmail.putExtra(Intent.EXTRA_EMAIL, new String[] {mGmail});
        gmail.setData(Uri.parse(mGmail));
        gmail.putExtra(Intent.EXTRA_SUBJECT, mContext.getString(R.string.subject_of_email));
        gmail.setType("plain/text");
        gmail.putExtra(Intent.EXTRA_TEXT, mContext.getString(R.string.hi_android_developer));
        mContext.startActivity(gmail);
    }

    public static int getDayLeftToDateEasyToConceive(int cycleLength){
        switch (cycleLength){
            case 23:
                return 4;
            case 24:
                return 5;
            case 25:
                return 6;
            case 26:
                return 7;
            case 27:
                return 8;
            case 28:
                return 9;
            case 29:
                return 10;
            case 30:
                return 11;
            case 31:
                return 12;
            case 32:
                return 13;
            case 33:
                return 14;
            case 34:
                return 15;
            case 35:
                return 16;
            default:
                return 10;
        }
    }
}
