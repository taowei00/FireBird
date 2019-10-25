package com.crazy.http.request;

import com.crazy.http.parse.IParse;
import com.crazy.http.annoation.RequestMethod;
import com.crazy.http.request.host.IHost;

import java.lang.reflect.Type;
import java.util.Map;

public class LfRequest implements IRequest {

    protected String path;

    protected IHost mHost;

    protected Map<String, Object> params;

    protected Type type;

    protected IParse resultParse;

    @RequestMethod
    protected int requestMethod;

    @Override
    public void setParams(Map<String, Object> params) {
        this.params = params;
    }

    @Override
    public Map<String, Object> getParams() {
        return params;
    }

    @Override
    public int getRequestMethod() {
        return requestMethod;
    }

    @Override
    public IHost getHost() {
        return mHost;
    }

    @Override
    public String getPath() {
        return path;
    }

    @Override
    public IParse getParse() {
        return resultParse;
    }

    @Override
    public Type getType() {
        return type;
    }


}
