package com.example.myapplication;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.myapplication.projectJava.Activity.Activity_ViewPager;

public class MainActivity extends AppCompatActivity {

    private final String TAG = "tag";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void jumpToAc2(View view) {
        Intent intent = new Intent(this, MainActivity2.class);
        Bean bean = new Bean();
        bean.setAge(10);
        bean.setName("余光");

        intent.putExtra("packet1", bean);
//        startActivity(intent);
        startActivityForResult(intent, 0);


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 0 && resultCode == 1 && data != null) {
            String s = data.getStringExtra("packet2");
            Log.d(TAG, "onActivityResult: ------------------" + s);
        }
    }

    public void conveyBundle(View view) {
        Intent intent = new Intent(this,MainActivity2.class);

        Bundle bundle = new Bundle();
        bundle.putString("String", "string");
        bundle.putInt("Int", 22);

        intent.putExtra("Bundle", bundle);
        startActivity(intent);
    }

    public void jumpToMenu(View view) {
//        startActivity(new Intent(MainActivity.this,));
    }

    public void jumpToFragmentTest(View view) {
        startActivity(new Intent(MainActivity.this, Activity_StaticFragment.class));
    }

    public void jumpToDynamicFragmentTest(View view) {
        startActivity(new Intent(MainActivity.this, Activity_DynamicFragment.class));
    }


    public void jumpToFragmentToF(View view) {
        startActivity(new Intent(MainActivity.this, FragToFragActivity.class));

    }

    public void jumpToViewPager1(View view) {
        startActivity(new Intent(MainActivity.this, Activity_ViewPager.class));

    }

    public void jumpToHome(View view) {
        startActivity(new Intent(MainActivity.this, Activity_ViewPager.class));

    }
}