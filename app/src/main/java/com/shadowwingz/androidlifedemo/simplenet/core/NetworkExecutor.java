package com.shadowwingz.androidlifedemo.simplenet.core;

import android.util.Log;

import com.shadowwingz.androidlifedemo.simplenet.base.Request;
import com.shadowwingz.androidlifedemo.simplenet.base.Response;
import com.shadowwingz.androidlifedemo.simplenet.cache.Cache;
import com.shadowwingz.androidlifedemo.simplenet.cache.LruMemCache;
import com.shadowwingz.androidlifedemo.simplenet.httpstacks.HttpStack;
import com.shadowwingz.androidlifedemo.utils.LogUtil;

import java.util.concurrent.BlockingQueue;

/**
 * 网络请求 Executor,继承自 Thread,从网络请求队列中循环读取请求并且执行
 */
final class NetworkExecutor extends Thread {

    /**
     * 网络请求队列
     */
    private BlockingQueue<Request<?>> mRequestQueue;
    /**
     * 网络请求栈
     */
    private HttpStack mHttpStack;
    /**
     * 结果分发器,将结果投递到主线程
     */
    private static ResponseDelivery mResponseDelivery = new ResponseDelivery();
    /**
     * 请求缓存
     */
    private static Cache<String, Response> mReqCache = new LruMemCache();
    /**
     * 是否停止
     */
    private boolean isStop = false;

    public NetworkExecutor(BlockingQueue<Request<?>> queue, HttpStack httpStack) {
        mRequestQueue = queue;
        mHttpStack = httpStack;
    }

    @Override
    public void run() {
        try {
            while (!isStop) {
                final Request<?> request = mRequestQueue.take();
                // 如果请求被取消，就跳过这个请求，继续执行下一个请求
                if (request.isCanceled()) {
                    Log.d("### ", "### 取消执行了");
                    continue;
                }
                Response response;
                if (isUseCache(request)) {
                    // 从缓存中取
                    response = mReqCache.get(request.getUrl());
                } else {
                    // 从网络上获取数据
                    response = mHttpStack.performRequest(request);
                    // 如果该请求需要缓存,那么请求成功则缓存到 mResponseCache 中
                    if (request.shouldCache() && isSuccess(response)) {
                        mReqCache.put(request.getUrl(), response);
                    }
                }

                // 分发请求结果
                mResponseDelivery.deliveryResponse(request, response);
            }
        } catch (InterruptedException e) {
            LogUtil.d("请求分发器退出");
        }

    }

    private boolean isSuccess(Response response) {
        return response != null && response.getStatusCode() == 200;
    }

    private boolean isUseCache(Request<?> request) {
        return request.shouldCache() && mReqCache.get(request.getUrl()) != null;
    }

    public void quit() {
        isStop = true;
        interrupt();
    }
}
