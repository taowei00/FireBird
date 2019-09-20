package com.crazy.firebirdtools;

import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import butterknife.BindView;
import butterknife.OnClick;

@ViewInject(mainLayoutid = R.layout.activity_main)
public class MainActivity extends BaseActivity {

    @BindView(R.id.fac_main_home)
    FloatingActionButton mFacMainHome;
    @BindView(R.id.rb_main_shanghai)
    RadioButton mRbMainShanghai;
    @BindView(R.id.rb_main_hangzhou)
    RadioButton mRbMainHangzhou;
    @BindView(R.id.rg_main_top)
    RadioGroup mRgMainTop;
    @BindView(R.id.fl_main_bottom)
    FrameLayout mFlMainBottom;
    @BindView(R.id.rb_main_beijing)
    RadioButton mRbMainBeijing;
    @BindView(R.id.rb_main_shenzheng)
    RadioButton mRbMainShenzheng;
    @BindView(R.id.rg_main_bottom)
    RadioGroup mRgMainBottom;

    private boolean isChangeTopToBottom = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        changeAnima(mRgMainBottom, mRgMainTop);
    }

    @OnClick(R.id.fac_main_home)
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.fac_main_home:
                isChangeTopToBottom = !isChangeTopToBottom;
                if (isChangeTopToBottom) {
                    changeAnima(mRgMainTop, mRgMainBottom);
                } else {
                    changeAnima(mRgMainBottom, mRgMainTop);
                }

                break;
        }
    }

    private void changeAnima(RadioGroup gone, RadioGroup show) {
        gone.clearAnimation();
        Animation animationGone = AnimationUtils.loadAnimation(this, R.anim.main_tab_translate_hide);
        gone.startAnimation(animationGone);
        gone.setVisibility(View.GONE);

        show.clearAnimation();
        Animation animationShow = AnimationUtils.loadAnimation(this, R.anim.main_tab_translate_show);
        show.startAnimation(animationShow);
        show.setVisibility(View.VISIBLE);
    }
}
