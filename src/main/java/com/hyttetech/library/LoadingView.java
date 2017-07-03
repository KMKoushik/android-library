package com.hyttetech.library;

/**
 * Created by koushik on 1/7/17.
 */


import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

import com.hyttetech.library.animators.ExpandingCircle;

/**
 * Created by koushik on 27/6/17.
 */

public class LoadingView extends android.support.v7.widget.AppCompatImageView  {

    Animators animator=new ExpandingCircle(100);
    int animColor=Color.MAGENTA;
    public LoadingView(Context context) {
        super(context);
        init(context,null,0,0);
    }

    public LoadingView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context,attrs,0,0);
    }

    public LoadingView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context,attrs,defStyleAttr,0);
    }

    private void init(Context context,AttributeSet attributeSet,int defStyleAttr,int defStyleRes)
    {
        final TypedArray a = context.obtainStyledAttributes(
                attributeSet, R.styleable.LoadingView, defStyleAttr, defStyleRes);
        String animatorName=a.getString(R.styleable.LoadingView_indicatorName);
        animColor=a.getColor(R.styleable.LoadingView_indicatorColor, Color.WHITE);
        if(animatorName!=null) {
            setAnimator(animatorName);
        }
        else
            setAnimator(animator);
    }

    public void setAnimator(Animators animators) {
        if (animators != null) {
            animator= animators;
            animator.setCallback(this);
            animator.setColor(animColor);
            setImageDrawable(animator);
            animator.start();
        }
        postInvalidate();
    }

    public void setAnimColor(int color)
    {
        animColor=color;
        setAnimator(animator);
    }

    public void setAnimator(String animatorName)
    {
        StringBuilder drawableClassName=new StringBuilder();
        if (!animatorName.contains(".")){
            String defaultPackageName=getClass().getPackage().getName();
            drawableClassName.append(defaultPackageName)
                    .append(".animators")
                    .append(".");
        }
        drawableClassName.append(animatorName);
        try {
            Class<?> drawableClass = Class.forName(drawableClassName.toString());
            Animators animators = (Animators) drawableClass.newInstance();
            animators.setRadius(100);
            setAnimator(animators);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public void start()
    {
        animator.start();
    }

    public void stop()
    {
        animator.stop();

    }

}
