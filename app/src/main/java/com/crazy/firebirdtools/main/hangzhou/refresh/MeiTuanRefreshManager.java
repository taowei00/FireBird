package com.crazy.firebirdtools.main.hangzhou.refresh;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ImageView;

import com.crazy.firebirdtools.R;
import com.crazy.refresh.BaseRefershManager;

/**
 * created by ${tw}
 * on 2019/12/26
 */
public class MeiTuanRefreshManager extends BaseRefershManager {

    private ImageView mImageView;

    public MeiTuanRefreshManager(Context context) {
        super(context);
    }

    @Override
    public View getHeadView() {
        View view = mLayoutInflater.inflate(R.layout.meituan_header_refresh_layout, null, false);
        mImageView = view.findViewById(R.id.loading);
        return view;
    }

    @Override
    public void downRefresh() {

    }

    @Override
    public void releaseRefresh() {
        mImageView.setImageResource(R.drawable.mei_tuan_loading_pre);
        AnimationDrawable animationDrawable = (AnimationDrawable) mImageView.getDrawable();
        animationDrawable.start();
    }

    @Override
    public void iddleRefresh() {
        mImageView.clearAnimation();
        mImageView.setImageResource(R.mipmap.pull_image);
        mImageView.setScaleX(0);
        mImageView.setScaleY(0);
    }

    @Override
    public void refreshing() {
        mImageView.setImageResource(R.drawable.mei_tuan_loading);
        AnimationDrawable animationDrawable = (AnimationDrawable) mImageView.getDrawable();
        animationDrawable.start();
    }

    @Override
    public void refreshOver() {

    }

    @Override
    public void refreshPercent(float percent) {
        mImageView.setScaleX(percent);
        mImageView.setScaleY(percent);
    }
}
