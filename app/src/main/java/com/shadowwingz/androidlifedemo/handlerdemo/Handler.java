package com.shadowwingz.androidlifedemo.handlerdemo;

/**
 * Created by shadowwingz on 2019-07-17 10:41
 */
public class Handler {

    private MessageQueue mQueue;

    public Handler() {
        // 初始化 Looper
        Looper.prepare();
        // 获取当前线程的 Looper
        Looper looper = Looper.myLooper();
        // MessageQueue 和 Looper 关联
        mQueue = looper.sMessageQueue;
    }

    public void handleMessage(Message msg) {

    }

    public void dispatchMessage(Message msg) {
        handleMessage(msg);
    }

    public void sendMessage(Message msg) {
        MessageQueue queue = mQueue;
        if (queue != null) {
            // 将消息投递到消息队列
            queue.enqueueMessage(msg);
        }
    }
}
