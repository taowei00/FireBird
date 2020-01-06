package com.crazy.firebirdtools.base;

import com.crazy.mvp.mvp.IMvpView;
import com.crazy.mvp.mvp.base.BaseMvpPresenter;
import com.crazy.task.LfTask;
import com.crazy.task.TaskHelper;

import crazy.information.mvp.MvpEmptyViewFactory;

public class BasePresenter<T extends IMvpView> extends BaseMvpPresenter<T> {

    public BasePresenter(T view) {
        super(view);
    }

    protected void submitTask(LfTask task) {
        TaskHelper.submitTask(task, task);
    }

    @Override
    protected T getEmptyView() {
        T t = null;
        Class superClassGenricType = GenericsUtils.getSuperClassGenricType(this.getClass(), 0);
        try {
            t = (T) MvpEmptyViewFactory.create(superClassGenricType);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return t;

    }
}
