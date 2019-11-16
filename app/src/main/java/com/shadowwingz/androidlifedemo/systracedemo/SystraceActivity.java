package com.shadowwingz.androidlifedemo.systracedemo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.shadowwingz.androidlifedemo.R;

public class SystraceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_systrace);

        findViewById(R.id.btn_word_list).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SystraceActivity.this, WordListMainActivity.class));
            }
        });

        findViewById(R.id.btn_large_image).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SystraceActivity.this, LargeImageActivity.class));
            }
        });
    }
}
