package com.crazy.firebirdtools.splash;

import com.crazy.firebirdtools.mvp.ILifeCircle;
import com.crazy.firebirdtools.mvp.IMvpView;
import com.crazy.firebirdtools.mvp.MvpControler;

public interface ISplashActivityContract {

    interface Iview extends IMvpView {
        void setTvTimer(String timer);
    }

    interface IPresenter extends ILifeCircle {
        void initTimer();
    }

    Iview emptyView = new Iview() {
        @Override
        public void setTvTimer(String timer) {

        }

        @Override
        public MvpControler getMvpControler() {
            return null;
        }
    };

}
