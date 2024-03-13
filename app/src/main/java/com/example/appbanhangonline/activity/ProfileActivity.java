package com.example.appbanhangonline.activity;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.appbanhangonline.R;


public class ProfileActivity extends AppCompatActivity {
    UserLocalStore userLocalStore;
    TextView txtNameUser,txtNameUser2,txtEmailUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        userLocalStore = new UserLocalStore(this);

        addControls();
        addEvents();
    }

    private void addEvents() {
        User user = userLocalStore.getLoggedInUser();
        txtNameUser.setText(user.username);
        txtNameUser2.setText(user.username);
        txtEmailUser.setText(user.email);
    }

    private void addControls() {

        txtNameUser = findViewById(R.id.txtNameUser);
        txtNameUser2 = findViewById(R.id.txtNameUser2);
    }

    @Override
    protected void onStart() {
        super.onStart();

    }
}
