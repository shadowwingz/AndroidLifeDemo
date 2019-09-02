package com.shadowwingz.androidlifedemo.customviewdemo.dashboard;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.shadowwingz.androidlifedemo.R;

import java.util.Random;

public class DashBoardActivity extends AppCompatActivity {

    private DashboardView mDashboardView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_board);

        mDashboardView = (DashboardView) findViewById(R.id.dashboard_view_1);

        mDashboardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDashboardView.setRealTimeValue(new Random().nextInt(100));
            }
        });
    }
}
