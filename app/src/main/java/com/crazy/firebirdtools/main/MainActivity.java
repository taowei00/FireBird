package com.crazy.firebirdtools.main;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.fragment.app.Fragment;

import com.airbnb.lottie.LottieAnimationView;
import com.crazy.firebirdtools.R;
import com.crazy.firebirdtools.base.BaseActivity;
import com.crazy.firebirdtools.base.ViewInject;
import com.crazy.firebirdtools.main.tools.MainConstantTool;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

@ViewInject(mainLayoutid = R.layout.activity_main)
public class MainActivity extends BaseActivity implements IMainActivityContract.Iview {

    IMainActivityContract.IPresenter mPresenter = new MainActivityPresenter(this);
    @BindView(R.id.fac_main_home)
    FloatingActionButton mFacMainHome;
    @BindView(R.id.rb_main_shanghai)
    LottieAnimationView mRbMainShanghai;
    @BindView(R.id.rb_main_hangzhou)
    LottieAnimationView mRbMainHangzhou;
    @BindView(R.id.rg_main_top)
    LinearLayout mRgMainTop;
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
    public void afterBindView() {
        initHomeFragment();
        changeAnima(mRgMainBottom, mRgMainTop);

        mRbMainShanghai.playAnimation();
        mRbMainHangzhou.reverseAnimation();
    }

    /**
     * 初始化fragment
     */
    private void initHomeFragment() {
        mPresenter.initHomeFragment();
    }

    private void handlerBottomPosition() {
        if (mPresenter.getCurrentTopIndex() != MainConstantTool.HANGZHOU) {
            mPresenter.replaceFragment(MainConstantTool.SHANGHAI);
//            mRbMainShanghai.setChecked(true);
            mRbMainShanghai.playAnimation();
        } else {
            mPresenter.replaceFragment(MainConstantTool.HANGZHOU);
//            mRbMainHangzhou.setChecked(true);
            mRbMainHangzhou.playAnimation();
        }
    }

    private void handlerTopPosition() {
        if (mPresenter.getCurrentBottomIndex() != MainConstantTool.SHENZHEN) {
            mPresenter.replaceFragment(MainConstantTool.BEIJING);
            mRbMainBeijing.setChecked(true);
        } else {
            mPresenter.replaceFragment(MainConstantTool.SHENZHEN);
            mRbMainShenzheng.setChecked(true);
        }
    }

    private void changeAnima(ViewGroup gone, ViewGroup show) {
        gone.clearAnimation();
        Animation animationGone = AnimationUtils.loadAnimation(this, R.anim.main_tab_translate_hide);
        gone.startAnimation(animationGone);
        gone.setVisibility(View.GONE);

        show.clearAnimation();
        Animation animationShow = AnimationUtils.loadAnimation(this, R.anim.main_tab_translate_show);
        show.startAnimation(animationShow);
        show.setVisibility(View.VISIBLE);
    }

    @Override
    public void showFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction().show(fragment).commit();
    }

    @Override
    public void addFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction().add(R.id.fl_main_content, fragment).commit();
    }

    @Override
    public void hideFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction().hide(fragment).commit();
    }

    @OnClick({R.id.fac_main_home, R.id.rb_main_shanghai, R.id.rb_main_hangzhou, R.id.rb_main_beijing, R.id.rb_main_shenzheng})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rb_main_shanghai:
                mPresenter.replaceFragment(0);
                mRbMainShanghai.playAnimation();
                mRbMainHangzhou.reverseAnimation();
                break;
            case R.id.rb_main_hangzhou:
                mPresenter.replaceFragment(1);
                mRbMainHangzhou.playAnimation();
                mRbMainShanghai.reverseAnimation();
                break;
            case R.id.rb_main_beijing:
                mPresenter.replaceFragment(2);
                break;
            case R.id.rb_main_shenzheng:
                mPresenter.replaceFragment(3);
                break;
            case R.id.fac_main_home:
                isChangeTopToBottom = !isChangeTopToBottom;
                if (isChangeTopToBottom) {
                    changeAnima(mRgMainTop, mRgMainBottom);
                    handlerTopPosition();
                } else {
                    changeAnima(mRgMainBottom, mRgMainTop);
                    handlerBottomPosition();
                }
                break;
        }
    }
}
