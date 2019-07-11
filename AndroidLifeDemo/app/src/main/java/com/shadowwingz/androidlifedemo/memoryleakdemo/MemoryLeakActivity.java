package com.shadowwingz.androidlifedemo.memoryleakdemo;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;

import com.shadowwingz.androidlifedemo.R;

public class MemoryLeakActivity extends AppCompatActivity {

    public Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memory_leak);
//        handlerMemoryLeak();
        singletonMemoryLeak();
//        singletonNoMemoryLeak();
    }

    private void handlerMemoryLeak() {
        // 延时 3 分钟的消息
        mHandler.sendEmptyMessageDelayed(0, 3 * 60 * 1000);
    }

    private void singletonMemoryLeak() {
        SingletonMemoryLeak.getInstance(this);
    }

    private void singletonNoMemoryLeak() {
        SingletonNoMemoryLeak.getInstance(this);
    }
}
