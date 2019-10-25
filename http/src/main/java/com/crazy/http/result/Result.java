package com.crazy.http.result;

/**
 * created by ${tw}
 * on 2019/10/25
 */
public class Result<T> implements IResult<T>{

    public static final int CODE_200 = 200;
    public static final int CODE_404 = 404;
    public static final int CODE_504 = 504;
    public static final int CODE_505 = 505;

    protected T data;

    protected int code;

    protected boolean success;

    public static IResult failed(int code) {
        Result result = new Result();
        result.code = code;
        result.success = false;
        return result;
    }

    public static IResult success(Object object) {
        Result result = new Result();
        result.code = CODE_200;
        result.success = true;
        result.data = object;
        return result;
    }

    @Override
    public boolean isSuccess() {
        return success;
    }

    @Override
    public int getCode() {
        return code;
    }

    @Override
    public T data() {
        return data;
    }
}
