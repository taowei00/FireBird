package com.crazy.firebirdtools.splash;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.VideoView;

/**
 * 全屏的VideoView
 */
public class FullScreenVideoView extends VideoView {

    public FullScreenVideoView(Context context) {
        super(context);
    }

    public FullScreenVideoView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public FullScreenVideoView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        // widthMeasureSpec包含两部分  测量模式和测量大小
        // 父类的onMeasure方法中为了兼容性根据长宽比调整大小
        // 此处需要全屏显示  不考虑兼容性
        int width = getDefaultSize(0, widthMeasureSpec);
        int height = getDefaultSize(0, heightMeasureSpec);
        setMeasuredDimension(width, height);
//        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
}
