package com.crazy.firebirdtools.main;

import android.util.Log;

import androidx.fragment.app.Fragment;

import com.crazy.firebirdtools.R;
import com.crazy.firebirdtools.base.BasePresenter;
import com.crazy.firebirdtools.main.beijing.BeijingFragment;
import com.crazy.firebirdtools.main.hangzhou.HangzhouFragment;
import com.crazy.firebirdtools.main.shanghai.ShangHaiFragment;
import com.crazy.firebirdtools.main.shenzhen.ShenZhenFragment;
import com.crazy.firebirdtools.main.tools.MainConstantTool;
import com.crazy.mvp.mvp.base.BaseMvpPresenter;

public class MainActivityPresenter extends BasePresenter<IMainActivityContract.Iview> implements IMainActivityContract.IPresenter {

    public static final String TAG = MainActivityPresenter.class.getSimpleName();
    // 当前fragment的角标
    private int mCurrentFragmentIndex;
    private Fragment[] mFragments = new Fragment[4];
    private int mCurrentCheckedId;
    private int mCurrentTopIndex;
    private int mCurrentBottomIndex;

    public MainActivityPresenter(IMainActivityContract.Iview view) {
        super(view);
    }

//    @Override
//    protected IMainActivityContract.Iview getEmptyView() {
//        return IMainActivityContract.emptyView;
//    }

    @Override
    public void initHomeFragment() {
        mCurrentFragmentIndex = 0;
        replaceFragment(mCurrentFragmentIndex);
    }

    @Override
    public int getCurrentCheckedIndex() {
        return mCurrentFragmentIndex;
    }


    //  切换fragment
    @Override
    public void replaceFragment(int currentFragmentIndex) {
        Log.i(TAG, "replaceFragment: " + currentFragmentIndex+"---"+mFragments.length);
        for (int i = 0; i < mFragments.length; i++) {
            if (currentFragmentIndex != i) {
                if (mFragments[i] != null) {
                    Log.i(TAG, "replaceFragment: "+i);
                    hideFragment(mFragments[i]);
                }
            }
        }

        Fragment fragment = mFragments[currentFragmentIndex];
        if (fragment != null) {
            addAndShowFragment(fragment);
            setCurChecked(currentFragmentIndex);
        } else {
            newCurrentFragment(currentFragmentIndex);
            setCurChecked(currentFragmentIndex);
        }

    }

    @Override
    public int getCurrentTopIndex() {
        return mCurrentTopIndex;
    }

    @Override
    public int getCurrentBottomIndex() {
        return mCurrentBottomIndex;
    }

    // 记录当前角标
    private void setCurChecked(int currentFragmentIndex) {
        mCurrentFragmentIndex = currentFragmentIndex;
        switch (currentFragmentIndex) {
            case 0:
                mCurrentCheckedId = R.id.rb_main_shanghai;
                mCurrentTopIndex = MainConstantTool.SHANGHAI;
                break;
            case 1:
                mCurrentCheckedId = R.id.rb_main_hangzhou;
                mCurrentTopIndex = MainConstantTool.HANGZHOU;
                break;
            case 2:
                mCurrentCheckedId = R.id.rb_main_beijing;
                mCurrentBottomIndex = MainConstantTool.BEIJING;
                break;
            case 3:
                mCurrentCheckedId = R.id.rb_main_shenzheng;
                mCurrentBottomIndex = MainConstantTool.SHENZHEN;
                break;
        }
    }

    //  创建当前fragment
    private void newCurrentFragment(int currentFragmentIndex) {
        Fragment fragment = null;
        switch (currentFragmentIndex) {
            case 0:
                fragment = new ShangHaiFragment();
                break;
            case 1:
                fragment = new HangzhouFragment();
                break;
            case 2:
                fragment = new BeijingFragment();
                break;
            case 3:
                fragment = new ShenZhenFragment();
                break;
        }
        mFragments[currentFragmentIndex] = fragment;
        addAndShowFragment(fragment);
    }

    //  显示fragment
    private void addAndShowFragment(Fragment fragment) {
        if (fragment.isAdded()) {
            getView().showFragment(fragment);
        } else {
            getView().addFragment(fragment);
        }
    }

    // 隐藏fragment
    private void hideFragment(Fragment fragment) {
        if (fragment != null && fragment.isVisible()) {
            getView().hideFragment(fragment);
        }
    }
}
