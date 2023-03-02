package com.example.myapplication.viewpager2_test;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.view.View;

import com.example.myapplication.R;
import com.example.myapplication.adapter.MyVP2FragmentAdapter;

public class MainActivity_viewpager2 extends AppCompatActivity {

    ViewPager2 viewPager2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_viewpager2);

        initPager();


    }

    private void initPager() {

        viewPager2= findViewById(R.id.vp2);
        //MyVP2FragmentAdapter myVP2FragmentAdapter = new MyVP2FragmentAdapter();
        //viewPager2.setAdapter(myVP2FragmentAdapter);
    }
}