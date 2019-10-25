package com.crazy.http.okhttp;

import com.crazy.http.request.IRequest;
import com.crazy.http.request.call.ICall;
import com.crazy.http.response.IResponse;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Response;

public class OkHttpCall implements ICall {
    private Call call;

    private IRequest mIRequest;

    public OkHttpCall(IRequest request, Call call) {
        this.call = call;
        this.mIRequest = request;
    }

    @Override
    public IResponse execute() {
        Response response = null;
        try {
            response = call.execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
        OkHttpResponse okHttpResponse = new OkHttpResponse(response);
        return okHttpResponse;
    }

    @Override
    public IRequest getRequest() {
        return mIRequest;
    }
}
