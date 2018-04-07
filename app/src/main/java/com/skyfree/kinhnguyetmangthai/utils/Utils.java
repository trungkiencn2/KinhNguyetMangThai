package com.skyfree.kinhnguyetmangthai.utils;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.widget.Toast;

import com.skyfree.kinhnguyetmangthai.R;
import com.skyfree.kinhnguyetmangthai.activity.PasswordActivity;
import com.skyfree.kinhnguyetmangthai.model.CalendarItem;
import com.skyfree.kinhnguyetmangthai.model.NoteObj;
import com.skyfree.kinhnguyetmangthai.model.RealmDrug;
import com.skyfree.kinhnguyetmangthai.model.RealmMood;
import com.skyfree.kinhnguyetmangthai.model.RealmSymptom;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Calendar;

import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.RealmResults;

import static java.lang.Math.PI;

/**
 * Created by KienBeu on 3/9/2018.
 */

public class Utils {
    public static final long mOneDay = 86400000L;
    public static boolean mCurrentMonth = false;
    public static int mPositionToDay;
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
    public static final String FILE_MONTH = "FILE_MONTH";
    public static final String FILE_YEAR = "FILE_YEAR";

    public static final String TRUE = "TRUE";
    public static final String FALSE = "FALSE";
    public static final String DANG_MANG_THAI = "DANG_MANG_THAI";
    public static String CALENDAR_TO_NOTE = FALSE;

    public static final int REQUEST_NOTE = 1;
    public static final int REQUEST_DRUG = 2;
    public static final int REQUEST_SYMPTOM = 3;
    public static final int REQUEST_MOOD = 4;

    public static final String BACK_NOTE = "BACK_NOTE";
    public static final String BACK_DRUG = "BACK_DRUG";
    public static final String BACK_SYMPTOM = "BACK_SYMPTOM";
    public static final String BACK_MOOD = "BACK_MOOD";

    public static final String PUT_ID = "PUT_ID";
    public static final String PUT_DAY = "PUT_DAY";
    public static final String PUT_MONTH = "PUT_MONTH";
    public static final String PUT_YEAR = "PUT_YEAR";
    public static final String PUT_TIME_MILI = "PUT_TIME_MILI";

    public static String STATE = "";
    public static final String BACK_TO_RESULT = "BACK_TO_RESULT";

    public static int getmPositionToDay() {
        return mPositionToDay;
    }

    public static void setmPositionToDay(int mPositionToDay) {
        Utils.mPositionToDay = mPositionToDay;
    }

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

    public static void sendPassToMail(Context mContext, String info) {
        Intent i = new Intent(Intent.ACTION_SEND);
        i.setType("message/rfc822");
        i.putExtra(Intent.EXTRA_EMAIL, new String[]{"someone@gmail.com"});
        i.putExtra(Intent.EXTRA_SUBJECT, mContext.getString(R.string.subject_of_email));
        i.putExtra(Intent.EXTRA_TEXT, info);
        try {
            mContext.startActivity(Intent.createChooser(i, mContext.getString(R.string.send)));
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(mContext, "There are no email clients installed.", Toast.LENGTH_SHORT).show();
        }
    }

    public static void sendByGmail(Context mContext, String info, String mGmail) {
        Intent gmail = new Intent(Intent.ACTION_SEND);
        gmail.setPackage("com.google.android.gm");
        gmail.putExtra(Intent.EXTRA_EMAIL, new String[]{mGmail});
        gmail.setData(Uri.parse(mGmail));
        gmail.putExtra(Intent.EXTRA_SUBJECT, mContext.getString(R.string.subject_of_email));
        gmail.setType("plain/text");
        gmail.putExtra(Intent.EXTRA_TEXT, mContext.getString(R.string.hi_android_developer));
        mContext.startActivity(gmail);
    }

    public static int getDayLeftToDateEasyToConceive(int cycleLength) {
        switch (cycleLength) {
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

    public static int getNumberDataNull(String thuMayLaMung1) {
        int number = 0;
        switch (thuMayLaMung1) {
            case "T2":
                number = 1;
                break;
            case "T3":
                number = 2;
                break;
            case "T4":
                number = 3;
                break;
            case "T5":
                number = 4;
                break;
            case "T6":
                number = 5;
                break;
            case "T7":
                number = 6;
                break;
            case "CN":
                number = 0;
                break;
        }
        return number;
    }

    public static String getThuMayLaMung1(int thisMonth, int thisYear) {
        String thuMayLaMung1 = "";
        Calendar c = Calendar.getInstance();
        c.set(thisYear, thisMonth, 1);
        int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);
        if (Calendar.MONDAY == dayOfWeek) {
            thuMayLaMung1 = "T2";
        } else if (Calendar.TUESDAY == dayOfWeek) {
            thuMayLaMung1 = "T3";
        } else if (Calendar.WEDNESDAY == dayOfWeek) {
            thuMayLaMung1 = "T4";
        } else if (Calendar.THURSDAY == dayOfWeek) {
            thuMayLaMung1 = "T5";
        } else if (Calendar.FRIDAY == dayOfWeek) {
            thuMayLaMung1 = "T6";
        } else if (Calendar.SATURDAY == dayOfWeek) {
            thuMayLaMung1 = "T7";
        } else if (Calendar.SUNDAY == dayOfWeek) {
            thuMayLaMung1 = "CN";
        }
        return thuMayLaMung1;
    }

    public static int getSoNgayTrong1Thang(int thisMonth, int thisYear) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, thisYear);
        c.set(Calendar.MONTH, thisMonth);
        int maxDay = c.getActualMaximum(Calendar.DAY_OF_MONTH);
        return maxDay;
    }

    public static ArrayList<CalendarItem> createListCaItem(int numberOfDataNull, int maxDayInMonth, int month, int year) {
        ArrayList<CalendarItem> mListItem = new ArrayList<>();
        for (int i = 0; i < numberOfDataNull; i++) {
            mListItem.add(new CalendarItem(null, null, "", "", ""));
        }
        for (int i = 1; i <= maxDayInMonth; i++) {
            mListItem.add(new CalendarItem(null, null, i + "", month + "", year + ""));
        }
        return mListItem;
    }

    public static boolean checkStringExist(ArrayList<String> mList, String str) {
        if (mList.size() > 0) {
            for (int i = 0; i < mList.size(); i++) {
                if (mList.get(i).equals(str)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean checkNoteObjExistById(Realm realm, String id) {
        RealmResults<NoteObj> mListNoteObj = realm.where(NoteObj.class).findAll();
        for (int i = 0; i < mListNoteObj.size(); i++) {
            if (id.equals(mListNoteObj.get(i).getId())) {
                return true;
            }
        }
        return false;
    }

    public static void insertNoteObj(Realm realm, final NoteObj noteObj) {
        if(!checkNoteObjExistById(realm, noteObj.getId())){
            realm.executeTransaction(new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {
                    realm.insert(noteObj);
                }
            });
        }
    }

    public static void updateListDrug(Realm realm, String id, ArrayList<String> listDrug){
        realm.beginTransaction();
        NoteObj mNote = realm.where(NoteObj.class).equalTo("id", id).findFirst();
        RealmList<RealmDrug> mRealmListDrug = mNote.getmListDrug();
        mRealmListDrug.deleteAllFromRealm();
        for(int i = 0; i<listDrug.size(); i++){
            mRealmListDrug.add(new RealmDrug(listDrug.get(i)));
        }
        mNote.setmListDrug(mRealmListDrug);
        realm.copyToRealmOrUpdate(mNote);
        realm.commitTransaction();
    }

    public static void updateListSymptom(Realm realm, String id, ArrayList<String> listSymptom){
        realm.beginTransaction();
        NoteObj mNote = realm.where(NoteObj.class).equalTo("id", id).findFirst();
        RealmList<RealmSymptom> mRealmListSymptom = mNote.getmListSymptom();
        mRealmListSymptom.deleteAllFromRealm();
        for(int i = 0; i<listSymptom.size(); i++){
            mRealmListSymptom.add(new RealmSymptom(listSymptom.get(i)));
        }
        mNote.setmListSymptom(mRealmListSymptom);
        realm.copyToRealmOrUpdate(mNote);
        realm.commitTransaction();
    }

    public static void updateListMood(Realm realm, String id, ArrayList<String> listMood){
        realm.beginTransaction();
        NoteObj mNote = realm.where(NoteObj.class).equalTo("id", id).findFirst();
        RealmList<RealmMood> mRealmListMood = mNote.getmListMood();
        mRealmListMood.deleteAllFromRealm();
        for(int i = 0; i<listMood.size(); i++){
            mRealmListMood.add(new RealmMood(listMood.get(i)));
        }
        mNote.setmListMood(mRealmListMood);
        realm.copyToRealmOrUpdate(mNote);
        realm.commitTransaction();
    }

    public static void updateNoteOfNote(Realm realm, String id, String newNote){
        realm.beginTransaction();
        NoteObj mNote = realm.where(NoteObj.class).equalTo("id", id).findFirst();
        mNote.setmNoteNote(newNote);
        realm.copyToRealm(mNote);
        realm.commitTransaction();
    }

    public static void updateWeight(Realm realm, String id, float weight){
        realm.beginTransaction();;
        NoteObj mNote = realm.where(NoteObj.class).equalTo("id", id).findFirst();
        mNote.setmNoteWeight(weight);
        realm.copyToRealm(mNote);
        realm.commitTransaction();
    }

    public static void updateTemperature(Realm realm, String id, float temperature){
        realm.beginTransaction();;
        NoteObj mNote = realm.where(NoteObj.class).equalTo("id", id).findFirst();
        mNote.setmNoteTemperature(temperature);
        realm.copyToRealm(mNote);
        realm.commitTransaction();
    }

    public static void updateLuongKinh(Realm realm, String id, int luongKinh){
        realm.beginTransaction();
        NoteObj mNote = realm.where(NoteObj.class).equalTo("id", id).findFirst();
        mNote.setmNoteLuongKinh(luongKinh);
        realm.copyToRealm(mNote);
        realm.commitTransaction();
    }

    public static void updateNoteObj(final Realm mRealm, final String id, final NoteObj newNote) {
        mRealm.beginTransaction();
        NoteObj mManageNoteObjCheck = mRealm.copyToRealmOrUpdate(newNote);
        NoteObj db = mRealm.where(NoteObj.class).equalTo("id", id).findFirst();
        db.setmNoteLuongKinh(mManageNoteObjCheck.getmNoteLuongKinh());
        db.setmNoteNote(mManageNoteObjCheck.getmNoteNote());
        db.setmListDrug(mManageNoteObjCheck.getmListDrug());
        db.setmListSymptom(mManageNoteObjCheck.getmListSymptom());
        db.setmListMood(mManageNoteObjCheck.getmListMood());
        db.setmNoteWeight(mManageNoteObjCheck.getmNoteWeight());
        db.setmNoteTemperature(mManageNoteObjCheck.getmNoteTemperature());
        mRealm.copyToRealmOrUpdate(db);
        mRealm.commitTransaction();
    }

    public static void deleteNoteObj(Realm realm, final String id) {
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                RealmResults<NoteObj> rows = realm.where(NoteObj.class).equalTo("id", id).findAll();
                rows.deleteAllFromRealm();

            }
        });
    }

    public static void deleteAllNoteObj(Realm realm) {
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                RealmResults<NoteObj> rows = realm.where(NoteObj.class).findAll();
                rows.deleteAllFromRealm();
            }
        });
    }

    public static NoteObj getNoteObj(Realm realm, String id) {
        NoteObj noteObj = realm.where(NoteObj.class).equalTo("id", id).findFirst();
        return noteObj;
    }

    public static NoteObj getNoteObjByWeight(Realm realm, float weight){
        NoteObj noteObj = realm.where(NoteObj.class).equalTo("mNoteWeight", weight).findFirst();
        return noteObj;
    }

    public static NoteObj getNoteObjByTemperature(Realm realm, float temperature){
        NoteObj noteObj = realm.where(NoteObj.class).equalTo("mNoteTemperature", temperature).findFirst();
        return noteObj;
    }

    public static RealmResults<NoteObj> getAllNoteObj(Realm realm) {
        return realm.where(NoteObj.class).findAll();
    }
}
