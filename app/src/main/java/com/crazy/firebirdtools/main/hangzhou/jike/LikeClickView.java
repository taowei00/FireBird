package com.crazy.firebirdtools.main.hangzhou.jike;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

import com.crazy.firebirdtools.R;
import com.crazy.firebirdtools.main.tools.SystemUtil;

/**
 * created by ${tw}
 * 即刻的自定义点赞view
 * on 2019/12/19
 */
public class LikeClickView extends View {

    private Bitmap mLikeBitmap;
    private Bitmap mUnlikeBitmap;
    private Bitmap mShiingBitmap;
    private Paint mPaint;
    private int mLeft;
    private int mTop;
    private boolean mIsLike;
    private float scaleValue;
    private int centerX;
    private int centerY;

    public LikeClickView(Context context) {
        this(context, null, 0);
    }

    public LikeClickView(Context context, @Nullable AttributeSet attrs) {
        this(context, null, 0);
    }

    public LikeClickView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.like_click_view);
        mIsLike = typedArray.getBoolean(R.styleable.like_click_view_is_like, false);
        typedArray.recycle();
        init();
    }

    private void init() {
        Resources resources = getResources();
        mLikeBitmap = BitmapFactory.decodeResource(resources, R.mipmap.ic_message_like);
        mUnlikeBitmap = BitmapFactory.decodeResource(resources, R.mipmap.ic_message_unlike);
        mShiingBitmap = BitmapFactory.decodeResource(resources, R.mipmap.ic_message_like_shining);

        mPaint = new Paint();
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int measureWidth;
        int measureHeight;
        int maxWidth = mUnlikeBitmap.getWidth() + SystemUtil.dp2px(getContext(), 20);
        int maxHeight = mUnlikeBitmap.getHeight() + SystemUtil.dp2px(getContext(), 30);

        int mode = MeasureSpec.getMode(widthMeasureSpec);
        //  用户传入的宽高
        int sizeWidth = MeasureSpec.getSize(widthMeasureSpec);
        int sizeHeight = MeasureSpec.getSize(heightMeasureSpec);
        if (mode != MeasureSpec.EXACTLY) {
            //  没有指定测量模式  如果有背景属性  背景多大  就指定多大
            int suggestedMinimumWidth = getSuggestedMinimumWidth();
            int suggestedMinimumHeight = getSuggestedMinimumHeight();
            if (suggestedMinimumWidth == 0) {
                measureWidth = maxWidth;
            } else {
                measureWidth = Math.min(maxWidth, suggestedMinimumWidth);
            }
            if (suggestedMinimumHeight == 0) {
                measureHeight = maxHeight;
            } else {
                measureHeight = Math.min(maxHeight, suggestedMinimumHeight);
            }
        } else {
            measureWidth = Math.min(sizeWidth, maxWidth);
            measureHeight = Math.min(sizeHeight, maxHeight);
        }
        setMeasuredDimension(measureWidth, measureHeight);
        getPading(measureWidth, measureHeight);
    }

    private void getPading(int measureWidth, int measureHeight) {
        mLeft = (measureWidth - mLikeBitmap.getWidth())/2;
        mTop = (measureHeight - mLikeBitmap.getHeight())/2;
        centerX = measureWidth/2;
        centerY = measureHeight/2;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Bitmap handBitmap = mIsLike ? mLikeBitmap : mUnlikeBitmap;
        //  调用canvas的scale及其他效果的方法时  必须先调用sava   最后再调用restore (这两个方法必须成对出现)
        canvas.save();
        canvas.scale(scaleValue, scaleValue, centerX, centerY);
        canvas.drawBitmap(handBitmap, mLeft, mTop, mPaint);

        if (mIsLike) {
            canvas.drawBitmap(mShiingBitmap, mLeft+5, 8, mPaint);
        }
        canvas.restore();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                onClick();
                break;
            default: break;
        }
        return super.onTouchEvent(event);
    }

    private void onClick() {
        mIsLike = !mIsLike;
//        callObjectAnimator();
        callValueAnimator();

    }

    /**
     * 通过ValueAnimator来实现点赞的动画效果
     */
    private void callValueAnimator() {
        ValueAnimator valueAnimator = ValueAnimator.ofFloat(1.0f, 0.8f, 1.0f);
        valueAnimator.setDuration(250);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                scaleValue = (float) animation.getAnimatedValue();
                invalidate();
            }
        });
        valueAnimator.start();
    }

    /**
     * 通过ObjectAnimator来实现点赞的动画效果
     */
    private void callObjectAnimator() {
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(this, "handScale", 1.0f, 0.8f, 1.0f);
        objectAnimator.setDuration(250);
        objectAnimator.start();
    }

    /**
     * 使用ObjectAnimator  系统会自动调用该属性的set方法
     * @param value
     */
    public void setHandScale(float value) {
        scaleValue = value;
        invalidate();
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        mLikeBitmap.recycle();
        mUnlikeBitmap.recycle();
        mShiingBitmap.recycle();
    }
}
