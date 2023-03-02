package com.example.myapplication.projectJava.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatSpinner;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.TimePicker;

import com.example.myapplication.R;

public class Activity_perfectInfor extends AppCompatActivity {

    private static final String TAG = "tag";
    private EditText etName, etPhoneNo, etIdNo;

    private RadioButton rbMan, rbWoman;

    private AppCompatSpinner spinner;
    private TextView tvBirthday;

    private String[] cities;
    private int selectedCityPosition;
    private String selectedCity;

    private String birthday;
    private String birthdayTime;
    private String gender;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_perfect_infor);

        initView();

        initData();

        initEvent();
    }

    private void initView() {
        etName = findViewById(R.id.et_name_text);
        etPhoneNo = findViewById(R.id.et_phoneNumber_text);

        etIdNo = findViewById(R.id.et_idNumber_text);
        rbMan = findViewById(R.id.rb_man);
        rbWoman = findViewById(R.id.rb_woman);

        spinner = findViewById(R.id.sp_city);
        tvBirthday = findViewById(R.id.et_birthday_text);

        cities = getResources().getStringArray(R.array.cities);
    }

    private void initData() {
    }

    private void initEvent() {

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedCityPosition = position;
                selectedCity = cities[selectedCityPosition];
                //Log.d(TAG, "onItemSelected: " + position + "    " + selectedCity);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        tvBirthday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(Activity_perfectInfor.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        int realMonth = month + 1;
                        birthday = year + "年" + realMonth + "月" + dayOfMonth + "日";
                        popTimePick();

                    }
                }, 2000, 10, 10).show();
            }
        });

    }

    private void popTimePick() {
        new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                birthdayTime = birthday + hourOfDay + "时" + minute + "分";
                tvBirthday.setText(birthdayTime);
//                System.out.println(birthdayTime);
                Log.d(TAG, "onTimeSet: "+birthdayTime);
            }
        }, 12, 30, true).show();
    }

    public void save(View view) {
        //先将信息存到本地

        String name = etName.getText().toString();
        String phone = etPhoneNo.getText().toString();
        String id = etIdNo.getText().toString();
        String age = getAge(birthdayTime);

        if (rbMan.isChecked()) {
            gender = "男";
        }
        if (rbWoman.isChecked()) {
            gender = "女";
        }


        SharedPreferences sharedPreferences = getSharedPreferences("sp", MODE_PRIVATE);
        SharedPreferences.Editor edit = sharedPreferences.edit();
        edit.putString("name", name);
        edit.putString("phone", phone);
        edit.putString("id", id);

        edit.putString("birthdayTime", birthdayTime);
        edit.putString("city", selectedCity);
        edit.putString("gender", gender);
        edit.putString("age", age);
        edit.apply();

        this.finish();
    }

    private String getAge(String birthdayTime) {

        if (TextUtils.isEmpty(birthdayTime)) {
            return "";
        }
        try {
            int year = Integer.parseInt(birthdayTime.substring(0, birthdayTime.indexOf("年")));
            Log.d(TAG, "getAge: "+(2023-year));
            return String.valueOf(2023 - year);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";

    }
}