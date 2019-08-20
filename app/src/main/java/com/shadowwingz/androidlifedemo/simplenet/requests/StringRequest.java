package com.shadowwingz.androidlifedemo.simplenet.requests;

import com.shadowwingz.androidlifedemo.simplenet.base.Request;
import com.shadowwingz.androidlifedemo.simplenet.base.Response;

public class StringRequest extends Request<String> {

    public StringRequest(Request.HttpMethod method, String url, RequestListener<String> listener) {
        super(url, method, listener);
    }

    @Override
    public String parseResponse(Response response) {
        return new String(response.getRawData());
    }
}
