package com.crazy.http.request;

import com.crazy.http.parse.IParse;
import com.crazy.http.request.host.IHost;

import java.lang.reflect.Type;
import java.util.Map;

public interface IRequest {

    void setParams(Map<String, Object> params);

    Map<String, Object> getParams();

    int getRequestMethod();

    IHost getHost();

    String getPath();

    IParse getParse();

    Type getType();

}
