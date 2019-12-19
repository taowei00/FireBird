package com.crazy.firebirdtools.main.hangzhou.design;

import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.crazy.firebirdtools.R;

/**
 * created by ${tw}
 * on 2019/12/19
 */
public class BottomAnim {

    public static void show(View show) {
        show.clearAnimation();
        Animation animationShow = AnimationUtils.loadAnimation(show.getContext(), R.anim.main_tab_translate_show);
        show.startAnimation(animationShow);
        show.setVisibility(View.VISIBLE);
    }

    public static void hide(View gone) {
        gone.clearAnimation();
        Animation animationGone = AnimationUtils.loadAnimation(gone.getContext(), R.anim.main_tab_translate_hide);
        gone.startAnimation(animationGone);
        gone.setVisibility(View.INVISIBLE);
    }

}
