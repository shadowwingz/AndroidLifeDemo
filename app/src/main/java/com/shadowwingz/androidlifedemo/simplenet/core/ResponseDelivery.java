package com.shadowwingz.androidlifedemo.simplenet.core;

import android.os.Handler;
import android.os.Looper;

import com.shadowwingz.androidlifedemo.simplenet.base.Request;
import com.shadowwingz.androidlifedemo.simplenet.base.Response;

import java.util.concurrent.Executor;

/**
 * 请求结果投递类,将请求结果投递给 UI 线程
 */
class ResponseDelivery implements Executor {

    /**
     * 主线程的hander
     */
    Handler mResponseHandler = new Handler(Looper.getMainLooper());

    /**
     * 处理请求结果,将其执行在UI线程
     *
     * @param request
     * @param response
     */
    public void deliveryResponse(final Request<?> request, final Response response) {
        Runnable respRunnable = new Runnable() {
            @Override
            public void run() {
                request.deliveryResponse(response);
            }
        };

        execute(respRunnable);
    }

    @Override
    public void execute(Runnable command) {
        mResponseHandler.post(command);
    }

}
