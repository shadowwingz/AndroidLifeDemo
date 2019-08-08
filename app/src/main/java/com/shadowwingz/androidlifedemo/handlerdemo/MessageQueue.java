package com.shadowwingz.androidlifedemo.handlerdemo;

import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * Created by shadowwingz on 2019-07-17 10:36
 */
public class MessageQueue {

    // 消息队列，用来存储消息
    private BlockingDeque mBlockingDeque = new LinkedBlockingDeque(50);

    // 将 Handler 发送的消息存储起来
    public void enqueueMessage(Message msg) {
        if (msg.target == null) {
            throw new IllegalArgumentException("Message must have a target.");
        }
        mBlockingDeque.push(msg);
    }

    // 从消息队列中取出消息
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
