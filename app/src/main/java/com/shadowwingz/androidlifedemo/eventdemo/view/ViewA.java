package com.shadowwingz.androidlifedemo.eventdemo.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.shadowwingz.androidlifedemo.utils.LogUtil;

/**
 * Created by shadowwingz on 2019-08-16 23:06
 */
public class ViewA extends View {

    public ViewA(Context context) {
        super(context);
    }

    public ViewA(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public ViewA(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        LogUtil.d("dispatchTouchEvent viewA");
        return super.dispatchTouchEvent(event);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        LogUtil.d("onTouchEvent viewA");
        return super.onTouchEvent(event);
    }
}
