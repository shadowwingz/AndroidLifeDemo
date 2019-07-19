package com.shadowwingz.androidlifedemo.handlerdemo;

import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * Created by shadowwingz on 2019-07-17 10:36
 */
public class MessageQueue {

    private BlockingDeque mBlockingDeque = new LinkedBlockingDeque(50);

    public void enqueueMessage(Message msg) {
        if (msg.target == null) {
            throw new IllegalArgumentException("Message must have a target.");
        }
        mBlockingDeque.push(msg);
    }

    public Message next() {
        Message msg = null;
        try {
            msg = (Message) mBlockingDeque.take();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return msg;
    }
}
