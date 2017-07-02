package com.hyttetech.library.animators;

/**
 * Created by koushik on 2/7/17.
 */

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.RectF;
import android.view.animation.AnimationUtils;

import com.hyttetech.library.Animators;

/**
 * Created by koushik on 1/7/17.
 */



public class BouncyBall extends Animators {

    private Paint mPaint;
    private float mRadius;

    private long mStartTicks = 0;
    private boolean mIsRunning = false;

    public BouncyBall()
    {
        super();

        mRadius = 100;
        mPaint = new Paint();
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setColor(Color.BLACK);
        mPaint.setAntiAlias(true);
        mPaint.setStrokeWidth(2);
    }

    public BouncyBall(float radius) {
        super();

        mRadius = radius;

        mPaint = new Paint();

        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setColor(Color.BLACK);
        mPaint.setAntiAlias(true);
        mPaint.setStrokeWidth(2);
        //mPaint.setAlpha(10);

    }

    @Override
    public void setRadius(float radius) {
        mRadius=100;
    }

    public void setColor(int color)
    {
        mPaint.setColor(color);
    }

    @Override
    public void draw(Canvas canvas) {
        int flag=0;
        int canvasW = canvas.getWidth();
        int canvasH = canvas.getHeight();
        Point centerOfCanvas = new Point(canvasW / 2, canvasH / 2);
        int rectW = 100;
        int rectH = 100;
        int left = centerOfCanvas.x - (rectW / 2);
        int top = centerOfCanvas.y - (rectH / 2);
        int right = centerOfCanvas.x + (rectW / 2);
        int bottom = centerOfCanvas.y + (rectH / 2);
        RectF rect = new RectF(left, top, right, bottom);
        float loopPercent = calculateCurrentLoopPercent();

        float alpha = -150;

        mPaint.setAlpha((int) (255 * alpha));

        float radius = loopPercent * mRadius;
        if(radius==(mRadius-20)) {
            flag = 1;
        }
        if(radius==20) {
            flag = 0;
        }


        Rect bounds = getBounds();
        float x = ((bounds.right - bounds.left) / 2f) + bounds.left;
        float y = ((bounds.bottom - bounds.top) / 2f) + bounds.top;
        canvas.drawColor(Color.TRANSPARENT);
        //canvas.drawCircle(x, y, radius, mPaint);
        /*canvas.drawCircle(x, y, (float) (radius/1.5), mPaint);
        canvas.drawCircle(x, y, (float) (radius/2.5), mPaint);*/
       if(flag==0)
            canvas.drawCircle(x, y, radius+20, mPaint);
        if(flag==1)
            canvas.drawCircle(x, y, mRadius-radius-20, mPaint);


    }

    private float calculateCurrentLoopPercent() {
        float loopPercent =0.0f;
        if (isRunning()) {
            float loopMillis = 2000;

            loopPercent = (AnimationUtils.currentAnimationTimeMillis() - (mStartTicks) )/ loopMillis;
            while (loopPercent > 1) {
                loopPercent -= 1;
                mStartTicks += loopMillis;
            }
        }

        return loopPercent;
    }

    @Override
    public void run() {
        invalidateSelf();
        scheduleSelf(this, AnimationUtils.currentAnimationTimeMillis() + (1000/60));
    }

    @Override
    public boolean isRunning() {
        return mIsRunning;
    }

    @Override
    public void start() {
        if (!isRunning()) {
            mIsRunning = true;
            mStartTicks = AnimationUtils.currentAnimationTimeMillis();
            run();
        }
    }

    @Override
    public void stop() {
        if (isRunning()) {
            unscheduleSelf(this);
            mIsRunning = false;
        }
    }

    @Override
    public int getOpacity() {
        return PixelFormat.TRANSLUCENT;
    }

    @Override
    public void setAlpha(int arg0) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setColorFilter(ColorFilter arg0) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int getIntrinsicHeight() {
        return (int) (2 * mRadius);
    }

    @Override
    public int getIntrinsicWidth() {
        return (int) (2 * mRadius);
    }

    @Override
    public int getMinimumHeight() {
        return (int) (2 * mRadius);
    }

    @Override
    public int getMinimumWidth() {
        return (int) (2 * mRadius);
    }
}
