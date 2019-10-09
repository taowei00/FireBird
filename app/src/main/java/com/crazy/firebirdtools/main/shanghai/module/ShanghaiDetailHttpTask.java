package com.crazy.firebirdtools.main.shanghai.module;

import com.crazy.http.LfHttpTask;

import java.util.HashMap;
import java.util.Map;

public class ShanghaiDetailHttpTask extends LfHttpTask {

    public Object getXiaoHuaList(String sort, String page, String pageSize) {
        Map<String, Object> params = new HashMap<>();
        params.put("sort", sort);
        params.put("page", page);
        params.put("pagesize", pageSize);
        params.put("time", System.currentTimeMillis()/1000);
        params.put("key", "待申请");
        return super.execute(ShangHaiDetailRequest.xiaoHuaRequest, params);

    }

}
