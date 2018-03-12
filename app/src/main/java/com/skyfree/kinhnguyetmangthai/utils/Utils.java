package com.skyfree.kinhnguyetmangthai.utils;

import android.content.Context;
import android.content.Intent;
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
    public static final String LENGTH_OF_CYCLE = "LENGTH_OF_CYCLE";
    public static final String LENGTH_OF_MENSTRUAL_CYCLE = "LENGTH_OF_MENSTRUAL_CYCLE";
    public static final String DATE_ORDER = "DATE_ORDER";
    public static final String FILE_MENSTRUAL_LENGTH = "FILE_MENSTRUAL_LENGTH";
    public static final String FILE_CYCLE_LENGTH = "FILE_CYCLE_LENGTH";
    public static final String FILE_DATE_TIME = "FILE_DATE_TIME";
    public static final String FILE_OVULATION = "FILE_OVULATION";
    public static final String FILE_NEW_USER = "FILE_NEW_USER";
    public static final String OLD_USER = "OLD_USER";
    public static final String TRUE = "TRUE";
    public static final String FALSE = "FALSE";


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

    public static void sendByGmail(Context mContext, String info){
        Intent emailIntent = new Intent(android.content.Intent.ACTION_SEND);
        emailIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        emailIntent.setType("plain/text");
        emailIntent.setClassName("com.google.android.gm", "com.google.android.gm.ComposeActivityGmail");
        emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL, new String[]{"someone@gmail.com"});
        emailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, mContext.getString(R.string.subject_of_email));
        emailIntent.putExtra(android.content.Intent.EXTRA_TEXT, info);
        mContext.startActivity(emailIntent);
    }
}
