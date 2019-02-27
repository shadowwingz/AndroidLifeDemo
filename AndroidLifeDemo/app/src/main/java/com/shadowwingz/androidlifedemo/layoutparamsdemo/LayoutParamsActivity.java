package com.shadowwingz.androidlifedemo.layoutparamsdemo;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.shadowwingz.androidlifedemo.R;

public class LayoutParamsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layout_params);

        // 这里相当于布局文件中的
        // android:layout_width="match_parent"
        // android:layout_height="wrap_content"
//        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
//                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
        ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        LinearLayout parent = (LinearLayout) findViewById(R.id.container);
        TextView textView = new TextView(this);
        textView.setText("测试文本");
        textView.setTextSize(24);
        textView.setTextColor(Color.BLACK);
        textView.setLayoutParams(params);
        textView.setBackgroundColor(Color.GREEN);
        parent.addView(textView);

        // 修改 LayoutParams，长度修改为 200 像素
//        textView.setLayoutParams(new LinearLayout.LayoutParams(200, ViewGroup.LayoutParams.MATCH_PARENT));
        textView.setLayoutParams(new RelativeLayout.LayoutParams(200, ViewGroup.LayoutParams.MATCH_PARENT));
    }
}
