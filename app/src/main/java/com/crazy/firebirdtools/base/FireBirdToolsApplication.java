package com.crazy.firebirdtools.base;

import android.app.Application;

import com.crazy.firebirdtools.base.crash.CrashProtectManager;

/**
 * created by ${tw}
 * on 2020/1/9
 */
public class FireBirdToolsApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        CrashProtectManager.getInstance(this).init();
    }
}
