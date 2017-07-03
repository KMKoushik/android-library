package com.hyttetech.library.animators;

/**
 * Created by koushik on 1/7/17.
 */

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PixelFormat;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.RotateAnimation;

import com.hyttetech.library.Animators;

public class ArcLoader extends Animators {

    private Paint mPaint;
    private float mRadius;
    private Path circle;

    private long mStartTicks = 0;
    private boolean mIsRunning = false;

    public ArcLoader()
    {
        super();

        mRadius = 360;

        mPaint = new Paint();

        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(Color.BLACK);
        mPaint.setAntiAlias(true);
        mPaint.setStrokeWidth(10);
    }

    public ArcLoader(float radius) {
        super();

        mRadius = radius;

        mPaint = new Paint();

        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(Color.BLACK);
        mPaint.setAntiAlias(true);
        mPaint.setStrokeWidth(20);
    }

    @Override
    public void draw(Canvas canvas) {

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
        float radius = loopPercent * mRadius;
        Rect bounds = getBounds();
        float x = ((bounds.right - bounds.left) / 2f) + bounds.left;
        float y = ((bounds.bottom - bounds.top) / 2f) + bounds.top;
        canvas.drawArc(rect, radius,radius+2, false, mPaint);
    }
    private float calculateCurrentLoopPercent() {
        float loopPercent =0.0f;
        if (isRunning()) {
            float loopMillis =2600;

            loopPercent = (AnimationUtils.currentAnimationTimeMillis() - (mStartTicks) )/ loopMillis*2f;
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
    public void setRadius(float radius) {
        mRadius=360;
    }

    @Override
    public void setColor(int color) {
        mPaint.setColor(color);
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