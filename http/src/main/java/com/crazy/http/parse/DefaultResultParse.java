package com.crazy.http.parse;

import com.crazy.http.request.IRequest;
import com.crazy.http.response.IResponse;
import com.crazy.http.result.IResult;
import com.crazy.http.result.Result;
import com.google.gson.Gson;

import java.lang.reflect.Type;

/**
 * created by ${tw}
 * on 2019/10/24
 */
public class DefaultResultParse implements IParse{

    private static DefaultResultParse mInstance;
    private final Gson mGson;

    private DefaultResultParse() {
        mGson = new Gson();

    }

    public static IParse getInstance() {
        if (mInstance == null) {
            mInstance = new DefaultResultParse();
        }
        return mInstance;
    }

    @Override
    public IResult parseResponse(IRequest request, IResponse response) {
        Type type = request.getType();
        String bodyStr = response.getBodyStr();
        Object object = mGson.fromJson(bodyStr, type);
        return Result.success(object);
    }
}
