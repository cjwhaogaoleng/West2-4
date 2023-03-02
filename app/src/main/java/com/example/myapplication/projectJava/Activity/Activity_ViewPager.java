package com.example.myapplication.projectJava.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.myapplication.R;
import com.example.myapplication.adapter.MyVPFragmentAdapter;
import com.example.myapplication.projectJava.Fragment.MainFragment_mine;
import com.google.android.material.badge.BadgeDrawable;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class Activity_ViewPager extends AppCompatActivity {


    private ViewPager viewPager;
    private BottomNavigationView bottomNavigationView;
    private MyVPFragmentAdapter myVPFragmentAdapter;
    private List<Fragment> fragmentList;

    public Activity_ViewPager() {
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager);

        viewPager = findViewById(R.id.vp);
        bottomNavigationView = findViewById(R.id.button_menu);

        initData();

        myVPFragmentAdapter = new MyVPFragmentAdapter(getSupportFragmentManager(), fragmentList);
        viewPager.setAdapter(myVPFragmentAdapter);


        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                changeButton(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.menu_home:
                        viewPager.setCurrentItem(0);
                        break;
                    case R.id.menu_mine:
                        viewPager.setCurrentItem(1);
                        break;
                    default:
                        break;
                }
                return true;
            }
        });

        //红点
        BadgeDrawable orCreateBadge = bottomNavigationView.getOrCreateBadge(R.id.menu_mine);

    }

    private void changeButton(int position) {
        switch (position) {
            case 0:
                bottomNavigationView.setSelectedItemId(R.id.menu_home);
                break;
            case 1:
                bottomNavigationView.setSelectedItemId(R.id.menu_mine);
                break;
            default:
                break;
        }
    }

    private void initData() {
        fragmentList = new ArrayList<>();

        MainFragment_mine fragmentHome = MainFragment_mine.newInstance("主页", "");
        MainFragment_mine fragmentMine = MainFragment_mine.newInstance("我的", "");

        fragmentList.add(fragmentHome);
        fragmentList.add(fragmentMine);
    }
}