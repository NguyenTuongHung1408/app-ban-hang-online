package com.example.appbanhangonline.activity;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.appbanhangonline.App;
import com.example.appbanhangonline.R;
import com.example.appbanhangonline.database.KhachHangRepository;
import com.example.appbanhangonline.model.Account;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import com.example.appbanhangonline.ultil.Server;

public class RegisterActivity extends AppCompatActivity {

    public static final String TAG = RegisterActivity.class.getSimpleName();

    private EditText edtUserName;
    private EditText edtPassWord;
    private EditText edtEmail;
    private Button btnRegister;
    private Button btnLogin;
    private ProgressDialog pDialog;
    private KhachHangRepository khachHangRepository;

    public static final String REGISTER_URL = Server.signup;

    public static final String KEY_USERNAME = "username";
    public static final String KEY_PASSWORD = "password";
    public static final String KEY_EMAIL = "email";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        addControls();
        addEvents();
        khachHangRepository = new KhachHangRepository(App.getDb());
    }

    private void addEvents() {
        btnRegister.setOnClickListener(v -> {
            //Get data input
            String username = edtUserName.getText().toString().trim();
            String password = edtPassWord.getText().toString().trim();
            String email = edtEmail.getText().toString().trim();

            //Call method register
            registerUser(username, password, email);
        });
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }

    private void addControls() {

        edtUserName = findViewById(R.id.txtNameDK);
        edtPassWord = findViewById(R.id.txtPassDK);
        btnRegister = findViewById(R.id.btnDangkiDK);
        btnLogin = findViewById(R.id.btnDangnhapDK);
        edtEmail = findViewById(R.id.txtEmailDK);
        pDialog = new ProgressDialog(this);
        pDialog.setMessage("Đang đăng ký...");
        pDialog.setCanceledOnTouchOutside(false);
    }

    /**
     * Method register
     *
     * @param username
     * @param password
     * @param email    result json
     */
    private void registerUser(final String username, final String password, final String email) {

        if (checkEditText(edtUserName) && checkEditText(edtPassWord) && checkEditText(edtEmail) && isValidEmail(email)) {
            pDialog.show();
            User newUser = new User(username, email, password);
            khachHangRepository.register(newUser, ignored1 -> {
                pDialog.hide();
                showToast("Đăng ký thành công", Toast.LENGTH_SHORT);
                Intent intent = new Intent(this, LoginActivity.class);
                startActivity(intent);
            }, ignored2 -> {
                pDialog.hide();
            });
        }
    }

    private void showToast(String message, int time) {
        Toast.makeText(this, message, time).show();
    }

    /**
     * Check Input
     */
    private boolean checkEditText(EditText editText) {
        if (editText.getText().toString().trim().length() > 0)
            return true;
        else {
            editText.setError("Vui lòng nhập dữ liệu!");
        }
        return false;
    }

    /**
     * Check Email
     */
    private boolean isValidEmail(String target) {
        if (target.matches("[a-zA-Z0-9._-]+@[a-z]+.[a-z]+"))
            return true;
        else {
            edtEmail.setError("Email sai định dạng!");
        }
        return false;
    }
}
