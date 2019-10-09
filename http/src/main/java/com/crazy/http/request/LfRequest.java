package com.crazy.http.request;

import com.crazy.http.annoation.RequestMethod;
import com.crazy.http.request.host.IHost;

public class LfRequest implements IRequest {

    protected IHost mHost;

    @RequestMethod
    protected int requestMethod;
}
