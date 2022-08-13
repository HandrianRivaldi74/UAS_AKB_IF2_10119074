package com.example.uas_akb_if2_10119074.database;
/**
 * Nama : Handrian Rivaldi
 * Kelas : IF2
 * NIM :10119074
 * Email : handrianr28@gmail.com
 * **/
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class SQLite extends SQLiteOpenHelper {
    private static final String DATABASE = "adventure.db";
    private static final String TABLE = "catatan";
    private static final String COL_1 = "id";
    private static final String COL_2 = "judul";
    private static final String COL_3 = "isi";
    private static final String COL_4 = "date";
    private static final String COL_5 = "month";
    private static final String COL_6 = "year";

    public SQLite(@Nullable Context context) {
        super(context, DATABASE, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE + " (" +
                COL_1 + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COL_2 + " TEXT, " +
                COL_3 + " TEXT, " +
                COL_4 + " TEXT, " +
                COL_5 + " TEXT, " +
                COL_6 + " TEXT" +
                ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE);
    }

    /*Fungsi untuk menambahkan data*/
    public boolean insertData(String judul, String isi, String date, String month, String year) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COL_2, judul);
        values.put(COL_3, isi);
        values.put(COL_4, date);
        values.put(COL_5, month);
        values.put(COL_6, year);

        long results = db.insert(TABLE, null, values);

        return results != -1;
    }

    /*Fungsi untuk mengubah data*/
    public boolean updateData(String id, String judul, String isi, String date, String month, String year) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1, id);
        contentValues.put(COL_2, judul);
        contentValues.put(COL_3, isi);
        contentValues.put(COL_4, date);
        contentValues.put(COL_5, month);
        contentValues.put(COL_6, year);

        long results = db.update(TABLE, contentValues, COL_1 + " = ? ", new String[]{id});

        return results != -1;
    }

    /*Fungsi untuk mengambil data yang ada*/
    public Cursor getAllData() {
        SQLiteDatabase db = this.getWritableDatabase();

        return  db.rawQuery("SELECT * FROM " + TABLE, null);
    }

    /*Fungsi untuk menghapus data yang dipilih*/
    public Integer deteleData(String id){
        SQLiteDatabase db = getWritableDatabase();
        return db.delete(TABLE, COL_1 + " = ? ", new String[]{id});
    }
}
