package com.crazy.firebirdtools.main.shanghai.presenter;

import com.crazy.mvp.mvp.ILifeCircle;
import com.crazy.mvp.mvp.IMvpView;
import com.crazy.mvp.mvp.MvpControler;

public interface IShangHaiDetailContract {
    interface Iview extends IMvpView {

    }

    interface IPresenter extends ILifeCircle {

        void getNetData();
    }

    Iview emptyView = new Iview() {

        @Override
        public MvpControler getMvpControler() {
            return null;
        }
    };
}
