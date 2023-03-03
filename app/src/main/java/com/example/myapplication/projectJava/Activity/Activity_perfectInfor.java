package com.example.myapplication.projectJava.Activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatSpinner;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;
import androidx.core.content.PackageManagerCompat;

import android.Manifest;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.myapplication.R;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;

public class Activity_perfectInfor extends AppCompatActivity {

    private static final String TAG = "tag";
    public static final int REQUEST_CODE_TAKE = 1;
    public static final int REQUEST_CODE_CHOOSE = 1;


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
    private Uri imageUri;
    private String imageBase64;

    private ImageView ivHeader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_perfect_infor);

        initView();

        initData();

        initEvent();

        getDataFromSPF();

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

        ivHeader = findViewById(R.id.img_mine_header);
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
                Log.d(TAG, "onTimeSet: " + birthdayTime);
            }
        }, 12, 30, true).show();
    }

    //再次进入时信息自动更新
    private void getDataFromSPF() {

        SharedPreferences sharedPreferences = getSharedPreferences("sp", Context.MODE_PRIVATE);

        String name = sharedPreferences.getString("name", "用户");
        String city = sharedPreferences.getString("city", "北京");
        String birthdayTime = sharedPreferences.getString("birthdayTime", "2000年1月1日0时0分");
        String gender = sharedPreferences.getString("gender", "男");
        String age = sharedPreferences.getString("age", "23");
        String image64 = sharedPreferences.getString("image_64", "");

        etName.setText(name);
        tvBirthday.setText(birthdayTime);
        if (image64.isEmpty()) {
            ivHeader.setBackgroundResource(R.drawable.img);
        }
        else {
            ivHeader.setImageBitmap(ImageUtil.base64ToImage(image64));
        }

        if (TextUtils.equals("男",gender)) {
            rbMan.setSelected(true);
        }
        if (TextUtils.equals("nv",gender)) {
            rbWoman.setSelected(true);
        }

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
        edit.putString("image_64", imageBase64);
        edit.apply();

        this.finish();
    }

    private String getAge(String birthdayTime) {

        if (TextUtils.isEmpty(birthdayTime)) {
            return "";
        }
        try {
            int year = Integer.parseInt(birthdayTime.substring(0, birthdayTime.indexOf("年")));
            Log.d(TAG, "getAge: " + (2023 - year));
            return String.valueOf(2023 - year);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";

    }

    public void takePhoto(View view) {

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_DENIED) {
            //拍照
            doTake();
        } else {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, 1);
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                doTake();
            } else {
                Toast.makeText(this, "你没有摄像头权限", Toast.LENGTH_SHORT).show();

            }
        }
    }

    private void doTake() {
        File imageTemp = new File(getExternalCacheDir(), "imageOut,jpeg");
        if (imageTemp.exists()) {
            imageTemp.delete();
        }

        try {
            imageTemp.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        //API24以下的获取文件路径的方法
//        imageUri = Uri.fromFile(imageTemp);
        if (Build.VERSION.SDK_INT > 24) {
//            contentProvider
            imageUri = FileProvider.getUriForFile(this, "com.example.myapplication.fileProvider", imageTemp);
        }
        Intent intent = new Intent();
        intent.setAction("android.media.action.IMAGE_CAPTURE");
        intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);

        startActivityForResult(intent, REQUEST_CODE_TAKE);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_TAKE) {
            if (resultCode == RESULT_OK) {
                //获取拍摄的照片
                try {
                    InputStream inputStream = getContentResolver().openInputStream(imageUri);
                    Bitmap bitmap = BitmapFactory.decodeStream(inputStream);

                    ivHeader.setImageBitmap(bitmap);

                    String imageToBase64 = ImageUtil.imageToBase64(bitmap);
                    imageBase64 = imageToBase64;
                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}