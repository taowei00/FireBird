package com.crazy.firebirdtools.main.hangzhou.design;

import android.content.Context;
import android.text.Html;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.view.ViewCompat;

/**
 * created by ${tw}
 * on 2019/12/19
 */
public class BottomShowBehavior extends CoordinatorLayout.Behavior<TextView> {

    public BottomShowBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onStartNestedScroll(@NonNull CoordinatorLayout coordinatorLayout, @NonNull TextView child, @NonNull View directTargetChild, @NonNull View target, int axes, int type) {
        return axes == ViewCompat.SCROLL_AXIS_VERTICAL;
    }

    @Override
    public void onNestedScroll(@NonNull CoordinatorLayout coordinatorLayout, @NonNull TextView child, @NonNull View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed, int type) {
        super.onNestedScroll(coordinatorLayout, child, target, dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed, type);
        if (dyConsumed + dyUnconsumed > 0) {
            //  显示底部view
            if (child.getVisibility() == View.VISIBLE) {
                BottomAnim.hide(child);
            }
        } else {
            //　隐藏底部view
            if (child.getVisibility() != View.VISIBLE) {
                BottomAnim.show(child);
            }
        }
    }
}
