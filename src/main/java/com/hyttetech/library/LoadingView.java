package com.hyttetech.library;

/**
 * Created by koushik on 1/7/17.
 */


import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

import com.hyttetech.library.animators.ExpandingCircle;

/**
 * Created by koushik on 27/6/17.
 */

public class LoadingView extends android.support.v7.widget.AppCompatImageView  {

    Animators animator=new ExpandingCircle(100);
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
        setImageDrawable(animator);
        animator.start();


    }

    public void setAnimator(Animators animators) {
        animator= animators;
        System.out.print("hiii");


        if (animator != null) {
            animator.setCallback(this);
            setImageDrawable(animator);
            System.out.print("hiii1");

        }
        postInvalidate();

    }

    public void setAnimator(String animatorName)
    {
        System.out.print("hii :"+animatorName);
        StringBuilder drawableClassName=new StringBuilder();
        if (!animatorName.contains(".")){
            String defaultPackageName=getClass().getPackage().getName();
            drawableClassName.append(defaultPackageName)
                    .append(".animators")
                    .append(".");
        }
        drawableClassName.append(animatorName);
        System.out.print("hii :"+drawableClassName);
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
