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
        // 每个线程只能创建一个 Looper
        if (sThreadLocal.get() != null) {
            throw new RuntimeException("Only one Looper may be created per thread");
        }
        // 创建当前线程的 Looper 对象，存储到 ThreadLocal 中
        sThreadLocal.set(new Looper());
    }

    public static Looper myLooper() {
        return sThreadLocal.get();
    }

    // 不停地从消息队列中取出消息
    public static void loop() {
        for(;;) {
            // 取出消息
            Message msg = sMessageQueue.next();
            if (msg == null) {
                continue;
            }
            // 交给对应的 Handler 处理
            msg.target.dispatchMessage(msg);
        }
    }
}
