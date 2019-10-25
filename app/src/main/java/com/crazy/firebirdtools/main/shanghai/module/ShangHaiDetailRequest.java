package com.crazy.firebirdtools.main.shanghai.module;

import com.crazy.firebirdtools.base.JHRequest;
import com.crazy.firebirdtools.main.shanghai.dto.ShangHaiDetailBean;
import com.crazy.http.request.IRequest;
import com.crazy.http.annoation.RequestMethod;

public interface ShangHaiDetailRequest {

    IRequest xiaoHuaRequest = JHRequest.sendHttp("/joke/content/list.php", RequestMethod.Get, ShangHaiDetailBean.class);

}
