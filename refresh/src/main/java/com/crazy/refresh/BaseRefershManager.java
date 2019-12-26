package com.crazy.refresh;

import android.content.Context;
import android.content.MutableContextWrapper;
import android.view.LayoutInflater;
import android.view.View;

/**
 * created by ${tw}
 * on 2019/12/24
 */
public abstract class BaseRefershManager {

    public LayoutInflater mLayoutInflater;

    public BaseRefershManager(Context context) {
        mLayoutInflater = LayoutInflater.from(context);
    }

    public abstract View getHeadView();

    public abstract void downRefresh();

    public abstract void releaseRefresh();

    public abstract void iddleRefresh();

    public abstract void refreshing();

    public abstract void refreshOver();

    public abstract void refreshPercent(float percent);
}
