package com.shadowwingz.androidlifedemo;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

import com.shadowwingz.androidlifedemo.memoryleakdemo.LeakThread;
import com.squareup.leakcanary.LeakCanary;

import java.lang.ref.WeakReference;

/**
 * Created by shadowwingz on 2019-07-10 23:05
 */
public class BaseApplication extends Application {

    public static WeakReference<Activity> weakReference;

    private LeakThread leakThread = new LeakThread();

    @Override
    public void onCreate() {
        super.onCreate();
        initLeakCanary();
//        leakThread.start();
        registerActivityLifecycleCallbacks(new ActivityLifecycleCallbacks() {
            @Override
            public void onActivityCreated(Activity activity, Bundle savedInstanceState) {

            }

            @Override
            public void onActivityStarted(Activity activity) {

            }

            @Override
            public void onActivityResumed(Activity activity) {

            }

            @Override
            public void onActivityPaused(Activity activity) {

            }

            @Override
            public void onActivityStopped(Activity activity) {

            }

            @Override
            public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

            }

            @Override
            public void onActivityDestroyed(Activity activity) {
                weakReference = new WeakReference<>(activity);
            }
        });
    }

    private void initLeakCanary() {
        if (LeakCanary.isInAnalyzerProcess(this)) {//1
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return;
        }
        LeakCanary.install(this);
    }

    public static void checkActivityRef() {
        if (weakReference != null) {
            System.out.println("Activity 被回收 " + (weakReference.get() == null));
        }
    }
}
