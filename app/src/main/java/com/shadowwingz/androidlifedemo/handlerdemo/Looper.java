package com.shadowwingz.androidlifedemo.handlerdemo;

/**
 * Created by shadowwingz on 2019-07-17 10:39
 */
public class Looper {

    static MessageQueue sMessageQueue;

    private static ThreadLocal<Looper> sThreadLocal = new ThreadLocal<>();

    public Looper() {
        sMessageQueue = new MessageQueue();
    }

    public static void prepare() {
        if (sThreadLocal.get() != null) {
            throw new RuntimeException("Only one Looper may be created per thread");
        }
        sThreadLocal.set(new Looper());
    }

    public static Looper myLooper() {
        return sThreadLocal.get();
    }

    public static void loop() {
        for(;;) {
            Message msg = sMessageQueue.next();
            if (msg == null) {
                continue;
            }
            msg.target.dispatchMessage(msg);
        }
    }
}
