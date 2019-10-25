package com.crazy.firebirdtools.base;

import com.crazy.http.parse.DefaultResultParse;
import com.crazy.http.request.IRequest;
import com.crazy.http.annoation.RequestMethod;
import com.crazy.http.request.LfRequest;

import java.lang.reflect.Type;

public class JHRequest extends LfRequest {

    public static IRequest sendHttp(String path, @RequestMethod int requestMethod, Type type) {
        JHRequest jhRequest = new JHRequest();
        jhRequest.mHost = HostManager.jhHost;
        jhRequest.path = path;
        jhRequest.requestMethod = requestMethod;
        jhRequest.type = type;
        jhRequest.resultParse = DefaultResultParse.getInstance();
        return jhRequest;
    }

}
