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

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import com.example.appbanhangonline.database.KhachHangRepository;
import com.example.appbanhangonline.model.Account;
import com.example.appbanhangonline.ultil.Server;

public class LoginActivity extends AppCompatActivity {

    public static final String TAG = LoginActivity.class.getSimpleName();
    private EditText edtUserName;
    private EditText edtPassWord;
    private Button btnLogin;
    private Button btnRegister;
    private ProgressDialog pDialog;
    UserLocalStore userLocalStore;
    /**
     * URL : URL_LOGIN
     * param : KEY_USERNAME KEY_PASSWORD
     */

    private KhachHangRepository khachHangRepository;

    public static final String KEY_USERNAME = "username";
    public static final String KEY_PASSWORD = "password";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        userLocalStore  = new UserLocalStore(this);
        khachHangRepository = new KhachHangRepository(App.getDb());
        addControl();
        addEvent();
    }

    private void addEvent() {
        btnLogin.setOnClickListener(v -> {
            //Get value input
            String username = edtUserName.getText().toString().trim();
            String password = edtPassWord.getText().toString().trim();
            // Call method
            loginAccount(username, password);
        });
        btnRegister.setOnClickListener(v -> {
            Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
            startActivity(intent);
        });
    }

    private void addControl() {
        edtUserName =  findViewById(R.id.txtEmail);
        edtPassWord =  findViewById(R.id.txtPass);
        btnLogin =  findViewById(R.id.btnDangnhap);
        btnRegister =  findViewById(R.id.btnDangki);
        pDialog = new ProgressDialog(this);
        pDialog.setMessage("Đang đăng nhập...");
        pDialog.setCanceledOnTouchOutside(false);
    }

    public void loginAccount(final String username, final String password) {
        if (checkEditText(edtUserName) && checkEditText(edtPassWord)) {
            pDialog.show();
            khachHangRepository.login(username, password, account -> {
                pDialog.hide();
                showToast("Dang nhap thanh cong", Toast.LENGTH_SHORT);
                userLocalStore.storeUserData(account);
                userLocalStore.setUserLoggedIn(true);

                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                intent.putExtra("login", account);
                startActivity(intent);
            }, error -> {
                pDialog.hide();
                showToast("Tài khoản hoặc mật khẩu chưa chính xác", Toast.LENGTH_SHORT);
            });
        }
    }

    private void showToast(String message, int time) {
        Toast.makeText(this, message, time).show();
    }

    private boolean checkEditText(EditText editText) {
        if (!editText.getText().toString().trim().isEmpty()) {
            return true;
        }

        editText.setError("Vui lòng nhập dữ liệu!");
        return false;
    }
}

