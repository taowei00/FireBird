package com.crazy.http.okhttp;

import com.crazy.http.response.IResponse;

import java.io.IOException;

import okhttp3.Response;

/**
 * created by ${tw}
 * on 2019/10/23
 */
public class OkHttpResponse implements IResponse {


    private final Response response;

    public OkHttpResponse(Response response) {

        this.response = response;

    }

    @Override
    public String getBodyStr() {
        try {
            return response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
