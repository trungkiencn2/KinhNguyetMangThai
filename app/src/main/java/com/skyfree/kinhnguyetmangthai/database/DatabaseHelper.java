package com.skyfree.kinhnguyetmangthai.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.skyfree.kinhnguyetmangthai.model.Acount;
import com.skyfree.kinhnguyetmangthai.model.NoteObj;

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

    private static final String TABLE_NOTE = "TABLE_NOTE";
    private static final String KEY_DATE_NOTE = "KEY_DATA_NOTE";
    private static final String KEY_MONTH_NOTE = "KEY_MONTH_NOTE";
    private static final String KEY_YEAR_NOTE = "KEY_YEAR_NOTE";
    private static final String KEY_LUONGKINH_NOTE = "KEY_LUONGKINH_NOTE";
    private static final String KEY_GHICHU_NOTE = "KEY_GHICHU_NOTE";
    private static final String KEY_THUOC_NOTE = "KEY_THUOC_NOTE";
    private static final String KEY_TRIEUCHUNG_NOTE = "KEY_TRIEUCHUNG_NOTE";
    private static final String KEY_TAMTRANG_NOTE = "KEY_TAMTRANG_NOTE";
    private static final String KEY_CANNANG_NOTE = "KEY_CANNANG_NOTE";
    private static final String KEY_NHIETDO_NOTE = "KEY_NHIETDO_NOTE";

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

        String CREATE_NOTE_TABLE = "CREATE TABLE " + TABLE_NOTE + "("
                + KEY_DATE_NOTE + " INTEGER,"
                + KEY_MONTH_NOTE + " INTEGER,"
                + KEY_YEAR_NOTE + " INTEGER,"
                + KEY_LUONGKINH_NOTE + " TEXT,"
                + KEY_GHICHU_NOTE + " TEXT,"
                + KEY_THUOC_NOTE + " TEXT,"
                + KEY_TRIEUCHUNG_NOTE + " TEXT,"
                + KEY_TAMTRANG_NOTE + " TEXT,"
                + KEY_CANNANG_NOTE + " TEXT,"
                + KEY_NHIETDO_NOTE + " TEXT" + ")";

        db.execSQL(CREATE_ACOUNT_TABLE);
        db.execSQL(CREATE_NOTE_TABLE);
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

    public void addNote(NoteObj note){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
//        values.put(KEY_DATE_NOTE, note.getDate());
//        values.put(KEY_MONTH_NOTE, note.getMonth());
//        values.put(KEY_YEAR_NOTE, note.getYear());
//        values.put(KEY_LUONGKINH_NOTE, note.getLuongKinh());
//        values.put(KEY_GHICHU_NOTE, note.getGhiChu());
//        values.put(KEY_THUOC_NOTE, note.getThuoc());
//        values.put(KEY_TRIEUCHUNG_NOTE, note.getTrieuChung());
//        values.put(KEY_TAMTRANG_NOTE, note.getTamTrang());
//        values.put(KEY_CANNANG_NOTE, note.getCanNang());
//        values.put(KEY_NHIETDO_NOTE, note.getNhietDo());
        db.insert(TABLE_NOTE, null, values);
        db.close();
    }

    public void deleteAllAcount() {
        SQLiteDatabase db = this.getWritableDatabase();
        String sql = "delete from " + TABLE_ACOUNT;
        db.execSQL(sql);
        db.close();
    }

    public void deleteAllNote(){
        SQLiteDatabase db = this.getWritableDatabase();
        String sql = "delete from " + TABLE_NOTE;
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

    public ArrayList<NoteObj> getListNote(){
        ArrayList<NoteObj> mListNote = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "select " + KEY_DATE_NOTE + "," + KEY_MONTH_NOTE + "," + KEY_YEAR_NOTE
                + "," + KEY_LUONGKINH_NOTE
                + "," + KEY_GHICHU_NOTE + "," + KEY_THUOC_NOTE + "," + KEY_TRIEUCHUNG_NOTE
                + "," + KEY_TAMTRANG_NOTE + "," + KEY_CANNANG_NOTE
                + "," + KEY_NHIETDO_NOTE + " from " + TABLE_NOTE;
        Cursor cursor = db.rawQuery(sql, null);
//        while (cursor.moveToNext()) {
//            mListNote.add(new NoteObj(cursor.getInt(0), cursor.getInt(1), cursor.getInt(2), cursor.getString(3), cursor.getString(4),
//                    cursor.getString(5), cursor.getString(6), cursor.getString(7), cursor.getString(8), cursor.getString(9)));
//        }
        return mListNote;

    }
}


