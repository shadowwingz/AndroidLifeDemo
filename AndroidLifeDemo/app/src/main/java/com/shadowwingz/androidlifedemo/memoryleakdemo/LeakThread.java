package com.shadowwingz.androidlifedemo.memoryleakdemo;

import com.shadowwingz.androidlifedemo.BaseApplication;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by shadowwingz on 2019-07-10 23:29
 */
public class LeakThread extends Thread {

    @Override
    public void run() {
        super.run();
        Timer timer = new Timer();
        // 每隔 1 秒钟，检测一下 Activity 有没有被回收
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                BaseApplication.checkActivityRef();
            }
        }, 0, 1000);
    }
}
