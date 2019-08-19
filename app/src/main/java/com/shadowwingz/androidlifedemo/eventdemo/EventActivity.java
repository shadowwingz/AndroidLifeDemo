package com.shadowwingz.androidlifedemo.eventdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;

import com.shadowwingz.androidlifedemo.R;
import com.shadowwingz.androidlifedemo.utils.LogUtil;

public class EventActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN: {
                LogUtil.d("event type ACTION_DOWN");
                break;
            }
            case MotionEvent.ACTION_MOVE: {
                LogUtil.d("event type ACTION_MOVE");
                break;
            }
            case MotionEvent.ACTION_UP: {
                LogUtil.d("event type ACTION_UP");
                break;
            }
            default:
                break;
        }
        LogUtil.d("dispatchTouchEvent EventActivity");
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        LogUtil.d("onTouchEvent EventActivity");
        return super.onTouchEvent(event);
    }
}
