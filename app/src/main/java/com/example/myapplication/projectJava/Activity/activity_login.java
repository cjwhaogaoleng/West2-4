package com.example.myapplication.projectJava.Activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.projectJava.Service;
import com.example.myapplication.projectJava.UserResponds.User;
import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;

public class activity_login extends AppCompatActivity {


    public static final int REQUEST_CODE_REGISTER = 0;
    private static final String TAG = "tag";
    private Button btnLogin;
    private EditText et_account, et_password;

    private String account_test = "";
    private String password_test = "";
    private CheckBox cbRemember, cbAuto;

    private Retrofit retrofit;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        initView();

        initAP();


        //登录操作
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String account = et_account.getText().toString();
                String password = et_password.getText().toString();


                ifLogin(account, password);
                if (TextUtils.isEmpty(account_test)) {
                    Toast.makeText(activity_login.this, "未注册账号", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.equals(account, account_test)) {
                    if (TextUtils.equals(password, password_test)) {
                        if (ifLogin(account, password))
                            Toast.makeText(activity_login.this, "登录成功", Toast.LENGTH_SHORT).show();

                        //记住密码
                        if (cbRemember.isChecked()) {
                            SharedPreferences spRecord = getSharedPreferences("spRecord", MODE_PRIVATE);
                            SharedPreferences.Editor edit = spRecord.edit();
//                            edit.putString("account", account);
//                            edit.putString("password", password);
                            edit.putBoolean("isRemember", true);
                            edit.apply();

                        } else {
                            SharedPreferences spRecord = getSharedPreferences("spRecord", MODE_PRIVATE);
                            SharedPreferences.Editor edit = spRecord.edit();
                            edit.putBoolean("isRemember", false);
                        }
                        startActivity(new Intent(activity_login.this, Activity_ViewPager.class));
                        activity_login.this.finish();

                    } else {
                        Toast.makeText(activity_login.this, "密码错误", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(activity_login.this, "用户名错误", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    //访问数据库进行登录登录
    private boolean ifLogin(String account, String password) {
        String ifLogin = "";
        retrofit = new Retrofit.Builder().baseUrl("http://192.168.43.183:8080/Assessment4/").build();

        Call<ResponseBody> call = retrofit.create(Service.class).login(account, password);
        try {
            Response<ResponseBody> response = call.execute();
            System.out.println(response);
            if (response.isSuccessful()) {
                ifLogin = response.body().string();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return ifLogin.equals("true");
    }

    //记住密码的操作
    private void initAP() {

        SharedPreferences spRecord = getSharedPreferences("spRecord", MODE_PRIVATE);
        boolean isRemember = spRecord.getBoolean("isRemember", false);
        String account = spRecord.getString("account", "");
        String password = spRecord.getString("password", "");

        account_test = account;
        password_test = password;

        if (isRemember) {
            et_account.setText(account);
            et_password.setText(password);
            cbRemember.setChecked(true);
        }
    }

    private void initView() {
        btnLogin = findViewById(R.id.btn_login);
        et_account = findViewById(R.id.et_account);
        et_password = findViewById(R.id.et_password);
        cbRemember = findViewById(R.id.cb_rem);
        cbAuto = findViewById(R.id.cb_auto);

    }


    //接受注册账号信息
    public void jumpToRegister(View view) {
        Intent intent = new Intent(this, activity_register.class);

        startActivityForResult(intent, REQUEST_CODE_REGISTER);
    }


    //拿到注册页面回传的数据
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_REGISTER && resultCode == activity_register.RESULT_CODE_REGISTER && data != null) {
            Bundle extras = data.getExtras();

            String account = extras.getString("account", "");
            String password = extras.getString("password", "");

            et_account.setText(account);
            et_password.setText(password);

            account_test = account;
            password_test = password;


        }
        //http://localhost:8080/api
    }
}