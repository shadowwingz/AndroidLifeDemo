package com.shadowwingz.androidlifedemo.customviewdemo.conpon;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

import com.shadowwingz.androidlifedemo.R;

/**
 * Created by shadowwingz on 2019-07-29 14:24
 * <p>
 * 自定义 View 优惠卷
 */
public class CouponDisplayView extends LinearLayout {

    private Paint mPaint;
    /**
     * 半径
     */
    private float radius = 10;
    /**
     * 圆间距
     */
    private float gap = 8;

    /**
     * 圆数量
     */
    private int circleNum;
    private float remain;

    public CouponDisplayView(Context context) {
        this(context, null);
    }

    public CouponDisplayView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CouponDisplayView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray a = context.getTheme().obtainStyledAttributes(attrs,
                R.styleable.CouponDisplayView, defStyleAttr, 0);
        for (int i = 0; i < a.getIndexCount(); i++) {
            int attr = a.getIndex(i);
            switch (attr) {
                case R.styleable.CouponDisplayView_radius:
                    radius = a.getDimensionPixelSize(R.styleable.CouponDisplayView_radius, 10);
                    break;
                case R.styleable.CouponDisplayView_gap:
                    gap = a.getDimensionPixelSize(R.styleable.CouponDisplayView_radius, 8);
                    break;
            }
        }
        a.recycle();

        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setDither(true);
        mPaint.setColor(Color.WHITE);
        mPaint.setStyle(Paint.Style.FILL);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        if (remain == 0) {
            //计算不整除的剩余部分
            remain = (int) (w - gap) % (2 * radius + gap);
        }
        circleNum = (int) ((w - gap) / (2 * radius + gap));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        // 画圆
        for (int i = 0; i < circleNum; i++) {
            /**
             * x 是圆心的 x 坐标
             * remain / 2 是 比标准 gap 多出来的部分，除以 2，再分配给矩形两边，达到对称效果
             */
            float x = gap + radius + remain / 2 + ((gap + radius * 2) * i);
            // 画上面一行的圆
            canvas.drawCircle(x, 0, radius, mPaint);
            // 画下面一行的圆
            canvas.drawCircle(x, getHeight(), radius, mPaint);
        }
    }
}
