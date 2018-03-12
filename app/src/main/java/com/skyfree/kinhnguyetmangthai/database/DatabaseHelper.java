package com.skyfree.kinhnguyetmangthai.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.skyfree.kinhnguyetmangthai.model.Acount;

import java.util.ArrayList;

/**
 * Created by KienBeu on 3/10/2018.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    private ArrayList<Acount> mListAcount;

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "KINH_NGUYET_MANG_THAI";
    private static final String TABLE_ACOUNT = "KINH_NGUYET";
    private static final String KEY_PASS = "KEY_PASS";
    private static final String KEY_CONFIRM_PASS = "KEY_CONFIRM_PASS";
    private static final String KEY_QUESTION = "KEY_QUESTION";
    private static final String KEY_ANSWER = "KEY_ANSWER";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_ACOUNT_TABLE = "CREATE TABLE " + TABLE_ACOUNT + "("
                + KEY_PASS + " TEXT,"
                + KEY_CONFIRM_PASS + " TEXT,"
                + KEY_QUESTION + " TEXT,"
                + KEY_ANSWER + " TEXT" + ")";
        db.execSQL(CREATE_ACOUNT_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onCreate(db);
    }


    public void addAcount(Acount sa) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_PASS, sa.getmPass());
        values.put(KEY_CONFIRM_PASS, sa.getmConfirmPass());
        values.put(KEY_QUESTION, sa.getmQuestion());
        values.put(KEY_ANSWER, sa.getmAnswer());
        db.insert(TABLE_ACOUNT, null, values);
        db.close();
    }

    public void deleteAllAcount() {
        SQLiteDatabase db = this.getWritableDatabase();
        String sql = "delete from " + TABLE_ACOUNT;
        db.execSQL(sql);
        db.close();
    }

    public ArrayList<Acount> getListAcount(){
        mListAcount = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "select " + KEY_PASS + "," + KEY_CONFIRM_PASS + "," + KEY_QUESTION + "," + KEY_ANSWER + " from " + TABLE_ACOUNT;
        Cursor cursor = db.rawQuery(sql, null);
        while (cursor.moveToNext()) {
            mListAcount.add(new Acount(cursor.getString(0), cursor.getString(1), cursor.getString(2), cursor.getString(3)));
        }
        return mListAcount;
    }
}


