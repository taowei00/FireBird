package com.crazy.firebirdtools.main.shanghai.module;

import com.crazy.http.LfHttpServer;
import com.crazy.http.result.IResult;

import java.util.HashMap;
import java.util.Map;

public class ShanghaiDetailHttpTask<T> extends LfHttpServer {

    public IResult<T> getXiaoHuaList(String sort, String page, String pageSize) {
        Map<String, Object> params = new HashMap<>();
        params.put("sort", sort);
        params.put("page", page);
        params.put("pagesize", pageSize);
        params.put("time", System.currentTimeMillis()/1000);
        params.put("key", "b4e5f33ecc11aacc499cf800f9078bc9");
        return super.execute(ShangHaiDetailRequest.xiaoHuaRequest, params);

    }

}
