package com.crazy.http.request.call;

import com.crazy.http.request.IRequest;
import com.crazy.http.response.IResponse;
import com.crazy.http.result.IResult;

public interface ICall {

    IResponse execute();

    IRequest getRequest();
}
