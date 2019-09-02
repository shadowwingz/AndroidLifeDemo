package com.shadowwingz.androidlifedemo.layoutinflaterdemo;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.app.AppCompatActivity;

import com.shadowwingz.androidlifedemo.R;

public class LayoutInflaterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layout_inflater);
        ViewGroup mainLayout = (ViewGroup) findViewById(R.id.main_layout);
        LayoutInflater inflater = LayoutInflater.from(this);
//        inflater.inflate(R.layout.button_layout, mainLayout);
//        inflater.inflate(R.layout.button_layout, mainLayout, true);
//        View view = inflater.inflate(R.layout.button_layout, mainLayout, false);
//        mainLayout.addView(view);
        View view = inflater.inflate(R.layout.button_layout, null);
        mainLayout.addView(view);
    }
}
