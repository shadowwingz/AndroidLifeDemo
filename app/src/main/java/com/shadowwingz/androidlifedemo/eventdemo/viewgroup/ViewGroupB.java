package com.shadowwingz.androidlifedemo.eventdemo.viewgroup;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.LinearLayout;

import com.shadowwingz.androidlifedemo.utils.LogUtil;

/**
 * Created by shadowwingz on 2019-08-16 23:12
 */
public class ViewGroupB extends LinearLayout {

    public ViewGroupB(Context context) {
        super(context);
    }

    public ViewGroupB(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ViewGroupB(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        LogUtil.d("dispatchTouchEvent ViewGroupB");
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        LogUtil.d("onInterceptTouchEvent ViewGroupB");
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        LogUtil.d("onTouchEvent ViewGroupB");
        return super.onTouchEvent(event);
    }
}
