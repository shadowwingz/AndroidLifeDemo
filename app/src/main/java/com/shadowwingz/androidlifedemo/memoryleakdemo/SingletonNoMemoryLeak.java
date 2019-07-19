package com.shadowwingz.androidlifedemo.memoryleakdemo;

import android.content.Context;

import java.lang.ref.WeakReference;

/**
 * Created by shadowwingz on 2019-07-11 22:41
 */
public class SingletonNoMemoryLeak {
    public static WeakReference<Context> sActivityRef;

    private static SingletonNoMemoryLeak instance = new SingletonNoMemoryLeak();

    private SingletonNoMemoryLeak() {
    }

    public static SingletonNoMemoryLeak getInstance(Context context) {
        sActivityRef = new WeakReference<>(context);
        return instance;
    }
}
