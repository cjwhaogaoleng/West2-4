package com.example.myapplication.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.myapplication.FragToFragActivity;
import com.example.myapplication.R;


public class BlankFragment2 extends Fragment {

    private Button btn;
    private TextView textView_b;

    public BlankFragment2() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_blank2, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        btn = view.findViewById(R.id.bToa);
        textView_b = view.findViewById(R.id.tv_b);


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onDataChangeListener.sentData("这是bToa");
            }
        });

    }

    OnDataChangeListener onDataChangeListener;

    public void setOnDataChangeListener (OnDataChangeListener onDataChangeListener){
        this.onDataChangeListener=onDataChangeListener;
    }
    public interface OnDataChangeListener{
        void sentData(String data);

    }
}