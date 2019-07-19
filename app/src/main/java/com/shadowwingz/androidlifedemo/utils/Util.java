package com.shadowwingz.androidlifedemo.utils;

import android.app.ActivityManager;
import android.content.Context;

import java.util.List;

/**
 * Created by shadowwingz on 2019-01-13 17:18
 */
public class Util {

    private Util() {
    }

    public static String getProcessName(Context context) {
        ActivityManager activityManager = (ActivityManager) context.getSystemService(Context
                .ACTIVITY_SERVICE);
        List<ActivityManager.RunningAppProcessInfo> runningApps = activityManager
                .getRunningAppProcesses();
        if (runningApps == null) {
            return null;
        }
        int pid = android.os.Process.myPid();
        for (ActivityManager.RunningAppProcessInfo procInfo : runningApps) {
            if (procInfo.pid == pid) {
                return procInfo.processName;
            }
        }
        return "";
    }
}
