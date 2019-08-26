package com.shadowwingz.androidlifedemo.simplenet.requests;

import com.shadowwingz.androidlifedemo.simplenet.base.Request;
import com.shadowwingz.androidlifedemo.simplenet.base.Response;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * 返回的数据类型为 Json 的请求, Json 对应的对象类型为 JSONObject
 */
public class JsonRequest extends Request<JSONObject> {

    public JsonRequest(Request.HttpMethod method, String url,
                       RequestListener<JSONObject> listener) {
        super(url, method, listener);
    }

    /**
     * 将 Response 的结果转换为 JSONObject
     */
    @Override
    public JSONObject parseResponse(Response response) {
        String jsonString = new String(response.getRawData());
        try {
            return new JSONObject(jsonString);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
}
