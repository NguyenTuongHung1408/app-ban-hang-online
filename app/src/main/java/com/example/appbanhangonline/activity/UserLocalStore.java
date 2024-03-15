package com.example.appbanhangonline.activity;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.appbanhangonline.model.Account;

public class UserLocalStore {

    public static final String SP_NAME = "userDetails";

    SharedPreferences userLocalDatabase;

    public UserLocalStore(Context context) {
        userLocalDatabase = context.getSharedPreferences(SP_NAME, 0);
    }

    public void storeUserData(Account account) {
        SharedPreferences.Editor userLocalDatabaseEditor = userLocalDatabase.edit();
        userLocalDatabaseEditor.putString("email", account.getEmail());
        userLocalDatabaseEditor.putString("username", account.getUserName());
        userLocalDatabaseEditor.commit();
    }

    public void setUserLoggedIn(boolean loggedIn) {
        SharedPreferences.Editor userLocalDatabaseEditor = userLocalDatabase.edit();
        userLocalDatabaseEditor.putBoolean("loggedIn", loggedIn);
        userLocalDatabaseEditor.commit();
    }

    public void clearUserData() {
        SharedPreferences.Editor userLocalDatabaseEditor = userLocalDatabase.edit();
        userLocalDatabaseEditor.clear();
        userLocalDatabaseEditor.commit();
    }

    public User getLoggedInUser() {
        if (!userLocalDatabase.getBoolean("loggedIn", false)) {
            return null;
        }

        String email = userLocalDatabase.getString("email", "");
        String username = userLocalDatabase.getString("username", "");
        String password = userLocalDatabase.getString("password", "");

        User user = new User(email, username, password);
        return user;
    }
}
