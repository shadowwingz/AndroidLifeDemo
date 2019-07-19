package com.shadowwingz.androidlifedemo.handlerdemo;

/**
 * Created by shadowwingz on 2019-07-17 10:41
 */
public class Handler {

    private MessageQueue mQueue;

    public Handler() {
        Looper.prepare();
        Looper looper = Looper.myLooper();
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
            mQueue = queue;
            queue.enqueueMessage(msg);
        }
    }
}
