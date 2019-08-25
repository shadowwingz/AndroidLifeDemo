package com.shadowwingz.androidlifedemo.simplenet.httpstacks;

import com.shadowwingz.androidlifedemo.simplenet.base.Request;
import com.shadowwingz.androidlifedemo.simplenet.base.Response;

/**
 * 执行网络请求的接口
 */
public interface HttpStack {
    /**
     * 执行Http请求
     *
     * @param request 待执行的请求
     * @return 响应结果
     */
    Response performRequest(Request<?> request);
}
