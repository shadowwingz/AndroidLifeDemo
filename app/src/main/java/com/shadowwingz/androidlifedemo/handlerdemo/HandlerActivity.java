package com.shadowwingz.androidlifedemo.handlerdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.shadowwingz.androidlifedemo.R;
import com.shadowwingz.androidlifedemo.utils.LogUtil;

public class HandlerActivity extends AppCompatActivity {

    private Handler mMHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            LogUtil.d("收到消息，msg.what = " + msg.what);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handler);

        new Thread(new Runnable() {
            @Override
            public void run() {
                Looper.loop();
            }
        }).start();

        findViewById(R.id.btn_send_message).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Message msg = new Message();
                msg.what = 111;
                msg.target = mMHandler;
                mMHandler.sendMessage(msg);
            }
        });
    }
}
