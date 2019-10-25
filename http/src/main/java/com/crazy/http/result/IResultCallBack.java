package com.crazy.http.result;

/**
 * created by ${tw}
 * on 2019/10/25
 */
public interface IResultCallBack<T> {

    void onSuccess(IResult<T> t);

    void onFailed(IResult t);
}
