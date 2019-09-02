package com.shadowwingz.androidlifedemo.anrdemo;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;
import android.os.SystemClock;

import androidx.annotation.RequiresApi;

import com.shadowwingz.androidlifedemo.MainActivity;
import com.shadowwingz.androidlifedemo.R;

public class ForegroundService extends Service {
    public ForegroundService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Intent i = new Intent(this, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(getApplication(), 0, i, 0);
        Notification notification = new Notification.Builder(
                getApplication()).setAutoCancel(true).setSmallIcon(R.mipmap.ic_launcher)
                .setTicker("前台Service启动").setContentTitle("前台Service运行中").
                setContentText("这是一个正在运行的前台Service").setWhen(System.currentTimeMillis())
                .setContentIntent(pendingIntent).build();
        startForeground(1, notification);
        SystemClock.sleep(25 * 1000);
        return super.onStartCommand(intent, flags, startId);
    }
}
