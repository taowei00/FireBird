package com.crazy.firebirdtools.main.shanghai.presenter;

import com.crazy.annotation.MvpEmptyViewFactory;
import com.crazy.firebirdtools.main.shanghai.dto.ShangHaiDetailBean;
import com.crazy.mvp.mvp.ILifeCircle;
import com.crazy.mvp.mvp.IMvpView;
import com.crazy.mvp.mvp.MvpControler;

public interface IShangHaiDetailContract {

    @MvpEmptyViewFactory
    interface Iview extends IMvpView {

        void showData(ShangHaiDetailBean data);
    }

    interface IPresenter extends ILifeCircle {

        void getNetData(int pageSize);
    }

    /*Iview emptyView = new Iview() {

        @Override
        public void showData(ShangHaiDetailBean data) {

        }

        @Override
        public MvpControler getMvpControler() {
            return null;
        }
    };*/
}
