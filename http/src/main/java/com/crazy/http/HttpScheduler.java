package com.crazy.http;

import com.crazy.http.parse.IParse;
import com.crazy.http.request.IRequest;
import com.crazy.http.request.call.ICall;
import com.crazy.http.response.IResponse;
import com.crazy.http.result.IResult;

public abstract class HttpScheduler {

    public abstract ICall newCall(IRequest iRequest);

    public IResult execute(ICall call) {
        //  IResponse 和  IResult进行一个转换
        IResponse iResponse = call.execute();
        IRequest request = call.getRequest();
        IParse parse = request.getParse();
        return parse.parseResponse(request, iResponse);
    }
}
