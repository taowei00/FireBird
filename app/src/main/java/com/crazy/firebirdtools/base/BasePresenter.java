package com.crazy.firebirdtools.base;

import com.crazy.mvp.mvp.IMvpView;
import com.crazy.mvp.mvp.base.BaseMvpPresenter;
import com.crazy.task.LfTask;
import com.crazy.task.TaskHelper;

public abstract class BasePresenter<T extends IMvpView> extends BaseMvpPresenter<T> {

    public BasePresenter(T view) {
        super(view);
    }

    protected void submitTask(LfTask task) {
        TaskHelper.submitTask(task, task);
    }
}
