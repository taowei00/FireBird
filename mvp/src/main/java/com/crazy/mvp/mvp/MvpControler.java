package com.crazy.mvp.mvp;

import android.content.Intent;
import android.os.Bundle;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * 通过静态代理模式来同步presenter层和view层的生命周期
 */
public class MvpControler implements ILifeCircle{

    //  存放的是P层的实例
    private Set<ILifeCircle> mILifeCircles = new HashSet<>();

    public void savePresenter(ILifeCircle iLifeCircle) {
        mILifeCircles.add(iLifeCircle);
    }

    @Override
    public void onCreate(Bundle savedInstanceState, Intent intent, Bundle getArguments) {
        Iterator<ILifeCircle> iterator = mILifeCircles.iterator();
        while (iterator.hasNext()) {
            ILifeCircle next = iterator.next();
            if (intent == null) {
                intent = new Intent();
            }
            if (getArguments == null) {
                getArguments = new Bundle();
            }
            next.onCreate(savedInstanceState, intent, getArguments);
        }
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState, Intent intent, Bundle getArguments) {
        Iterator<ILifeCircle> iterator = mILifeCircles.iterator();
        while (iterator.hasNext()) {
            ILifeCircle next = iterator.next();
            if (intent == null) {
                intent = new Intent();
            }
            if (getArguments == null) {
                getArguments = new Bundle();
            }
            next.onActivityCreated(savedInstanceState, intent, getArguments);
        }
    }

    @Override
    public void onStart() {
        Iterator<ILifeCircle> iterator = mILifeCircles.iterator();
        while (iterator.hasNext()) {
            ILifeCircle next = iterator.next();
            next.onStart();
        }
    }

    @Override
    public void onResume() {
        Iterator<ILifeCircle> iterator = mILifeCircles.iterator();
        while (iterator.hasNext()) {
            ILifeCircle next = iterator.next();
            next.onResume();
        }
    }

    @Override
    public void onPause() {
        Iterator<ILifeCircle> iterator = mILifeCircles.iterator();
        while (iterator.hasNext()) {
            ILifeCircle next = iterator.next();
            next.onPause();
        }
    }

    @Override
    public void onStop() {
        Iterator<ILifeCircle> iterator = mILifeCircles.iterator();
        while (iterator.hasNext()) {
            ILifeCircle next = iterator.next();
            next.onStop();
        }
    }

    @Override
    public void onDestroy() {
        Iterator<ILifeCircle> iterator = mILifeCircles.iterator();
        while (iterator.hasNext()) {
            ILifeCircle next = iterator.next();
            next.onDestroy();
        }
    }

    @Override
    public void destroyView() {
        Iterator<ILifeCircle> iterator = mILifeCircles.iterator();
        while (iterator.hasNext()) {
            ILifeCircle next = iterator.next();
            next.destroyView();
        }
    }

    @Override
    public void onViewDestroy() {
        Iterator<ILifeCircle> iterator = mILifeCircles.iterator();
        while (iterator.hasNext()) {
            ILifeCircle next = iterator.next();
            next.onViewDestroy();
        }
    }

    @Override
    public void onNewIntent(Intent intent) {
        Iterator<ILifeCircle> iterator = mILifeCircles.iterator();
        while (iterator.hasNext()) {
            ILifeCircle next = iterator.next();
            next.onNewIntent(intent);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        Iterator<ILifeCircle> iterator = mILifeCircles.iterator();
        while (iterator.hasNext()) {
            ILifeCircle next = iterator.next();
            next.onActivityResult(requestCode, resultCode, data);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle bundle) {
        Iterator<ILifeCircle> iterator = mILifeCircles.iterator();
        while (iterator.hasNext()) {
            ILifeCircle next = iterator.next();
            next.onSaveInstanceState(bundle);
        }
    }

    @Override
    public void attachView(IMvpView iMvpView) {
        Iterator<ILifeCircle> iterator = mILifeCircles.iterator();
        while (iterator.hasNext()) {
            ILifeCircle next = iterator.next();
            next.attachView(iMvpView);
        }
    }
}
