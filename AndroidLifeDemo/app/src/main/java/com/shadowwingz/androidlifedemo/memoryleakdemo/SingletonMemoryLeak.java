package com.shadowwingz.androidlifedemo.memoryleakdemo;

import android.content.Context;

/**
 * Created by shadowwingz on 2019-07-11 22:00
 */
public class SingletonMemoryLeak {

    public static Context sContext;

    private static SingletonMemoryLeak instance = new SingletonMemoryLeak();

    private SingletonMemoryLeak() {
    }

    public static SingletonMemoryLeak getInstance(Context context) {
        sContext = context;
        return instance;
    }
}
