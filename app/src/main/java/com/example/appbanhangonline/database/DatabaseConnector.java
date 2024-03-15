package com.example.appbanhangonline.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseConnector extends SQLiteOpenHelper {

    public DatabaseConnector(Context context) {
        super(context, "food_db", null, 4);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS khach_hang (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "ten_dang_nhap TEXT, " +
                "email TEXT, " +
                "mat_khau TEXT)");
        db.execSQL("CREATE TABLE IF NOT EXISTS san_pham (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "ten TEXT, " +
                "gia INTEGER, " +
                "hinh_anh TEXT, " +
                "mo_ta TEXT)");
        db.execSQL("CREATE TABLE IF NOT EXISTS loai_san_phan (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "ten TEXT, " +
                "hinh_anh TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS khach_hang");
        onCreate(db);
    }
}
