package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

public class Activity_StaticFragment extends AppCompatActivity {

    private static final String TAG = "tag";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_static_fragment);


        if (onDataChangeListener.getData(null)!=null) {
            Log.d(TAG, "onCreate: "+ onDataChangeListener.getData(null));
        }

        onDataChangeListener.sentData("我来自activity");
    }


    private OnDataChangeListener onDataChangeListener;



    public void setOnDataChangeListener(OnDataChangeListener onDataChangeListener) {
        this.onDataChangeListener = onDataChangeListener;
    }
    public interface OnDataChangeListener{
        void sentData( String data);

        String getData(String data);
    }
}