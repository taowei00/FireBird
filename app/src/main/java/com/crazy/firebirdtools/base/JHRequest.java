package com.crazy.firebirdtools.base;

import com.crazy.http.request.IRequest;
import com.crazy.http.annoation.RequestMethod;
import com.crazy.http.request.LfRequest;

public class JHRequest extends LfRequest {

    public static IRequest sendHttp(String path, @RequestMethod int requestMethod) {
        JHRequest jhRequest = new JHRequest();
        jhRequest.mHost = HostManager.jhHost;
        jhRequest.requestMethod = requestMethod;
        return jhRequest;
    }

}
