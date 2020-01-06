package com.crazy.firebirdtools.main;

import androidx.fragment.app.Fragment;

import com.crazy.annotation.MvpEmptyViewFactory;
import com.crazy.mvp.mvp.ILifeCircle;
import com.crazy.mvp.mvp.IMvpView;
import com.crazy.mvp.mvp.MvpControler;

public interface IMainActivityContract {

    @MvpEmptyViewFactory
    interface Iview extends IMvpView {
        void showFragment(Fragment fragment);

        void addFragment(Fragment fragment);

        void hideFragment(Fragment fragment);
    }

    interface IPresenter extends ILifeCircle {
        void initHomeFragment();

        int getCurrentCheckedIndex();

        void replaceFragment(int index);

        int getCurrentTopIndex();

        int getCurrentBottomIndex();
    }

//    Iview emptyView = new Iview() {
//
//
//        @Override
//        public void showFragment(Fragment fragment) {
//
//        }
//
//        @Override
//        public void addFragment(Fragment fragment) {
//
//        }
//
//        @Override
//        public void hideFragment(Fragment fragment) {
//
//        }
//
//        @Override
//        public MvpControler getMvpControler() {
//            return null;
//        }
//    };

}
