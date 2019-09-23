package com.crazy.firebirdtools.splash;

import com.crazy.firebirdtools.mvp.base.BaseMvpPresenter;

public class SplashTimerPresenter extends BaseMvpPresenter<ISplashActivityContract.Iview> implements ISplashActivityContract.IPresenter {

    private CustomCountDownTimer mTimer;

    public SplashTimerPresenter(ISplashActivityContract.Iview view) {
        super(view);
    }


//    public SplashTimerPresenter(SplashActivity splashActivity) {
//        super(this);
//        this.splashActivity = splashActivity;
//    }

    @Override
    public void initTimer() {
        mTimer = new CustomCountDownTimer(5, new CustomCountDownTimer.ICountDownHandler() {
            @Override
            public void onTicker(int time) {
                getView().setTvTimer(time + "秒");
            }

            @Override
            public void onFinish() {
                getView().setTvTimer("跳过");
            }
        });
        mTimer.start();
    }


    public void cancel() {
        mTimer.cancel();
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        cancel();
    }

    @Override
    protected ISplashActivityContract.Iview getEmptyView() {
        return ISplashActivityContract.emptyView;
    }
}
