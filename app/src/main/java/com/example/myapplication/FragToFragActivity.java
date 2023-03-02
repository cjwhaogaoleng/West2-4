package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.example.myapplication.fragment.BlankFragment1;
import com.example.myapplication.fragment.BlankFragment2;

public class FragToFragActivity extends AppCompatActivity {

    public BlankFragment1 fra_a;
    public BlankFragment2 fra_b;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frag_to_frag);

        fra_a = new BlankFragment1();
        fra_b = new BlankFragment2();

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.fra_a, fra_a, null);
        fragmentTransaction.add(R.id.fra_b, fra_b, null);
        fragmentTransaction.commitNow();
    }
}