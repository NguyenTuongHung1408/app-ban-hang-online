package com.example.appbanhangonline;

import android.app.Application;
import android.database.sqlite.SQLiteDatabase;

import com.example.appbanhangonline.database.DatabaseConnector;

public class App extends Application {
    private static SQLiteDatabase db;

    public static SQLiteDatabase getDb() {
        return db;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        db = new DatabaseConnector(getApplicationContext()).getWritableDatabase();
    }
}
