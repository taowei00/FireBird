package com.crazy.http;

import com.crazy.http.request.IRequest;
import com.crazy.http.result.IResult;

import java.util.Map;

public class LfHttpServer {

    protected <T> IResult<T> execute(IRequest iRequest, Map<String, Object> params) {
        return HttpHelper.execute(iRequest, params);
    }

}
