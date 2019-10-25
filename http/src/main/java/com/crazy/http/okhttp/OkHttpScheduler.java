package com.crazy.http.okhttp;

import com.crazy.http.HttpScheduler;
import com.crazy.http.annoation.RequestMethod;
import com.crazy.http.request.IRequest;
import com.crazy.http.request.call.ICall;

import java.util.Iterator;
import java.util.Map;

import okhttp3.Call;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;

public class OkHttpScheduler extends HttpScheduler {

    private OkHttpClient client;

    @Override
    public ICall newCall(IRequest request) {
        Map<String, Object> params = request.getParams();
        int requestMethod = request.getRequestMethod();
        Request.Builder requestBuilder = new Request.Builder();
        switch (requestMethod) {
            case RequestMethod.Get:
                //   拼接Get请求的url   host + path
                StringBuilder urlStrBuild = new StringBuilder(request.getHost().getHost());
                urlStrBuild.append(request.getPath());
                HttpUrl.Builder urlBuilder = HttpUrl.parse(urlStrBuild.toString()).newBuilder();
                if (params != null && params.size() > 0) {
                    Iterator<Map.Entry<String, Object>> iterator = params.entrySet().iterator();
                    while (iterator.hasNext()) {
                        Map.Entry<String, Object> next = iterator.next();
                        // TODO: 2019/10/11   对象如何转成字符串
                        urlBuilder.addQueryParameter(next.getKey(), String.valueOf(next.getValue()));
                    }
                }
                requestBuilder.get().url(urlBuilder.build());
                break;
            case RequestMethod.Post:

                break;

        }
        Request okHttpRequest = requestBuilder.build();
        Call call = getClient().newCall(okHttpRequest);
        OkHttpCall okHttpCall = new OkHttpCall(request, call);
        return okHttpCall;
    }

    private OkHttpClient getClient() {
        if (client == null) {
            client = new OkHttpClient();
        }
        return client;
    }
}
