package com.crazy.http.parse;

import com.crazy.http.request.IRequest;
import com.crazy.http.response.IResponse;
import com.crazy.http.result.IResult;

/**
 * created by ${tw}
 * on 2019/10/24
 */
public interface IParse {

    IResult parseResponse(IRequest request, IResponse response);

}
