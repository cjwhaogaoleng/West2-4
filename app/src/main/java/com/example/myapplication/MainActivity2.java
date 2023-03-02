package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.io.Serializable;

public class MainActivity2 extends AppCompatActivity {

    private final String TAG = "tag";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);





    }

    public void back(View view) {

        Intent intent = getIntent();
        if (intent != null) {
            Bean bean = (Bean) intent.getSerializableExtra("packet1");
            if (bean!=null) {
                Log.d(TAG, "onCreate: " + bean);
            }

            intent.putExtra("packet2", "从activity2传回来的数据");
            setResult(1,intent);
            this.finish();
        }



    }

    public void receiveBundle(View view) {
        Intent intent = getIntent();
        if (intent!=null) {
            Bundle bundle = intent.getBundleExtra("Bundle");

            if (bundle!=null) {
                String stringFromAc1 = bundle.getString("String");
                int intFromAc1 = bundle.getInt("Int");
                String output="String : "+stringFromAc1+"Int : "+intFromAc1;
                Toast.makeText(MainActivity2.this,output,Toast.LENGTH_SHORT).show();
            }
            else {
                Toast.makeText(MainActivity2.this,"wu",Toast.LENGTH_SHORT).show();

            }

        }
    }
}