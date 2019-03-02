package com.shadowwingz.androidlifedemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.shadowwingz.androidlifedemo.binderdemo.BookManagerActivity;
import com.shadowwingz.androidlifedemo.layoutinflaterdemo.LayoutInflaterActivity;
import com.shadowwingz.androidlifedemo.layoutparamsdemo.LayoutParamsActivity;
import com.shadowwingz.androidlifedemo.setcontentviewdemo.EmptyActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btn_binder_demo).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, BookManagerActivity.class));
            }
        });

        findViewById(R.id.btn_layoutinflater_demo).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, LayoutInflaterActivity.class));
            }
        });

        findViewById(R.id.btn_layoutparams_demo).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, LayoutParamsActivity.class));
            }
        });

        findViewById(R.id.btn_setcontentview_demo).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, EmptyActivity.class));
            }
        });
    }
}
