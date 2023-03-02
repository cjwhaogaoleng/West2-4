package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.example.myapplication.fragment.DynamicBlankFragment;

public class
Activity_DynamicFragment extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dynamic_fragment);

        if (savedInstanceState==null) {
            FragmentManager supportFragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = supportFragmentManager.beginTransaction();

            fragmentTransaction.add(R.id.fcv, DynamicBlankFragment.class, null)
                    .setReorderingAllowed(true)
                    .addToBackStack(null)
                    .commit();

        }



    }
}