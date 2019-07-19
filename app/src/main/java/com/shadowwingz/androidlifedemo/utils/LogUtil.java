package com.shadowwingz.androidlifedemo.utils;

import android.util.Log;

/**
 * Created by shadowwingz on 2019-01-13 16:21
 */
public class LogUtil {

    private LogUtil() {

    }

    public static final String TAG = "androidlifedemo";

    public static void d(String msg) {
        Log.d(TAG, msg);
    }
}
