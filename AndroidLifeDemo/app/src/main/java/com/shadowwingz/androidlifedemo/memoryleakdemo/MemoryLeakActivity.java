package com.shadowwingz.androidlifedemo.memoryleakdemo;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;

import com.shadowwingz.androidlifedemo.R;

import java.util.ArrayList;
import java.util.List;

public class MemoryLeakActivity extends AppCompatActivity {

    private static List<Integer> integerList = new ArrayList<>();

    public Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
        }
    };
    private WorkThreadCanStop mThreadCanStop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memory_leak);
//        handlerMemoryLeak();
//        singletonMemoryLeak();
//        singletonNoMemoryLeak();
//        collectionsMemoryLeak();
//        threadMemoryLeak();
        threadStopNoMemoryLeak();
//        threadNoMemoryLeak();
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

    private void collectionsMemoryLeak() {
        for (int i = 0; i < 100; i++) {
            Integer number = i;
            integerList.add(number);
            number = null;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (integerList != null) {
            integerList.clear();
            integerList = null;
        }
        if (mThreadCanStop != null) {
            mThreadCanStop.stopWork();
        }
    }

    private void threadMemoryLeak() {
        new Thread() {
            @Override
            public void run() {
                SystemClock.sleep(3 * 60 * 1000);
            }
        }.start();
    }

    private void threadNoMemoryLeak() {
        new WorkThread().start();
    }

    private void threadStopNoMemoryLeak() {
        mThreadCanStop = new WorkThreadCanStop();
        mThreadCanStop.start();
    }

    private class WorkThreadCanStop extends Thread {

        private volatile boolean finished = false;

        public void stopWork() {
            finished = true;
        }

        @Override
        public void run() {
            super.run();
            while (!finished) {
                // 耗时操作
                System.out.println("线程在运行");
            }
            System.out.println("线程停止");
        }
    }

    private static class WorkThread extends Thread {
        @Override
        public void run() {
            super.run();
            SystemClock.sleep(3 * 60 * 1000);
        }
    }
}
