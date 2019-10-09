package com.crazy.http;

import com.crazy.http.request.IRequest;

import java.util.Map;

public class LfHttpTask {

    protected Object execute(IRequest iRequest, Map<String, Object> params) {
        return HttpHelper.execute(iRequest, params);
    }

}
