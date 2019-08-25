package com.shadowwingz.androidlifedemo.simplenet.core;


import com.shadowwingz.androidlifedemo.simplenet.httpstacks.HttpStack;

/**
 * SimpleNet 入口
 */
public final class SimpleNet {
    /**
     * 创建一个默认数量的请求队列 NetworkExecutor
     *
     * @return RequestQueue 对象
     */
    public static RequestQueue newRequestQueue() {
        return newRequestQueue(RequestQueue.DEFAULT_CORE_NUMS);
    }

    /**
     * 创建一个请求队列
     *
     * @param coreNums 请求数量
     * @return RequestQueue 对象
     */
    public static RequestQueue newRequestQueue(int coreNums) {
        return newRequestQueue(coreNums, null);
    }

    /**
     * 创建一个请求队列,NetworkExecutor 数量为 coreNums
     *
     * @param coreNums  线程数量
     * @param httpStack 网络执行者
     * @return
     */
    public static RequestQueue newRequestQueue(int coreNums, HttpStack httpStack) {
        RequestQueue queue = new RequestQueue(Math.max(0, coreNums), httpStack);
        queue.start();
        return queue;
    }
}
