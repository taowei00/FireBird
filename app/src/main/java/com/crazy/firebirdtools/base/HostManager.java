package com.crazy.firebirdtools.base;

import com.crazy.http.request.host.IHost;

public interface HostManager {

    IHost jhHost = new IHost() {
        @Override
        public String getHost() {
            return "http://v.juhe.cn";
        }

        @Override
        public String getDefaultPath() {
            return "/joke/content/list.php";
        }
    };
}
