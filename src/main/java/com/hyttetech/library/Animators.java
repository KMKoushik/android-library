package com.hyttetech.library;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * Created by koushik on 2/7/17.
 */

public abstract class Animators extends Drawable implements Animatable, Runnable {

    public abstract void setRadius(float radius);

    public abstract void setColor(int color);

    @Override
    public void start() {

    }

    @Override
    public void stop() {

    }

    @Override
    public boolean isRunning() {
        return false;
    }

    @Override
    public void draw(@NonNull Canvas canvas) {

    }

    @Override
    public void setAlpha(@IntRange(from = 0, to = 255) int alpha) {

    }

    @Override
    public void setColorFilter(@Nullable ColorFilter colorFilter) {

    }

    @Override
    public int getOpacity() {
        return 0;
    }

    @Override
    public void run() {

    }
}
