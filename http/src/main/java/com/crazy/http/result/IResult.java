package com.crazy.http.result;

/**
 * created by ${tw}
 * on 2019/10/23
 *
 * 所有IResponse解析后的结果
 */
public interface IResult<T> {

    boolean isSuccess();

    int getCode();

    T data();
}
