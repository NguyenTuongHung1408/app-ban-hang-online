package com.example.appbanhangonline.database;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.appbanhangonline.activity.User;
import com.example.appbanhangonline.model.Account;
import com.example.appbanhangonline.ultil.ErrorCallback;
import com.example.appbanhangonline.ultil.SuccessCallback;

import java.security.MessageDigest;

public class KhachHangRepository {
    private final SQLiteDatabase databaseConnector;


    public KhachHangRepository(SQLiteDatabase databaseConnector) {
        this.databaseConnector = databaseConnector;
    }

    public void login(String email, String password, SuccessCallback<Account> successCallback, ErrorCallback errorCallback) {
        try (Cursor cursor = databaseConnector.rawQuery("SELECT ten_dang_nhap as username, email FROM khach_hang WHERE email = ? AND mat_khau = ?",
                new String[]{email, password})) {
            if (cursor != null && cursor.moveToFirst()) {
                String username = cursor.getString(1);
                successCallback.run(new Account(username, email));
            }

            errorCallback.run(new Exception("Tài khoản hoặc mật khẩu chưa chính xác"));
        }
    }

    public void register(User user, SuccessCallback<Object> successCallback, ErrorCallback errorCallback) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("ten_dang_nhap", user.getUsername());
        contentValues.put("email", user.getEmail());
        contentValues.put("mat_khau", user.getPassword());
        long newId = databaseConnector.insert("khach_hang", null, contentValues);
        if (newId == -1) {
            errorCallback.run(new Exception());
        } else {
            successCallback.run(null);
        }
    }
}
