package com.shadowwingz.androidlifedemo.customviewdemo.dashboard;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.shadowwingz.androidlifedemo.R;

import java.util.Random;

public class DashBoardActivity extends AppCompatActivity {

    private DashboardView1 mDashboardView1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_board);

        mDashboardView1 = (DashboardView1) findViewById(R.id.dashboard_view_1);

        mDashboardView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDashboardView1.setRealTimeValue(new Random().nextInt(100));
            }
        });
    }
}
