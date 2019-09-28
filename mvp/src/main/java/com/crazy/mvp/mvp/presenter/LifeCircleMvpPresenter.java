package com.crazy.mvp.mvp.presenter;

import android.widget.ImageView;

import com.crazy.mvp.mvp.ILifeCircle;
import com.crazy.mvp.mvp.IMvpView;
import com.crazy.mvp.mvp.MvpControler;

import java.lang.ref.WeakReference;

/**
 * 保存和获取view层引用
 * @param <T>
 */
public abstract class LifeCircleMvpPresenter<T extends IMvpView> implements ILifeCircle {

    protected WeakReference<T> mWeakReference;

    protected LifeCircleMvpPresenter() {
        super();
    }

    protected LifeCircleMvpPresenter(IMvpView iMvpView) {
        super();
        attachView(iMvpView);
        MvpControler mvpControler = iMvpView.getMvpControler();
        mvpControler.savePresenter(this);
    }

    @Override
    public void attachView(IMvpView iMvpView) {
        if (mWeakReference == null) {
            mWeakReference = new WeakReference(iMvpView);
        } else {
            T view = (T) mWeakReference.get();
            if (view != iMvpView) {
                mWeakReference = new WeakReference(iMvpView);
            }
        }
    }

    @Override
    public void onDestroy() {
        mWeakReference = null;
    }

    protected T getView() {
        T view = mWeakReference != null ? mWeakReference.get() : null;
        if (view == null) {
            return getEmptyView();
        }
        return view;
    }

    protected abstract T getEmptyView();
}
