package com.crazy.task;

import android.graphics.Interpolator;

import com.crazy.task.tools.ThreadUtil;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class AsyncTaskInstance<Result> extends FutureTask<Result> {

    private final ITaskBackground iTaskBackground;
    private final ITaskCallbank iTaskCallbank;

    public AsyncTaskInstance(final ITaskBackground<Result> iTaskBackground, ITaskCallbank<Result> iTaskCallbank) {
        super(new Callable() {
            @Override
            public Result call() throws Exception {
                return iTaskBackground.onBankground();
            }
        });
        this.iTaskBackground = iTaskBackground;
        this.iTaskCallbank = iTaskCallbank;
    }

    public static AsyncTaskInstance getInstance(ITaskBackground iTaskBackground, ITaskCallbank iTaskCallbank) {
        return new AsyncTaskInstance(iTaskBackground, iTaskCallbank);
    }

    @Override
    protected void done() {
//        super.done();
        if (iTaskCallbank != null) {
            onComplete();
        }
    }

    private void onComplete() {
        try {
            final Object object = get();
            if (object != null) {
                ThreadUtil.postMainThread(new Runnable() {
                    @Override
                    public void run() {
                        iTaskCallbank.onComplete(object);
                    }
                });

            }
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取FutureTask执行过程中的异常
     * @param t
     */
    @Override
    protected void setException(final Throwable t) {
        super.setException(t);
        if (iTaskCallbank != null) {
            ThreadUtil.postMainThread(new Runnable() {
                @Override
                public void run() {
                    iTaskCallbank.onException(t);
                }
            });
        }
    }
}
