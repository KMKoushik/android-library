package com.hyttetech.library;

/**
 * Created by koushik on 1/7/17.
 */


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PixelFormat;
import android.graphics.Rect;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

/**
 * Created by koushik on 27/6/17.
 */

public class LoadingView extends android.support.v7.widget.AppCompatImageView  {

    ExpandingCircle expandingCircle;
    ExpandingMultipleCircle circle=new ExpandingMultipleCircle(100);
    private Paint paint = new Paint();
    private Paint paint1=new Paint();
    Path path=new Path();
    private float mRadius;
    private long mStartTicks = 0;
    private boolean mIsRunning = false;
    public LoadingView(Context context) {
        super(context);
        init(null,0);
    }

    public LoadingView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(attrs,0);
    }

    public LoadingView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs,defStyleAttr);
    }

    private void init(AttributeSet attributeSet,int defStyleAttr)
    {

        paint = new Paint();
        mRadius=100;

        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.BLACK);
        paint.setAntiAlias(true);
        paint.setStrokeWidth(2);
        paint1.setStyle(Paint.Style.STROKE);
        paint1.setColor(Color.BLACK);
        paint1.setAntiAlias(true);
        //setDrawable(circle);
        setImageDrawable(circle);


    }

    public void setDrawable(Drawable expandingCircle) {
        circle= (ExpandingMultipleCircle) expandingCircle;

        if (circle != null) {
            circle.setCallback(this);
            setImageDrawable(circle);
            //invalidateDrawable(circle);
        }
        postInvalidate();

    }

    public void start()
    {
        circle.start();
    }

    public void stop()
    {
        circle.stop();

    }

}
