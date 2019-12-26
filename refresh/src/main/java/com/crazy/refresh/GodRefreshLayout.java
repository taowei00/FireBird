package com.crazy.refresh;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

/**
 * created by ${tw}
 * on 2019/12/24
 */
public class GodRefreshLayout extends LinearLayout {

    private Context mContext;
    private BaseRefershManager mRefreshManager;
    private View mHeadView;
    private int mDownY;
    private int mMoveY;
    private int mMinMeasuredHeight;
    private int mMaxMeasuredHeight;
    private RefreshListener refreshListener;
    private int mInterceptDownX;
    private int mInterceptDownY;
    private RecyclerView recylerView;

    public GodRefreshLayout(Context context) {
        super(context);
        initView(context);
    }

    public GodRefreshLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public GodRefreshLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(Context context) {
        mContext = context;
    }

    public void setRefreshManager() {
        mRefreshManager = new DefaultRefreshManager(mContext);
        initHeadView();
    }

    public void setRefreshManager(BaseRefershManager refreshManager) {
        mRefreshManager = refreshManager;
        initHeadView();
    }

    public void initHeadView() {
        setOrientation(VERTICAL);
        mHeadView = mRefreshManager.getHeadView();
        mHeadView.measure(0, 0);
        int measuredHeight = mHeadView.getMeasuredHeight();
        mMinMeasuredHeight = -measuredHeight;
        mMaxMeasuredHeight = (int) (measuredHeight * 0.3f);
        LayoutParams layoutParams = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, measuredHeight);
        layoutParams.topMargin = mMinMeasuredHeight;
        addView(mHeadView, 0, layoutParams);
    }

    public interface RefreshListener {
        void refreshing();
    }

    public void refreshOver() {
        hideHeadView(getHeadViewLayoutParams());
        mCurrentRefreState = RefreState.REFRESHOVER;
        handlerRefreshState(mCurrentRefreState);
    }

    public void setRefreshListener(RefreshListener listener) {
        refreshListener = listener;
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        View childView = getChildAt(0);
        if (childView instanceof RecyclerView) {
            recylerView = (RecyclerView) childView;
        }
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mInterceptDownX = (int) ev.getX();
                mInterceptDownY = (int) ev.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                if (mDownY == 0) mDownY = mInterceptDownY;
                //  判断滑动方向  只处理竖向滑动
                int dX = (int) (ev.getX() - mInterceptDownX);
                int dY = (int) (ev.getY() - mInterceptDownY);
                if (Math.abs(dY) > Math.abs(dX) && dY > 0 && handlerChildViewIsTop()) {
                    return true;
                }
                break;
            case MotionEvent.ACTION_UP:

                break;
        }
        return super.onInterceptTouchEvent(ev);
    }

    private boolean handlerChildViewIsTop() {
        if (recylerView != null) {
            return RefreshScrollingUtil.isRecyclerViewToTop(recylerView);
        }
        // TODO: 2019/12/25   可对其它的view做处理

        return false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mDownY = (int) event.getY();
                Log.i("test", "onTouchEvent: " + mDownY);
                return true;
            case MotionEvent.ACTION_MOVE:
                mMoveY = (int) event.getY();
                int dY = mMoveY - mDownY;
                if (dY > 0) {
                    LayoutParams layoutParams = getHeadViewLayoutParams();
                    int topMargin = (int) Math.min(dY / 1.8f + mMinMeasuredHeight, mMaxMeasuredHeight);
                    layoutParams.topMargin = topMargin;
                    mHeadView.setLayoutParams(layoutParams);
                    //  美团下拉刷新用-----------start
                    if (topMargin <= 0) {
                        float percent = ((-mMinMeasuredHeight) - (-topMargin))*1.0f/-mMinMeasuredHeight;
                        mRefreshManager.refreshPercent(percent);
                    }
                    //----------------------end
                    if (topMargin < 0 && mCurrentRefreState != RefreState.DOWNREFRESH) {
                        //  下拉刷新
                        mCurrentRefreState = RefreState.DOWNREFRESH;
                        handlerRefreshState(mCurrentRefreState);
                    } else if (topMargin >= 0 && mCurrentRefreState != RefreState.RELEASEREFRESH) {
                        //  释放刷新
                        mCurrentRefreState = RefreState.RELEASEREFRESH;
                        handlerRefreshState(mCurrentRefreState);
                    }
                }
                return true;
            case MotionEvent.ACTION_UP:
                if (handlerUpEvent(event)) {
                    return true;
                }
                break;
        }
        return super.onTouchEvent(event);
    }

    private LayoutParams getHeadViewLayoutParams() {
        return (LayoutParams) mHeadView.getLayoutParams();
    }

    private boolean handlerUpEvent(MotionEvent event) {
        final LayoutParams layoutParams = getHeadViewLayoutParams();
        if (mCurrentRefreState == RefreState.DOWNREFRESH) {
            //   回到静止状态
            mCurrentRefreState = RefreState.IDDLE;
            handlerRefreshState(mCurrentRefreState);
            hideHeadView(layoutParams);
        } else if (mCurrentRefreState == RefreState.RELEASEREFRESH) {
            //  保持刷新状态
            layoutParams.topMargin = 0;
            mHeadView.setLayoutParams(layoutParams);
            mCurrentRefreState = RefreState.REFRESHING;
            handlerRefreshState(mCurrentRefreState);
            if (refreshListener != null) {
                refreshListener.refreshing();
            }
        }
        return layoutParams.topMargin > mMinMeasuredHeight;
    }

    private void hideHeadView(final LayoutParams layoutParams) {
        ValueAnimator animator = ValueAnimator.ofInt(layoutParams.topMargin, mMinMeasuredHeight);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int animatedValue = (int) animation.getAnimatedValue();
                layoutParams.topMargin = animatedValue;
                mHeadView.setLayoutParams(layoutParams);
            }
        });
        animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
//                    super.onAnimationEnd(animation);
                mCurrentRefreState = RefreState.IDDLE;
                handlerRefreshState(mCurrentRefreState);
            }
        });
        animator.setDuration(500);
        animator.start();
    }

    private void handlerRefreshState(RefreState currentRefreState) {
        switch (currentRefreState) {
            case IDDLE:
                mRefreshManager.iddleRefresh();
                break;
            case DOWNREFRESH:
                mRefreshManager.downRefresh();
                break;
            case RELEASEREFRESH:
                mRefreshManager.releaseRefresh();
                break;
            case REFRESHING:
                mRefreshManager.refreshing();
                break;
            case REFRESHOVER:
                mRefreshManager.refreshOver();
                break;
                default:
                    break;
        }
    }

    private RefreState mCurrentRefreState = RefreState.IDDLE;
    private enum RefreState {
        IDDLE, DOWNREFRESH, RELEASEREFRESH, REFRESHING, REFRESHOVER
    }
}
