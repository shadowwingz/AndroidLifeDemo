package com.shadowwingz.androidlifedemo.setcontentviewdemo;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.shadowwingz.androidlifedemo.R;

public class EmptyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_empty);
    }
}
