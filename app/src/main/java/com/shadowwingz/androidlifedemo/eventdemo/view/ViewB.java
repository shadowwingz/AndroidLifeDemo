package com.shadowwingz.androidlifedemo.eventdemo.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.shadowwingz.androidlifedemo.utils.LogUtil;

/**
 * Created by shadowwingz on 2019-08-16 23:09
 */
public class ViewB extends View {
    public ViewB(Context context) {
        super(context);
    }

    public ViewB(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public ViewB(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        LogUtil.d("dispatchTouchEvent viewB");
        return super.dispatchTouchEvent(event);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        LogUtil.d("onTouchEvent viewB");
        return super.onTouchEvent(event);
    }
}
