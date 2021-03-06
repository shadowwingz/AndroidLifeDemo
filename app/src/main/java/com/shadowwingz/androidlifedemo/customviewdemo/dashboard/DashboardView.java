package com.shadowwingz.androidlifedemo.customviewdemo.dashboard;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import com.shadowwingz.androidlifedemo.R;

import static com.shadowwingz.androidlifedemo.utils.Util.dp2px;
import static com.shadowwingz.androidlifedemo.utils.Util.sp2px;

/**
 * Created by shadowwingz on 2019-08-01 11:03
 */
public class DashboardView extends View {

    private int mRadius; // 扇形半径
    private int mStartAngle = 180; // 起始角度
    private int mSweepAngle = 180; // 绘制角度
    private int mMin = 0; // 最小值
    private int mMax = 100; // 最大值
    private int mSection = 10; // 值域（mMax-mMin）等分份数
    private int mPortion = 10; // 一个mSection等分份数
    private String mHeaderText = "℃"; // 表头
    private int mRealTimeValue = mMin; // 实时读数
    private boolean isShowValue = true; // 是否显示实时读数
    private int mStrokeWidth; // 画笔宽度
    private int mLength1; // 长刻度的长度
    private int mLength2; // 刻度读数顶部到圆弧的长度
    private int mPLRadius; // 指针长半径
    private int mPSRadius; // 指针短半径

    private int mPadding;
    private float mCenterX, mCenterY; // 圆心坐标
    private Paint mPaint;
    private RectF mRectFArc;
    private Path mPath;
    private RectF mRectFInnerArc;
    private Rect mRectText;
    private String[] mTexts;

    public DashboardView(Context context) {
        this(context, null);
    }

    public DashboardView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DashboardView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init();
    }

    private void init() {
        mStrokeWidth = dp2px(1);
        mLength1 = dp2px(8) + mStrokeWidth;
        mLength2 = mLength1 + dp2px(2);
        mPSRadius = dp2px(10);

        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setStrokeCap(Paint.Cap.ROUND);

        mRectFArc = new RectF();
        mPath = new Path();
        mRectFInnerArc = new RectF();
        mRectText = new Rect();

        mTexts = new String[mSection + 1]; // 需要显示mSection + 1个刻度读数
        for (int i = 0; i < mTexts.length; i++) {
            int n = (mMax - mMin) / mSection;
            mTexts[i] = String.valueOf(mMin + i * n);
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mPadding = Math.max(
                Math.max(getPaddingLeft(), getPaddingTop()),
                Math.max(getPaddingRight(), getPaddingBottom())
        );
        // 我们在 xml 中设置自定义 view 的 padding，要想让 padding 生效，需要调用 setPadding 方法
        setPadding(mPadding, mPadding, mPadding, mPadding);

        // 控件的宽度，这里要参考父容器的 widthMeasureSpec 来确定控件的宽度
        int measureWidth = resolveSize(dp2px(200), widthMeasureSpec);
        mRadius = (measureWidth - mPadding * 2 - mStrokeWidth * 2) / 2;

        mPaint.setTextSize(sp2px(16));
        if (isShowValue) { // 显示实时读数，View高度增加字体高度3倍
            mPaint.getTextBounds("0", 0, "0".length(), mRectText);
        } else {
            mPaint.getTextBounds("0", 0, 0, mRectText);
        }
        // 由半径 + 指针短半径 + 实时读数文字高度确定的高度
        int measureHeight = mRadius + mStrokeWidth * 2 + mPSRadius + mRectText.height() * 3;
        // 确定控件的大小
        setMeasuredDimension(measureWidth, measureHeight + getPaddingTop() + getPaddingBottom());

        // 确定圆心坐标
        mCenterX = mCenterY = getMeasuredWidth() / 2f;
        // 最外层的半圆所在的弧形
        mRectFArc.set(
                getPaddingLeft() + mStrokeWidth,
                getPaddingTop() + mStrokeWidth,
                getMeasuredWidth() - getPaddingRight() - mStrokeWidth,
                getMeasuredWidth() - getPaddingBottom() - mStrokeWidth
        );

        mPaint.setTextSize(sp2px(10));
        mPaint.getTextBounds("0", 0, "0".length(), mRectText);
        // 刻度 0 10 20 ... 所在的弧形
        mRectFInnerArc.set(
                getPaddingLeft() + mLength2 + mRectText.height(),
                getPaddingTop() + mLength2 + mRectText.height(),
                getMeasuredWidth() - getPaddingRight() - mLength2 - mRectText.height(),
                getMeasuredWidth() - getPaddingBottom() - mLength2 - mRectText.height()
        );

        mPLRadius = mRadius - (mLength2 + mRectText.height() + dp2px(5));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(mStrokeWidth);
        mPaint.setColor(ContextCompat.getColor(getContext(), R.color.colorPrimary));
        /**
         * 画圆弧
         */
        canvas.drawArc(mRectFArc, mStartAngle, mSweepAngle, false, mPaint);

        /**
         * 画长刻度
         * 画好起始角度的一条刻度后通过canvas绕着原点旋转来画剩下的长刻度
         */
        float x0 = (float) (mPadding + mStrokeWidth);
        float y0 = (float) (mPadding + mStrokeWidth + mRadius);
        float x1 = (float) (mPadding + mStrokeWidth + mLength1);
        float y1 = (float) (mPadding + mStrokeWidth + mRadius);

        canvas.save();
        canvas.drawLine(x0, y0, x1, y1, mPaint);
        float angle = mSweepAngle * 1f / mSection;
        for (int i = 0; i < mSection; i++) {
            canvas.rotate(angle, mCenterX, mCenterY);
            canvas.drawLine(x0, y0, x1, y1, mPaint);
        }
        canvas.restore();

        /**
         * 画短刻度
         * 同样采用canvas的旋转原理
         */
        canvas.save();
        mPaint.setStrokeWidth(1);
        float x2 = mPadding + mStrokeWidth + mLength1 / 2f;
        float y2 = (float) (mPadding + mStrokeWidth + mRadius);
        canvas.drawLine(x0, y0, x2, y2, mPaint);
        angle = mSweepAngle * 1f / (mSection * mPortion);
        for (int i = 1; i < mSection * mPortion; i++) {
            canvas.rotate(angle, mCenterX, mCenterY);
            if (i % mPortion == 0) { // 避免与长刻度画重合
                continue;
            }
            canvas.drawLine(x0, y0, x2, y2, mPaint);
        }
        canvas.restore();

        /**
         * 画长刻度读数
         * 添加一个圆弧 path，文字沿着 path 绘制
         */
        mPaint.setTextSize(sp2px(10));
        mPaint.setTextAlign(Paint.Align.LEFT);
        mPaint.setStyle(Paint.Style.FILL);
        for (int i = 0; i < mTexts.length; i++) {
            mPaint.getTextBounds(mTexts[i], 0, mTexts[i].length(), mRectText);
            // 粗略把文字的宽度视为圆心角2*θ对应的弧长，利用弧长公式得到θ，下面用于修正角度
            float θ = (float) (180 * mRectText.width() / 2 /
                    (Math.PI * (mRadius - mLength2 - mRectText.height())));

            mPath.reset();
            mPath.addArc(
                    mRectFInnerArc,
                    mStartAngle + i * (mSweepAngle / mSection) - θ, // 正起始角度减去θ使文字居中对准长刻度
                    mSweepAngle
            );
            canvas.drawTextOnPath(mTexts[i], mPath, 0, 0, mPaint);
        }

        /**
         * 画表头
         * 没有表头就不画
         */
        if (!TextUtils.isEmpty(mHeaderText)) {
            mPaint.setTextSize(sp2px(14));
            mPaint.setTextAlign(Paint.Align.CENTER);
            // 在绘制文字之前，需要知道文字的高度，要测量待绘制文字的高度，可以使用 getTextBounds 方法，
            // 把待绘制文字的高度存储在 mRectText 中，调用 mRectText.height() 即可获取高度
            mPaint.getTextBounds(mHeaderText, 0, mHeaderText.length(), mRectText);
            canvas.drawText(mHeaderText, mCenterX, mCenterY / 2f + mRectText.height(), mPaint);
        }

        /**
         * 画指针
         */
        float θ = mStartAngle + mSweepAngle * (mRealTimeValue - mMin) / (mMax - mMin); // 指针与水平线夹角
        int d = dp2px(5); // 指针由两个等腰三角形构成，d为共底边长的一半
        mPath.reset();
        // 下
        float[] p1 = getCoordinatePoint(d, θ + 270);
        mPath.moveTo(p1[0], p1[1]);
        // 左
        float[] p2 = getCoordinatePoint(mPLRadius, θ);
        mPath.lineTo(p2[0], p2[1]);
        // 上
        float[] p3 = getCoordinatePoint(d, θ + 90);
        mPath.lineTo(p3[0], p3[1]);
        // 右
        float[] p4 = getCoordinatePoint(mPSRadius, θ + 180);
        mPath.lineTo(p4[0], p4[1]);
        mPath.close();
        canvas.drawPath(mPath, mPaint);

        /**
         * 画指针围绕的镂空圆心
         */
        mPaint.setColor(Color.WHITE);
        canvas.drawCircle(mCenterX, mCenterY, dp2px(2), mPaint);

        /**
         * 画实时度数值
         */
        if (isShowValue) {
            mPaint.setTextSize(sp2px(16));
            mPaint.setTextAlign(Paint.Align.CENTER);
            mPaint.setColor(ContextCompat.getColor(getContext(), R.color.colorPrimary));
            String value = String.valueOf(mRealTimeValue);
            mPaint.getTextBounds(value, 0, value.length(), mRectText);
            canvas.drawText(value, mCenterX, mCenterY + mPSRadius + mRectText.height() * 2, mPaint);
        }
    }

    public float[] getCoordinatePoint(int radius, float angle) {
        float[] point = new float[2];

        double arcAngle = Math.toRadians(angle); //将角度转换为弧度
        if (angle < 90) {
            point[0] = (float) (mCenterX + Math.cos(arcAngle) * radius);
            point[1] = (float) (mCenterY + Math.sin(arcAngle) * radius);
        } else if (angle == 90) {
            point[0] = mCenterX;
            point[1] = mCenterY + radius;
        } else if (angle > 90 && angle < 180) {
            arcAngle = Math.PI * (180 - angle) / 180.0;
            point[0] = (float) (mCenterX - Math.cos(arcAngle) * radius);
            point[1] = (float) (mCenterY + Math.sin(arcAngle) * radius);
        } else if (angle == 180) {
            point[0] = mCenterX - radius;
            point[1] = mCenterY;
        } else if (angle > 180 && angle < 270) {
            arcAngle = Math.PI * (angle - 180) / 180.0;
            point[0] = (float) (mCenterX - Math.cos(arcAngle) * radius);
            point[1] = (float) (mCenterY - Math.sin(arcAngle) * radius);
        } else if (angle == 270) {
            point[0] = mCenterX;
            point[1] = mCenterY - radius;
        } else {
            arcAngle = Math.PI * (360 - angle) / 180.0;
            point[0] = (float) (mCenterX + Math.cos(arcAngle) * radius);
            point[1] = (float) (mCenterY - Math.sin(arcAngle) * radius);
        }

        return point;
    }

    public int getRealTimeValue() {
        return mRealTimeValue;
    }

    public void setRealTimeValue(int realTimeValue) {
        if (mRealTimeValue == realTimeValue || realTimeValue < mMin || realTimeValue > mMax) {
            return;
        }

        mRealTimeValue = realTimeValue;
        postInvalidate();
    }
}
