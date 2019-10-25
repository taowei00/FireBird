package com.crazy.firebirdtools.base;

import com.crazy.http.result.IResult;
import com.crazy.http.result.IResultCallBack;
import com.crazy.http.result.Result;
import com.crazy.task.LfTask;

/**
 * created by ${tw}
 * on 2019/10/25
 */
public abstract class JHTask<T> extends LfTask<IResult<T>> implements IResultCallBack<T> {

    @Override
    public void onComplete(IResult<T> iResult) {
        if (iResult != null) {
            if (iResult.isSuccess()) {
                onSuccess(iResult);
            } else {
                onFailed(Result.failed(Result.CODE_505));
            }
        } else {
            onFailed(Result.failed(Result.CODE_404));
        }
    }

    @Override
    public void onException(Throwable throwable) {
        onFailed(Result.failed(Result.CODE_504));
    }

    @Override
    public void onFailed(IResult t) {
        switch (t.getCode()) {
            //  统一错误码的处理
            case Result.CODE_404:
                break;
            case Result.CODE_504:
                break;
            case Result.CODE_505:
                break;
        }
    }
}
