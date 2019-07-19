package com.shadowwingz.androidlifedemo;

import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.shadowwingz.androidlifedemo.binderdemo.BookManagerActivity;
import com.shadowwingz.androidlifedemo.handlerdemo.HandlerActivity;
import com.shadowwingz.androidlifedemo.layoutinflaterdemo.LayoutInflaterActivity;
import com.shadowwingz.androidlifedemo.layoutparamsdemo.LayoutParamsActivity;
import com.shadowwingz.androidlifedemo.memoryleakdemo.MemoryLeakActivity;

public class MainActivity extends AppCompatActivity {

    private Button mButton;

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
//                startActivity(new Intent(MainActivity.this, EmptyActivity.class));
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        int i = 0;
                        while (true) {
                            String s = new String("" + (i++));
                            System.out.println(s);
                        }
                    }
                }).start();
            }
        });

        findViewById(R.id.btn_anr_demo).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 第一次 anr 情况
                testANR1();
            }
        });

        findViewById(R.id.btn_memory_leak).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, MemoryLeakActivity.class));
            }
        });

        findViewById(R.id.btn_handler_demo).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, HandlerActivity.class));
            }
        });

        // 第二种情况
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                testANR2();
//            }
//        }).start();
//        SystemClock.sleep(5000);
//        initView();

//        startService(new Intent(this, ForegroundService.class));
    }

    private void testANR1() {
        SystemClock.sleep(30 * 1000);
    }

    private synchronized void testANR2() {
        SystemClock.sleep(30000 * 1000);
    }

    private synchronized void initView() {
    }
}
