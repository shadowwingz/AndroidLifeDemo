package com.shadowwingz.androidlifedemo.eventdemo.viewgroup;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.LinearLayout;

import com.shadowwingz.androidlifedemo.utils.LogUtil;

/**
 * Created by shadowwingz on 2019-08-16 23:12
 */
public class ViewGroupA extends LinearLayout {

    public ViewGroupA(Context context) {
        super(context);
    }

    public ViewGroupA(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ViewGroupA(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        LogUtil.d("dispatchTouchEvent ViewGroupA");
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        LogUtil.d("onInterceptTouchEvent ViewGroupA");
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        LogUtil.d("onTouchEvent ViewGroupA");
        return super.onTouchEvent(event);
    }
}
