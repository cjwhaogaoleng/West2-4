package com.example.myapplication.projectJava.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.myapplication.R;

public class activity_register extends AppCompatActivity implements View.OnClickListener {

    public static final int RESULT_CODE_REGISTER = 1;
    private EditText account, password, passConfirm;
    private RadioButton rbAgree;
    private Button register;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        account = findViewById(R.id.et_account_r);
        password = findViewById(R.id.et_password_r);
        passConfirm = findViewById(R.id.et_passConfirm);

        rbAgree = findViewById(R.id.rb);

        register = findViewById(R.id.btn_register);
        register.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        String name = account.getText().toString();
        String pass = password.getText().toString();
        String confirm = passConfirm.getText().toString();

        if (TextUtils.isEmpty(name)) {
            Toast.makeText(activity_register.this, "用户名不能为空", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(pass)) {
            Toast.makeText(activity_register.this, "密码不能为空", Toast.LENGTH_SHORT).show();
            return;
        }
        if (!TextUtils.equals(pass, confirm)) {
            Toast.makeText(activity_register.this, "密码不一致", Toast.LENGTH_SHORT).show();
            return;
        }
        if (!rbAgree.isChecked()) {
            Toast.makeText(activity_register.this, "请同意协议", Toast.LENGTH_SHORT).show();
            return;
        }

        //注册数据存储
        //弊端本地只能存一次
        SharedPreferences spRecord = getSharedPreferences("spRecord", MODE_PRIVATE);
        SharedPreferences.Editor edit = spRecord.edit();
        edit.putString("account", name);
        edit.putString("password", pass);

        //数据回传

        Intent intent = new Intent();
        Bundle bundle = new Bundle();
        bundle.putString("account", name);
        bundle.putString("password",pass);
        intent.putExtras(bundle);

        setResult(RESULT_CODE_REGISTER,intent);

        Toast.makeText(activity_register.this, "注册成功", Toast.LENGTH_SHORT).show();

        this.finish();

    }
}