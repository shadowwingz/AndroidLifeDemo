package com.shadowwingz.androidlifedemo.customviewdemo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.shadowwingz.androidlifedemo.R;
import com.shadowwingz.androidlifedemo.customviewdemo.conpon.CouponDisplayActivity;
import com.shadowwingz.androidlifedemo.customviewdemo.dashboard.DashBoardActivity;

public class CustomViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_view);

        findViewById(R.id.btn_coupon_dispaly).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CustomViewActivity.this, CouponDisplayActivity.class));
            }
        });

        findViewById(R.id.btn_dashboard).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CustomViewActivity.this, DashBoardActivity.class));
            }
        });
    }
}
