package com.crazy.http;

import com.crazy.http.okhttp.OkHttpScheduler;
import com.crazy.http.request.IRequest;
import com.crazy.http.request.call.ICall;
import com.crazy.http.result.IResult;

import java.util.Map;

public class HttpHelper {

    private volatile static HttpScheduler sHttpScheduler;

    public static HttpScheduler getHttpScheduler() {
        if (sHttpScheduler == null) {
            synchronized (HttpHelper.class) {
                if (sHttpScheduler == null) {
                    sHttpScheduler = new OkHttpScheduler();
                }
            }
        }
        return sHttpScheduler;
    }


    // TODO: 2019/9/28  
    protected static <T> IResult<T> execute(IRequest request, Map<String, Object> params) {
        request.setParams(params);
        ICall call = getHttpScheduler().newCall(request);
        return getHttpScheduler().execute(call);
    }
}
