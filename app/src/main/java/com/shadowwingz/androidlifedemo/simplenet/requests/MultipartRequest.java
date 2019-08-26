package com.shadowwingz.androidlifedemo.simplenet.requests;

import com.shadowwingz.androidlifedemo.simplenet.base.Request;
import com.shadowwingz.androidlifedemo.simplenet.base.Response;
import com.shadowwingz.androidlifedemo.simplenet.entity.MultipartEntity;
import com.shadowwingz.androidlifedemo.utils.LogUtil;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * Multipart请求 (只能为POST请求),该请求可以搭载多种类型参数,比如文本、文件等,
 * 但是文件仅限于小文件,否则会出现 OOM 异常.
 */
public class MultipartRequest extends Request<String> {

    MultipartEntity mMultiPartEntity = new MultipartEntity();

    public MultipartRequest(String url, Request.RequestListener<String> listener) {
        super(url, HttpMethod.POST, listener);
    }

    /**
     * @return
     */
    public MultipartEntity getMultiPartEntity() {
        return mMultiPartEntity;
    }

    @Override
    public String getBodyContentType() {
        return mMultiPartEntity.getContentType().getValue();
    }

    @Override
    public byte[] getBody() {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try {
            // 将MultipartEntity中的参数写入到bos中
            mMultiPartEntity.writeTo(bos);
        } catch (IOException e) {
            LogUtil.d("IOException writing to ByteArrayOutputStream");
        }
        return bos.toByteArray();
    }

    @Override
    public String parseResponse(Response response) {
        if (response != null && response.getRawData() != null) {
            return new String(response.getRawData());
        }
        return "";
    }

}
