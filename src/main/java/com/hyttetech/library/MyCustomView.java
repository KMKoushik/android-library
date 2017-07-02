package com.hyttetech.library;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

import com.hyttetech.library.animators.ExpandingCircle;
import com.hyttetech.library.animators.ExpandingMultipleCircle;

/**
 * Created by koushik on 27/6/17.
 */

public class MyCustomView extends android.support.v7.widget.AppCompatImageView  {

    ExpandingCircle expandingCircle;
    ExpandingMultipleCircle circle=new ExpandingMultipleCircle(100);
    private Paint paint = new Paint();
    private Paint paint1=new Paint();
    Path path=new Path();
    private float mRadius;
    private long mStartTicks = 0;
    private boolean mIsRunning = false;
    public MyCustomView(Context context) {
        super(context);
        init(null,0);
    }

    public MyCustomView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(attrs,0);
    }

    public MyCustomView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
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
